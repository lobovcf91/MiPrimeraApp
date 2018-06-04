package miprimeraapp.android.teaching.com.misegundaapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import Interactors.GameFirebaseInteractor;
import Interactors.GamesInteractor;
import Interactors.GamesInteractorCallback;

public class lista extends AppCompatActivity {
    private Myadapter myadapter;
    private GameFirebaseInteractor gameFirebaseInteractor;
    private GridView gridView;
    private MyConnectivityBroadcastReceiver myConnectivityBroadcastReceiver;

    // String[] juegos = {"Spyro", "Malaga", "rachet", "Valencia", "Fornite"};
    //   int[] gameIcons = {R.drawable.spyro, R.drawable.malagaicono, R.drawable.rachet, R.drawable.valencia, R.drawable.forniteicono};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

//PARA COMPROBAR SI TENEMOS PERMISOS DE LOCALIZACION SI NO EN EL ELSE TENEMOS QUE PEDIRSELO.
        int permissionCheck = ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //TENEMOS PERMISOS
            obtenerUbicacion();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION} ,100);
            //no tenemos permisos
        }


        //PARA DETECTAR SI TNEEMOS CONEXION O NO.
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            gameFirebaseInteractor = new GameFirebaseInteractor();
            gameFirebaseInteractor.getGames(new GamesInteractorCallback() {
                @Override
                //con esto pedimos los juegos al interactor y te daras cuenta en ongamesaviable que es donde se ejecutara
                public void OnGamesAviable() {
                    findViewById(R.id.progressBar3).setVisibility(View.GONE);
                    //aqui , Gamesferirabseinteracter list ya tiene los juegos.
                    myadapter = new Myadapter();
                    gridView.setAdapter(new Myadapter());
                }
            });
        } else {
            findViewById(R.id.progressBar3).setVisibility(View.GONE);
            Toast.makeText(this, "no tienes internet, PAGA", Toast.LENGTH_LONG).show();
        }
//escucha cmabios de conectividad
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        myConnectivityBroadcastReceiver = new MyConnectivityBroadcastReceiver();
        registerReceiver(new MyConnectivityBroadcastReceiver(), intentFilter);


        Toolbar myToolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(myToolbar);

        getSupportActionBar().

                setDisplayHomeAsUpEnabled(true);


        gridView = findViewById(R.id.lista);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(lista.this, "Seleccionada posición " + position,
                        Toast.LENGTH_LONG).show();
                //abrir pantalla de detalle
                Intent intent = new Intent(lista.this, GameDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }

        });
    }


    private class Myadapter extends BaseAdapter {


        @Override
        public int getCount() {

            return gameFirebaseInteractor.getGames().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.plantillaparalista, parent, false);

            ImageView icon = rowView.findViewById(R.id.imagen);
            Glide.with(lista.this).load(
                    gameFirebaseInteractor.getGames().get(position).getIcon()).into(icon);
            //     GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
            //     icon.setImageResource(gameIcons[position]);

            TextView textView = rowView.findViewById(R.id.texto);
            textView.setText(gameFirebaseInteractor.getGames().get(position).getName());

            if (position == 0) {
                textView.setAllCaps(true);
                textView.setTextColor(getColor(R.color.crash));
                textView.setTextSize(10);
            }
            if (position == 1) {
                textView.setTextColor(getColor(R.color.crash));
                textView.setAllCaps(true);
                textView.setTextSize(10);
            }
            if (position == 2) {
                textView.setTextSize(10);
                textView.setTextColor(getColor(R.color.crash));
                textView.setAllCaps(true);
            }
            if (position == 3) {
                textView.setTextSize(10);
                textView.setTextColor(getColor(R.color.crash));
                textView.setAllCaps(true);
            }
            if (position == 4) {
                textView.setTextSize(10);
                textView.setTextColor(getColor(R.color.crash));
                textView.setAllCaps(true);
            }

            return rowView;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(myConnectivityBroadcastReceiver);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent login = new Intent(this, loginprofile.class);
        startActivity(login);
        return super.onOptionsItemSelected(item);
    }


//para que muestre el dialogo de petiicion de permission

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
       if (requestCode == 100);{
           if (grantResults.length > 0
                   && grantResults[0] == PackageManager.PERMISSION_GRANTED)
           {
               //obetener permisos
               obtenerUbicacion();
               // el usuario acepta los permisos
           } else {
               //el usuario deniega los permisos
           }
        }
    }
    @SuppressLint("MissingPermission")
    private void obtenerUbicacion()  {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        //2-crear el listener
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location Changed", "Location Changed"+ location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //3- escuchar las localizaciones
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,0, 0, listener);
    }
}


