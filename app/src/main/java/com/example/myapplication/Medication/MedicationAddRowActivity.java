package com.example.myapplication.Medication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MedicationAddRowActivity extends AppCompatActivity {
    private MedicationProfile medicationProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_add_row);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        medicationProfile = new MedicationProfile();
        if (getIntent().getSerializableExtra(getString(R.string.medication_key)) != null)
            medicationProfile = (MedicationProfile) getIntent().getSerializableExtra(getString(R.string.medication_key));

        Button aEdit = findViewById(R.id.btDoneARM);
        aEdit.setOnClickListener(v -> {

            String filename = "medication.srl";
            ObjectOutput out = null;

            try {
                out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                out.writeObject(medicationProfile);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(verifyEditTexts()){
                Intent myIntent = new Intent(MedicationAddRowActivity.this, MedicationEditActivity.class);
                myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
                MedicationAddRowActivity.this.startActivity(myIntent);
                finish();
            }

        });

        Button aBack = findViewById(R.id.btCancelARM);
        aBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(MedicationAddRowActivity.this, MedicationEditActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
            MedicationAddRowActivity.this.startActivity(myIntent);
        });

        Button aLogOff = findViewById(R.id.LogOutARM);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        TableLayout tableLayout = findViewById(R.id.tableDataMedicationARM);
        tableLayout.invalidate();
    }


    public boolean verifyEditTexts() {
        String name;
        String hours = "";
        String nextTake = "";
        String nextTake1;
        String hours2;

        EditText etName = findViewById(R.id.MedicationARM);
        name = etName.getText().toString();

        EditText etHours = findViewById(R.id.HoursARM);
        hours2 = etHours.getText().toString();
        int intervalo = Integer.parseInt(hours2);


        EditText etNextTake = findViewById(R.id.NextTakeARM);
        nextTake1 = etNextTake.getText().toString();
        String splitTime[]=nextTake1.split(":");
        String nexttake2=splitTime[0];
        String nexttake3=splitTime[1];
        int horas = Integer.parseInt(nexttake2);
        int minutos = Integer.parseInt(nexttake3);
        if(horas <= 23 && horas >= 0 && minutos <= 59 && minutos >= 0 && intervalo<= 23 && intervalo >= 0){
            nextTake = nexttake2 + "h:" + nexttake3 + "m";
            hours = hours2 + "h - " + hours2 + "h";
            MedicationData medicationData = new MedicationData(name, hours, nextTake);
            medicationProfile.getMedication().getMedicationData().add(medicationData);
        }
        else{
            Toast.makeText(this,
                    "Insira uma data correta",
                    Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(hours) || TextUtils.isEmpty(nextTake)) {
            Toast.makeText(this,
                    "Need to insert all Medication data",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(MedicationAddRowActivity.this, MedicationEditActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
            MedicationAddRowActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
