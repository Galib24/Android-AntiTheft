package com.example.accelerometer_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    
    private SensorManager sensorManager;
    Sensor accerlerometer;
    TextView x,y,z,a,b,c;
    Button btn,btn2,btn4;
    String xc,yc,zc;
    boolean clicked = false;
    Vibrator vibrator;
    double o,t,th;
    double r1,r2,r3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accerlerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accerlerometer,SensorManager.SENSOR_DELAY_NORMAL);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        x = (TextView)findViewById(R.id.textViewX);
        y = (TextView)findViewById(R.id.textViewY);
        z = (TextView)findViewById(R.id.textViewZ);

        a = (TextView)findViewById(R.id.textViewXc);
        b = (TextView)findViewById(R.id.textViewYc);
        c = (TextView)findViewById(R.id.textViewZc);


        btn = (Button)findViewById((R.id.button));
        btn.setOnClickListener(this);
        btn2 = (Button)findViewById((R.id.button3));
        btn2.setOnClickListener(this);
        btn4 = (Button)findViewById((R.id.button4));
        btn4.setOnClickListener(this);

}



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button) {
            // Toast.makeText(getApplicationContext(), "Hoise!", Toast.LENGTH_SHORT).show();
            xc = x.getText().toString();
            o = Double.valueOf(x.getText().toString());
            yc = y.getText().toString();
            t = Double.valueOf(y.getText().toString());
            zc = z.getText().toString();
            th = Double.valueOf(z.getText().toString());
            clicked = true;
        }
       else if(view.getId() == R.id.button3) {
            // Toast.makeText(getApplicationContext(), "Hoise!", Toast.LENGTH_SHORT).show();
            a.setText(xc);
            b.setText(yc);
            c.setText(zc);
        }
       if(view.getId() == R.id.button4)
       {
           vibrator.cancel();
           clicked = false;
       }
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // Log.d(TAG, "onSensorChanged: X = "+sensorEvent.values[0]+"Y = "+sensorEvent.values[1]+"Z = "+sensorEvent.values[2]);

        //String xx =
        x.setText( String.valueOf(( sensorEvent.values[0])));
        y.setText( String.valueOf(( sensorEvent.values[1])));
        z.setText( String.valueOf(( sensorEvent.values[2])));

       // if((!((String.valueOf( sensorEvent.values[0]))).equals(a.getText().toString())))// !String.valueOf(( sensorEvent.values[1])).equals(yc) && !String.valueOf(( sensorEvent.values[2])).equals(zc) )
       r1 = (int)sensorEvent.values[0];
        r2 = (int)sensorEvent.values[1];
        r3 = (int)sensorEvent.values[2];

        if(clicked) {
            if ((Math.abs(r1 - o) >= 1) || (Math.abs(r2 - t) >= 1) || (Math.abs(r3 - th) >= 1)) {
                // Toast.makeText(getApplicationContext(), "Error Hoise!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "" + yc, Toast.LENGTH_LONG).show();
                vibrator.vibrate(7000);
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                // return;


            }
        }
        else
        {
            vibrator.cancel();
        }



    }
}
