package com.example.jaken;

import java.util.Random;

public class Jaken {
    static private final int manches = 5;

    private final int nManche = 0;
    private int level;
    private String[] levelChoose;
    private String[] firstLevel = {"Pierre", "Feuille", "Ciseaux"};
    private String[] secondLevel = {"Pierre", "Puit", "Papier", "Ciseaux"};
    private String[] thirdLevel = {"Pierre", "Feu", "Ciseaux", "Éponges", "Feuille", "Air", "Eau"};

    

    public Jaken(int level) {
        this.level = level;
        if (this.level == 1) {
            this.levelChoose = firstLevel;
        } else if (this.level == 2) {
            this.levelChoose = secondLevel;
        } else {
            this.levelChoose = thirdLevel;
        }
    }

    public String[] getSignes() {
        return this.levelChoose;
    }

    public void play(int position) {

    }

    private int getIaChoose() {
        Random r = new Random();
        return r.nextInt(levelChoose.length + 1);
    }
}
