package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText displayName;
    private EditText favoriteFood;
    private Button registerButton;

    private AuthentificationManager auth;


    private void initializeUI() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        displayName = findViewById(R.id.display_name);
        favoriteFood = findViewById(R.id.favorite_food);
        registerButton = findViewById(R.id.register_button);
    }


    private void registerNewUser() {
        auth = AuthentificationManager.getInstance();
        auth.registerNewUser(email.getText().toString(), displayName.getText().toString(), password.getText().toString(),
                confirmPassword.getText().toString(), favoriteFood.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeUI();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
