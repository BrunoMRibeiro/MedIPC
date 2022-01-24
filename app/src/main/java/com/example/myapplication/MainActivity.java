package com.example.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Alarms.AlarmActivity;
import com.example.myapplication.Alarms.AlarmProfile;
import com.example.myapplication.Appointment.Appointment;
import com.example.myapplication.Appointment.AppointmentActivity;
import com.example.myapplication.Appointment.AppointmentProfile;
import com.example.myapplication.Medication.MedicationActivity;
import com.example.myapplication.Medication.MedicationProfile;
import com.example.myapplication.Profile.Profile;
import com.example.myapplication.Profile.ProfileActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

// Classe inicial da aplicação (depois de fazer login)
public class MainActivity extends AppCompatActivity {
    private Profile profile;
    private AlarmProfile alarmprofile;
    private MedicationProfile medicationProfile;
    private AppointmentProfile appointmentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Classes com toda a informação
        profile = new Profile();
        alarmprofile = new AlarmProfile();
        medicationProfile = new MedicationProfile();
        appointmentProfile = new AppointmentProfile();

        if(getIntent().getSerializableExtra(getString(R.string.profile)) != null)
            profile = (Profile)getIntent().getSerializableExtra(getString(R.string.profile));

        if(getIntent().getSerializableExtra(getString(R.string.alarm)) != null)
            alarmprofile = (AlarmProfile)getIntent().getSerializableExtra(getString(R.string.alarm));

        if(getIntent().getSerializableExtra(getString(R.string.medication_key)) != null)
            medicationProfile = (MedicationProfile) getIntent().getSerializableExtra(getString(R.string.medication_key));

        if(getIntent().getSerializableExtra(getString(R.string.appointment_key)) != null)
            appointmentProfile = (AppointmentProfile) getIntent().getSerializableExtra(getString(R.string.appointment_key));


        Button button = findViewById(R.id.btProfile);
        Button button2 = findViewById(R.id.btAlarm);
        Button button3 = findViewById(R.id.btMedication);
        Button button4 = findViewById(R.id.btAppointment);

        // Evento para a ação de aceder ao profile
        button.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
            myIntent.putExtra("profile", profile);
            MainActivity.this.startActivity(myIntent);
        });

        // Evento para a ação de aceder aos alarmes
        button2.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, AlarmActivity.class);
            myIntent.putExtra("alarm", alarmprofile);
            MainActivity.this.startActivity(myIntent);
        });

        // Evento para a ação de aceder à medicação
        button3.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, MedicationActivity.class);
            myIntent.putExtra(getString(R.string.medication_key), medicationProfile);
            MainActivity.this.startActivity(myIntent);
        });

        // Evento para a ação de aceder às consultas
        button4.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, AppointmentActivity.class);
            myIntent.putExtra(getString(R.string.appointment_key), appointmentProfile);
            MainActivity.this.startActivity(myIntent);
        });

        readProfile();
        readAlarmProfile();
        readMedicationProfile();
        readAppointmentProfile();

    }

    // Carrega a informação do Profile
    public void readProfile(){
        ObjectInputStream input;
        String filename = "profile.srl";

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            profile = (Profile) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Carrega a informação dos Alarmes
    public void readAlarmProfile(){
        ObjectInputStream input;
        String filename = "alarm.srl";

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            alarmprofile = (AlarmProfile) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Carrega a informação da medicação
    public void readMedicationProfile(){
        ObjectInputStream input;
        String filename = "medication.srl";

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            medicationProfile = (MedicationProfile) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Carrega a informação dos Appointments
    public void readAppointmentProfile(){
        ObjectInputStream input;
        String filename = "appointment.srl";

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            appointmentProfile = (AppointmentProfile) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

