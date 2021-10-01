package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PageMenu extends AppCompatActivity {
    Button btnNiveau1;
    Button btnNiveau2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_menu);

        btnNiveau1 = findViewById(R.id.btnNiveau1);
        btnNiveau2 = findViewById(R.id.btnNiveau2);

        btnNiveau1.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, PagePremierNiveau.class);
            startActivity(intent);
        });

        btnNiveau2.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, PageDeuxiemeNiveau.class);
            startActivity(intent);
        });
    }
}