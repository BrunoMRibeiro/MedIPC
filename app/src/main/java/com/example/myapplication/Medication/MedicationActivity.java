package com.example.myapplication.Medication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.io.File;
import java.util.Scanner;

public class MedicationActivity extends AppCompatActivity {
    private MedicationProfile medicationprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        medicationprofile = new MedicationProfile();
        if(getIntent().getSerializableExtra(getString(R.string.medication_key)) != null)
            medicationprofile = (MedicationProfile) getIntent().getSerializableExtra(getString(R.string.medication_key));

        Button btEdit = findViewById(R.id.btEditM);
        btEdit.setOnClickListener(v -> {
            Intent myIntent = new Intent(MedicationActivity.this, MedicationEditActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationprofile);
            MedicationActivity.this.startActivity(myIntent);
        });

        Button btLogOff = findViewById(R.id.LogOutM);
        btLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        Button btCheckHistoryM = findViewById(R.id.HistoryMA);
        btCheckHistoryM.setOnClickListener(v -> {
            openFile();
        });

        init(medicationprofile.getMedication());
        TableLayout tableLayout = findViewById(R.id.tableDataMedication);
        tableLayout.invalidate();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void init(Medication medicationTable){
        TableLayout stk = findViewById(R.id.tableDataMedication);
        for(int i = 0; i < medicationTable.getMedicationData().size(); i++){
            Drawable border;

            if((i % 2) == 0){
                border = this.getResources().getDrawable(R.drawable.cell_par);
            }
            else{
                border = this.getResources().getDrawable(R.drawable.cell_impar);
            }

            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(medicationTable.getMedicationData().get(i).getName());
            t1v.setTextColor(getResources().getColor(R.color.black));
            t1v.setTextSize(16);
            t1v.setGravity(Gravity.CENTER);
            t1v.setBackground(border);
            t1v.setPadding(20,20,20,20);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(medicationTable.getMedicationData().get(i).getHours());
            t2v.setTextColor(getResources().getColor(R.color.black));
            t2v.setTextSize(16);
            t2v.setGravity(Gravity.CENTER);
            t2v.setBackground(border);
            t2v.setPadding(20,20,20,20);
            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setText(medicationTable.getMedicationData().get(i).getNext_take());
            t3v.setTextColor(getResources().getColor(R.color.black));
            t3v.setTextSize(16);
            t3v.setGravity(Gravity.CENTER);
            t3v.setBackground(border);
            t3v.setPadding(20,20,20,20);
            tbrow.addView(t3v);
            stk.addView(tbrow);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(MedicationActivity.this, MainActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationprofile);
            MedicationActivity.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Scanner x;

    public void openFile(){
        try{
            x = new Scanner(new File("HistoricoMed.txt"));
        }
        catch(Exception e){
            System.out.println("File could not be found");
        }
        readFile();
    }

    public void readFile(){
        while(x.hasNext()){
            String a = x.next();
            String b = x.next();
            String c = x.next();

            MedicationData medicationData = new MedicationData(a, b, c);
            medicationprofile.getMedication().getMedicationData().add(medicationData);
        }
    }
}

