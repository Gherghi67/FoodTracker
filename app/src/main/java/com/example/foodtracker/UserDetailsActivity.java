package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserDetailsActivity extends AppCompatActivity {
    private EditText display_name;
    private EditText favorite_food;
    private Button next_button;
    private void InitializeUI(){
        display_name = findViewById(R.id.display_name);
        favorite_food = findViewById(R.id.favorite_food);
        next_button = findViewById(R.id.next_button);
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        InitializeUI();
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
