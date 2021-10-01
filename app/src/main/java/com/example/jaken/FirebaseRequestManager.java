package com.example.jaken;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

class FirebaseRequestManager {
    private final FirebaseFirestore db;
    private final FirebaseAuth firebaseAuth;
    private String prenomUtilisateur;

    public FirebaseRequestManager() {
        this.db = FirebaseFirestore.getInstance();
        this.firebaseAuth = FirebaseAuth.getInstance();
        setPrenomUtilisateur();
    }

    public void setPrenomUtilisateur() {
        String userUid = firebaseAuth.getCurrentUser().getUid();

        DocumentReference docRef = db.collection("Joueurs").document(userUid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        prenomUtilisateur = document.get("prenom", String.class);
                    }
                }
            }
        });
    }

    public String getPrenomUtilisateur() {
        return this.prenomUtilisateur;
    }
}
