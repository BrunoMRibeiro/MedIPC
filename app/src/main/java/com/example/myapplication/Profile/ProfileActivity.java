package com.example.myapplication.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

// Classe responsável por mostrar a informação do utilizador
public class ProfileActivity extends AppCompatActivity {
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados do profile
        profile = new Profile();
        if(getIntent().getSerializableExtra(getString(R.string.profile)) != null)
            profile = (Profile)getIntent().getSerializableExtra(getString(R.string.profile));

        // Evento para a ação de editar o profile
        Button btEdit = findViewById(R.id.btEditP);
        btEdit.setOnClickListener(v -> {
            Intent myIntent = new Intent(ProfileActivity.this, ProfileActivityEdit.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            ProfileActivity.this.startActivity(myIntent);
        });

        // Evento para a ação de aceder ao booklet
        Button btBooklet = findViewById(R.id.btCVBookletP);
        btBooklet.setOnClickListener(v -> {
            Intent myIntent = new Intent(ProfileActivity.this, BookletActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            ProfileActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button btLogOff = findViewById(R.id.LogOutP);
        btLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        EditText etName = findViewById(R.id.vlNameP);
        if (!profile.getName().trim().isEmpty()){
            etName.setText(profile.getName());

        }
        EditText etAge = findViewById(R.id.vlAgeP);
        if (profile.getAge() > 0){
            etAge.setText(String.valueOf(profile.getAge()));
        }
        EditText etSex = findViewById(R.id.vlSexP);
        if (!profile.getSex().trim().isEmpty()){
            etSex.setText(profile.getSex());
        }
        EditText etHeight = findViewById(R.id.vlHeightP);
        if (profile.getHeight() > 0 && profile.getHeight() <= 250){
            etHeight.setText(String.valueOf(profile.getHeight()));
        }
        EditText etWeight = findViewById(R.id.vlWeightP);
        if (profile.getWeight() > 0 && profile.getWeight() <= 300){
            /*NumberFormat formatter = NumberFormat.getInstance(Locale.ENGLISH);
            formatter.setMaximumFractionDigits(2);
            formatter.setMinimumFractionDigits(2);
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            Float formatedFloat = new Float(formatter.format(profile.getWeight()));*/
            etWeight.setText(String.valueOf(profile.getWeight()));
        }
        EditText etBloodType = findViewById(R.id.vlBloodTypeP);
        if (!profile.getBloodType().trim().isEmpty()){
            etBloodType.setText(profile.getBloodType());
        }
        EditText etAlergies = findViewById(R.id.vlAlergiesP);
        if (!profile.getAlergies().trim().isEmpty()){
            etAlergies.setText(profile.getAlergies());
        }

        TableLayout tableLayout = findViewById(R.id.tableDataP);
        tableLayout.invalidate();
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(ProfileActivity.this, MainActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            ProfileActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
