package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

public class pageTroisiemeNiveau extends AppCompatActivity {
    ImageButton btnCiseaux;
    ImageButton btnEponge;
    ImageButton btnPierre;
    ImageButton btnFeuille;
    ImageButton btnFeu;
    ImageButton btnAir;
    ImageButton btnEau;
    TextView textViewScores;
    TextView textViewNumeroManche;
    ImageButton btnRegle;
    Jaken jaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_troisieme_niveau);
        jaken = new Jaken(getApplicationContext(), 3);
        btnCiseaux = findViewById(R.id.imageButton6);
        btnPierre = findViewById(R.id.imageButton5);
        btnFeuille = findViewById(R.id.imageButton3);
        btnEponge = findViewById(R.id.imageButton4);
        btnFeu = findViewById(R.id.imageButton2);
        btnAir = findViewById(R.id.imageButton);
        btnEau = findViewById(R.id.imageButton7);
        textViewScores = findViewById(R.id.textViewScores6);
        textViewNumeroManche = findViewById(R.id.textViewNumeroManche3);
        btnRegle = findViewById(R.id.btnRegle2);

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

        btnRegle.setOnClickListener(v-> {
            Intent intent = new Intent(pageTroisiemeNiveau.this, PageRegles.class);
            Bundle b = new Bundle();
            b.putInt("level", 3);
            intent.putExtras(b);
            startActivity(intent);
        });
    }

    public void setNumeroManche() {
        int numManche = jaken.getManches();

        if (jaken.getScore() == 3 || jaken.getIaScore() == 3 || numManche > 5) {
            Bundle b = new Bundle();
            b.putInt("level", 3);
            b.putInt("score", jaken.getScore());
            b.putInt("scoreIa", jaken.getIaScore());
            b.putInt("victoire", jaken.victoire());
            Intent intent = new Intent(pageTroisiemeNiveau.this, PageRejouer.class);
            intent.putExtras(b);
            startActivity(intent);
            finish();
            return;
        }
        textViewNumeroManche.setText(String.valueOf(numManche));
    }

    public void gestionTour(ImageButton btn, int value) {
        int iaColor;
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

        String text = jaken.getScore() + " / " + jaken.getIaScore();

        textViewScores.setText(text);
    }

    private void clearColors() {
        btnAir.setClickable(false);
        btnFeu.setClickable(false);
        btnEau.setClickable(false);
        btnCiseaux.setClickable(false);
        btnEponge.setClickable(false);
        btnPierre.setClickable(false);
        btnFeuille.setClickable(false);

        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                btnAir.setBackgroundColor(getColor(R.color.white));
                btnFeu.setBackgroundColor(getColor(R.color.white));
                btnEau.setBackgroundColor(getColor(R.color.white));
                btnCiseaux.setBackgroundColor(getColor(R.color.white));
                btnEponge.setBackgroundColor(getColor(R.color.white));
                btnPierre.setBackgroundColor(getColor(R.color.white));
                btnFeuille.setBackgroundColor(getColor(R.color.white));

                btnAir.setClickable(true);
                btnFeu.setClickable(true);
                btnEau.setClickable(true);
                btnCiseaux.setClickable(true);
                btnEponge.setClickable(true);
                btnPierre.setClickable(true);
                btnFeuille.setClickable(true);
            }
        }.start();
    }
}