package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PageRejouer extends AppCompatActivity {
    Button btnChangerNiveau;
    Button btnRejouer;
    TextView textViewInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_rejouer);

        btnChangerNiveau = findViewById(R.id.btnChangerNiveau);
        btnRejouer = findViewById(R.id.btnRejouer);
        textViewInformation = findViewById(R.id.textViewInformation);

        boolean isVictory = getIntent().getExtras().getBoolean("isVictory");
        System.out.println(isVictory);
        if (isVictory) {
            textViewInformation.setText("Vous avez gagné");
        } else {
            textViewInformation.setText("Vous avez perdu");
        }

        btnChangerNiveau.setOnClickListener(v-> {
            Intent intent = new Intent(PageRejouer.this, PageMenu.class);
            startActivity(intent);
            finish();
        });

        btnRejouer.setOnClickListener(v-> {
            int level = getIntent().getExtras().getInt("level");
            Intent intent;

            switch (level) {
                case 1:
                    intent = new Intent(PageRejouer.this, PagePremierNiveau.class);
                    break;
                case 2:
                    intent = new Intent(PageRejouer.this, PageDeuxiemeNiveau.class);
                    break;
                case 3:
                    intent = new Intent(PageRejouer.this, pageTroisiemeNiveau.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + level);
            }
            startActivity(intent);
            finish();
        });
    }
}