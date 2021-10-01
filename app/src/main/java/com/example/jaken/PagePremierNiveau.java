package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

public class PagePremierNiveau extends AppCompatActivity {
    ImageButton btnCiseaux;
    ImageButton btnFeuille;
    ImageButton btnPierre;
    TextView textViewNumeroManche;
    TextView textViewTour;
    Jaken jaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_premier_niveau);
        jaken = new Jaken(getApplicationContext(), 1);

        btnCiseaux = findViewById(R.id.btnCiseaux);
        btnFeuille = findViewById(R.id.btnFeuille);
        btnPierre = findViewById(R.id.btnPierre);
        textViewNumeroManche = findViewById(R.id.textViewNumeroManche);
        textViewTour = findViewById(R.id.textViewTour);

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
    }

    public void setNumeroManche() {
        int numManche = jaken.getManches();
        textViewNumeroManche.setText(String.valueOf(numManche));

        if (numManche < 6) return;

        // cas de victoire
    }

    public void gestionTour(ImageButton btn, int value) {
        int iaColor;
        switch (value) {
            case -1:
                btn.setBackgroundColor(getColor(R.color.red));
                iaColor = getColor(R.color.green);
                break;
            case 0:
                btn.setBackgroundColor(getColor(R.color.black));
                iaColor = getColor(R.color.black);
                break;
            case 1:
                btn.setBackgroundColor(getColor(R.color.green));
                iaColor = getColor(R.color.red);
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
        }
    }

    private void clearColors() {
        new CountDownTimer(2000, 1000) {
                public void onTick(long millisUntilFinished) {}

                public void onFinish() {
                    btnCiseaux.setBackgroundColor(getColor(R.color.white));
                    btnPierre.setBackgroundColor(getColor(R.color.white));
                    btnFeuille.setBackgroundColor(getColor(R.color.white));
                }
            }.start();
    }
}