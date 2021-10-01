package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class PagePremierNiveau extends AppCompatActivity {
    ImageButton btnCiseaux;
    Jaken jaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_premier_niveau);
        jaken = new Jaken(getApplicationContext(), 1);

        btnCiseaux = findViewById(R.id.btnCiseaux);

        btnCiseaux.setOnClickListener(v-> {
            switch (jaken.play(Signe.ciseaux.getValue())) {
                case -1:
                    break;
                case 0:
                    break;
                case 1:
                    break;
            }
        });
    }
}