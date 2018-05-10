package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");
      //  Toolbar myToolbar = findViewById(R.id.toolbar);

        //getSupportActianbar(.setTitle(R.string email)
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onStart gato");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume gato");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause gato");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop gato");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy gato");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","onRestart gato");
    }

    public void onClick(View view){
        Intent segunda= new Intent(this,Segunda_activity.class);
        segunda.putExtra("Paco","VALOR DE PACO");
        segunda.putExtra("manolo", "300");
        startActivity(segunda);
    }
    public void tercera(View view){
        Intent tercera= new Intent(this,tercera.class);
        startActivity(tercera);
    }
    public void profile(View view){
        Intent profile= new Intent(this,ProfileActivity.class);
        startActivity(profile);
    }
    public void login(View view){
        Intent login= new Intent(this,loginprofile.class);
        startActivity(login);
    }
    public void fondos(View view){
        Intent fondos= new Intent(this,Gallery.class);
        startActivity(fondos);
    }
    public void lista(View view){
        Intent lista= new Intent(this,lista.class);
        startActivity(lista);
    }

}
