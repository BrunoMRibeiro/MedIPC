package com.example.myapplication.Alarms;

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
import com.example.myapplication.R;

// Classe responsável por mostrar os dados dos alarmes
public class AlarmActivity extends AppCompatActivity {
    private AlarmProfile alarmprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados dos alarmes
        alarmprofile = new AlarmProfile();
        if(getIntent().getSerializableExtra(getString(R.string.alarm)) != null)
            alarmprofile = (AlarmProfile)getIntent().getSerializableExtra(getString(R.string.alarm));


        // Evento para o botão de editar alarme
        Button aEdit = findViewById(R.id.btEditAlarm);
        aEdit.setOnClickListener(v -> {
            Intent myIntent = new Intent(AlarmActivity.this, AlarmEditActivity.class);
            myIntent.putExtra(getString(R.string.alarm), alarmprofile);
            AlarmActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button aLogOff = findViewById(R.id.LogOutAlarm);
        aLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        init(alarmprofile.getAlarm());
        TableLayout tableLayout = findViewById(R.id.tableDataAlarm);
        tableLayout.invalidate();
    }


    // Classe responsável por mostrar todos os alarmes
    @SuppressLint("UseCompatLoadingForDrawables")
    public void init(Alarm alarmsTable) {
        TableLayout stk = findViewById(R.id.tableDataAlarm);
        for (int i = 0; i < alarmsTable.getAlarmsData().size(); i++) {
            Drawable border;

            if ((i % 2) == 0)
                border = this.getResources().getDrawable(R.drawable.cell_par);
            else
                border = this.getResources().getDrawable(R.drawable.cell_impar);

            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(alarmsTable.getAlarmsData().get(i).getName());
            t1v.setTextColor(getResources().getColor(R.color.black));
            t1v.setTextSize(16);
            t1v.setGravity(Gravity.CENTER);
            t1v.setBackground(border);
            t1v.setPadding(20,20,20,20);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(alarmsTable.getAlarmsData().get(i).getDescription());
            t2v.setTextColor(getResources().getColor(R.color.black));
            t2v.setTextSize(16);
            t2v.setGravity(Gravity.CENTER);
            t2v.setBackground(border);
            t2v.setPadding(20,20,20,20);
            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setText(alarmsTable.getAlarmsData().get(i).getSetTime());
            t3v.setTextColor(getResources().getColor(R.color.black));
            t3v.setTextSize(16);
            t3v.setGravity(Gravity.CENTER);
            t3v.setBackground(border);
            t3v.setPadding(20,20,20,20);
            tbrow.addView(t3v);
            stk.addView(tbrow);
        }
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(AlarmActivity.this, MainActivity.class);
            myIntent.putExtra(getString(R.string.alarm), alarmprofile);
            AlarmActivity.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
