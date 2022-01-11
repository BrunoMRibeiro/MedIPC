package com.example.myapplication.Appointment;


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


public class AppointmentAddRowActivity extends AppCompatActivity {
    private AppointmentProfile appointmentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_add_row);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        appointmentProfile = new AppointmentProfile();
        if (getIntent().getSerializableExtra(getString(R.string.appointment_key)) != null)
            appointmentProfile = (AppointmentProfile) getIntent().getSerializableExtra(getString(R.string.appointment_key));

        Button aEdit = findViewById(R.id.btDoneARAp);
        aEdit.setOnClickListener(v -> {
            if(verifyEditTexts()){
                Intent myIntent = new Intent(AppointmentAddRowActivity.this, AppointmentEditActivity.class);
                myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
                AppointmentAddRowActivity.this.startActivity(myIntent);
                finish();
            }

        });

        Button aBack = findViewById(R.id.btCancelARAp);
        aBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(AppointmentAddRowActivity.this, AppointmentEditActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentAddRowActivity.this.startActivity(myIntent);
        });

        Button aLogOff = findViewById(R.id.LogOutARAp);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        TableLayout tableLayout = findViewById(R.id.tableDataAppointmentARM);
        tableLayout.invalidate();
    }


    public boolean verifyEditTexts() {
        String info;
        String Date;

        EditText etName = findViewById(R.id.AppointmentARM);
        info = etName.getText().toString();

        EditText etHours = findViewById(R.id.DateARAp);
        Date = etHours.getText().toString();


        if((TextUtils.isEmpty(info) && TextUtils.isEmpty(Date)) || (TextUtils.isEmpty(info) && (!TextUtils.isEmpty(Date)))) {
            Toast.makeText(this,
                    R.string.need_to_incert_appointment_info,
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        AppointmentData appointmentData = new AppointmentData(info, Date);
        appointmentProfile.getAppointment().getAppointmentData().add(appointmentData);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(AppointmentAddRowActivity.this, AppointmentEditActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentAddRowActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}