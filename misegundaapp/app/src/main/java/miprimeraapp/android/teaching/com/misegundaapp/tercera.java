package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class tercera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);
    }
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
                Uri.parse("https://www.youtube.com/?hl=es&gl=ES"));
        startActivity(google);
    }
    public void segunda(View view){
        Intent tercera= new Intent(this,Segunda_activity.class);
        startActivity(tercera);
    }
}
