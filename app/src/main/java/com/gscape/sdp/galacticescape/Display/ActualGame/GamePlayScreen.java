package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.annotation.SuppressLint;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
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

//        HandlerThread simHandlerThread = new HandlerThread("TestDisplay");
//        simHandlerThread.start();

        Thread backSim = new Thread(simulator);
        backSim.start();

        Handler simHandler = new Handler();
        simHandler.postDelayed(simulationDisplayer, 500);
    }

    private void init() {
        Random rand = new Random();
        ArrayList<SpaceObject> spaceObjects = new ArrayList<>(6);

        spaceObjects.add(new SpaceObject(getApplicationContext(),
                new PhysicsObject(
                        200,
                        75,
                        Vector.make2D(0, 0),
                        Vector.make2D(0, 0),
                        Vector.make2D(0, 0))));

        for (int i = 1; i < 5; i++) {
            spaceObjects.add(new SpaceObject(getApplicationContext(),
                    new PhysicsObject(
                            rand.nextInt(4500) + 500,
                            rand.nextInt(175) + 25,
                            Vector.make2D(rand.nextInt(100) - 100, rand.nextInt(100) - 100),
                            Vector.make2D(0, 0),
                            Vector.make2D(0, 0))));
        }

        final ViewTreeObserver observer = simulationDisplay.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (contentDisplayHeight == 0 || contentDisplayWidth == 0) {
                    contentDisplayHeight = simulationDisplay.getHeight();
                    contentDisplayWidth = simulationDisplay.getWidth();
                } else simulationDisplay.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        simulationContents = new SimulationContents(spaceObjects, Vector.make2D(contentDisplayWidth, contentDisplayHeight));
        simulationContents.getPlayer().setScreenLocation(Vector.make2D());
        simulationContents.updateLocations();

        for (SpaceObject currentObject : simulationContents.getSpaceObjects()) {
            simulationDisplay.addView(currentObject.getSpaceView());
        }

        simulationState = new SimulationState();

        GravitationCalculator calculator = new GravitationCalculator(0.006987);

        simulator = new SimulationRunnable(simulationContents.getSpaceObjects(), calculator, simulationState);
        simulationDisplayer = new SimulationDisplayRunnable(simulationDisplay, simulationContents, simulationState);

        simulationState.setRunning();
    }
}
