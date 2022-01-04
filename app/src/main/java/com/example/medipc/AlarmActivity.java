package com.example.medipc;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmActivity extends AppCompatActivity {

    /*AlarmManager alarmManager;
    private PendingIntent pendingIntent;*/

    private TimePicker alarmTime;
    TextClock currentTime;
    final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
    /*private static AlarmActivity inst;
    private TextView alarmTextView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmTime = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.textClock);


        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentTime.getText().toString().equals(AlarmTime())) {
                    r.play();
                }else {
                    r.stop();
                }
            }
        }, 0, 1000);
    }

    public String AlarmTime(){
        Integer alarmHours = alarmTime.getCurrentHour();
        Integer alarmMinutes = alarmTime.getCurrentMinute();

        String stringAlarmTime;

        if(alarmHours>12){
            alarmHours = alarmHours - 12;
            stringAlarmTime = alarmHours.toString().concat(":").concat(alarmMinutes.toString()).concat(" PM");
        }
        else {
            stringAlarmTime = alarmHours.toString().concat(":").concat(alarmMinutes.toString()).concat(" AM");
        }
        return stringAlarmTime;
    }

    /*public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("AlarmeActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("AlarmeActivity", "Alarm Off");
        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }*/
    }
