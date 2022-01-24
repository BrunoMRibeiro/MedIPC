package com.example.myapplication.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

// Classe responsável por editar a informação do utilizador
public class ProfileActivityEdit extends AppCompatActivity {
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados do profile
        profile = new Profile();
        profile = (Profile)getIntent().getSerializableExtra(getString(R.string.profile));

        // Evento para a ação de confirmar a edição do profile
        Button button = findViewById(R.id.btDonePE);
        button.setOnClickListener(v -> {
            if (profile.getAge() < 0 || profile.getAge() > 100){
                Toast.makeText(this, "Insira uma idade entre 1 e 100", Toast.LENGTH_SHORT).show();
                return;
            }
            if (profile.getWeight() < 0 || profile.getWeight() > 300){
                Toast.makeText(this, "Insira um peso entre 1kg e 300kg", Toast.LENGTH_SHORT).show();
                return;
            }
            if (profile.getHeight() < 0 || profile.getHeight() > 250){
                Toast.makeText(this, "Insira uma altura entre 1cm e 250cm", Toast.LENGTH_SHORT).show();
                return;
            }

            String filename = "profile.srl";
            ObjectOutput out = null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                out.writeObject(profile);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (profile.getAge() == 0)
                return;

            Intent myIntent = new Intent(ProfileActivityEdit.this, ProfileActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            ProfileActivityEdit.this.startActivity(myIntent);
            finish();
        });

        // Evento para o botão de desligar a aplicação
        Button bLogOff = findViewById(R.id.LogOutPE);
        bLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        // Evento para edição do nome
        EditText etName = findViewById(R.id.vlNamePE);
        if (!profile.getName().trim().isEmpty()){
            etName.setText(profile.getName());
        }
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    profile.setName(editable.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento para edição da idade
        EditText etAge = findViewById(R.id.vlAgePE);
        if (profile.getAge() > 0 && profile.getAge() <= 100){
            etAge.setText(String.valueOf(profile.getAge()));
        }
        etAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    profile.setAge(Integer.parseInt(editable.toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento para edição do sexo
        EditText etSex = findViewById(R.id.vlSexPE);
        if (!profile.getSex().trim().isEmpty()){
            etSex.setText(profile.getSex());
        }
        etSex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    profile.setSex(editable.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento para edição da altura
        EditText etHeight = findViewById(R.id.vlHeightPE);
        if (profile.getHeight() > 0 && profile.getHeight() <= 250){
            etHeight.setText(String.valueOf(profile.getHeight()));
        }
        etHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    profile.setHeight(Integer.parseInt(editable.toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento para edição do peso
        EditText etWeight = findViewById(R.id.vlWeightPE);
        if (profile.getWeight() > 0 && profile.getWeight() <= 300){
            NumberFormat formatter = NumberFormat.getInstance(Locale.ENGLISH);
            formatter.setMaximumFractionDigits(2);
            formatter.setMinimumFractionDigits(2);
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            Float formatedFloat = Float.valueOf(formatter.format(profile.getWeight()));
            etWeight.setText(String.valueOf(formatedFloat));
        }
        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    NumberFormat formatter = NumberFormat.getInstance(Locale.ENGLISH);
                    formatter.setMaximumFractionDigits(2);
                    formatter.setMinimumFractionDigits(2);
                    formatter.setRoundingMode(RoundingMode.HALF_UP);
                    Float formatedFloat = Float.valueOf(formatter.format(profile.getWeight()));
                    profile.setWeight(formatedFloat);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento para edição do tipo sanguineo
        EditText etBloodType = findViewById(R.id.vlBloodTypePE);
        if (!profile.getBloodType().trim().isEmpty()){
            etBloodType.setText(profile.getBloodType());
        }
        etBloodType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    profile.setBloodType(editable.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        // Evento para edição das alergias
        EditText etAlergies = findViewById(R.id.vlAlergiesPE);
        if (!profile.getAlergies().trim().isEmpty()){
            etAlergies.setText(profile.getAlergies());
        }
        etAlergies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    profile.setAlergies(editable.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        TableLayout tableLayout = findViewById(R.id.tableDataPE);
        tableLayout.invalidate();
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();

            if (profile.getAge() < 0 || profile.getAge() > 100){
                profile.setAge(0);
            }
            if (profile.getWeight() < 0 || profile.getWeight() > 300){
                profile.setWeight(0);
            }
            if (profile.getHeight() < 0 || profile.getHeight() > 250){
                profile.setHeight(0);
            }
            Intent myIntent = new Intent(ProfileActivityEdit.this, ProfileActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            ProfileActivityEdit.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
