package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

public class PageDeuxiemeNiveau extends AppCompatActivity {
    ImageButton btnPierre;
    ImageButton btnCiseaux;
    ImageButton btnPuit;
    ImageButton btnFeuille;
    TextView textViewNumeroManche;
    TextView textViewScores;

    Jaken jaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_deuxieme_niveau);
        jaken = new Jaken(getApplicationContext(), 2);

        btnPierre = findViewById(R.id.btnPierre2);
        btnCiseaux = findViewById(R.id.btnCiseaux2);
        btnPuit = findViewById(R.id.btnPuit);
        btnFeuille = findViewById(R.id.btnFeuille2);
        textViewNumeroManche = findViewById(R.id.textViewNumeroManche2);
        textViewScores = findViewById(R.id.textViewScores4);

        btnCiseaux.setBackgroundColor(getColor(R.color.white));
        btnPierre.setBackgroundColor(getColor(R.color.white));
        btnFeuille.setBackgroundColor(getColor(R.color.white));
        btnPuit.setBackgroundColor(getColor(R.color.white));

        btnCiseaux.setOnClickListener(v-> {
            gestionTour(btnCiseaux, jaken.play(Signe.ciseaux.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnFeuille.setOnClickListener(v-> {
            gestionTour(btnFeuille, jaken.play(Signe.feuille.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnPierre.setOnClickListener(v-> {
            gestionTour(btnPierre, jaken.play(Signe.pierre.getValue()));

            setNumeroManche();
            clearColors();
        });

        btnPuit.setOnClickListener(v-> {
            gestionTour(btnPuit, jaken.play(Signe.puit.getValue()));

            setNumeroManche();
            clearColors();
        });
    }

    public void setNumeroManche() {
        int numManche = jaken.getManches();

        if (numManche > 5) {
            Intent intent = new Intent(PageDeuxiemeNiveau.this, PageRejouer.class);
            startActivity(intent);
            finish();
            return;
        }
        textViewNumeroManche.setText(String.valueOf(numManche));
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
        } else if (jaken.getIaTurn() == Signe.puit.getValue()) {
            btnPuit.setBackgroundColor(iaColor);
        }

        String text = jaken.getScore() + " / " + jaken.getIaScore();

        textViewScores.setText(text);
    }

    private void clearColors() {
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                btnCiseaux.setBackgroundColor(getColor(R.color.white));
                btnPierre.setBackgroundColor(getColor(R.color.white));
                btnFeuille.setBackgroundColor(getColor(R.color.white));
                btnPuit.setBackgroundColor(getColor(R.color.white));
            }
        }.start();
    }
}