package de.seemann;

import java.util.List;

public record Spieler(List<Historieneintrag> lpzWerte, String name) {

    public Spieler {
        lpzWerte.sort((emp1, emp2) -> emp2.date().compareTo(emp1.date()));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Spieler) obj).name());
    }
}
