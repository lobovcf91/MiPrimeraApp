package miprimeraapp.android.teaching.com.misegundaapp;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
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
import android.widget.TextView;

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
//comprobar si existe el almacenamiento externo
        Log.d ("ListActivity", "Escribir: " + isExternalStorageWritable());
        Log.d ("ListActivity", "leer: " + isExternalStorageReadable());
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

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.basic_preference_file),
                Context.MODE_PRIVATE);
        String username = sharedPref.getString("Username", "");
        String email = sharedPref.getString("Email", "");
        String password = sharedPref.getString("Password", "");
        int ageValue = sharedPref.getInt("Age", 0);
        if (ageValue != -1) {
            ageEditex.setText(ageValue + "");
        }
        String genderValue = sharedPref.getString("gender_key", "");
        if (genderValue.equals("h")) {
            radioButton.setChecked(true);
        } else if (genderValue.equals("m")) {
            radioButton2.setChecked(true);
        }

        TextView username2 = findViewById(R.id.username);
        TextView email2 = findViewById(R.id.email);
        TextView password2 = findViewById(R.id.password);

        username2.setText(username);
        email2.setText(email);
        password2.setText(password);

    }

    @Override
    protected void onStop() {
        super.onStop();

        //aqui es para cogerlo dps de ver si usuario y contraseña son correctas
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.basic_preference_file),
                Context.MODE_PRIVATE);

        TextView username2 = findViewById(R.id.username);
        TextView email2 = findViewById(R.id.email);
        TextView password2 = findViewById(R.id.password);

        SharedPreferences.Editor myEditor = sharedPref.edit();
        myEditor.putString("Username", username2.getText().toString());
        myEditor.putString("Email", email2.getText().toString());
        myEditor.putInt("Age", Integer.parseInt(ageEditex.getText().toString()));
        myEditor.putString("Password", password2.getText().toString());
        if (radioButton.isChecked()){
            myEditor.putString("gender", "h");
    }   else if (radioButton2.isChecked()){
            myEditor.putString("gender", "m");
        }
        myEditor.apply();

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
//check if external storage availabel,  sirve para comprobar si el alma externo exitiste y esta pra escribir
    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

//si no podemos escribir comprobar almenos si existe y podemos leer

    public boolean isExternalStorageReadable (){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }




}



