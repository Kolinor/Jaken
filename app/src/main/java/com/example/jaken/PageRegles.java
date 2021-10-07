package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.TextureView;
import android.widget.Button;
import android.widget.TextView;

public class PageRegles extends AppCompatActivity {
    Button btnBack;
    TextView titreRegle;
    TextView textViewRegle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regles);
        int level = getIntent().getExtras().getInt("level");

        btnBack = findViewById(R.id.btnBack);
        titreRegle = findViewById(R.id.titreRegle);
        textViewRegle = findViewById(R.id.textViewRegle);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        if (level == 1) {

            textViewRegle.setText("- Le jeux de pierre bat les ciseaux (en les écrasants\n"+
                    "- Le jeux de ciseaux bat la feuille (en la coupant)\n" + "- La feuille bat la pierre (en l'enveloppant)\n");


        } else if (level == 2) {

            textViewRegle.setText("- Le jeux de pierre bat les ciseaux (en les écrasants)\n"  +
                    "- Le jeux de ciseaux bat la feuille (en la coupant)\n" + "- La feuille bat la pierre (en l'enveloppant)\n" +
                    "- La feuille bat le puits (en le recouvrant)\n" +
                    "- Le puits bat le ciseau (qui tombe dans le puit)\n" +
                    "- Le puits bat la pierre (qui tombe dans le puit)\n");

        } else {

            textViewRegle.setText("La pierre éteint le feu, écrase les ciseaux\n" +
                    "Le feu fait fondre les ciseaux, brûle l'éponge et le papier\n" +
                    "Les ciseaux coupent l'éponge, le papier et son claquement résonne dans l'air\n" +
                    "L'éponge mouille le papier, contient des trous d'air et absorbe l'eau\n" +
                    "Le papier évente l'air, flotte sur l'eau et recouvre la pierre\n" +
                    "L'eau érode la pierre, éteint le feu et rouille les ciseaux\n");

        }
    }
}