package org.example;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WriteCSV {
    public void writeDiff(List<Pair<Spieler,Integer>> pairList, String dateiname, String seitDat){
        try (PrintWriter writer = new PrintWriter("src/main/resources/"+dateiname+".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name");
            sb.append(';');
            sb.append("Punkteunterschied seit "+seitDat+" (Stand "+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+")");
            sb.append(System.lineSeparator());
            for(Pair<Spieler,Integer> pair : pairList){
                sb.append(pair.getLeft().getName());
                sb.append(';');
                sb.append(pair.getRight());
                sb.append(System.lineSeparator());
            }
            writer.write(sb.toString());
            System.out.println(dateiname + " done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeList(List<Spieler> List, String dateiname){
        try (PrintWriter writer = new PrintWriter("src/main/resources/"+dateiname+".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name");
            sb.append(';');
            sb.append("Punkte (Stand "+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+")");
            sb.append(System.lineSeparator());
            for(Spieler spieler : List){
                sb.append(spieler.getName());
                sb.append(';');
                sb.append(spieler.getLpzWerte().get(0).getPoints());
                sb.append(System.lineSeparator());
            }
            writer.write(sb.toString());
            System.out.println(dateiname + " done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
