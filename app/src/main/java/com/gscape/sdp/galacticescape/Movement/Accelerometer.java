package com.gscape.sdp.galacticescape.Movement;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;


public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private double x, y, z; //3D vector values
    private Sensor sensor; //accelerometer sensor
    private SensorManager sensorManager; //sensor manager to register the sensor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        //Initialise the Sensor Manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Initialise the Accelerometer Sensor
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register the Accelerometer sensor
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //gets a new vector based on values provided by the sensor
    public Vector getVectorFromSensor()
    {
       return Vector.make3D(x,y,z);
    }

    /*
    Sets the vector values to the information provided by the sensor.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

    }
/*
Method of SensorEventListener interface. Is redundant and will not be implemented.
 */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Will not be used
    }


}