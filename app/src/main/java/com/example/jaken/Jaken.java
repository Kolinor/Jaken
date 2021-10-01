package com.example.jaken;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Random;

public class Jaken {
    static private final int manches = 5;

    private int nManche = 0;
    private int level;
    ArrayList<Drawable> levelChoose;
    ArrayList<Drawable> weakness;
    private final boolean ia = true;
    private Context context;
    private int iaTurn;

    public Jaken(Context context, int level) {
        this.context = context;
        this.level = level;
        this.levelChoose = new ArrayList<Drawable>();
        this.weakness = new ArrayList<Drawable>();

        if (this.level == 1) {
            levelChoose.add(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ciseaux, null));
            levelChoose.add(ResourcesCompat.getDrawable(context.getResources(), R.drawable.feuille, null));
            levelChoose.add(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pierre, null));

        } else if (this.level == 2) {

        } else {

        }
    }

    public int play(int position) {
        int iaPlay;

        if (level == 1) {
            iaPlay = getIaChoose();
            if (iaPlay == position) return 0;
            this.nManche++;

            switch (position) {
                case 1:

                    break;
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
