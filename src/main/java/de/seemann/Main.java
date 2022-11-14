package de.seemann;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("start crawl");
            long sZeit = System.currentTimeMillis();
            SpielerCreator spielerCreator = new SpielerCreator();
            Stats stats = new Stats(spielerCreator.getAlleSpieler());
            System.out.println("crawl done! (" + (System.currentTimeMillis() - sZeit)/1000.0 +"s)");

            WriteCSV writeCSV = new WriteCSV();
            String path = "ergebnisse/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
            writeCSV.writeList(stats.sortedByPoints(),"gesamt",path);

            LocalDate date = LocalDate.of(2022,1,1);
            writeCSV.writeDiff(stats.punkteUnterschiedSeit(date),date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),"2022",path);

            LocalDate date2 = LocalDate.of(2022,8,25);
            writeCSV.writeDiff(stats.punkteUnterschiedSeit(date2),date2.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),"saison",path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}