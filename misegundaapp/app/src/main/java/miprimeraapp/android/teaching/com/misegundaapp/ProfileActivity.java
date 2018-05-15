package miprimeraapp.android.teaching.com.misegundaapp;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    //views
    private EditText usernameEditex;
    private EditText emailEditex;
    private EditText passwordEditex;
    private EditText ageEditex;
    private RadioButton radioButton;
    private RadioButton radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameEditex = findViewById(R.id.username);
        emailEditex = findViewById(R.id.email);
        passwordEditex = findViewById(R.id.password);
        ageEditex = findViewById(R.id.age);
        radioButton = findViewById(R.id.radioButton);
        ageEditex.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //MOSTRAR date pickerdialog

                    new DatePickerDialog(ProfileActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet
                                        (DatePicker view, int year, int month, int dayOfMonth) {
                                    int anoActual = Calendar.getInstance().get(Calendar.YEAR);
                                    int edad = anoActual - year;
                                    ageEditex.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                                }
                            }, 1970, 1, 1).show();


                }
            }
        });
        radioButton2 = findViewById(R.id.radioButton2);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //con esto se ejecuta el click de save

    private void saveInternal() {
        Log.d("ProfileActivity", "Username:" + usernameEditex.getText());
        Log.d("ProfileActivity", "Email:" + emailEditex.getText());
        Log.d("ProfileActivity", "Password:" + passwordEditex.getText());
        Log.d("ProfileActivity", "Age:" + ageEditex.getText());

//esto es para que cuando eliga masculino si no es de femenino estos son los parametros.
        if (radioButton.isChecked()) {
            Log.d("ProfileActivity", "Gender:male");
        } else if (radioButton2.isChecked()) {
            Log.d("ProfileActivity", "Gender:Female");
        }
    }

    public void guardar(View view) {
        saveInternal();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;

    }


    public void createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.titulo)
                .setMessage(R.string.dialogo)
                .setPositiveButton(R.string.si,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNeutralButton(R.string.cancelar,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        builder.create().show();
    }

    public void borrar(View view) {
        createSimpleDialog();
    }

}



