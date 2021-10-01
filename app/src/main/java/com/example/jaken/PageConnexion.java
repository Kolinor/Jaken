package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnValiderConnexion = findViewById(R.id.btnValiderConnexion);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPasswordConnexion = findViewById(R.id.editTextPasswordConnexion);


        btnValiderConnexion.setOnClickListener(v -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            if (firebaseUser != null) {
                logged();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(), editTextPasswordConnexion.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                System.out.println("User bien connecte");
                                logged();
                            } else {
                                System.err.println(task.getException().toString());
                            }
                        }
                    });
        });
    }

    private void logged() {
        startActivity(new Intent(PageConnexion.this, PageModeSoloMulti.class));
        finish();
    }
}