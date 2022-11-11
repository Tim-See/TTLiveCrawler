package org.example;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Spieler{
    private final List<Historieneintrag> lpzWerte;
    private final String name;

    public List<Historieneintrag> getLpzWerte() {
        return lpzWerte;
    }

    public String getName() {
        return name;
    }

    public Spieler(List<Historieneintrag> lpzWerte, String name) {
        Collections.sort(lpzWerte, (emp1, emp2) -> emp2.getDate().compareTo(emp1.getDate()));
        this.lpzWerte = lpzWerte;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Spieler)obj).getName());
    }
}
