package com.example.jaken;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

enum Signe {
    ciseaux(0),
    pierre(1),
    feuille(2);

    private final int value;
    ArrayList<Integer> weakness;

    Signe(final int newValue) {
        value = newValue;
    }

    public ArrayList<Integer> getWeakness() {
        weakness = new ArrayList<Integer>();

        switch (value) {
            case 0:
                weakness.addAll(Arrays.asList(Signe.pierre.getValue()));
                break;
            case 1:
                weakness.addAll(Arrays.asList(Signe.feuille.getValue()));
                break;
            case 2:
                weakness.addAll(Arrays.asList(Signe.ciseaux.getValue()));
                break;
        }
        return weakness;
    }

    public int getValue() { return value; }
}

public class Jaken {
    static private final int manches = 5;

    private int nManche = 0;
    private int level;
    ArrayList<Integer> levelChoose;
    private final boolean ia = true;
    private Context context;
    private int iaTurn;

    public void test() {
        System.out.println(Signe.ciseaux);
    }

    public Jaken(Context context, int level) {
        this.context = context;
        this.level = level;
        this.levelChoose = new ArrayList<Integer>();

        if (this.level == 1) {
            levelChoose.addAll(Arrays.asList(Signe.ciseaux.getValue(), Signe.feuille.getValue(), Signe.pierre.getValue()));
        } else if (this.level == 2) {

        } else {

        }
    }

    public int play(int choose) {
        int iaPlay;

        if (level == 1) {
            iaPlay = getIaChoose();
            if (iaPlay == choose) return 0;
            this.nManche++;

            if (Signe.ciseaux.getValue() == choose) {
                return Signe.ciseaux.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.feuille.getValue() == choose) {
                return Signe.feuille.getWeakness().contains(iaPlay) ? -1 : 1;
            } else if (Signe.pierre.getValue() == choose) {
                return Signe.pierre.getWeakness().contains(iaPlay) ? -1 : 1;
            }
        } else if (level == 2) {

        } else {

        }

        return 1;
    }

    public int getIaTurn() {
        return this.iaTurn;
    }

    private int getIaChoose() {
        Random r = new Random();
        return r.nextInt(levelChoose.size() + 1);
    }

    public int getManches() {
        return this.nManche;
    }
}
