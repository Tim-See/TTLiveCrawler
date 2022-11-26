package de.see;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WriteCSV {
    public void writeDiff(List<Pair<Spieler,Integer>> pairList, String seitDat, String dateiname, String pfad) throws IOException {
        try (PrintWriter writer = new PrintWriter( createFileWithDir(pfad,dateiname+".csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name");
            sb.append(';');
            sb.append("Punkteunterschied seit "+seitDat+" (Stand "+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+")");
            writePair(pairList, dateiname, writer, sb);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeList(List<Pair<Spieler,Integer>> list, LocalDate standDat, String dateiname, String pfad) throws IOException {
        try (PrintWriter writer = new PrintWriter(createFileWithDir( pfad,dateiname+".csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name");
            sb.append(';');
            sb.append("Punkte (Stand "+ standDat.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+")");
            writePair(list, dateiname, writer, sb);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writePair(List<Pair<Spieler, Integer>> list, String dateiname, PrintWriter writer, StringBuilder sb) {
        sb.append(System.lineSeparator());
        for(Pair<Spieler,Integer> spieler : list){
            sb.append(spieler.getLeft().getName());
            sb.append(';');
            sb.append(spieler.getRight());
            sb.append(System.lineSeparator());
        }
        writer.write(sb.toString());
        System.out.println(dateiname + " done!");
    }

    private File createFileWithDir(String directory, String filename) throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) Files.createDirectories(dir.toPath());
        return new File(directory + File.separatorChar + filename);
    }
}
