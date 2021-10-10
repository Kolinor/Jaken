package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class PageMenu extends AppCompatActivity {
    Button btnNiveau1;
    Button btnNiveau2;
    Button btnNiveau3;
    Button btnClassement;
    TextView textViewScores9;
    TextView textViewScores10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_menu);

        btnNiveau1 = findViewById(R.id.btnNiveau1);
        btnNiveau2 = findViewById(R.id.btnNiveau2);
        btnNiveau3 = findViewById(R.id.btnNiveau3);
        btnClassement = findViewById(R.id.btnClassement);
        textViewScores9 = findViewById(R.id.textViewScores9);
        textViewScores10 = findViewById(R.id.textViewScores10);

        btnNiveau1.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, PagePremierNiveau.class);
            startActivity(intent);
        });

        btnNiveau2.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, PageDeuxiemeNiveau.class);
            startActivity(intent);
        });

        btnNiveau3.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, pageTroisiemeNiveau.class);
            startActivity(intent);
        });

        btnClassement.setOnClickListener(v-> {
            Intent intent = new Intent(PageMenu.this, PageClassement.class);
            startActivity(intent);
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Joueurs").document(FirebaseAuth.getInstance().getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    long score = (long)document.get("score");

                    String a = "Votre score : " + score;
                    textViewScores9.setText(a);
                }
            }
        });

        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("Joueurs")
                .orderBy("score", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String text = "";
                            int i = 0;
                            boolean start = true;
                            long lastScore = 0;
                            String uidDoc;

                            for (DocumentSnapshot document : task.getResult()) {
                                if (lastScore != (long)document.get("score")) i += 1;

                                uidDoc = document.getId();
                                if (userUid.equals(uidDoc)) {
                                    String rang = "Votre rang : " + i;
                                    textViewScores10.setText(rang);
                                    return;
                                }

                                lastScore = (long)document.get("score");
                            }
                        } else {
                            Log.d("getDoc", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}