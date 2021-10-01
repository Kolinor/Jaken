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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inscription);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        btnValider = findViewById(R.id.btnValider);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextNom = findViewById(R.id.editTextNom);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnValider.setOnClickListener(v -> {
            Map<String, Object> joueur = new HashMap<>();
            CharSequence prenom = editTextPrenom.getText();
            CharSequence nom = editTextNom.getText();
            CharSequence email = editTextEmailAddress.getText();
            CharSequence password = editTextPassword.getText();

            auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener(PageInscription.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                System.err.println(task.getException().toString());
                            }
                            else {
                                System.out.println("User bien creer");
                                joueur.put("email", email.toString());
                                joueur.put("nom", nom.toString());
                                joueur.put("prenom", prenom.toString());

                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                db.collection("Joueurs")
                                        .document(firebaseUser.getUid())
                                        .set(joueur);

                                startActivity(new Intent(PageInscription.this, PageModeSoloMulti.class));
                                finish();
                            }
                        }
                    });
        });
    }
}