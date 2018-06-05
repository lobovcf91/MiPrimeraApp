package miprimeraapp.android.teaching.com.misegundaapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;




public class MediaPlayerService extends Service {

    private MediaPlayer myMediaplayer;
    private boolean isPrepared = false;

    public MediaPlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myMediaplayer = MediaPlayer.create(this, R.raw.cancion);
        myMediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
                    public void onCompletion (MediaPlayer mp) {
                stopSelf();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (myMediaplayer.isPlaying()) {
             myMediaplayer.pause();
        }else {
            myMediaplayer.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myMediaplayer.release();
        myMediaplayer = null;

    }



}
