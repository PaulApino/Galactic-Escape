package com.gscape.sdp.galacticescape.PhysicsEngine;

import java.util.ArrayList;
import java.util.Iterator;

public class PhysicsEngine {

    private final PhysicsEngine onlyInstance = new PhysicsEngine();

    private PhysicsEngine() {}

    public final PhysicsEngine getEngine () { return onlyInstance; }

    public final void update (ArrayList<SpaceObject> spaceObjects) {
        if (spaceObjects.size() < 2) throw new IllegalArgumentException("not enough space objects for calculation");

        ArrayList<NewtonGravSys>  isolatedSystems = new ArrayList<>(spaceObjects.size());

        Iterator objectArr1 = spaceObjects.iterator();
        Iterator objectArr2 = spaceObjects.iterator();
    }

    private class NewtonGravSys {

        protected final double gravConst = 123;
        protected final SpaceObject objectA, objectB;
        protected final double force;

        protected NewtonGravSys(SpaceObject objectA, SpaceObject objectB) {
            this.objectA = objectA;
            this.objectB = objectB;

            double distance = objectA.location().subtract(objectB.location()).magnitude();

            force = gravConst * (objectA.mass() * objectB.mass()) / (distance * distance);
        }
    }
}
