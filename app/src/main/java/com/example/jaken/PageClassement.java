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
       // textViewClassement2 = findViewById(R.id.textViewClassement3);
        //textViewClassement3 = findViewById(R.id.textViewClassement4);

        db.collection("Scores")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(15)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String text = "";
                            String text1 = "";
                            String text2 = "";
                            int i = 1;
                            int y = 1;
                            int x = 1;
                            for (DocumentSnapshot document : task.getResult()) {
                                if (document.get("level").toString().equals("1")) {
                                    text += i + " - " + document.get("prenom").toString() + " : " + document.get("score").toString() + "\n";
                                    i += 1;
                                } else if (document.get("level").toString().equals("2")) {
                                    text1 += y + " - " + document.get("prenom").toString() + " : " + document.get("score").toString() + "\n";
                                    y += 1;
                                } else if (document.get("level").toString().equals("3")) {
                                    text2 += x + " - " + document.get("prenom").toString() + " : " + document.get("score").toString() + "\n";
                                    x += 1;
                                }

                            }
                            textViewClassement.setText(text);
                            textViewClassement2.setText(text1);
                            textViewClassement3.setText(text2);
                        } else {
                            Log.d("getDoc", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}