package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;

public class pageTroisiemeNiveau extends AppCompatActivity {
    ImageButton btnCiseaux;
    ImageButton btnFeuille;
    ImageButton btnPapier;
    ImageButton btnFeu;
    ImageButton btnAir;
    ImageButton btnEau;
    Jaken jaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_troisieme_niveau);
        jaken = new Jaken(getApplicationContext(), 3);

        btnCiseaux = findViewById(R.id.imageButton5);
        btnPapier = findViewById(R.id.imageButton6);
        btnFeuille = findViewById(R.id.imageButton7);
        btnFeuille = findViewById(R.id.imageButton9);
        btnFeu = findViewById(R.id.imageButton10);
        btnAir = findViewById(R.id.imageButton11);
        btnEau = findViewById(R.id.imageButton8);


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

//        if (jaken.getIaTurn() == Signe.feuille.getValue()) {
//            btnFeuille.setBackgroundColor(iaColor);
//        } else if (jaken.getIaTurn() == Signe.pierre.getValue()) {
//            btnPierre.setBackgroundColor(iaColor);
//        } else if (jaken.getIaTurn() == Signe.ciseaux.getValue()) {
//            btnCiseaux.setBackgroundColor(iaColor);
//        } else if (jaken.getIaTurn() == Signe.puit.getValue()) {
//            btnPuit.setBackgroundColor(iaColor);
//        }
//
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
                btnFeuille.setBackgroundColor(getColor(R.color.white));
                btnPapier.setBackgroundColor(getColor(R.color.white));
            }
        }.start();
    }
}