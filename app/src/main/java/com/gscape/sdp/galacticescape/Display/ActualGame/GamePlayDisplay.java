package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.GravitationCalculator;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePlayDisplay extends AppCompatActivity {

    private RelativeLayout contentDisplay;
    private ArrayList<SpaceObject> spaceObjects;
    private Switch spawnMoveSwitch;
    private Vector screenLocation;

    private AsyncSimulation spaceSim;
    private Random rand;

    private int contentDisplayHeight;
    private float originX, originY;

    private View.OnTouchListener contentDisplayListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            final float touchX = motionEvent.getRawX();
            final float touchY = motionEvent.getRawY();

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    originX = (float)screenLocation.getX() + touchX;
                    originY = (float)screenLocation.getY() + (contentDisplayHeight - touchY);
                    if (!spawnMoveSwitch.isChecked())  {
                        double mass = rand.nextInt(9801) + 200;
                        double radius = mass / 10000 * 150;
                        PhysicsObject aPhysObject = new PhysicsObject(mass, radius,
                                Vector.make2D(originX, originY),
                                Vector.make2D(0, 0),
                                Vector.make2D(0, 0));
                        SpaceObject aSpaceObject = new SpaceObject(getApplicationContext(), aPhysObject);
                        aSpaceObject.setScreenLocation(screenLocation);
                        contentDisplay.addView(aSpaceObject.getSpaceView());
                        if (spaceObjects.isEmpty()) {
                            spaceObjects.add(aSpaceObject);
                            spaceSim.execute();
                        } else spaceObjects.add(aSpaceObject);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (spawnMoveSwitch.isChecked()) {
                        double newScreenX = originX - touchX;
                        double newScreenY = originY - (contentDisplayHeight - touchY);
                        screenLocation = Vector.make2D(newScreenX, newScreenY);
                        if (!spaceObjects.isEmpty()) {
                            for (SpaceObject currentObject : spaceObjects) {
                                currentObject.setScreenLocation(screenLocation);
                            }
                        }
                    }
                    break;
            }
            contentDisplay.invalidate();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_display);

        contentDisplay = findViewById(R.id.space_objects_display);
        contentDisplay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        contentDisplay.setOnTouchListener(contentDisplayListener);

        contentDisplayHeight = 0;
        final ViewTreeObserver observer = contentDisplay.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (contentDisplayHeight == 0)
                    contentDisplayHeight = contentDisplay.getHeight();
                else contentDisplay.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        spawnMoveSwitch = findViewById(R.id.spawn_move_switch);

        screenLocation = Vector.make2D(0, 0);

        spaceObjects = new ArrayList<>(10);

        spaceSim = new AsyncSimulation();
        rand = new Random();
    }

    private class AsyncSimulation extends AsyncTask<Void, Void, Void> {

        private GravitationCalculator mySimulator;

        @Override
        protected void onPreExecute() {
            mySimulator = new GravitationCalculator(0.667343);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("Something", "asdasd");
            while (!spaceObjects.isEmpty()) {
                Iterator<SpaceObject> spaceObjectIterator = spaceObjects.iterator();
                while (spaceObjectIterator.hasNext()) {
                    SpaceObject currentObject = spaceObjectIterator.next();
                    PhysicsObject currentPhys = currentObject.getPhysicsObject();
                    if (currentPhys.getLocation().minus(screenLocation).getMagnitude() > 5000) {
                        spaceObjectIterator.remove();
                    }
                }
                PhysicsObject[] arrPhys = new PhysicsObject[spaceObjects.size()];
                for (int i = 0; i < spaceObjects.size(); i++) {
                    SpaceObject currentObject = spaceObjects.get(i);
                    arrPhys[i] = currentObject.getPhysicsObject();
                }
                publishProgress();
                try {
                    mySimulator.simulate(arrPhys);
                    mySimulator.update(arrPhys);
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Log.i("Error", e.getMessage());
                } catch (IllegalArgumentException e) {}
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            for (SpaceObject currentObject : spaceObjects) {
                currentObject.setScreenLocation(screenLocation);
            }
            contentDisplay.invalidate();
        }
    }
}
