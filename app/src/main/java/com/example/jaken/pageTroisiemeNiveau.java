package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;

public class pageTroisiemeNiveau extends AppCompatActivity {
    ImageButton btnCiseaux;
    ImageButton btnEponge;
    ImageButton btnPierre;
    ImageButton btnFeuille;
    ImageButton btnFeu;
    ImageButton btnAir;
    ImageButton btnEau;
    Jaken jaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_troisieme_niveau);
        jaken = new Jaken(getApplicationContext(), 3);

        btnCiseaux = findViewById(R.id.imageButton6);
        btnPierre = findViewById(R.id.imageButton5);
        btnFeuille = findViewById(R.id.imageButton3);
        btnEponge = findViewById(R.id.imageButton3);
        btnFeu = findViewById(R.id.imageButton2);
        btnAir = findViewById(R.id.imageButton);
        btnEau = findViewById(R.id.imageButton7);

        btnCiseaux.setOnClickListener(v-> {
            gestionTour(btnCiseaux, jaken.play(Signe.ciseaux.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnPierre.setOnClickListener(v-> {
            gestionTour(btnPierre, jaken.play(Signe.pierre.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnFeuille.setOnClickListener(v-> {
            gestionTour(btnFeuille, jaken.play(Signe.feuille.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnEponge.setOnClickListener(v-> {
            gestionTour(btnEponge, jaken.play(Signe.eponge.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnFeu.setOnClickListener(v-> {
            gestionTour(btnFeu, jaken.play(Signe.feu.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnAir.setOnClickListener(v-> {
            gestionTour(btnAir, jaken.play(Signe.air.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnEau.setOnClickListener(v-> {
            gestionTour(btnEau, jaken.play(Signe.eau.getValue()));

            setNumeroManche();
            clearColors();
        });
    }

    public void setNumeroManche() {
        int numManche = jaken.getManches();

        if (numManche > 5) {
            Intent intent = new Intent(pageTroisiemeNiveau.this, PageRejouer.class);
            startActivity(intent);
            finish();
            return;
        }
//        textViewNumeroManche.setText(String.valueOf(numManche));
    }

    public void gestionTour(ImageButton btn, int value) {
        int iaColor;
        System.out.println("test : " + value);
        switch (value) {
            case -1:
                btn.setBackgroundColor(getColor(R.color.red));
                iaColor = getColor(R.color.green);
                jaken.setScores(false);
                break;
            case 0:
                btn.setBackgroundColor(getColor(R.color.black));
                iaColor = getColor(R.color.black);
                break;
            case 1:
                btn.setBackgroundColor(getColor(R.color.green));
                iaColor = getColor(R.color.red);
                jaken.setScores(true);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }

        if (jaken.getIaTurn() == Signe.feuille.getValue()) {
            btnFeuille.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.pierre.getValue()) {
            btnPierre.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.ciseaux.getValue()) {
            btnCiseaux.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.feu.getValue()) {
            btnFeu.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.eau.getValue()) {
            btnEau.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.air.getValue()) {
            btnAir.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.eponge.getValue()) {
            btnEponge.setBackgroundColor(iaColor);
        }

//        String text = jaken.getScore() + " / " + jaken.getIaScore();
//
//        textViewScores.setText(text);
    }

    private void clearColors() {
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                btnAir.setBackgroundColor(getColor(R.color.white));
                btnFeu.setBackgroundColor(getColor(R.color.white));
                btnEau.setBackgroundColor(getColor(R.color.white));
                btnCiseaux.setBackgroundColor(getColor(R.color.white));
                btnEponge.setBackgroundColor(getColor(R.color.white));
                btnPierre.setBackgroundColor(getColor(R.color.white));
                btnEponge.setBackgroundColor(getColor(R.color.white));
            }
        }.start();
    }
}