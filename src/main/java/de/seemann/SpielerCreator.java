package de.seemann;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class SpielerCreator {
    private final Document vereinDoc;
    public SpielerCreator() throws IOException {
        this.vereinDoc = Jsoup
                .connect("https://steinburg.tischtennislive.de/?" +
                        "L1=Public" +
                        "&L2=Verein" +
                        "&L2P=239" +
                        "&Page=Spielbetrieb" +
                        "&Sportart=96")
                .get();
    }
    public Set<Spieler> getAlleSpieler() throws IOException {
        Set<Spieler> spielerListe = new HashSet<>();
        Elements uebersicht = this.vereinDoc
                .getElementsByClass("CONTENTTABLETEXT")
                .get(0)
                .getElementsByTag("tbody")
                .get(0)
                .getElementsByTag("tr");
        List<String> linkList = new ArrayList<>();
        for(int i=1;i<uebersicht.size();i++){
                String link =
                        "https://steinburg.tischtennislive.de/" +
                        uebersicht.get(i)
                                .getElementsByTag("td")
                                .get(3)
                                .getElementsByTag("a")
                                .attr("href");
                linkList.add(link);
        }
        linkList.parallelStream().forEach(link -> {
            try {
                spielerListe.addAll(getTeamListe(link));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return spielerListe;
    }

    private Set<Spieler> getTeamListe(String link) throws IOException {
        Set<Spieler> teamListe = new HashSet<>();
        Document doc = Jsoup
                .connect(link)
                .get();
        (getSpielerLinks(doc)).parallelStream().forEach(linkz-> {
            try{
                Document docu = Jsoup
                        .connect(linkz.getRight())
                        .get();
                LivePZHistorie livePZHistorie = new LivePZHistorie(docu);
                teamListe.add(new Spieler(livePZHistorie.getHistorieneintraege(),linkz.getLeft()));
            }
            catch(NullPointerException ignored){} catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return teamListe;
    }

    private Set<Pair<String,String>> getSpielerLinks(Document doc){
        Set<Pair<String,String>> links = new HashSet<>();
        try {
            Elements eee = doc.select("td:contains(Einzel Bilanzen)")
                    .get(1).parent().parent()
                    .getElementsByTag("tr");

            for (int i = 3; i <= eee.size() - 2; i++) {
                Element element = eee
                        .get(i) //das durchlaufen
                        .getElementsByTag("tr")
                        .get(0)
                        .getElementsByTag("td")
                        .get(3);
                String name = element.text();
                String playerLink = "https://steinburg.tischtennislive.de/" + element
                        .getElementsByTag("a")
                        .attr("href")
                        .replace("Vorrunde", "EntwicklungTTR");
                links.add(new ImmutablePair<>(name, playerLink));
            }
        }
        catch(IndexOutOfBoundsException ignored){}
        return links;
    }
}
