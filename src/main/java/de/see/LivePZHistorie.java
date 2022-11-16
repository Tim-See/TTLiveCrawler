package de.see;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LivePZHistorie {
    private final Document doc;

    public LivePZHistorie(Document doc) {
        this.doc = doc;
    }

    public  List<Historieneintrag> getHistorieneintraege(){
        Element element;
        element = this.doc.getElementById("ImageMap");
        Elements areas = element.getElementsByTag("area");
        List<Historieneintrag> eintraege = new ArrayList<>();
        for(Element element1 : areas){
            String[] daten  = element1.attr("title").split(": ");
            eintraege.add(new Historieneintrag(LocalDate.parse(daten[0],DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    ,Integer.parseInt(daten[1])));
        }
        return eintraege;
    }
}
