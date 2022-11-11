package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                spielerListe.addAll(getTeam(link));
            }
            catch(Exception ignored){
            }
        }
        return spielerListe;
    }

    private List<Spieler> getTeam(String link) throws IOException {
        //TODO: es wird nur der erste Spieler ausgegeben
        List<Spieler> teamListe = new ArrayList<>();
        Document doc = Jsoup
                .connect(link)
                .get();
        Element element = doc.getElementsByTag("table")
                .get(12)
                .getElementsByTag("tr")
                .get(3).getElementsByTag("td")
                .get(3).getElementsByTag("a").get(0);

        String name = element.text();
        String playerLink ="https://steinburg.tischtennislive.de/"+element.attr("href")
                .replace("Vorrunde","EntwicklungTTR");
        Document docu = Jsoup
                .connect(playerLink)
                .get();
        LivePZHistorie livePZHistorie = new LivePZHistorie(docu);
        teamListe.add(new Spieler(livePZHistorie.getHistorieneintraege(),name));
        return teamListe;
    }
}
