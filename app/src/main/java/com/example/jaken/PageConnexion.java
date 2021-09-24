package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PageConnexion extends AppCompatActivity {
    Button btnValiderConnexion;
    EditText editTextEmail;
    EditText editTextPasswordConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_connexion);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnValiderConnexion = findViewById(R.id.btnValiderConnexion);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPasswordConnexion = findViewById(R.id.editTextPasswordConnexion);

        btnValiderConnexion.setOnClickListener(v -> {
            db.collection("Joueurs")
                    .whereEqualTo("email", editTextEmail.getText().toString())
                    .whereEqualTo("password", editTextPasswordConnexion.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().size() >= 1) {
                                    Intent intent = new Intent(PageConnexion.this, PageMenu.class);
                                    startActivity(intent);
                                }
                            } else {
                                System.err.println("Error getting documents: " + task.getException());
                            }
                        }
                    });
        });
    }
}