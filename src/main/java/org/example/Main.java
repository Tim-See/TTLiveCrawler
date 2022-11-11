package org.example;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            SpielerCreator spielerCreator = new SpielerCreator();
            Stats stats = new Stats(spielerCreator.getAlleSpieler());
            System.out.println("Gesamtpunkte:");
            for(Spieler spieler : stats.sortedByPoints()){
                    System.out.println(spieler.getName());
                    System.out.println(spieler.getLpzWerte().get(0).getPoints());
            }
            System.out.println();
            System.out.println("Punkteunterschied 2022:");
            for(Pair<Spieler, Integer> spielerEin : stats.punkteUnterschiedSeit(LocalDate.of(2022,1,1))){
                System.out.println(spielerEin.getLeft().getName());
                System.out.println(spielerEin.getRight());
            }
            System.out.println();
            WriteCSV writeCSV = new WriteCSV();
            writeCSV.writeDiff(stats.punkteUnterschiedSeit(LocalDate.of(2022,8,25)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}