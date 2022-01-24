package com.example.myapplication.Alarms;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

// Classe responsável pela remoção de um alarme
public class AlarmDelRowActivity extends AppCompatActivity {
    private AlarmProfile alarmprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_del_row);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados dos alarmes
        alarmprofile = new AlarmProfile();
        if (getIntent().getSerializableExtra(getString(R.string.alarm)) != null)
            alarmprofile = (AlarmProfile) getIntent().getSerializableExtra(getString(R.string.alarm));

        // Evento para a ação de confirmar a remoção do alarme
        Button aEdit = findViewById(R.id.btDoneADR);
        aEdit.setOnClickListener(v -> {
            if(verifyEditTexts()){

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

                Intent myIntent = new Intent(AlarmDelRowActivity.this, AlarmEditActivity.class);
                myIntent.putExtra(getString(R.string.alarm), alarmprofile);
                AlarmDelRowActivity.this.startActivity(myIntent);
                finish();
            }

        });

        // Evento para o botão de cancelar a remoção de um alarme
        Button aBack = findViewById(R.id.btCancelADR);
        aBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(AlarmDelRowActivity.this, AlarmEditActivity.class);
            myIntent.putExtra(getString(R.string.alarm), alarmprofile);
            AlarmDelRowActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button aLogOff = findViewById(R.id.LogOutADR);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

    }

    // Verifica os campos preenchidos pelo utilizador
    public boolean verifyEditTexts() {
        String name;

        EditText etMedicine = findViewById(R.id.etNameADR);
        name = etMedicine.getText().toString();


        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this,
                    "Need to insert alarm name",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        for (int i = 0; i <  alarmprofile.getAlarm().getAlarmsData().size(); i++){
            if (name.equals(alarmprofile.getAlarm().getAlarmsData().get(i).getName())){
                alarmprofile.getAlarm().getAlarmsData().remove(i);
            }
        }

        return true;
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(AlarmDelRowActivity.this, AlarmEditActivity.class);
            myIntent.putExtra(getString(R.string.alarm), alarmprofile);
            AlarmDelRowActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}