package de.seemann;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WriteCSV {
    public void writeDiff(List<Pair<Spieler,Integer>> pairList, String seitDat, String dateiname, String pfad){
        try (PrintWriter writer = new PrintWriter( createFileWithDir(pfad,dateiname+".csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name");
            sb.append(';');
            sb.append("Punkteunterschied seit "+seitDat+" (Stand "+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+")");
            sb.append(System.lineSeparator());
            for(Pair<Spieler,Integer> pair : pairList){
                sb.append(pair.getLeft().name());
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
    public void writeList(List<Spieler> list, String dateiname, String pfad){
        try (PrintWriter writer = new PrintWriter(createFileWithDir( pfad,dateiname+".csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name");
            sb.append(';');
            sb.append("Punkte (Stand "+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+")");
            sb.append(System.lineSeparator());
            for(Spieler spieler : list){
                sb.append(spieler.name());
                sb.append(';');
                sb.append(spieler.lpzWerte().get(0).points());
                sb.append(System.lineSeparator());
            }
            writer.write(sb.toString());
            System.out.println(dateiname + " done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public File createFileWithDir(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return new File(directory + File.separatorChar + filename);
    }
}
