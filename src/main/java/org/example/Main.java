package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        try {
            // Dokument von URL laden
            SpielerErzeuger spielerErzeuger = new SpielerErzeuger();
            spielerErzeuger.addPlayer(108800);
            for(Historieneintrag eintr : spielerErzeuger.getSpielerliste().get(0).lpzWerte){
                System.out.println(eintr.getDate());
                System.out.println(eintr.getPoints());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}