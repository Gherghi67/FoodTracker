package com.example.foodtracker;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CurrentUser {

    private User currentUser;

    private static CurrentUser singleInstance = null;

    private FirebaseFirestore database;

    private AuthentificationManager auth;

    private CurrentUser() {
        database = FirebaseFirestore.getInstance();

        auth = AuthentificationManager.getInstance();

        DocumentReference reference = database.collection("users").document(auth.getCurrentUser().getUid());
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(User.class);
            }
        });
    }

    public static CurrentUser getInstance() {
        if(singleInstance == null) {
            singleInstance = new CurrentUser();
        }
        return singleInstance;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
