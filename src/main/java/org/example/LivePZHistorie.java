package org.example;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LivePZHistorie {
    private Document doc;

    public LivePZHistorie(Document doc) {
        this.doc = doc;
    }

    public  Historieneintrag getHistorieneintragbyMatchId(int id){
        Element element;
        element = this.doc.getElementById("Match"+id);
        if(element == null)
            element = this.doc.getElementById("Match"+id+"_0");
        System.out.println(element.getElementsByTag("td"));
            return new Historieneintrag(getDate(element),1000);
    }
    private LocalDate getDate(Element element){
        String output = element.getElementsByTag("td").get(1).toString();
        return LocalDate.parse(output.substring(4,output.length()-5), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    private int getPoints(Element element){
        Elements tds = element.getElementsByTag("td");
        String points = tds.get(tds.size()-3).toString().substring(4,8);
        return Integer.parseInt(points);
    }






}
