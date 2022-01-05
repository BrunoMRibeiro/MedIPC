package com.example.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.Profile.Profile;
import com.example.myapplication.Profile.ProfileActivity;


public class MainActivity extends AppCompatActivity {
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = new Profile();
        if(getIntent().getSerializableExtra("profile") != null)
            profile = (Profile)getIntent().getSerializableExtra("profile");

        Button button = findViewById(R.id.btContinue);

        button.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
            myIntent.putExtra("profile", profile);
            MainActivity.this.startActivity(myIntent);
        });

    }


}

