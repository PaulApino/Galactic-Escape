package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.util.Log;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;

/**
 * Handles changing object screen position.
 * @author Paul Apino
 */
public class SimulationContents {

    private SpaceObject player;
    private ArrayList<SpaceObject> spaceObjects;
    private Vector screenSize, screenLocation;

    public SimulationContents(ArrayList<SpaceObject> spaceObjects, Vector screenSize) {
        this.player = spaceObjects.get(0);
        this.spaceObjects = spaceObjects;
        this.screenSize = screenSize;
    }

    public SpaceObject getPlayer() {
        return player;
    }

    public ArrayList<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public Vector getScreenLocation() {
        return screenLocation;
    }

    public void updateLocations() {
        screenLocation = Vector.make2D(
                player.getPhysicsObject().getLocation().getX() - screenSize.getX(),
                player.getPhysicsObject().getLocation().getY() - screenSize.getY());

        Log.i("ScreenLocation", screenLocation.getX() + ", " + screenLocation.getY());

        for (int i = 1; i < spaceObjects.size(); i++) {
            spaceObjects.get(i).setScreenLocation(screenLocation);
        }
    }
}
