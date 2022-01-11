package com.example.myapplication.Appointment;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AppointmentEditActivity extends AppCompatActivity {
    private AppointmentProfile appointmentProfile;
    private ArrayList<EditText> editTexts;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_appointment_edit);

        editTexts = new ArrayList<>();
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        appointmentProfile = new AppointmentProfile();
        if(getIntent().getSerializableExtra(getString(R.string.appointment_key)) != null)
            appointmentProfile = (AppointmentProfile)getIntent().getSerializableExtra(getString(R.string.appointment_key));


        Button btAddRow = findViewById(R.id.btAddAppointment);
        btAddRow.setOnClickListener(v -> {
            Intent myIntent = new Intent(AppointmentEditActivity.this, AppointmentAddRowActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentEditActivity.this.startActivity(myIntent);
        });

        Button btDone = findViewById(R.id.btDoneAPE);
        btDone.setOnClickListener(v -> {
            updateData();

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

            Intent myIntent = new Intent(AppointmentEditActivity.this, AppointmentActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentEditActivity.this.startActivity(myIntent);
            finish();
        });

        Button bLogOff = findViewById(R.id.LogOutAPE);
        bLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
        createFill(appointmentProfile.getAppointment());

        TableLayout tableLayout = findViewById(R.id.tableDataAppointmentEdit);
        tableLayout.invalidate();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void createFill(Appointment appointmentTable){
        TableLayout stk = findViewById(R.id.tableDataAppointmentEdit);
        for(int i = 0; i < appointmentTable.getAppointmentData().size(); i++){
            Drawable border;

            if((i % 2) == 0){
                border = this.getResources().getDrawable(R.drawable.cell_par);
            }
            else{
                border = this.getResources().getDrawable(R.drawable.cell_impar);
            }

            TableRow tbrow = new TableRow(this);
            EditText t1v = new EditText(this);
            t1v.setText(appointmentTable.getAppointmentData().get(i).getinfo());
            t1v.setTextColor(getResources().getColor(R.color.black));
            t1v.setTextSize(16);
            t1v.setGravity(Gravity.CENTER);
            t1v.setBackground(border);
            t1v.setPadding(20,20,20,20);
            t1v.setId(count++);
            tbrow.addView(t1v);
            editTexts.add(t1v);

            EditText t2v = new EditText(this);
            t2v.setText(appointmentTable.getAppointmentData().get(i).getDate());
            t2v.setTextColor(getResources().getColor(R.color.black));
            t2v.setTextSize(16);
            t2v.setGravity(Gravity.CENTER);
            t2v.setBackground(border);
            t2v.setPadding(20,20,20,20);
            t2v.setId(count++);
            tbrow.addView(t2v);
            editTexts.add(t2v);
            stk.addView(tbrow);
        }
    }

    public void updateData(){
        ArrayList<AppointmentData> appointments = new ArrayList<>();

        for(int i = 0; i < editTexts.size(); i+=2){
            AppointmentData appointmentData = new AppointmentData(editTexts.get(i).getText().toString(), editTexts.get(i+1).getText().toString());
            appointments.add(appointmentData);
        }
        appointmentProfile.getAppointment().setAppointmentData(appointments);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            Intent myIntent = new Intent(AppointmentEditActivity.this, AppointmentActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentEditActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}