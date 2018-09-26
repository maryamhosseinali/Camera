package com.example.fujitsu.cameratest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class MainActivity extends Activity implements SensorEventListener {

    private Camera cam;
    private campreview cp;

    Sensor accelerometer;
    SensorManager sm;

    ImageView kadre1 , Kadre2 , btn , ofoq , amod;
    FrameLayout fl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        kadre1 = findViewById(R.id.kadrt1);
        Kadre2 =  findViewById(R.id.kadrt2);
        btn =  findViewById(R.id.btn);
        fl =  findViewById(R.id.fl1);
        ofoq =  findViewById(R.id.ofoqi);
        amod = findViewById(R.id.amodi);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        try {
            cam = Camera.open();

        } catch (Exception e) {

        }

        cp = new campreview(this, cam);
        if(cam != null){
            sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }

        fl.addView(cp);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cam.takePicture(null, null, mpic);
                String path = Environment.getExternalStorageDirectory().getPath() + "/0mypic/" + System.currentTimeMillis() + ".jpg";
                Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();


            }
        });

    }

    private PictureCallback mpic = new PictureCallback() {


        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File f1 = new File(Environment.getExternalStorageDirectory().getPath() + "/0mypic");
            if (!f1.exists()) {

                f1.mkdir();
            }
            File f2 = new File(Environment.getExternalStorageDirectory().getPath() + "/0mypic/" + System.currentTimeMillis() + ".jpg");
            try {

                FileOutputStream fos = new FileOutputStream(f2);
                fos.write(data);
                fos.close();
            } catch (IOException e) {
            }
        }
    };
    public void amod (View view){

            kadre1.setBackgroundResource(R.drawable.sabz_amod);
            Kadre2.setBackgroundResource(R.drawable.qermez_amod);
    }
    public void ofoq(View view){
        kadre1.setBackgroundResource(R.drawable.sabz_ofoq);
        Kadre2.setBackgroundResource(R.drawable.qemez_ofoq);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] <= 9 && sensorEvent.values[0] >= 8 ) {
            Vibrator vibe = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);
            kadre1.setVisibility(View.VISIBLE);
            Kadre2.setVisibility(View.INVISIBLE);
            long milliseconds = 50;
            vibe.vibrate(milliseconds);
        }else {
            kadre1.setVisibility(View.INVISIBLE);
            Kadre2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}



