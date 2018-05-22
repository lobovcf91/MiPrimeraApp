package miprimeraapp.android.teaching.com.misegundaapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loginprofile extends AppCompatActivity {

    private EditText usernameEditex;
    private EditText passwordEditex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginprofile);
        usernameEditex = findViewById(R.id.username2);
        passwordEditex = findViewById(R.id.password2);

        Toolbar myToolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.basic_preference_file),
                Context.MODE_PRIVATE);
        String savedUsername = sharedPref.getString("username_key","");
        usernameEditex.setText(savedUsername);
    }

    public void Login(View view) {
        //obtener valores
        String username = usernameEditex.getText().toString();
        String password = passwordEditex.getText().toString();


        if (TextUtils.isEmpty(username)) {
            //el campo username esta vacio
            usernameEditex.setError(getString(R.string.username_error));
        } else if (TextUtils.isEmpty(password))
            passwordEditex.setError(getString(R.string.password_error));
        else {
            //para logearme dps de registrarme con la base de datos
            AppDatabase myDatabase= Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "miprimerabasedatos")
                    .allowMainThreadQueries()
                    .build();

            user retrieveduser = myDatabase.userDao().findByUsername(username);
            if (retrieveduser ==null){
                Toast.makeText(this,"no existe buscate la vida", Toast.LENGTH_LONG).show();
            } else if (password.equals(retrieveduser.getPassword())){
                //e exo login aqui
                //aqui es para cogerlo dps de ver si usuario y contraseña son correctas
                SharedPreferences sharedPref = getSharedPreferences(
                        getString(R.string.basic_preference_file),
                        Context.MODE_PRIVATE);

                SharedPreferences.Editor myEditor = sharedPref.edit();
                myEditor.putString("username_key", username);
                myEditor.apply();

                Intent Login = new Intent(this, ProfileActivity.class);
                startActivity(Login);

            } else {
                Toast.makeText(loginprofile.this, "acuestate",Toast.LENGTH_LONG).show();
            }

        }

    }


    public void profile(View view) {
        Intent profile = new Intent(this, ProfileActivity.class);
        startActivity(profile);
    }

    public void oncancel(View view) {
        usernameEditex.setText("");
        passwordEditex.setText("");
    }
    //   public boolean onCreateOptionsMenu(Menu menu) {
    //      MenuInflater inflater = getMenuInflater();
    //    inflater.inflate(R.menu.menu, menu);
    //     return true;
    // }

}
