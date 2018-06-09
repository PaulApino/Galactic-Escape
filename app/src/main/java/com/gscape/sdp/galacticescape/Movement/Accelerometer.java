package com.gscape.sdp.galacticescape.Movement;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;
/**
 * Set the vector values used in the game to the information provided by the sensor.
 * @author Michelle Extross
 */

public class Accelerometer implements SensorEventListener {

    private double x, y; //3D vector values
    private Sensor sensor; //accelerometer sensor
    private SensorManager sensorManager; //sensor manager to register the sensor

    public Accelerometer(Context context) {
        //Initialise the Sensor Manager
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        //Initialise the Accelerometer Sensor
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register the Accelerometer sensor
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //gets a new vector based on values provided by the sensor
    public Vector getVectorFromSensor()
    {
       return Vector.make2D(y,-(x - 3.5));
    }

    /*
    Sets the vector values to the information provided by the sensor.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
    }
    /*
    Method of SensorEventListener interface. Is redundant and will not be implemented.
    */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Will not be used
    }
}