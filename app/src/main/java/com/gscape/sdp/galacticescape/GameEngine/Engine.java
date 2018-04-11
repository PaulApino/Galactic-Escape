package com.gscape.sdp.galacticescape.GameEngine;

import java.util.ArrayList;

public class Engine {

    private final ArrayList<SpaceObject> physicsObjects;

    public Engine(ArrayList<SpaceObject> physicsObjects) {
        this.physicsObjects = physicsObjects;
    }

    public void run (int cycles) {
        for (int i = 0;  i < cycles; i++) {
            Physics.simulate(physicsObjects);
        }
    }
}
