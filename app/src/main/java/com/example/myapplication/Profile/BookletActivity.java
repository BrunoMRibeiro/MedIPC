package com.example.myapplication.Profile;

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
import com.example.myapplication.R;

// Classe responsável por mostrar a informação do boletim de vacinas
public class BookletActivity extends AppCompatActivity {
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklet);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados do profile
        profile = new Profile();
        if(getIntent().getSerializableExtra(getString(R.string.profile)) != null)
            profile = (Profile)getIntent().getSerializableExtra(getString(R.string.profile));

        // Evento para a ação de editar o booklet
        Button bEdit = findViewById(R.id.btEditB);
        bEdit.setOnClickListener(v -> {
            Intent myIntent = new Intent(BookletActivity.this, BookletEditActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            BookletActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button bLogOff = findViewById(R.id.LogOutB);
        bLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        init(profile.getBooklet());
        TableLayout tableLayout = findViewById(R.id.tableDataB);
        tableLayout.invalidate();
    }

    // Classe responsável por mostrar a informação do booklet
    @SuppressLint("UseCompatLoadingForDrawables")
    public void init(Booklet vaccinesTable) {
        TableLayout stk = findViewById(R.id.tableDataB);
        for (int i = 0; i < vaccinesTable.getVaccinesData().size(); i++) {
            Drawable border;

            if ((i % 2) == 0)
                border = this.getResources().getDrawable(R.drawable.cell_par);
            else
                border = this.getResources().getDrawable(R.drawable.cell_impar);

            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(vaccinesTable.getVaccinesData().get(i).getVaccineName());
            t1v.setTextColor(getResources().getColor(R.color.black));
            t1v.setTextSize(16);
            t1v.setGravity(Gravity.CENTER);
            t1v.setBackground(border);
            t1v.setPadding(20,20,20,20);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(vaccinesTable.getVaccinesData().get(i).getDate());
            t2v.setTextColor(getResources().getColor(R.color.black));
            t2v.setTextSize(16);
            t2v.setGravity(Gravity.CENTER);
            t2v.setBackground(border);
            t2v.setPadding(20,20,20,20);
            tbrow.addView(t2v);
            stk.addView(tbrow);
        }
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(BookletActivity.this, ProfileActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            BookletActivity.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
