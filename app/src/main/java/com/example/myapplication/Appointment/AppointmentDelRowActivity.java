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

// Classe responsável pela remoção de uma consulta
public class AppointmentDelRowActivity extends AppCompatActivity {
    private AppointmentProfile appointmentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_del_row);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados dos appointments
        appointmentProfile = new AppointmentProfile();
        if (getIntent().getSerializableExtra(getString(R.string.appointment_key)) != null)
            appointmentProfile = (AppointmentProfile) getIntent().getSerializableExtra(getString(R.string.appointment_key));

        // Evento para a ação de confirmar a remoção da consulta
        Button aEdit = findViewById(R.id.btDoneDRAp);
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
                Intent myIntent = new Intent(AppointmentDelRowActivity.this, AppointmentEditActivity.class);
                myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
                AppointmentDelRowActivity.this.startActivity(myIntent);
                finish();
            }
        });

        // Evento para a ação de cancelar a remoção
        Button aBack = findViewById(R.id.btCancelDRAp);
        aBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(AppointmentDelRowActivity.this, AppointmentEditActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentDelRowActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button aLogOff = findViewById(R.id.LogOutDRAp);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
    }

    // Classe responsável pela verificação das caixas de texto
    public boolean verifyEditTexts() {
        String info;

        EditText etName = findViewById(R.id.AppointmentDRM);
        info = etName.getText().toString();

        if(TextUtils.isEmpty(info)) {
            Toast.makeText(this,
                    R.string.need_to_incert_appointment_info,
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        for (int i = 0; i <  appointmentProfile.getAppointment().getAppointmentData().size(); i++){
            if (info.equals(appointmentProfile.getAppointment().getAppointmentData().get(i).getinfo())){
                appointmentProfile.getAppointment().getAppointmentData().remove(i);
            }
        }
        return true;
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(AppointmentDelRowActivity.this, AppointmentEditActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            AppointmentDelRowActivity.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}