package com.example.foodtracker;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {
    private static FirebaseUser currentUser;

    private static String favoriteFood;


    //method where we call the updateProfile() method of FirebaseUser class

    private static void updateProfile(UserProfileChangeRequest profileChangeRequest){
        currentUser.updateProfile(profileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d("da", "User profile updated!");
                        }
                    }
                });
    }

    public static void setCurrentUser(FirebaseUser user){
        currentUser = user;
    }


    public static boolean isNull(){
        return currentUser == null;
    }


    public static String getUid(){
        return currentUser.getUid();
    }


    public static String getDisplayName(){
        return currentUser.getDisplayName();
    }

    public static Uri getPhotoUrl(){
        return currentUser.getPhotoUrl();
    }

    public static String getFavoriteFood(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(
                "users/" + currentUser.getUid() + "/favoriteFood"
        );
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                favoriteFood = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return favoriteFood;
    }

    public static void setDisplayName(String displayName){
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName).build();
        updateProfile(profileChangeRequest);
    }

    public static void setPhotoUrl(String photoUrl){
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(photoUrl)).build();
        updateProfile(profileChangeRequest);
    }

    public static void setFavoriteFood(String food){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(currentUser.getUid()).child("favoriteFood").setValue(food);
    }

}
