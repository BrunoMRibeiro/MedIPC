package com.example.medipc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*private Button button;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Button btnAddItem = (Button) findViewById(R.id.addItemTableRow);
        btnAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //create a new row to add
                TableRow row = new TableRow(MainActivity.this);
                //add Layouts to your new row
                TextView txt = new TextView(MainActivity.this);
                txt.setText("New Row");
                row.addView(txt);
                //add your new row to the TableLayout:
                TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
                table.addView(row);

            }
        });
        /*button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });*/
        }
        }