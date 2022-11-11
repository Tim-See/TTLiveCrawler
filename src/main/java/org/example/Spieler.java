package org.example;

import java.util.List;

public class Spieler {
    private List<Historieneintrag> lpzWerte;
    private String name;

    public List<Historieneintrag> getLpzWerte() {
        return lpzWerte;
    }

    public String getName() {
        return name;
    }

    public Spieler(List<Historieneintrag> lpzWerte, String name) {
        this.lpzWerte = lpzWerte;
        this.name = name;
    }
}
