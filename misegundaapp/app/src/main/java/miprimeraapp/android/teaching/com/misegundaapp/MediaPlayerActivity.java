package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;


public class MediaPlayerActivity extends AppCompatActivity {

 //  private MediaPlayer myMediaplayer;
//   private boolean isPrepared = false;
    private ProgressBar audioprogressbar;
    private Runnable audioProgressupdaterunnable = new Runnable() {
        @Override
        public void run() {
     //       audioprogressbar.setProgress(myMediaplayer.getCurrentPosition());
        }
    };
    private Handler audioprogressbarhandler = new Handler();
    private VideoView webvideo;

// String videoURL = "http://img-9gag-fun.9cache.com/photo/aBxGoNN_460sv.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.mediaplayeractivity); //con esto llamo los xml
  //      myMediaplayer = MediaPlayer.create(this, R.raw.cancion);
 //       myMediaplayer.start();
    audioprogressbar = findViewById(R.id.audioprogressbar);
    webvideo = findViewById(R.id.videoView);
    webvideo.setVideoURI(Uri.parse("http://img-9gag-fun.9cache.com/photo/aBxGoNN_460sv.mp4"));
    webvideo.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(webvideo);
        webvideo.setMediaController(mediaController);


    }
    protected void onStart() {
        super.onStart();
    }


    protected void onStop() {
        super.onStop();
   //     audioprogressbarhandler.removeCallbacks(audioProgressupdaterunnable);
   //     myMediaplayer.release();
    //    myMediaplayer = null;
    }

    public void onPlayerplay(View view){
        Intent myServiceIntent = new Intent(this, MediaPlayerService.class);
        startService(myServiceIntent);
    }

    public void onPlayerpause(View view){
        Intent myServiceIntent = new Intent(this, MediaPlayerService.class);
        stopService(myServiceIntent);

    }





}
