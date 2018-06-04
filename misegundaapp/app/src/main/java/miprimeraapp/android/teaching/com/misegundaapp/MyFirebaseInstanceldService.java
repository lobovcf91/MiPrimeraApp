package miprimeraapp.android.teaching.com.misegundaapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceldService extends FirebaseInstanceIdService {
    public MyFirebaseInstanceldService() {
    }


    public void onTokenrefresh() {

        //con esto de aqui manipulo la base de datos desde aqui y le doi los datos con esto
        //estoy haciendo la practica de firebasainstancel y para que salga hay que hacerlo
        //en la pagina principal poniendo lo mismmo, con el value consigo que se ejecute.

        String refreshedToken =
                FirebaseInstanceId.getInstance().getToken();
        Log.d("InstanceIdService", "refreshed token:" + refreshedToken);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("device_push_token");
        myRef1.setValue(refreshedToken);
    }


}
