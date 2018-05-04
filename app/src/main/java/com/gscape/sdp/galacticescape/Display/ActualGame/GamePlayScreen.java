package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.GravitationCalculator;
import com.gscape.sdp.galacticescape.Engine.Physics.SimulationRunnable;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GamePlayScreen extends AppCompatActivity {

    private RelativeLayout simulationDisplay;

    private SimulationState simulationState;
    private SimulationDisplayRunnable simulationDisplayer;
    private SimulationRunnable simulator;

    private SimulationContents simulationContents;

    private double contentDisplayHeight = 0;
    private double contentDisplayWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen);

        simulationDisplay = findViewById(R.id.game_play_screen_container);

        simulationDisplay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        init();

        Thread backSim = new Thread(simulator);
        Thread frontSim = new Thread(simulationDisplayer);

        backSim.start();
        frontSim.start();
    }

    private void init() {
        Random rand = new Random();
        ArrayList<PhysicsObject> physicsObjects = new ArrayList<>(10);
        ArrayList<ImageView> imageObjects = new ArrayList<>(10);

        physicsObjects.add(new PhysicsObject(
                        10,
                        75,
                        Vector.make2D(0, 0),
                        Vector.make2D(0, 0),
                        Vector.make2D(0, 0)));


        for (int i = 1; i < 20; i++) {
            double mass = rand.nextInt(39000) + 1000;
            physicsObjects.add(new PhysicsObject(
                            mass,
                            mass / 40000 * 170,
                            Vector.make2D(rand.nextInt(1100) + 300, rand.nextInt(1100) + 500),
                            Vector.make2D(-0.2, -0.2),
                            Vector.make2D(0, 0)));
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        contentDisplayHeight = displayMetrics.heightPixels;
        contentDisplayWidth = displayMetrics.widthPixels;

        Log.i("Display", contentDisplayHeight + ", " + contentDisplayWidth);

        for (PhysicsObject currentObject : physicsObjects) {
            int physicsObjectDiameter = (int) currentObject.getCollisionRadius() * 2;

            Bitmap mBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.temp_space_object);
            Bitmap mResizedBitmap = Bitmap.createScaledBitmap(mBitmap, physicsObjectDiameter, physicsObjectDiameter, true);

            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(mResizedBitmap);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.topMargin = Integer.MIN_VALUE;
            layoutParams.bottomMargin = Integer.MIN_VALUE;
            layoutParams.leftMargin = Integer.MIN_VALUE;
            layoutParams.rightMargin = Integer.MIN_VALUE;
            imageView.setLayoutParams(layoutParams);

            imageObjects.add(imageView);
        }

        ImageView player = imageObjects.get(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        player.setLayoutParams(layoutParams);

        for (ImageView anImage : imageObjects) {
            simulationDisplay.addView(anImage);
        }

        simulationContents = new SimulationContents(physicsObjects, imageObjects);

        simulationState = new SimulationState();
        simulationState.setRunning();

        GravitationCalculator calculator = new GravitationCalculator(0.0064543);

        simulator = new SimulationRunnable(simulationContents, calculator, simulationState);
        simulationDisplayer = new SimulationDisplayRunnable(simulationDisplay, Vector.make2D(contentDisplayWidth, contentDisplayHeight), simulationContents, simulationState);
    }
}
