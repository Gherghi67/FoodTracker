package com.example.foodtracker;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthentificationManager {
    private static final String TAG = "AuthentificationManager";


    private FirebaseAuth firebaseAuth;


    private static AuthentificationManager singleInstance = null;

    private AuthentificationManager() {
        firebaseAuth = FirebaseAuth.getInstance();
    }




    private void addUserToDatabase(String id, String email, String displayName, String favoriteFood) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        Map<String, String> user = new HashMap<>();
        user.put("email", email);
        user.put("displayName", displayName);
        user.put("favoriteFood", favoriteFood);

        database.collection("users").document(id).set(user);

        /*database.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Document snapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                }); */
    }


    public static AuthentificationManager getInstance() {
        if (singleInstance == null) {
            singleInstance = new AuthentificationManager();
        }
        return singleInstance;
    }



    public void registerNewUser(String email, String displayName, String password, String confirmPassword, String favoriteFood) {
        if (password.equals(confirmPassword)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail : success");
                    } else {
                        Log.w(TAG, "createUserWithEmail : failure", task.getException());
                    }
                }
            });
            addUserToDatabase(this.getCurrentUser().getUid(), email, displayName, favoriteFood);
        } else {
            Log.w(TAG, "password trebuie sa coincida cu confirmPassword");
        }
    }

    public void signInUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail : success");
                } else {
                    Log.w(TAG, "signInWithEmail : failure", task.getException());
                }
            }
        });
    }

    public void signOutUser() {
        firebaseAuth.signOut();
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
