package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import Interactors.GameFirebaseInteractor;
import Interactors.GamesInteractorCallback;

public class MyConnectivityBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // AQUI SE A PRODUCIDO UN CAMBIO DE CONECTIVIDAD.
        //con este parametros sirve para comprobar si esta conectado a internet o no
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        Log.d("MyConnectivityReceiver", "Connectivity changed:" + isConnected);

    }
}

