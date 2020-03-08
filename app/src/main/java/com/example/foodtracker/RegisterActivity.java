package com.example.foodtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText confirm_password;
    private Button register_button;

    private FirebaseAuth firebaseAuth;


    private void InitializeUI(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        register_button = findViewById(R.id.register_button);
    }

    private void RegisterNewUser(){
        String email_text = email.getText().toString();
        String password_text = password.getText().toString();
        String confirm_password_text = confirm_password.getText().toString();
        if(TextUtils.isEmpty(email_text)){
            Toast.makeText(getApplicationContext(), "Please enter e-mail address...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password_text)) {
            Toast.makeText(getApplicationContext(), "Please enter password...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(confirm_password_text)){
            Toast.makeText(getApplicationContext(), "Please enter confirm password...", Toast.LENGTH_LONG).show();
            return;
        }
        if(password_text.equals(confirm_password_text)){
            firebaseAuth.createUserWithEmailAndPassword(email_text, password_text)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User.setCurrentUser(firebaseAuth.getCurrentUser());
                                Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Registration failed!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        InitializeUI();
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterNewUser();
            }
        });
    }
}
