package org.example;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("start crawl");

            SpielerCreator spielerCreator = new SpielerCreator();
            Stats stats = new Stats(spielerCreator.getAlleSpieler());
            System.out.println("crawl done!");

            WriteCSV writeCSV = new WriteCSV();

            writeCSV.writeList(stats.sortedByPoints(),"gesamt");

            LocalDate date = LocalDate.of(2022,1,1);
            writeCSV.writeDiff(stats.punkteUnterschiedSeit(date),"2022",date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            LocalDate date2 = LocalDate.of(2022,8,25);
            writeCSV.writeDiff(stats.punkteUnterschiedSeit(date2),"saison",date2.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}