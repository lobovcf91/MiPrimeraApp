package miprimeraapp.android.teaching.com.misegundaapp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }
//crear una clase llamada firebase, y heredarla conn el extendes y implementar el metodo on message
    //reciev dandole el log.d
    @Override
    public void onMessageReceived (RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        Log.d("MyfirebaseMessaging", remoteMessage.getData().toString());


        if (data.containsKey("show_notification")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationForOreo();
            } else {
                createNotificationForLowerThanOreo();
            }
        }
    }

    private void createNotificationForLowerThanOreo() {
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("PRIVET MIR")
                .setContentText("ZAVTRA es Mañana en ruso");

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build());
    }

    @TargetApi(26)
    private void createNotificationForOreo() {
        NotificationChannel channel = new NotificationChannel("ID", "name", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Notification.Builder builder = new Notification
                .Builder(this, "ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("PRIVET MIR")
                .setContentText("ZAVTRA es mañana en ruso");

        notificationManager.notify(1, builder.build());
    }
}
