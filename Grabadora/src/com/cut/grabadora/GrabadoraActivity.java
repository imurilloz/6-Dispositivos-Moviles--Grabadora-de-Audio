package com.cut.grabadora;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class GrabadoraActivity extends Activity { 
private static final String LOG_TAG = "Grabadora";          
private MediaRecorder mediaRecorder;
private MediaPlayer mediaPlayer;
private Button bGrabar, bDetener, bReproducir;
private static String fichero = Environment.


getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";
   
@Override public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bGrabar = (Button) findViewById(R.id.bGrabar);
    bDetener = (Button) findViewById(R.id.bDetener);
    bReproducir = (Button) findViewById(R.id.bReproducir);
    bDetener.setEnabled(false);
    bReproducir.setEnabled(false);
 }
 
 public void grabar(View view) {
    mediaRecorder = new MediaRecorder();
    mediaRecorder.setOutputFile(fichero);
    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    mediaRecorder.setOutputFormat(MediaRecorder.
                                          OutputFormat.THREE_GPP);
    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.
                                                     AMR_NB); 
    try {
        mediaRecorder.prepare();
    } catch (IOException e) {
        Log.e(LOG_TAG, "Fallo en grabación");
    }
    mediaRecorder.start();
    
    bGrabar.setEnabled(false);
    bDetener.setEnabled(true);
    bReproducir.setEnabled(false);
  }
 
  public void detenerGrabacion(View view) {
     mediaRecorder.stop();
     mediaRecorder.release();
     bGrabar.setEnabled(true);
     bDetener.setEnabled(false);
     bReproducir.setEnabled(true);
  }
 
  public void reproducir(View view) {
     mediaPlayer = new MediaPlayer();
     try {
         mediaPlayer.setDataSource(fichero);
         mediaPlayer.prepare();
         mediaPlayer.start();
     } catch (IOException e) {
         Log.e(LOG_TAG, "Fallo en reproducción");
     }
   }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
