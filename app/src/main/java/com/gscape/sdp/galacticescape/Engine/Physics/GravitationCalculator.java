package com.gscape.sdp.galacticescape.Engine.Physics;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

/**
 * Calculates object gravitational interaction between all
 * objects in the system using Newton's Law of Gravition
 * with the given gravitational constant.
 * @author Paul Apino
 */
public class GravitationCalculator {

    private final double gravityConstant;

    /**
     * Makes a GravitationalCalculator that calculates with the given gravitational constant.
     * @param gravityConstant the gravitational constant for Newton's Law Gravitation equation.
     */
    public GravitationCalculator(double gravityConstant) {
        this.gravityConstant = gravityConstant;
    }

    /**
     * Calculates the gravitational reaction between all objects based on Newton's Law of Gravitation.
     * @param objects Array of PhysicsObject. (array must contain 2 objects)
     */
    public void simulate (PhysicsObject [] objects) {
        if (objects.length < 2) throw new IllegalArgumentException("Not enough objects to simulate gravity");

        //Calculates gravity effect for each PhysicsObject
        int tempIndex = 1;
        for (PhysicsObject currentObject : objects) {
            for (int i = tempIndex; i <  objects.length; i++) {
                double distance = currentObject.getLocation().minus(objects[i].getLocation()).getMagnitude();       //distance between the two PhysicsObject
                double force = gravityConstant * (currentObject.getMass() * objects[i].getMass()) / (distance * distance);     //force experienced by the two PhysicsObject
                currentObject.setAcceleration(currentObject.getAcceleration().add(objects[i].getLocation().minus(currentObject.getLocation()).projectWith(force / currentObject.getMass())));       //adds the acceleration to currentObject
                objects[i].setAcceleration(objects[i].getAcceleration().add(currentObject.getLocation().minus(objects[i].getLocation()).projectWith(force / objects[i].getMass())));      //adds the acceleration to other object
            }
            tempIndex++;
        }
    }

    public void update (PhysicsObject [] objects) {
        for (PhysicsObject currentObject : objects) {
            currentObject.setLocation(currentObject.getLocation().add(currentObject.getVelocity()));
        }

        if (objects.length > 1) {
            int tempIndex = 1;
            for (PhysicsObject currentObject : objects) {
                for (int i = tempIndex; i < objects.length; i++) {
                    double collisionDistance = currentObject.getCollisionRadius() + objects[i].getCollisionRadius();
                    double objectDistance = currentObject.getLocation().minus(objects[i].getLocation()).getMagnitude();
                    double distanceDifference = objectDistance - collisionDistance;
                    if (distanceDifference < 5.0) {
                        currentObject.setCollided();
                        objects[i].setCollided();
                    }
                }
                tempIndex++;
            }
        }

        for (PhysicsObject currentObject : objects) {
            if (!currentObject.getCollided()) {
                currentObject.setVelocity(currentObject.getVelocity().add(currentObject.getAcceleration()));
            }
        }
    }
}
