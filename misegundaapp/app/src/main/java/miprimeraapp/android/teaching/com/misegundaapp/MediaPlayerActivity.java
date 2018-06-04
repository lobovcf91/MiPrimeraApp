package miprimeraapp.android.teaching.com.misegundaapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MediaPlayerActivity extends AppCompatActivity {

    private MediaPlayer myMediaplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediaplayeractivity); //con esto llamo los xml
        myMediaplayer = MediaPlayer.create(this, R.raw.cancion);
        myMediaplayer.start();


    }
    protected void onStart() {
        super.onStart();
    }


    protected void onStop() {
        super.onStop();
        myMediaplayer.release();
        myMediaplayer = null;
    }

    public void onPlayerplay(View view){
        myMediaplayer.start();
    }

    public void onPlayerpause(View view){
        myMediaplayer.pause();
    }





}
