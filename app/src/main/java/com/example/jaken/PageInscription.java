package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class PageInscription extends AppCompatActivity {
    Button btnValider;
    EditText editTextPrenom;
    EditText editTextNom;
//    DatePicker editTextDateNaissance;
    EditText editTextEmailAddress;
    EditText editTextPassword;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inscription);

        db = FirebaseFirestore.getInstance();

        btnValider = findViewById(R.id.btnValider);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextNom = findViewById(R.id.editTextNom);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
//        editTextDateNaissance = findViewById(R.id.editTextDateNaissance);

        btnValider.setOnClickListener(v -> {
            Map<String, Object> joueur = new HashMap<>();
            CharSequence prenom = editTextPrenom.getText();
            CharSequence nom = editTextNom.getText();
            CharSequence email = editTextEmailAddress.getText();
            CharSequence password = editTextPassword.getText();
//            Date dateNaissance =


            joueur.put("email", email.toString());
            joueur.put("password", password.toString());

            db.collection("Joueurs")
                    .whereEqualTo("email", email.toString())
                    .whereEqualTo("password", email.toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().size() > 1) {

                                }
                            } else {
                                System.err.println("Error getting documents: " + task.getException());
                            }
                        }
                    });

            db.collection("Joueurs")
                    .add(joueur)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            System.out.println("DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    });
        });
    }

    private boolean isIdentifiantExist(CharSequence email, CharSequence password) {
        final boolean[] exist = new boolean[1];
        db.collection("Joueurs")
                .whereEqualTo("email", email.toString())
                .whereEqualTo("password", email.toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                           exist[0] = task.getResult().size() <= 1;
                        } else {
                            System.err.println("Error getting documents: " + task.getException());
                        }
                    }
                });
        return exist[0];
    }
}