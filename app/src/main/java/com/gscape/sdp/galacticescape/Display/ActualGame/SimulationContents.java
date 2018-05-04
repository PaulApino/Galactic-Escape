package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;

/**
 * Container of Simulation objects, Handles adding and deleting simulation objects.
 * @author Paul Apino
 */
public class SimulationContents {

    private ArrayList<PhysicsObject> physicsObjects;
    private ArrayList<ImageView> imageObjects;

    public SimulationContents(ArrayList<PhysicsObject> physicsObjects, ArrayList<ImageView> imageObjects) {
        this.physicsObjects = physicsObjects;
        this.imageObjects = imageObjects;
    }

    public ArrayList<PhysicsObject> getPhysicsObjects() {
        return physicsObjects;
    }

    public ArrayList<ImageView> getImageObjects() {
        return imageObjects;
    }
}
