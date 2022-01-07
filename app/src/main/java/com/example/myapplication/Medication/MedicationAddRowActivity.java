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
        String hours;
        String nextTake;

        EditText etName = findViewById(R.id.MedicationARM);
        name = etName.getText().toString();

        EditText etHours = findViewById(R.id.HoursARM);
        hours = etHours.getText().toString();

        EditText etNextTake = findViewById(R.id.NextTakeARM);
        nextTake = etNextTake.getText().toString();


        if((TextUtils.isEmpty(name) && TextUtils.isEmpty(name)) || (TextUtils.isEmpty(name) && (!TextUtils.isEmpty(name)))) {
            Toast.makeText(this,
                    R.string.need_to_incert_medicine_name,
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        MedicationData medicationData = new MedicationData(name, hours, nextTake);
        medicationProfile.getMedication().getMedicationData().add(medicationData);
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
