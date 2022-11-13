package de.see;

import java.util.List;

public class Spieler {

    private final String name;

    private final List<Historieneintrag> lpzWerte;

    public Spieler(List<Historieneintrag> lpzWerte, String name) {
        lpzWerte.sort((emp1, emp2) -> emp2.getDate().compareTo(emp1.getDate()));
        this.lpzWerte = lpzWerte;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Historieneintrag> getLpzWerte() {
        return lpzWerte;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Spieler) obj).getName());
    }
}
