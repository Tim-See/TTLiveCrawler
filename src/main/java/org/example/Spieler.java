package org.example;

import java.util.List;

public class Spieler {
    List<Historieneintrag> lpzWerte;
    String name;

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
