package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChoosePhotoActivity extends AppCompatActivity {
    private ImageView photo;
    private Button uploadButton;
    private Button finishRegistrationButton;

    private static final int PHOTO_CODE = 1;


   private void InitializeUI() {
       photo = findViewById(R.id.profile_photo);
       uploadButton = findViewById(R.id.upload);
       finishRegistrationButton = findViewById(R.id.finish);
   }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       if(requestCode == PHOTO_CODE && resultCode == RESULT_OK) {
           Uri uri = data.getData();
           if(User.isNull()) {
               Toast.makeText(getApplicationContext(), "Current user is null!", Toast.LENGTH_LONG).show();
           }
           else {
               assert uri != null;
               User.setPhotoUrl(uri.toString());
           }
       }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
        InitializeUI();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PHOTO_CODE);
            }
        });
    }
}
