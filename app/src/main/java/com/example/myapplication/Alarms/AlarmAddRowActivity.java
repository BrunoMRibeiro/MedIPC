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

import com.example.myapplication.R;

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

        alarmprofile = new AlarmProfile();
        if (getIntent().getSerializableExtra(getString(R.string.alarm)) != null)
            alarmprofile = (AlarmProfile) getIntent().getSerializableExtra(getString(R.string.alarm));

        Button aEdit = findViewById(R.id.btDoneAAR);
        aEdit.setOnClickListener(v -> {
            if(verifyEditTexts()){

                    Intent myIntent = new Intent(AlarmAddRowActivity.this, AlarmEditActivity.class);
                    myIntent.putExtra(getString(R.string.alarm), alarmprofile);
                    AlarmAddRowActivity.this.startActivity(myIntent);

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
                        startActivity(intent);
                    }

                    finish();
            }

        });

        Button aBack = findViewById(R.id.btCancelAAR);
        aBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(AlarmAddRowActivity.this, AlarmEditActivity.class);
            myIntent.putExtra(getString(R.string.alarm), alarmprofile);
            AlarmAddRowActivity.this.startActivity(myIntent);
        });

        Button aLogOff = findViewById(R.id.LogOutAAR);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        TableLayout tableLayout = findViewById(R.id.tableDataAlarmAddRow);
        tableLayout.invalidate();
    }


    public boolean verifyEditTexts() {
        String medicine;
        String description;
        String settime;

        EditText etMedicine = findViewById(R.id.etMedicineAAR);
        medicine = etMedicine.getText().toString();

        EditText etDescription = findViewById(R.id.etDescriptionAAR);
        description = etDescription.getText().toString();

        EditText etSetTime = findViewById(R.id.etSetTimeAAR);
        settime = etSetTime.getText().toString();

        if((TextUtils.isEmpty(medicine) && TextUtils.isEmpty(medicine)) || (TextUtils.isEmpty(medicine) && (!TextUtils.isEmpty(medicine)))) {
            Toast.makeText(this,
                    R.string.need_to_incert_medicine_name,
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        AlarmData alarmData = new AlarmData(medicine, description, settime);
        alarmprofile.getAlarm().getAlarmsData().add(alarmData);
        return true;
    }

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