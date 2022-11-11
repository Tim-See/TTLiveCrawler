package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            SpielerCreator spielerCreator = new SpielerCreator();
            for(Spieler spieler : spielerCreator.getAlleSpieler()){
                System.out.println(spieler.name);
                for(Historieneintrag historieneintrag : spieler.lpzWerte){
                    System.out.println(historieneintrag.getDate());
                    System.out.println(historieneintrag.getPoints());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}