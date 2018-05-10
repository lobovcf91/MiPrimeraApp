package miprimeraapp.android.teaching.com.misegundaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

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
        radioButton2 = findViewById(R.id.radioButton2);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //con esto se ejecuta el click de save

    public void guardar (View view){
        Log.d("ProfileActivity", "Username:" + usernameEditex.getText());
        Log.d("ProfileActivity", "Email:" + emailEditex.getText());
        Log.d("ProfileActivity", "Password:" + passwordEditex.getText());
        Log.d("ProfileActivity", "Age:" + ageEditex.getText());

//esto es para que cuando eliga masculino si no es de femenino estos son los parametros.
        if (radioButton.isChecked()){
            Log.d("ProfileActivity", "Gender:male");
        }
        else if (radioButton2.isChecked()){
            Log.d("ProfileActivity","Gender:Female");
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


}
