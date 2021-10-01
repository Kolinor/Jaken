package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button btnConnexion;
    Button btnInscription;
    Button btnDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConnexion = findViewById(R.id.btnConnexion);
        btnInscription = findViewById(R.id.btnInscription);
        btnDebug = findViewById(R.id.btnDebug);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnConnexion.setOnClickListener(v -> {
//            Bundle b = new Bundle();
//            b.putString("message", message);

            Intent intent = new Intent(MainActivity.this, PageConnexion.class);
//            intent.putExtras(b);
            startActivity(intent);

            System.out.println(getPackageName());
        });

        btnInscription.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PageInscription.class);
            startActivity(intent);
        });

        btnDebug.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PageModeSoloMulti.class));
        });
    }
}