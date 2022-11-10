package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        try {
            // Dokument von URL laden
            Document doc = Jsoup
                    .connect("https://steinburg.tischtennislive.de/" +
                            "?L1=Ergebnisse" +
                            "&L2=TTStaffeln&L2P=16427" +
                            "&L3=Spieler" +
                            "&L3P=83950&Page=EntwicklungTTR")
                    .get();
            LivePZHistorie livePZHistorie = new LivePZHistorie(doc);
            System.out.println(livePZHistorie.getHistorieneintragbyMatchId(5).getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}