package com.example.myapplication.Appointment;


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
import com.example.myapplication.Medication.Medication;
import com.example.myapplication.Medication.MedicationEditActivity;
import com.example.myapplication.Medication.MedicationProfile;
import com.example.myapplication.R;

public class AppointmentActivity extends AppCompatActivity {
    private AppointmentProfile appointmentprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        appointmentprofile = new AppointmentProfile();
        if(getIntent().getSerializableExtra(getString(R.string.appointment_key)) != null)
            appointmentprofile = (AppointmentProfile) getIntent().getSerializableExtra(getString(R.string.appointment_key));

        Button btEdit = findViewById(R.id.btEditAp);
        btEdit.setOnClickListener(v -> {
            Intent myIntent = new Intent(AppointmentActivity.this, AppointmentEditActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentprofile);
            AppointmentActivity.this.startActivity(myIntent);
        });

        Button btLogOff = findViewById(R.id.LogOutAp);
        btLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        init(appointmentprofile.getAppointment());
        TableLayout tableLayout = findViewById(R.id.tableDataAppointment);
        tableLayout.invalidate();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void init(Appointment appointmentTable){
        TableLayout stk = findViewById(R.id.tableDataAppointment);
        for(int i = 0; i < appointmentTable.getAppointmentData().size(); i++){
            Drawable border;

            if((i % 2) == 0){
                border = this.getResources().getDrawable(R.drawable.cell_par);
            }
            else{
                border = this.getResources().getDrawable(R.drawable.cell_impar);
            }

            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(appointmentTable.getAppointmentData().get(i).getinfo());
            t1v.setTextColor(getResources().getColor(R.color.black));
            t1v.setTextSize(16);
            t1v.setGravity(Gravity.CENTER);
            t1v.setBackground(border);
            t1v.setPadding(20,20,20,20);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(appointmentTable.getAppointmentData().get(i).getDate());
            t2v.setTextColor(getResources().getColor(R.color.black));
            t2v.setTextSize(16);
            t2v.setGravity(Gravity.CENTER);
            t2v.setBackground(border);
            t2v.setPadding(20,20,20,20);
            tbrow.addView(t2v);
            stk.addView(tbrow);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(AppointmentActivity.this, MainActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentprofile);
            AppointmentActivity.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}