package org.example;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class WriteCSV {
    public void writeDiff(List<Pair<Spieler,Integer>> pairList){
        try (PrintWriter writer = new PrintWriter("src/main/resources/diff.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name");
            sb.append(',');
            sb.append("Punkteunterschied");
            sb.append('\n');
            for(Pair<Spieler,Integer> pair : pairList){
                sb.append(pair.getLeft().getName());
                sb.append(',');
                sb.append(pair.getRight());
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
