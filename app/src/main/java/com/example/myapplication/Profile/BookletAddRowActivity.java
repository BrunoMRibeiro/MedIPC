package com.example.myapplication.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Appointment.AppointmentData;
import com.example.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

// Classe responsável por adicionar um novo registo no booklet
public class BookletAddRowActivity extends AppCompatActivity {
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklet_add_row);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Recebe os dados do profile
        profile = new Profile();
        if (getIntent().getSerializableExtra(getString(R.string.profile)) != null)
            profile = (Profile) getIntent().getSerializableExtra(getString(R.string.profile));

        // Evento para a ação de confirmar a adição
        Button bEdit = findViewById(R.id.btDoneBAR);
        bEdit.setOnClickListener(v -> {

            if(verifyEditTexts()){
                String filename = "profile.srl";
                ObjectOutput out = null;

                try {
                    out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
                    out.writeObject(profile);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent myIntent = new Intent(BookletAddRowActivity.this, BookletEditActivity.class);
                myIntent.putExtra(getString(R.string.profile), profile);
                BookletAddRowActivity.this.startActivity(myIntent);
                finish();
            }else{
                Toast.makeText(this,
                        "Insira o nome e a data corretamente",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Evento para a ação de cancelar a adição
        Button bBack = findViewById(R.id.btCancelBAR);
        bBack.setOnClickListener(v -> {
            Intent myIntent = new Intent(BookletAddRowActivity.this, BookletEditActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            BookletAddRowActivity.this.startActivity(myIntent);
        });

        // Evento para o botão de desligar a aplicação
        Button bLogOff = findViewById(R.id.LogOutBAR);
        bLogOff.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        TableLayout tableLayout = findViewById(R.id.tableDataBAR);
        tableLayout.invalidate();
    }

    // Classe responsável pela verificação dos campos de texto
    public boolean verifyEditTexts() {
        String vaccine;
        String date;
        EditText etVaccine = findViewById(R.id.etVaccineBAR);
        vaccine = etVaccine.getText().toString();

        EditText etDate = findViewById(R.id.etDateBAR);
        date = etDate.getText().toString();

        if((TextUtils.isEmpty(vaccine) && TextUtils.isEmpty(date)) || (TextUtils.isEmpty(vaccine) && (!TextUtils.isEmpty(date)))) {
            Toast.makeText(this,
                    R.string.need_to_incert_vaccine_name,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (vaccine.length() > 50){
            Toast.makeText(this,
                    "O nome da vacina tem de ter no máximo 50 caracteres",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        String splitTime[]=date.split("/");
        if (splitTime.length == 3)
        {
            String date1=splitTime[0];
            String date2=splitTime[1];
            String date3=splitTime[2];

            try{
                int dia = Integer.parseInt(date1);
                int mes = Integer.parseInt(date2);
                int ano = Integer.parseInt(date3);
                if(dia <= 31 && dia > 0 && mes <= 12 && mes > 0 && ano >= 1900){
                    int length = (int)(Math.log10(ano)+1);
                    Log.i("a", "length ->" + length);
                    if (length > 4){
                        Toast.makeText(this, "Insira uma data conforme o exemplo: 24/01/2022", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    date = dia + "/" + mes + "/" + ano;
                    VaccineData vaccineData = new VaccineData(vaccine, date);
                    profile.getBooklet().getVaccinesData().add(vaccineData);
                }
                else{
                    Toast.makeText(this, "Insira uma data conforme o exemplo: 24/01/2022", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }catch (NumberFormatException e){
                Toast.makeText(this, "Insira uma data conforme o exemplo: 24/01/2022", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    // Caso o utilizador carregue no botão "up" volta para a Atividade anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Intent myIntent = new Intent(BookletAddRowActivity.this, BookletEditActivity.class);
            myIntent.putExtra(getString(R.string.profile), profile);
            BookletAddRowActivity.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}