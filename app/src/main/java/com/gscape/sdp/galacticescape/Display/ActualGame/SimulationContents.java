package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.content.Context;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Container of Simulation objects, Handles adding and deleting simulation objects.
 * @author Paul Apino
 */
public class SimulationContents {

    private final ObjectViewPair player;
    private final ArrayList<ObjectViewPair> objectViewPairs;

    public SimulationContents(ObjectViewPair player, ArrayList<ObjectViewPair> objectViewPairs) {
        this.player = player;
        this.objectViewPairs = objectViewPairs;
    }

    public ArrayList<ObjectViewPair> getObjectViewPairs() {
        return objectViewPairs;
    }

    public ObjectViewPair getPairOf (PhysicsObject targetObject) {
        Iterator<ObjectViewPair> pairIterator = objectViewPairs.iterator();
        while (pairIterator.hasNext()) {
            ObjectViewPair currentPair = pairIterator.next();
            if (currentPair.getPhysicsObject() == targetObject) {
                return currentPair;
            }
        }

        throw new NullPointerException("No pair found with targetObject.");
    }

    public void addObject (Context context, RelativeLayout container, PhysicsObject aPhysicsObject) {
        synchronized (objectViewPairs) {
            final RelativeLayout containerThread = container;
            final ObjectViewPair newPair = ObjectViewPair.getObjectValuePair(context, aPhysicsObject);
            containerThread.post(new Runnable() {
                @Override
                public void run() {
                    containerThread.addView(newPair.getImageView());
                }
            });
            objectViewPairs.add(newPair);
        }
    }

    public void removeObject (RelativeLayout container, PhysicsObject targetObject) {
        synchronized (objectViewPairs) {
            final RelativeLayout containerThread = container;
            final ObjectViewPair targetPair = getPairOf(targetObject);
            containerThread.post(new Runnable() {
                @Override
                public void run() {
                    containerThread.removeView(targetPair.getImageView());
                }
            });
            objectViewPairs.remove(targetPair);
        }
    }
}
