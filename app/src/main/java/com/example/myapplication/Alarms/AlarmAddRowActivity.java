package com.example.myapplication.Alarms;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Medication.MedicationData;
import com.example.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

// Classe responsável por adicionar um novo alarme
public class AlarmAddRowActivity extends AppCompatActivity {
    private AlarmProfile alarmprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_add_row);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados dos alarmes
        alarmprofile = new AlarmProfile();
        if (getIntent().getSerializableExtra(getString(R.string.alarm)) != null)
            alarmprofile = (AlarmProfile) getIntent().getSerializableExtra(getString(R.string.alarm));


        // Evento para a ação de confirmar o novo alarme
        Button aEdit = findViewById(R.id.btDoneAAR);
        aEdit.setOnClickListener(v -> {
                try{
                    EditText setTime = (EditText)findViewById(R.id.etSetTimeAAR);
                    EditText medicine = (EditText)findViewById(R.id.etMedicineAAR);
                    EditText description = (EditText)findViewById(R.id.etDescriptionAAR);
                    String medicamento = (medicine.getText().toString());
                    String descricao = (description.getText().toString());
                    String mensagem = medicamento + "(" + descricao + ")";
                    String tempo = (setTime.getText().toString());
                    String splitTime[]=tempo.split(":");
                    String horas=splitTime[0];
                    String minutos=splitTime[1];
                    int horas2 = Integer.parseInt(horas);
                    int minutos2 = Integer.parseInt(minutos);

                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR,horas2);
                    intent.putExtra(AlarmClock.EXTRA_MINUTES,minutos2);
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE,mensagem);
                    if(horas2 <= 24 && minutos2 <= 60) {
                        String filename = "alarm.srl";
                        ObjectOutput out = null;

                        try {
                            out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                            out.writeObject(alarmprofile);
                            out.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        AlarmData alarmData = new AlarmData(medicamento, descricao, tempo);
                        alarmprofile.getAlarm().getAlarmsData().add(alarmData);

                        startActivity(intent);
                        Intent myIntent = new Intent(AlarmAddRowActivity.this, AlarmEditActivity.class);
                        myIntent.putExtra(getString(R.string.alarm), alarmprofile);
                        AlarmAddRowActivity.this.startActivity(myIntent);
                        finish();
                    }else{
                        Toast.makeText(this,
                                "Need to insert: Hour:Minutes",
                                Toast.LENGTH_SHORT).show();
                    }
                }catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
                    Toast.makeText(this,
                            "Need to insert: Hour:Minutes",
                            Toast.LENGTH_SHORT).show();
                }
        });

        // Evento para o botão de cancelar a adição de novo alarme
        Button aBack = findViewById(R.id.btCancelAAR);
        aBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(AlarmAddRowActivity.this, AlarmEditActivity.class);
            myIntent.putExtra(getString(R.string.alarm), alarmprofile);
            AlarmAddRowActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button aLogOff = findViewById(R.id.LogOutAAR);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        TableLayout tableLayout = findViewById(R.id.tableDataAlarmAddRow);
        tableLayout.invalidate();
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(AlarmAddRowActivity.this, AlarmEditActivity.class);
            myIntent.putExtra(getString(R.string.alarm), alarmprofile);
            AlarmAddRowActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}