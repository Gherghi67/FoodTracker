package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserDetailsActivity extends AppCompatActivity {
    private EditText display_name;
    private EditText favorite_food;
    private Button next_button;
    private void InitializeUI(){
        display_name = findViewById(R.id.display_name);
        favorite_food = findViewById(R.id.favorite_food);
        next_button = findViewById(R.id.next_button);
    }


    private void addDetailsToUser(String display_name, String favorite_food){
        if(User.isNull()){
            Toast.makeText(getApplicationContext(), "Current user is null!", Toast.LENGTH_LONG).show();
        }
        else{
            User.setDisplayName(display_name);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("users");
            Map<String, String> map = new HashMap<>();
            map.put(User.getUid(), favorite_food);
            reference.setValue(map);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        InitializeUI();
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetailsToUser(display_name.getText().toString(), favorite_food.getText().toString());
                Intent intent = new Intent(getApplicationContext(), ChoosePhotoActivity.class);
                startActivity(intent);
            }
        });
    }
}
