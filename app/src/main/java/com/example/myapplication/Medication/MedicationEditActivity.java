package com.example.myapplication.Medication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MedicationEditActivity extends AppCompatActivity {
    private MedicationProfile medicationProfile;
    private ArrayList<EditText> editTexts;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_medication_edit);

        editTexts = new ArrayList<>();
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        medicationProfile = new MedicationProfile();
        if(getIntent().getSerializableExtra(getString(R.string.medication_key)) != null)
            medicationProfile = (MedicationProfile)getIntent().getSerializableExtra(getString(R.string.medication_key));


        Button btAddRow = findViewById(R.id.btAddMedication);
        btAddRow.setOnClickListener(v -> {
            Intent myIntent = new Intent(MedicationEditActivity.this, MedicationAddRowActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
            MedicationEditActivity.this.startActivity(myIntent);
        });

        Button btDone = findViewById(R.id.btDoneME);
        btDone.setOnClickListener(v -> {
            updateData();
            Intent myIntent = new Intent(MedicationEditActivity.this, MedicationActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
            MedicationEditActivity.this.startActivity(myIntent);
            finish();
        });

        Button bLogOff = findViewById(R.id.LogOutME);
        bLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
        createFill(medicationProfile.getMedication());

        TableLayout tableLayout = findViewById(R.id.tableDataMedicationEdit);
        tableLayout.invalidate();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void createFill(Medication medicationTable){
        TableLayout stk = findViewById(R.id.tableDataMedicationEdit);
        for(int i = 0; i < medicationTable.getMedicationData().size(); i++){
            Drawable border;

            if((i % 2) == 0){
                border = this.getResources().getDrawable(R.drawable.cell_par);
            }
            else{
                border = this.getResources().getDrawable(R.drawable.cell_impar);
            }

            TableRow tbrow = new TableRow(this);
            EditText t1v = new EditText(this);
            t1v.setText(medicationTable.getMedicationData().get(i).getName());
            t1v.setTextColor(getResources().getColor(R.color.black));
            t1v.setTextSize(16);
            t1v.setGravity(Gravity.CENTER);
            t1v.setBackground(border);
            t1v.setPadding(20,20,20,20);
            t1v.setId(count++);
            tbrow.addView(t1v);
            editTexts.add(t1v);

            EditText t2v = new EditText(this);
            t2v.setText(medicationTable.getMedicationData().get(i).getHours());
            t2v.setTextColor(getResources().getColor(R.color.black));
            t2v.setTextSize(16);
            t2v.setGravity(Gravity.CENTER);
            t2v.setBackground(border);
            t2v.setPadding(20,20,20,20);
            t2v.setId(count++);
            tbrow.addView(t2v);
            editTexts.add(t2v);

            EditText t3v = new EditText(this);
            t3v.setText(medicationTable.getMedicationData().get(i).getNext_take());
            t3v.setTextColor(getResources().getColor(R.color.black));
            t3v.setTextSize(16);
            t3v.setGravity(Gravity.CENTER);
            t3v.setBackground(border);
            t3v.setPadding(20,20,20,20);
            t3v.setId(count++);
            tbrow.addView(t3v);
            stk.addView(tbrow);
            editTexts.add(t3v);
        }
    }

    public void updateData(){
        ArrayList<MedicationData> medications = new ArrayList<>();

        for(int i = 0; i < editTexts.size(); i+=3){
            MedicationData medicationData = new MedicationData(editTexts.get(i).getText().toString(), editTexts.get(i+1).getText().toString(), editTexts.get(i+2).getText().toString());
            medications.add(medicationData);
        }
        medicationProfile.getMedication().setMedicationData(medications);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            Intent myIntent = new Intent(MedicationEditActivity.this, MedicationActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
            MedicationEditActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
