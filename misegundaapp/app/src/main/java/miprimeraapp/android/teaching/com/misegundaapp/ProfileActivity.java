package miprimeraapp.android.teaching.com.misegundaapp;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
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
                                    ageEditex.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
        Log.d("ListActivity", "Escribir: " + isExternalStorageWritable());
        Log.d("ListActivity", "leer: " + isExternalStorageReadable());

        //meter una imagen desde un directorio
 //       File imgFile = new File(getExternalFilesDir(null), "iconocamara.png");
 //       if (imgFile.exists()) {
//            ImageView myImage = findViewById(R.id.iconocamara);
 //           myImage.setImageURI(Uri.fromFile(imgFile));
        }
 //   }

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
        //   Intent intent = new Intent(this, )
// esto es para la base de datos para que ella obtenga la inf del usuario registrado .

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "miprimerabasedatos").allowMainThreadQueries().build();

        user user = new user();
        user.setUsername(usernameEditex.getText().toString());
        user.setEmail(emailEditex.getText().toString());
        user.setPassword(passwordEditex.getText().toString());
        user.setAge(ageEditex.getText().toString());
        try {
            db.userDao().insert(user);
        } catch (SQLiteConstraintException sqlmia) {
            Toast.makeText(ProfileActivity.this, "lo siento este ya existe", Toast.LENGTH_LONG).show();

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
//con esto consigo que cuando me logeee me salga todos los datos dle logeado.
        String usernameValue = sharedPref.getString("username_key", "");
        AppDatabase myDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "miprimerabasedatos")
                // con este de aqui hago el llamamientoa buscar en la base de datos
                .allowMainThreadQueries()
                .build();
        user myUser = myDatabase.userDao().findByUsername(usernameValue);
        if (myUser != null) {
            usernameEditex.setText(myUser.getUsername());
            emailEditex.setText(myUser.getEmail());
            ageEditex.setText(myUser.getAge());
            passwordEditex.setText(myUser.getPassword());
            // con esto hago que lo busque en la base de datos.
            String genderValue = myUser.getGender();
            if (genderValue != null){
                if (genderValue.equals("h")) {
                    radioButton.setChecked(true);
                } else if (genderValue.equals("m")) {
                    radioButton2.setChecked(true);
                }
            }

        }
    }
//cosas exa antes
        //      String username = sharedPref.getString("Username", "");
        //    String email = sharedPref.getString("Email", "");
        //  String password = sharedPref.getString("Password", "");
        // String age = sharedPref.getString("Age", "");
        //      TextView username2 = findViewById(R.id.username);
        //      TextView email2 = findViewById(R.id.email);
        //     TextView password2 = findViewById(R.id.password);

        //     username2.setText(username);
        //      email2.setText(email);
        //     password2.setText(password);



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
        TextView age2 = findViewById(R.id.age);

        SharedPreferences.Editor myEditor = sharedPref.edit();
        myEditor.putString("Username", username2.getText().toString());
        myEditor.putString("Email", email2.getText().toString());
        myEditor.putString("Age", age2.getText().toString());
        myEditor.putString("Password", password2.getText().toString());
        if (radioButton.isChecked()) {
            myEditor.putString("gender", "h");
        } else if (radioButton2.isChecked()) {
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
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

//si no podemos escribir comprobar almenos si existe y podemos leer

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }



public void camaraintent (View view){
        //el usuario ha pulsado la imagen de perfil
    //crear intent que llama a camara
    Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    if (camaraIntent.resolveActivity(getPackageManager()) !=null) {
        //esto de aqui de photofile es el nombre que le doi le puedo dar cualq otro
        File photoFile = createImageFile();
        Uri photoURI =
                FileProvider.getUriForFile
                        (this,
                                "miprimeraapp.android.teaching.com.misegundaapp",
                                photoFile);
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(camaraIntent, 100);
    }
}

private File createImageFile () {
        File storageDir =
                //esta ruta es la externa publica
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(storageDir,"profile.jpg");
}

    @Override
    protected void onResume() {

        File myFile = createImageFile();

        if (myFile.exists()) {
            ImageView imageView = findViewById(R.id.iconocamara);
            imageView.setImageBitmap(BitmapFactory.decodeFile(myFile.getAbsolutePath()));

        }
        super.onResume();
    }






//este es el onActitivity result donde una vez el usuario capturaba la imagne deseada el foco
    //regresa  la priemra acittivyt ejecutando el onactitiviresult
  //  @Override
  //  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
      //  if (requestCode == 100 && resultCode == RESULT_OK){
        //    Bundle extras= data.getExtras();
         //   Bitmap imageBitmap = (Bitmap) extras.get("data");
           // ImageView mImageView = findViewById(R.id.iconocamara);
 //           mImageView.setImageBitmap(imageBitmap);
   //     }else if (requestCode == 100 && resultCode == RESULT_CANCELED) {
     //       Toast.makeText(this, "no has exo nada", Toast.LENGTH_SHORT).show();
       // }
    //}




    }









