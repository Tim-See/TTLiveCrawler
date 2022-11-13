package de.see;

import java.time.LocalDate;


public class Historieneintrag {
    private final LocalDate date;
    private final int points;

    public LocalDate getDate() {
        return date;
    }

    public int getPoints() {
        return points;
    }

    public Historieneintrag(LocalDate date, int points){
        this.date = date;
        this.points = points;
    }

}
