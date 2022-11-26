package de.see;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EveryVerein {
    public static void main(String[] args) throws IOException {
        Set<Spieler> spielerList = new HashSet<>();
        long sZeit = System.currentTimeMillis();
        System.out.println("start crawl");
        for(int i = 0; i <2500;i++){
            String link = "https://steinburg.tischtennislive.de/?" +
                    "L1=Public&" +
                    "L2=Verein&" +
                    "L2P="+i+"&" +
                    "Page=Spielbetrieb&" +
                    "Sportart=96";
            try {
                spielerList.addAll((new SpielerCreator(link)).getAlleSpieler());

            } catch (Exception ignored) {
            }
        }
        Stats stats = new Stats(spielerList);
        System.out.println("crawl done! (" + (System.currentTimeMillis() - sZeit)/1000.0 +"s)");
        WriteCSV writeCSV = new WriteCSV();
        String path = "ergebnisse/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
        writeCSV.writeList(stats.sortedByPointsAt(LocalDate.now()),LocalDate.now(), "everything",path);

    }
}
