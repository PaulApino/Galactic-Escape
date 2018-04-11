package com.gscape.sdp.galacticescape.GameEngine;

import java.util.ArrayList;
import java.util.Iterator;

public final class Physics {

    private static final double gravConst = 123;
    private static final Physics onlyInstance = new Physics();

    private Physics() {}

    public static final void simulate (ArrayList<SpaceObject> spaceObjects) {
        attract(spaceObjects);
        changeVals(spaceObjects);
    }

    private static final void attract (ArrayList<SpaceObject> spaceObjects) {
        if (spaceObjects.size() < 2) throw new IllegalArgumentException("not enough space objects for calculation");

        Iterator objectArr1 = spaceObjects.iterator();
        Iterator objectArr2 = new ArrayList<>(spaceObjects).iterator();

        while (objectArr1.hasNext()) {
            SpaceObject currentObjectA = (SpaceObject)objectArr1;

            objectArr2.next();
            objectArr2.remove();

            while (objectArr2.hasNext()) {
                newtonGravSystem((SpaceObject) objectArr2, currentObjectA);
            }
        }
    }

    private static final void newtonGravSystem (SpaceObject objectArr2, SpaceObject currentObjectA) {
        SpaceObject currentObjectB = objectArr2;
        Vector displacementAtoB = currentObjectB.location().subtract(currentObjectA.location());
        Vector displacementBtoA = currentObjectA.location().subtract(currentObjectB.location());
        double distance = displacementAtoB.magnitude();
        double force = gravConst * (currentObjectA.mass() * currentObjectB.mass()) / (distance * distance);

        Vector accelObjectA = displacementAtoB.projectFrom(force);
        currentObjectA.setAcceleration(currentObjectA.acceleration().add(accelObjectA));

        Vector accelObjectB = displacementBtoA.projectFrom(force);
        currentObjectB.setAcceleration(currentObjectB.acceleration().add(accelObjectB));
    }

    private static final void changeVals (ArrayList<SpaceObject> spaceObjects) {
        Iterator spaceObjArrA = spaceObjects.iterator();

        while (spaceObjArrA.hasNext()) {
            SpaceObject currentObject = (SpaceObject)spaceObjArrA.next();
            currentObject.setLocation(currentObject.location().add(currentObject.velocity()));
            currentObject.setVelocity(currentObject.velocity().add(currentObject.acceleration()));
        }

        spaceObjArrA = spaceObjects.iterator();

        while (spaceObjArrA.hasNext()) {
            SpaceObject objectA = (SpaceObject)spaceObjArrA.next();
            if (!objectA.isCollided()) {
                for (SpaceObject anObject : spaceObjects) {
                    if(objectA != anObject && !anObject.isCollided()) {
                        double distanceBetween = objectA.location().subtract(anObject.location()).magnitude();
                        if (distanceBetween <= (objectA.collisionRadius() + anObject.collisionRadius())) {
                            objectA.setCollided();
                            anObject.setCollided();
                        }
                    }
                }
            }
        }
    }
}
