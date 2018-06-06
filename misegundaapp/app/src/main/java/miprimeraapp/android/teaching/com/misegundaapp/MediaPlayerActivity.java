package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.net.URL;


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
    private VideoView myVideoView;
    private ProgressBar counterProgressBar;

// String videoURL = "http://img-9gag-fun.9cache.com/photo/aBxGoNN_460sv.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.mediaplayeractivity); //con esto llamo los xml
  //      myMediaplayer = MediaPlayer.create(this, R.raw.cancion);
 //       myMediaplayer.start();
    audioprogressbar = findViewById(R.id.audioprogressbar);
    webvideo = findViewById(R.id.videoView);
    // video de mi pc y poniendo enlace desde fuera
    webvideo.setVideoURI(Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.video));
    webvideo.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(webvideo);
        webvideo.setMediaController(mediaController);

//        new ContadorAsynctask().execute();

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


    //crear clase Aynctask

    private class ContadorAsynctask extends AsyncTask<URL, Integer, Integer> {

        Integer i = 0;
// contador de 100
        @Override
        protected Integer doInBackground(URL... urls) {
            int count;
            for(count = 0; count <= 100; count++) {
                publishProgress(count);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 100;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//          counterProgressBar.setMax(100);
        }

        @Override
            protected void onProgressUpdate (Integer...values){
                super.onProgressUpdate(values);
            counterProgressBar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute (Integer integer){
                super.onPostExecute(integer);
                Log.d("AsyncTask", "onPostExecute: " + integer.toString());
            }
        }
    }




