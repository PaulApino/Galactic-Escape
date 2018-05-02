package com.gscape.sdp.galacticescape.Display.Testing;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Display.ActualGame.SpaceObject;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.GravitationCalculator;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;
import java.util.Iterator;

public class SimulationViewTest {

    private Context context;
    private RelativeLayout contentView;
    private Vector screenLocation;
    private int contentViewCentreX, contentViewCentreY;
    private boolean isRunning;

    private SpaceObject player;
    private ArrayList<SpaceObject> spaceObjects;
    private AsyncSimulation simulation;
    private GravitationCalculator gravitationCalculator;

    public SimulationViewTest(Context context, RelativeLayout contentView, int contentViewHeight, int contentViewWidth, SpaceObject player, ArrayList<SpaceObject> spaceObjects, Vector screenLocation) {
        this.context = context;
        this.contentView = contentView;
        this.contentViewCentreX = contentViewHeight / 2;
        this.contentViewCentreY = contentViewWidth / 2;
        this.player = player;
        this.spaceObjects = spaceObjects;
        this.screenLocation = screenLocation;
        init();
    }

    private void init() {
        simulation = new AsyncSimulation();
        gravitationCalculator = new GravitationCalculator(0.0066972);
        isRunning = false;


    }

    public RelativeLayout getContentView() {
        return contentView;
    }

    public SpaceObject getPlayer() {
        return player;
    }

    public ArrayList<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning () {
        try {
            simulation.execute();
            isRunning = true;
        } catch (IllegalStateException e) {}
    }

    private class AsyncSimulation extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (isRunning && !player.getPhysicsObject().isCollided()) {
                Iterator<SpaceObject> iterator = spaceObjects.iterator();
                while (iterator.hasNext()) {
                    SpaceObject currentObject = iterator.next();
                    if (currentObject.getPhysicsObject().isRemoved()) {
                        iterator.remove();
                    }
                }

                PhysicsObject[] physicsObjects = new PhysicsObject[spaceObjects.size() + 1];
                physicsObjects[0] = player.getPhysicsObject();
                int index = 1;
                iterator = spaceObjects.iterator();
                while (iterator.hasNext()) {
                    physicsObjects[index] = iterator.next().getPhysicsObject();
                    index++;
                }

                screenLocation = Vector.make2D(player.getPhysicsObject().getLocation().getX() - contentViewCentreX, player.getPhysicsObject().getLocation().getY() - contentViewCentreY);
                publishProgress();

                try {
                    Thread.sleep(20);
                    gravitationCalculator.simulate(physicsObjects);
                    gravitationCalculator.update(physicsObjects);
                } catch (InterruptedException e) {
                } catch (IllegalArgumentException e) {}
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Iterator<SpaceObject> iterator = spaceObjects.iterator();
            while(iterator.hasNext()) {
                iterator.next().setScreenLocation(screenLocation);
            }
            contentView.invalidate();
        }
    }
}
