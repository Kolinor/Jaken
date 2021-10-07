package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PageMenu extends AppCompatActivity {
    Button btnNiveau1;
    Button btnNiveau2;
    Button btnNiveau3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_menu);

        btnNiveau1 = findViewById(R.id.btnNiveau1);
        btnNiveau2 = findViewById(R.id.btnNiveau2);
        btnNiveau3 = findViewById(R.id.btnNiveau3);

        btnNiveau1.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, PagePremierNiveau.class);
            startActivity(intent);
        });

        btnNiveau2.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, PageDeuxiemeNiveau.class);
            startActivity(intent);
        });

        btnNiveau3.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, pageTroisiemeNiveau.class);
            startActivity(intent);
        });
    }
}