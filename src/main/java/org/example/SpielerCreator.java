package org.example;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class SpielerCreator {
    private Document vereinDoc;
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
    public List<Spieler> getAlleSpieler(){
        List<Spieler> spielerListe = new ArrayList<>();
        Elements uebersicht = this.vereinDoc
                .getElementsByClass("CONTENTTABLETEXT")
                .get(0)
                .getElementsByTag("tbody")
                .get(0)
                .getElementsByTag("tr");
        for(int i=1;i<uebersicht.size();i++){
            try{
                String link =
                        "https://steinburg.tischtennislive.de/" +
                        uebersicht.get(i)
                                .getElementsByTag("td")
                                .get(3)
                                .getElementsByTag("a")
                                .attr("href");
                spielerListe.addAll(getTeamListe(link));
            }
            catch(Exception ignored){
            }
        }
        return spielerListe;
    }

    private List<Spieler> getTeamListe(String link) throws IOException {
        List<Spieler> teamListe = new ArrayList<>();
        Document doc = Jsoup
                .connect(link)
                .get();
        for(Pair<String,String> linkz : getSpielerLinks(doc)){
            Document docu = Jsoup
                    .connect(linkz.getRight())
                    .get();
            LivePZHistorie livePZHistorie = new LivePZHistorie(docu);
            teamListe.add(new Spieler(livePZHistorie.getHistorieneintraege(),linkz.getLeft()));
        }

        return teamListe;
    }

    private List<Pair<String,String>> getSpielerLinks(Document doc){
        List<Pair<String,String>> links = new LinkedList<>();
        Elements eee = doc.select("td:contains(Einzel Bilanzen)")
                .get(1).parent().parent()
                .getElementsByTag("tr");
        for(int i = 3;i<=eee.size()-2;i++){
            Element element = eee
            .get(i) //das durchlaufen
            .getElementsByTag("tr")
            .get(0)
            .getElementsByTag("td")
            .get(3);
            String name = element.text();
            String playerLink ="https://steinburg.tischtennislive.de/"+element
                    .getElementsByTag("a")
                    .attr("href")
                    .replace("Vorrunde","EntwicklungTTR");
            links.add(new ImmutablePair<>(name,playerLink));
        }
        return links;
    }
}
