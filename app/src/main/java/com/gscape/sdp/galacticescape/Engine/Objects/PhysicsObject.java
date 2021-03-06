package com.gscape.sdp.galacticescape.Engine.Objects;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

/**
 * PhysicsObject contains physics values used in physics calculations.
 * @author Paul Apino
 */
public class PhysicsObject {

    protected final double mass, collisionRadius;
    protected Vector location, velocity, acceleration;
    protected boolean collided;
    protected boolean removed;
    protected final ObjectTypes objectType;

    /**
     * Makes a PhysicsObject from the given values.
     * @param mass mass of the object
     * @param collisionRadius the radius which the object is considered colliding with another object.
     * @param location the location of the object in a coordinate system.
     * @param velocity the velocity of the object.
     * @param acceleration the acceleration of the object.
     */
    public PhysicsObject(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration, ObjectTypes objectType) {
        this.mass = mass;
        this.collisionRadius = collisionRadius;
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.objectType = objectType;
        collided = false;
        removed = false;
    }

    public double getMass() {
        return mass;
    }

    public double getCollisionRadius() {
        return collisionRadius;
    }

    public boolean isCollided() {
        return collided;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Vector getLocation() {
        return location;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public ObjectTypes getObjectType() {
        return objectType;
    }

    public void setCollided() {
        collided = true;
    }

    public void setRemoved() {
        removed = true;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }


}
