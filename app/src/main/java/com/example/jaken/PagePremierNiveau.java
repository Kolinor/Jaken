package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

public class PagePremierNiveau extends AppCompatActivity {
    ImageButton btnCiseaux;
    ImageButton btnFeuille;
    ImageButton btnPierre;
    TextView textViewNumeroManche;
    TextView textViewScores;
    ImageButton btnRegle;
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
        textViewScores = findViewById(R.id.textViewScores);
        btnRegle = findViewById(R.id.btnRegle);

        btnCiseaux.setBackgroundColor(getColor(R.color.white));
        btnPierre.setBackgroundColor(getColor(R.color.white));
        btnFeuille.setBackgroundColor(getColor(R.color.white));

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

        btnRegle.setOnClickListener(v-> {
            Intent intent = new Intent(PagePremierNiveau.this, PageRegles.class);
            Bundle b = new Bundle();
            b.putInt("level", 1);
            intent.putExtras(b);
            startActivity(intent);
        });
    }

    public void setNumeroManche() {
        int numManche = jaken.getManches();

        if (numManche > 5) {
            Bundle b = new Bundle();
            b.putInt("level", 1);
            b.putInt("score", jaken.getScore());
            b.putInt("scoreIa", jaken.getIaScore());
            b.putInt("victoire", jaken.victoire());
            Intent intent = new Intent(PagePremierNiveau.this, PageRejouer.class);
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

        System.out.println(jaken.getIaTurn());
        if (jaken.getIaTurn() == Signe.feuille.getValue()) {
            btnFeuille.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.pierre.getValue()) {
            btnPierre.setBackgroundColor(iaColor);
        } else if (jaken.getIaTurn() == Signe.ciseaux.getValue()) {
            btnCiseaux.setBackgroundColor(iaColor);
        }
        String text = jaken.getScore() + " / " + jaken.getIaScore();

        textViewScores.setText(text);

    }

    private void clearColors() {
        new CountDownTimer(1500, 1000) {
                public void onTick(long millisUntilFinished) {}

                public void onFinish() {
                    btnCiseaux.setBackgroundColor(getColor(R.color.white));
                    btnPierre.setBackgroundColor(getColor(R.color.white));
                    btnFeuille.setBackgroundColor(getColor(R.color.white));
                }
            }.start();
    }

    private void setTextView() {

    }
}