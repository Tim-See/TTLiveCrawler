package org.example;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDate;
import java.util.*;

public class Stats {
    private final Set<Spieler> spielerList;

    public Stats(Set<Spieler> spielerList) {
        this.spielerList = spielerList;
    }

    public List<Spieler> sortedByPoints(){
        List<Spieler> out = new java.util.ArrayList<>(this.spielerList.stream().toList());
        out.sort(Comparator.comparingInt(s -> - s.getLpzWerte().get(0).getPoints()));
        return out;
    }

    public List<Pair<Spieler,Integer>> punkteUnterschiedSeit(LocalDate date){
        List<Pair<Spieler,Integer>> punkteListe = new ArrayList<>();
        for(Spieler spieler : this.spielerList){
            List<Historieneintrag> punkte = spieler.getLpzWerte();
            boolean written = false;
            for (Historieneintrag historieneintrag : punkte) {
                if (historieneintrag.getDate().isBefore(date) && !written) {
                    punkteListe.add(new ImmutablePair<>(spieler,
                            punkte.get(0).getPoints() - historieneintrag.getPoints()
                    ));
                    written =true;
                }
            }
            if(!written){
                punkteListe.add(new ImmutablePair<>(spieler,
                        punkte.get(0).getPoints() - punkte.get(punkte.size()-1).getPoints()
                ));
            }
        }
        punkteListe.sort(Comparator.comparingInt(p -> - p.getRight()));
        return punkteListe;
    }

}
