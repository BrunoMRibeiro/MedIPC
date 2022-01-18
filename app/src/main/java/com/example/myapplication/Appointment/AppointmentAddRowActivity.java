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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


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

            String filename = "appointment.srl";
            ObjectOutput out = null;

            try {
                out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                out.writeObject(appointmentProfile);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
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
        String date;

        EditText etName = findViewById(R.id.AppointmentARM);
        info = etName.getText().toString();

        EditText etHours = findViewById(R.id.DateARAp);
        date = etHours.getText().toString();

        String splitTime[]=date.split("/");
        String date1=splitTime[0];
        String date2=splitTime[1];
        String date3=splitTime[2];
        int dia = Integer.parseInt(date1);
        int mes = Integer.parseInt(date2);
        int ano = Integer.parseInt(date3);
        if(dia <= 31 && dia > 0 && mes <= 12 && mes > 0 && ano >= 2021){
            date = date1 + "/" + date2 + "/" + date3;
            AppointmentData appointmentData = new AppointmentData(info, date);
            appointmentProfile.getAppointment().getAppointmentData().add(appointmentData);
        }
        else{
            Toast.makeText(this,
                    "Insira uma data correta",
                    Toast.LENGTH_SHORT).show();
        }


        if((TextUtils.isEmpty(info) || TextUtils.isEmpty(date))) {
            Toast.makeText(this,
                    "Need to insert Appointment data",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        AppointmentData appointmentData = new AppointmentData(info, date);
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