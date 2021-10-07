package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PageRejouer extends AppCompatActivity {
    Button btnChangerNiveau;
    Button btnRejouer;
    TextView textViewInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_rejouer);

        btnChangerNiveau = findViewById(R.id.btnChangerNiveau);
        btnRejouer = findViewById(R.id.btnRejouer);
        textViewInformation = findViewById(R.id.textViewInformation);

        int victoire = getIntent().getExtras().getInt("victoire");

        if (victoire == 1) {
            textViewInformation.setText(R.string.win);
        } else if (victoire == -1) {
            textViewInformation.setText(R.string.loose);
        } else {
            textViewInformation.setText(R.string.equal);
        }

        // envoyer score
        sendScore(getIntent());

        btnChangerNiveau.setOnClickListener(v-> {
            Intent intent = new Intent(PageRejouer.this, PageMenu.class);
            startActivity(intent);
            finish();
        });

        btnRejouer.setOnClickListener(v-> {
            int level = getIntent().getExtras().getInt("level");
            Intent intent;

            switch (level) {
                case 1:
                    intent = new Intent(PageRejouer.this, PagePremierNiveau.class);
                    break;
                case 2:
                    intent = new Intent(PageRejouer.this, PageDeuxiemeNiveau.class);
                    break;
                case 3:
                    intent = new Intent(PageRejouer.this, pageTroisiemeNiveau.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + level);
            }
            startActivity(intent);
            finish();
        });
    }

    private void sendScore(Intent intent) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> objScore = new HashMap<>();
        int score = intent.getExtras().getInt("score");
        int level = intent.getExtras().getInt("level");

        DocumentReference docRef = db.collection("Joueurs").document(userUid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String prenomUtilisateur = document.get("prenom", String.class);

                        objScore.put("uid", userUid);
                        objScore.put("prenom", prenomUtilisateur);
                        objScore.put("score", score);
                        objScore.put("level", level);

                        db.collection("Scores")
                                .document()
                                .set(objScore);
                    }
                }
            }
        });
    }
}