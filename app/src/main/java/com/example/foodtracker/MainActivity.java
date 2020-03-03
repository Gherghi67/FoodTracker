package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    // method to start the activities
    private void StartActivity(Class cls){
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sign_in_button = (Button) findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(SignInActivity.class);
            }
        });
        Button register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(RegisterActivity.class);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    }
}
