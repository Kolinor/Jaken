package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    EditText editTextEmailAddress;
    EditText editTextPassword;
    EditText editTextDateNaissance;
    FirebaseFirestore db;
    RadioGroup radioGrpSexe;

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
        radioGrpSexe = findViewById(R.id.radioGrpSexe);
        editTextDateNaissance = findViewById(R.id.editTextDateNaissance);

        btnValider.setOnClickListener(v -> {
            Map<String, Object> joueur = new HashMap<>();
            CharSequence prenom = editTextPrenom.getText();
            CharSequence nom = editTextNom.getText();
            CharSequence email = editTextEmailAddress.getText();
            CharSequence password = editTextPassword.getText();
            CharSequence dateNaissance = editTextDateNaissance.getText();
            CharSequence sexe;

            int radioButtonID = radioGrpSexe.getCheckedRadioButtonId();
            View radioButton = radioGrpSexe.findViewById(radioButtonID);
            int idx = radioGrpSexe.indexOfChild(radioButton);
            RadioButton r = (RadioButton) radioGrpSexe.getChildAt(idx);
            String selectedtext = r.getText().toString();


            if (selectedtext.equals("Femme")) {
                sexe = "F";
            } else {
                sexe = "H";
            }
            System.out.println(dateNaissance);
            auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener(PageInscription.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                System.err.println(task.getException().toString());
                                Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                System.out.println("User bien creer");
                                joueur.put("email", email.toString());
                                joueur.put("nom", nom.toString());
                                joueur.put("prenom", prenom.toString());
                                joueur.put("sexe", sexe);
                                joueur.put("dateNaissance", dateNaissance.toString());
                                joueur.put("score", 0);

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