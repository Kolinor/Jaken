package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;

import java.util.HashMap;
import java.util.Map;

public class PageInscription extends AppCompatActivity {
    Button btnValider;
    EditText editTextPrenom;
    EditText editTextNom;
    DatePicker editTextDateNaissance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inscription);

        btnValider = findViewById(R.id.btnValider);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextNom = findViewById(R.id.editTextNom);
//        editTextDateNaissance = findViewById(R.id.editTextDateNaissance);

        btnValider.setOnClickListener(v -> {
            Map<String, Object> joueur = new HashMap<>();
            CharSequence prenom = editTextPrenom.getText();
            CharSequence nom = editTextNom.getText();
//            Date dateNaissance =


        });
    }
}