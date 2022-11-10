package org.example;


import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LivePZHistorie {
    private Document doc;

    public LivePZHistorie(Document doc) {
        this.doc = doc;
    }

    public  LocalDate getDatebyMatchId(int id){
        Elements values;
    try {
        values = this.doc.getElementById("Match"+id+"1").getElementsByTag("td");
    }
    catch(IndexOutOfBoundsException e){
        values = this.doc.getElementById("Match0"+id+"_0").getElementsByTag("td");

    }
        String output = values.get(1).toString();
        return LocalDate.parse(output.substring(4,output.length()-5), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
