package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SpielerErzeuger {
    public List<Spieler> getSpielerliste() {
        return spielerliste;
    }

    private final List<Spieler> spielerliste = new LinkedList<>();

    public void addPlayer(int id) throws IOException {
        Document doc = Jsoup
                .connect("https://steinburg.tischtennislive.de/" +
                        "?L1=Ergebnisse" +
                        "&L2=TTStaffeln" +
                        "&L2P=16427" +
                        "&L3=Spieler" +
                        "&L3P=" + id +
                        "&Page=EntwicklungTTR")
                .get();
        LivePZHistorie livePZHistorie = new LivePZHistorie(doc);
        this.spielerliste.add(new Spieler(livePZHistorie.getHistorieneintraege(),"Otto Lotto"));
    }

}
