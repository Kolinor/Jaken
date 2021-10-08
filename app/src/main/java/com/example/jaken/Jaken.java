package com.example.jaken;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

enum Signe {
    ciseaux(0),
    pierre(1),
    feuille(2),
    puit(3),
    eau(4),
    air(5),
    eponge(6),
    feu(7);

    private final int value;
    ArrayList<Integer> weakness;

    Signe(final int newValue) {
        value = newValue;
    }

    public ArrayList<Integer> getWeakness() {
        weakness = new ArrayList<Integer>();

        switch (value) {
            case 0:
                weakness.addAll(Arrays.asList(Signe.pierre.getValue(),
                        Signe.puit.getValue(),
                        Signe.feu.getValue(),
                        Signe.eau.getValue()));
                break;
            case 1:
                weakness.addAll(Arrays.asList(Signe.feuille.getValue(),
                        Signe.puit.getValue(),
                        Signe.air.getValue(),
                        Signe.eau.getValue()));
                break;
            case 2:
                weakness.addAll(Arrays.asList(Signe.ciseaux.getValue(),
                        Signe.feu.getValue(),
                        Signe.eponge.getValue()));
                break;
            case 3:
                weakness.addAll(Arrays.asList(Signe.feuille.getValue()));
                break;
            case 4:
                weakness.addAll(Arrays.asList(Signe.air.getValue(),
                        Signe.feuille.getValue(),
                        Signe.eponge.getValue()));
                break;
            case 5:
                weakness.addAll(Arrays.asList(Signe.ciseaux.getValue(),
                        Signe.eponge.getValue(),
                        Signe.feuille.getValue()));
                break;
            case 6:
                weakness.addAll(Arrays.asList(Signe.ciseaux.getValue(),
                        Signe.feu.getValue(),
                        Signe.pierre.getValue()));
                break;
            case 7:
                weakness.addAll(Arrays.asList(Signe.pierre.getValue(),
                        Signe.eau.getValue(),
                        Signe.air.getValue()));
                break;
        }
        return weakness;
    }

    public int getValue() { return value; }
}

public class Jaken {
    static private final int manches = 5;

    private int nManche = 1;
    private int level;
    ArrayList<Integer> levelChoose;
    private final boolean ia = true;
    private Context context;
    private int iaTurn;
    private int score = 0;
    private int iaScore = 0;

    private int firstLevelVictory = 3;
    private int secondLevelVictory = 5;
    private int thridLevelVictory = 8;

    private int firstLevelLoss = -1;
    private int secondLevelLoss = -2;
    private int thridLevelLoss = -3;

    public Jaken(Context context, int level) {
        this.context = context;
        this.level = level;
        this.levelChoose = new ArrayList<Integer>();

        if (this.level == 1) {
            levelChoose.addAll(Arrays.asList(Signe.ciseaux.getValue(), Signe.feuille.getValue(), Signe.pierre.getValue()));
        } else if (this.level == 2) {
            levelChoose.addAll(Arrays.asList(Signe.ciseaux.getValue(), Signe.feuille.getValue(), Signe.pierre.getValue(), Signe.puit.getValue()));
        } else if (this.level == 3) {
            levelChoose.addAll(Arrays.asList(Signe.pierre.getValue(),
                    Signe.eau.getValue(),
                    Signe.air.getValue(),
                    Signe.feuille.getValue(),
                    Signe.eponge.getValue(),
                    Signe.ciseaux.getValue(),
                    Signe.feu.getValue()));
        }
    }

    public int play(int choose) {
        int iaPlay;
        iaPlay = getIaChoose();
        this.iaTurn = iaPlay;
        if (iaPlay == choose) return 0;
        this.nManche++;

        if (level == 1) {
            if (Signe.ciseaux.getValue() == choose) {
                return Signe.ciseaux.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.feuille.getValue() == choose) {
                return Signe.feuille.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.pierre.getValue() == choose) {
                return Signe.pierre.getWeakness().contains(iaPlay) ? -1 : 1;
            }
        } else if (level == 2) {
            if (Signe.ciseaux.getValue() == choose) {
                return Signe.ciseaux.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.feuille.getValue() == choose) {
                return Signe.feuille.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.pierre.getValue() == choose) {
                return Signe.pierre.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.puit.getValue() == choose) {
                return Signe.puit.getWeakness().contains(iaPlay) ? -1 : 1;
            }
        } else if (level == 3) {
            if (Signe.ciseaux.getValue() == choose) {
                return Signe.ciseaux.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.feuille.getValue() == choose) {
                return Signe.feuille.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.pierre.getValue() == choose) {
                return Signe.pierre.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.eau.getValue() == choose) {
                return Signe.eau.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.air.getValue() == choose) {
                return Signe.air.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.eponge.getValue() == choose) {
                return Signe.eponge.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.feu.getValue() == choose) {
                return Signe.feu.getWeakness().contains(iaPlay) ? -1 : 1;
            }
        }

        return 1;
    }

    public int getIaTurn() {
        return this.iaTurn;
    }

    private int getIaChoose() {
        Random r = new Random();
        int iaChoose = r.nextInt(levelChoose.size());
        if (this.level == 3 && iaChoose == 3) {
            do {
                iaChoose = r.nextInt(levelChoose.size());
            } while(iaChoose == 3);
        }
        return iaChoose;
    }

    public int getManches() {
        return this.nManche;
    }

    public int getScore() {
        return score;
    }

    public void setScores(boolean isVictory) {
        if (level == 1) {
            if (isVictory) {
                this.score += firstLevelVictory;
                this.iaScore = Math.max(this.iaScore + firstLevelLoss, 0);
            } else {
                this.score = Math.max(this.score + firstLevelLoss, 0);
                this.iaScore += firstLevelVictory;
            }
        } else if (level == 2) {
            if (isVictory) {
                this.score += secondLevelVictory;
                this.iaScore = Math.max(this.iaScore + secondLevelLoss, 0);
            } else {
                this.score = Math.max(this.score + secondLevelLoss, 0);
                this.iaScore += secondLevelVictory;
            }
        } else if (level == 3) {
            if (isVictory) {
                this.score += thridLevelVictory;
                this.iaScore = Math.max(this.iaScore + thridLevelLoss, 0);
            } else {
                this.score = Math.max(this.score + thridLevelLoss, 0);
                this.iaScore += thridLevelVictory;
            }
        }

//        System.out.println("IA SCORE : " + this.getIaScore());
//        System.out.println("SCORE : " + this.getScore());
    }

    public int getIaScore() {
        return iaScore;
    }

    public int victoire () {
        if (this.score > this.iaScore) {
            return 1;
        } else if (this.score < this.iaScore) {
            return -1;
        } else {
            return 0;
        }
    }
}
