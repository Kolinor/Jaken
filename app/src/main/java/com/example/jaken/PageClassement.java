package com.example.jaken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PageClassement extends AppCompatActivity {
    TextView textViewClassement;
    TextView textViewClassement2;
    TextView textViewClassement3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_classement);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        textViewClassement = findViewById(R.id.textViewClassement);

        db.collection("Joueurs")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(15)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String text = "";
                            int i = 1;
                            for (DocumentSnapshot document : task.getResult()) {
                                text += i + " - " + document.get("prenom").toString() + " : " + document.get("score").toString() + "\n";
                                i += 1;
                            }
                            textViewClassement.setText(text);
                        } else {
                            Log.d("getDoc", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}