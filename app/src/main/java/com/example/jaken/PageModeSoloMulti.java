package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PageModeSoloMulti extends AppCompatActivity {
    Button btnModeSolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_mode_solo_multi);

        btnModeSolo = findViewById(R.id.btnModeSolo);

        btnModeSolo.setOnClickListener(v -> {
            Intent intent = new Intent(PageModeSoloMulti.this, PageMenu.class);
            startActivity(intent);
        });
    }
}