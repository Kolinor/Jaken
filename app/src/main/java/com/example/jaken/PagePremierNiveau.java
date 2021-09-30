package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class PagePremierNiveau extends AppCompatActivity {
    ImageButton btnImage1;
    Jaken jaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_premier_niveau);
        jaken = new Jaken(getApplicationContext(), 1);

        btnImage1 = findViewById(R.id.btnImage1);

        btnImage1.setOnClickListener(v-> {
            jaken.play(1);
        });
    }
}