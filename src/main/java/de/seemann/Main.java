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
            System.out.println("crawl done! (" + (System.currentTimeMillis() - sZeit)/1000 +"s)");

            WriteCSV writeCSV = new WriteCSV();
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
            writeCSV.writeList(stats.sortedByPoints(),"gesamt_"+today);

            LocalDate date = LocalDate.of(2022,1,1);
            writeCSV.writeDiff(stats.punkteUnterschiedSeit(date),"2022_"+today,date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            LocalDate date2 = LocalDate.of(2022,8,25);
            writeCSV.writeDiff(stats.punkteUnterschiedSeit(date2),"saison_"+today,date2.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}