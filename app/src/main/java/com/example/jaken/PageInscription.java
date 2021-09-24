package com.example.jaken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PageInscription extends AppCompatActivity {
    Button btnValider;
    EditText editTextPrenom;
    EditText editTextNom;
//    DatePicker editTextDateNaissance;
    EditText editTextEmailAddress;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inscription);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                    .add(joueur)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            System.out.println("DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    });
        });
    }
}