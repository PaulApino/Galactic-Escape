package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldBackgroundRunnable;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarForge;
import com.gscape.sdp.galacticescape.Engine.Objects.ObstacleManager;
import com.gscape.sdp.galacticescape.Engine.Objects.Player;
import com.gscape.sdp.galacticescape.Engine.Physics.GravitationCalculator;
import com.gscape.sdp.galacticescape.Engine.Physics.SimulationRunnable;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.Movement.Accelerometer;
import com.gscape.sdp.galacticescape.Movement.TiltAcceleration;
import com.gscape.sdp.galacticescape.Movement.TiltMovementRunnable;
import com.gscape.sdp.galacticescape.R;
import com.gscape.sdp.galacticescape.Submenu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GamePlayScreen extends Activity {

    private GravitationCalculator gravitationCalculator;
    private ObstacleManager obstacleManager;
    private StarForge starForge;
    private TiltAcceleration tiltAcceleration;

    private ObjectViewPair player;
    private SimulationState simulationState;
    private SimulationContents simulationContents;
    private ScreenValues screenValues;

    private SimulationRunnable simulationRunnable;
    private SimulationDisplayRunnable simulationDisplayRunnable;
    private StarFieldBackgroundRunnable starFieldBackgroundRunnable;
    private TiltMovementRunnable tiltMovementRunnable;

    private RelativeLayout rootDisplay;
    private RelativeLayout objectContainer;
    private RelativeLayout starFieldContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_play_screen);

        initSetup();

    }

    private void initSetup() {
        rootDisplay = findViewById(R.id.game_play_screen_container);

        starFieldContainer = new RelativeLayout(getApplicationContext());
        RelativeLayout.LayoutParams starContainerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        starContainerParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        starContainerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        starContainerParams.topMargin = Integer.MIN_VALUE;
        starContainerParams.bottomMargin = Integer.MIN_VALUE;
        starContainerParams.leftMargin = Integer.MIN_VALUE;
        starContainerParams.rightMargin = Integer.MIN_VALUE;
        starFieldContainer.setLayoutParams(starContainerParams);
        rootDisplay.addView(starFieldContainer);

        objectContainer = new RelativeLayout(getApplicationContext());
        RelativeLayout.LayoutParams simulationParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        objectContainer.setLayoutParams(simulationParams);
        rootDisplay.addView(objectContainer);

        initGameState();

        initRunnableSetup();

        runRunnables();
    }

    private void initGameState() {
        simulationState = new SimulationState();

        starForge = new StarForge(1234);
        gravitationCalculator = new GravitationCalculator(0.016123);
        obstacleManager = new ObstacleManager();

        makePlayer();
        simulationContents = new SimulationContents(player, getObstacles());
        tiltAcceleration = new TiltAcceleration(player.getPhysicsObject(), new Accelerometer(getApplicationContext()));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenValues = new ScreenValues(Vector.make2D(displayMetrics.widthPixels, displayMetrics.heightPixels), Vector.make2D(0,0), player.getPhysicsObject().getLocation());

        Iterator<ObjectViewPair> initPairs = simulationContents.getObjectViewPairs().iterator();
        while (initPairs.hasNext()) {
            ImageView currentObjectView = initPairs.next().getImageView();
            objectContainer.addView(currentObjectView);
        }
    }

    private void initRunnableSetup() {
        simulationRunnable = new SimulationRunnable(simulationContents, gravitationCalculator, simulationState);
        simulationDisplayRunnable = new SimulationDisplayRunnable(objectContainer, screenValues, simulationContents, simulationState);
        starFieldBackgroundRunnable = new StarFieldBackgroundRunnable(getApplicationContext(), starFieldContainer, starForge, screenValues, simulationState);
        tiltMovementRunnable = new TiltMovementRunnable(tiltAcceleration, player.getPhysicsObject(), simulationState);

        simulationState.setRunning();
    }

    private void makePlayer () {
        Player thePlayer = new Player(100, 70,
                Vector.make2D(0, 0),
                Vector.make2D(0, 1),
                Vector.make2D(0, 0));
        player = ObjectViewPair.getObjectValuePair(getApplicationContext(), thePlayer);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        player.getImageView().setLayoutParams(layoutParams);
    }

    private ArrayList<ObjectViewPair> getObstacles () {
        ArrayList<ObjectViewPair> objectViewPairs = new ArrayList<>(30);
        for (int i = 0; i < 15; i++) {
            objectViewPairs.add(ObjectViewPair.getObjectValuePair(getApplicationContext(), obstacleManager.generateObstacle((Player)player.getPhysicsObject())));
        }
        return objectViewPairs;
    }

    private void runRunnables () {
        Thread simulationThread = new Thread(simulationRunnable);
        Thread simulationDisplayThread = new Thread(simulationDisplayRunnable);
        Thread starFieldThread = new Thread(starFieldBackgroundRunnable);
        Thread tiltMoveThread = new Thread(tiltMovementRunnable);

        simulationThread.start();
        simulationDisplayThread.start();
        starFieldThread.start();
        tiltMoveThread.start();
    }

//    /**
//     * Pause Menu created when back button is pushed during game play.
//     * method created by Aseni
//     */
//    @Override
//    public void onBackPressed() {
//        //Execute your code here
//        finish(); //closes intent so intent isn't running in the background
//        Intent subMenuIntent = new Intent(this, Submenu.class);
//        startActivity(subMenuIntent); //takes you to the pause menu intent.
//    }
}
