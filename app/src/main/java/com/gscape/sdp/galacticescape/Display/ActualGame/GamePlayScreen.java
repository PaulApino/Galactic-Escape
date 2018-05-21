package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldBackgroundRunnable;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunkView;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarForge;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.GravitationCalculator;
import com.gscape.sdp.galacticescape.Engine.Physics.SimulationRunnable;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.Movement.Accelerometer;
import com.gscape.sdp.galacticescape.Movement.TiltAcceleration;
import com.gscape.sdp.galacticescape.Movement.TiltMovementRunnable;
import com.gscape.sdp.galacticescape.R;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GamePlayScreen extends Activity {

    private RelativeLayout mainDisplay;
    private TableLayout starFieldBackgroundDisplay;
    private RelativeLayout simulationDisplay;

    private SimulationState simulationState;
    private SimulationDisplayRunnable simulationDisplayer;
    private SimulationRunnable simulator;
    private StarFieldBackgroundRunnable starFieldBackgroundRunnable;
    private TiltMovementRunnable tiltMovement;

    private SimulationContents simulationContents;
    private ScreenValues screenValues;
    private StarForge starForge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_play_screen);

        mainDisplay = findViewById(R.id.game_play_screen_container);

        starFieldBackgroundDisplay = new TableLayout(getApplication());
        RelativeLayout.LayoutParams starParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        starParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        starParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        starParams.topMargin = Integer.MIN_VALUE;
        starParams.bottomMargin = Integer.MIN_VALUE;
        starParams.leftMargin = Integer.MIN_VALUE;
        starParams.rightMargin = Integer.MIN_VALUE;
        starFieldBackgroundDisplay.setLayoutParams(starParams);
        mainDisplay.addView(starFieldBackgroundDisplay);

        simulationDisplay = new RelativeLayout(getApplication());
        RelativeLayout.LayoutParams simulationParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        simulationDisplay.setLayoutParams(simulationParams);
        mainDisplay.addView(simulationDisplay);

        init();

        Thread backSim = new Thread(simulator);
        Thread frontSim = new Thread(simulationDisplayer);
        Thread starSim = new Thread(starFieldBackgroundRunnable);
//        Thread tiltSimulation = new Thread(tiltMovement);

        backSim.start();
        frontSim.start();
        starSim.start();
//        tiltSimulation.start();
    }

    private void init() {
        ArrayList<PhysicsObject> physicsObjects = new ArrayList<>(10);
        ArrayList<ImageView> imageObjects = new ArrayList<>(10);

        PhysicsObject physA = new PhysicsObject(158845.97, 90.378,
                Vector.make2D(960, 500),
                Vector.make2D( 0, 0),
                Vector.make2D(0, 0));
        PhysicsObject physB = new PhysicsObject(1539.47, 55.378,
                Vector.make2D(500, 840),
                Vector.make2D(1.2, 0.2),
                Vector.make2D(0, 0));
        PhysicsObject physC = new PhysicsObject(1530.35, 40.973,
                Vector.make2D(1100, 340),
                Vector.make2D(-1.45, -0.7),
                Vector.make2D(0, 0));

        physicsObjects.add(physC);
        physicsObjects.add(physA);
        physicsObjects.add(physB);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenValues = new ScreenValues(Vector.make2D(displayMetrics.widthPixels, displayMetrics.heightPixels), Vector.make2D(0,0), physC.getLocation());

        starForge = new StarForge(9237);

        for (int i = 0; i < physicsObjects.size(); i++) {
            int physicsObjectDiameter = (int) physicsObjects.get(i).getCollisionRadius() * 2;

            Bitmap mBitmap = null;

            switch (i) {
                case 0 :
                    mBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ship);
                    break;
                case 1 :
                    mBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.jupiter);
                    break;
                case 2 :
                    mBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.earth);
                    break;
            }
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

        GravitationCalculator calculator = new GravitationCalculator(0.0067139);

        simulator = new SimulationRunnable(simulationContents, calculator, simulationState);
        simulationDisplayer = new SimulationDisplayRunnable(simulationDisplay, screenValues, simulationContents, simulationState);

        starFieldBackgroundRunnable = new StarFieldBackgroundRunnable(getApplication(), starFieldBackgroundDisplay, starForge, screenValues, simulationState);

        StarFieldChunkView[][] starFieldChunkViews = new StarFieldChunkView[starFieldBackgroundRunnable.getStarFieldBackground().getMaxMatrixRow()][starFieldBackgroundRunnable.getStarFieldBackground().getMaxMatrixColumn()];
        TableRow[] rowViews = new TableRow[starFieldChunkViews.length];

        for (int i = 0; i < starFieldBackgroundRunnable.getStarFieldBackground().getMaxMatrixRow(); i++) {
            for (int j = 0; j < starFieldBackgroundRunnable.getStarFieldBackground().getMaxMatrixColumn(); j++) {
            }
        }

        tiltMovement = new TiltMovementRunnable(new TiltAcceleration(physC, new Accelerometer(getApplicationContext())), physC, simulationState);
    }
}
