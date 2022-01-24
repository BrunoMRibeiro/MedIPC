package com.example.myapplication.Medication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

// Classe responsável por remover uma medicação existente
public class MedicationDelRowActivity extends AppCompatActivity {
        private MedicationProfile medicationProfile;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_medication_del_row);

            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(true);
            }

            // Recebe os dados das medicações
            medicationProfile = new MedicationProfile();
            if (getIntent().getSerializableExtra(getString(R.string.medication_key)) != null)
                medicationProfile = (MedicationProfile) getIntent().getSerializableExtra(getString(R.string.medication_key));

            // Evento para a ação de confirmar a remoção
            Button aEdit = findViewById(R.id.btDoneDRM);
            aEdit.setOnClickListener(v -> {

                String filename = "medication.srl";
                ObjectOutput out = null;

                try {
                    out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                    out.writeObject(medicationProfile);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(verifyEditTexts()){
                    Intent myIntent = new Intent(MedicationDelRowActivity.this, MedicationEditActivity.class);
                    myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
                    MedicationDelRowActivity.this.startActivity(myIntent);
                    finish();
                }
            });

            // Evento para a ação de cancelar a remoção de uma medicação
            Button aBack = findViewById(R.id.btCancelDRM);
            aBack.setOnClickListener(v -> {
                Intent myIntent = new Intent(MedicationDelRowActivity.this, MedicationEditActivity.class);
                myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
                MedicationDelRowActivity.this.startActivity(myIntent);
            });

            // Evento para o botão de desligar a aplicação
            Button aLogOff = findViewById(R.id.LogOutDRM);
            aLogOff.setOnClickListener(v -> {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            });
        }

        // Classe responsável pela verificação dos campos de texto
        public boolean verifyEditTexts() {
            String name;

            EditText etName = findViewById(R.id.etMedicationDRM);
            name = etName.getText().toString();

            if(TextUtils.isEmpty(name)) {
                Toast.makeText(this,
                        R.string.need_to_incert_medicine_name,
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            for (int i = 0; i <  medicationProfile.getMedication().getMedicationData().size(); i++){
                if (name.equals(medicationProfile.getMedication().getMedicationData().get(i).getName())){
                    medicationProfile.getMedication().getMedicationData().remove(i);
                }
            }
            return true;
        }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                onBackPressed();
                Intent myIntent = new Intent(MedicationDelRowActivity.this, MedicationEditActivity.class);
                myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
                MedicationDelRowActivity.this.startActivity(myIntent);
                finish();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
