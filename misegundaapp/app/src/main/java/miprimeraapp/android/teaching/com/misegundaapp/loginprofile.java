package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

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



    public void Login(View view){
        //obtener valores
        String username = usernameEditex.getText().toString();
        String password = passwordEditex.getText().toString();

        if (TextUtils.isEmpty(username)) {
            //el campo username esta vacio
            usernameEditex.setError(getString(R.string.username_error));
        }
        else if (TextUtils.isEmpty(password))
           passwordEditex.setError(getString(R.string.password_error));
        else {
            Intent Login = new Intent ( this, ProfileActivity.class);
            startActivity(Login);
        }

    }


    public void profile(View view){
        Intent profile= new Intent(this,ProfileActivity.class);
        startActivity(profile);
    }

    public void oncancel(View view) {
        usernameEditex.setText("");
        passwordEditex.setText("");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
