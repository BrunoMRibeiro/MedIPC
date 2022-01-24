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

// Classe responsável por adicionar uma nova consulta
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

        // Recebe os dados dos appointments
        appointmentProfile = new AppointmentProfile();
        if (getIntent().getSerializableExtra(getString(R.string.appointment_key)) != null)
            appointmentProfile = (AppointmentProfile) getIntent().getSerializableExtra(getString(R.string.appointment_key));

        // Evento para a ação de confirmar a nova consulta
        Button aEdit = findViewById(R.id.btDoneARAp);
        aEdit.setOnClickListener(v -> {
            if(verifyEditTexts()){
                String filename = "appointment.srl";
                ObjectOutput out = null;

                try {
                    out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                    out.writeObject(appointmentProfile);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent myIntent = new Intent(AppointmentAddRowActivity.this, AppointmentEditActivity.class);
                myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
                AppointmentAddRowActivity.this.startActivity(myIntent);
                finish();
            }
        });

        // Evento para a ação de cancelar a adição de nova consulta
        Button aBack = findViewById(R.id.btCancelARAp);
        aBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(AppointmentAddRowActivity.this, AppointmentEditActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentAddRowActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button aLogOff = findViewById(R.id.LogOutARAp);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        TableLayout tableLayout = findViewById(R.id.tableDataAppointmentARM);
        tableLayout.invalidate();
    }

    // Classe responsável pela verificação das caixas de texto
    public boolean verifyEditTexts() {
        String info;
        String date;

        EditText etName = findViewById(R.id.AppointmentARM);
        info = etName.getText().toString();

        EditText etHours = findViewById(R.id.DateARAp);
        date = etHours.getText().toString();

        if((TextUtils.isEmpty(info) || TextUtils.isEmpty(date))) {
            Toast.makeText(this,
                    "Need to insert Appointment data",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        String splitTime[]=date.split("/");

        if (splitTime.length == 3) {
            try{
                String date1 = splitTime[0];
                String date2 = splitTime[1];
                String date3 = splitTime[2];
                int dia = Integer.parseInt(date1);
                int mes = Integer.parseInt(date2);
                int ano = Integer.parseInt(date3);
                if (dia <= 31 && dia > 0 && mes <= 12 && mes > 0 && ano >= 2021) {
                    date = date1 + "/" + date2 + "/" + date3;
                    AppointmentData appointmentData = new AppointmentData(info, date);
                    appointmentProfile.getAppointment().getAppointmentData().add(appointmentData);
                } else {
                    Toast.makeText(this, "Insira uma data conforme o exemplo: 24/01/2022", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }catch (NumberFormatException e){
                Toast.makeText(this, "Insira uma data conforme o exemplo: 24/01/2022", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
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