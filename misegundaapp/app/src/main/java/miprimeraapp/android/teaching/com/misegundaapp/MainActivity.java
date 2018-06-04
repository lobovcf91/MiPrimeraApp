package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Collections;

import model.GameModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference ("games");
    //    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
    //        @Override
   //         public void onDataChange(DataSnapshot dataSnapshot) {
    //            for (dataSnapshot modoJuego: dataSnapshot.getChildren()){
   //                 Log.d("MainActivity", "nombre del juego");
    //            }

   //         }

  //       @Override
   //        public void onCancelled(DatabaseError databaseError) {

   //         }
    //    });




            String refreshedToken =
                    FirebaseInstanceId.getInstance().getToken();
            Log.d("InstanceIdService", "refreshed token:" + refreshedToken);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef1 = database.getReference("device_push_token");
            myRef1.setValue(refreshedToken);



        StringRequest myStringRequest = new StringRequest(Request.Method.GET,
                "https://misegundaapp.firebaseio.com/games.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MainActivity", "Response is: " + response);
                        try {
                            JSONArray myArray = new JSONArray(response);
                            for (int i = 0; i < myArray.length();
                                 i++) {
                                JSONObject object = myArray.getJSONObject(i);
                                GameModel game = new GameModel(
                                        i,
                                        object.getString("name"),
                                        object.getString("description"),
                                        object.getString("officialWebsiterUrl"),
                                        "","");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue myQueue = Volley.newRequestQueue(this);
        myQueue.add(myStringRequest);

        Log.d("MainActivity", "onCreate");

        File directorioInterno = getFilesDir();
        File directorioCache = getCacheDir();
        Log.d("ListActivity", "Interno: " + directorioInterno.getAbsolutePath());
        Log.d("ListActivity", "Cache: " + directorioCache.getAbsolutePath());


        //  Toolbar myToolbar = findViewById(R.id.toolbar);

        //getSupportActianbar(.setTitle(R.string email)

        getExternalFilesDir(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart gato");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume gato");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause gato");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop gato");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy gato");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart gato");
    }

    public void onClick(View view) {
        Intent segunda = new Intent(this, Segunda_activity.class);
        segunda.putExtra("Paco", "VALOR DE PACO");
        segunda.putExtra("manolo", "300");
        startActivity(segunda);
    }

    public void tercera(View view) {
        Intent tercera = new Intent(this, tercera.class);
        startActivity(tercera);
    }

    public void profile(View view) {
        Intent profile = new Intent(this, ProfileActivity.class);
        startActivity(profile);
    }

    public void login(View view) {
        Intent login = new Intent(this, loginprofile.class);
        startActivity(login);
    }

    public void fondos(View view) {
        Intent fondos = new Intent(this, Gallery.class);
        startActivity(fondos);
    }

    public void lista(View view) {
        Intent lista = new Intent(this, lista.class);
        startActivity(lista);
    }

    public void fragment(View view) {
        Intent fragment = new Intent(this, FragmentTestActivity.class);
        startActivity(fragment);
    }

    public void fornite(View view) {
        Intent fornite = new Intent(this, GameDetailActivity.class);
        startActivity(fornite);
    }

    public void web(View view) {
        Intent web = new Intent(this, WebViewActivity.class);
        startActivity(web);
    }
    public void recycler(View view) {
        Intent recycler = new Intent(this, RecyclerViewActivity.class);
        startActivity(recycler);
    }
    public void mussica(View view) {
        Intent mussica = new Intent(this, MediaPlayerActivity.class);
        startActivity(mussica);
    }
}
