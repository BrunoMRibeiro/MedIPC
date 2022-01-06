package com.example.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Alarms.AlarmActivity;
import com.example.myapplication.Alarms.AlarmProfile;
import com.example.myapplication.Profile.Profile;
import com.example.myapplication.Profile.ProfileActivity;


public class MainActivity extends AppCompatActivity {
    private Profile profile;
    private AlarmProfile alarmprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = new Profile();
        alarmprofile = new AlarmProfile();
        if(getIntent().getSerializableExtra("profile") != null)
            profile = (Profile)getIntent().getSerializableExtra("profile");
        alarmprofile = new AlarmProfile();
        if(getIntent().getSerializableExtra("alarm") != null)
            alarmprofile = (AlarmProfile) getIntent().getSerializableExtra("alarm");

        Button button = findViewById(R.id.btContinue);
        Button button2 = findViewById(R.id.btContinue2);

        button.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
            myIntent.putExtra("profile", profile);
            MainActivity.this.startActivity(myIntent);
        });
        button2.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, AlarmActivity.class);
            myIntent.putExtra("alarm", alarmprofile);
            MainActivity.this.startActivity(myIntent);
        });

    }


}

