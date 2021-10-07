package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        });

        btnInscription.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PageInscription.class);
            startActivity(intent);
        });

        btnDebug.setOnClickListener(v -> {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.signInWithEmailAndPassword("thomas@gmail.com", "thomas")
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                System.out.println("User bien connecte");
                                startActivity(new Intent(MainActivity.this, PageModeSoloMulti.class));
                            } else {
                                System.err.println(task.getException().toString());
                            }
                        }
                    });
        });
    }
}