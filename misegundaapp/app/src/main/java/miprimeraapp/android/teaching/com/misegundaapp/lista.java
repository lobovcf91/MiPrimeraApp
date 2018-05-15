package miprimeraapp.android.teaching.com.misegundaapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import Interactors.GamesInteractor;

public class lista extends AppCompatActivity {

    String[] juegos = {"Spyro", "Malaga", "rachet", "Valencia", "Fornite"};
    int[] gameIcons = {R.drawable.spyro, R.drawable.malagaicono, R.drawable.rachet, R.drawable.valencia, R.drawable.forniteicono};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        GridView listView = findViewById(R.id.lista);
        listView.setAdapter(new Myadapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(lista.this, "Seleccionada posición " + position,
                        Toast.LENGTH_LONG).show();
                //abrir pantalla de detalle
                Intent intent = new Intent (lista.this, GameDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });


    }


    private class Myadapter extends BaseAdapter {


        @Override
        public int getCount() {
            return juegos.length;
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
            icon.setImageResource(gameIcons[position]);

            TextView textView = rowView.findViewById(R.id.texto);
            textView.setText(juegos[position]);

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


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent login = new Intent(this,loginprofile.class);
        startActivity(login);
        return super.onOptionsItemSelected(item);
    }


}
