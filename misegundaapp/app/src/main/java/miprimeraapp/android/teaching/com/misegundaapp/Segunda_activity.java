package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Segunda_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_activity);
        Intent segunda = getIntent();
        String paco = segunda.getStringExtra("Paco");//"VALOR DE PACO"
        int dinero= getIntent().getIntExtra("dinero", 0); //200
        Log.d("Segunda_activity", "El extra 'Paco' vale" + paco);
        Log.d("Segunda_activity", "El extra 'dinero' vale" + dinero);

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
        Log.d("MainActivity", "onRestart gato");
    }

    public void onClick(View view){
        
        Intent google = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com"));
        startActivity(google);
    }
    public void llamarme(View view){
        Intent llamar = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:1234"));
        startActivity(llamar);
    }




}
