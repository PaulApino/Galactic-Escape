package com.gscape.sdp.galacticescape.PhysicsEngine;

public class SpaceObject {

    private final double mass;
    private final double collisionRadius;
    private boolean collided;
    private Vector location, velocity, acceleration;

    public SpaceObject(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration) {
        this.mass = mass;
        this.collisionRadius = collisionRadius;
        this.collided = false;
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public double mass () { return mass; }

    public double collisionRadius () { return collisionRadius; }

    public boolean isCollided () { return collided; }

    public Vector location () { return location; }

    public Vector velocity () { return velocity; }

    public Vector acceleration () { return acceleration; }

    public void setCollided () {
        collided = true;
    }

    public void setLocation (Vector newLocation) {
        location = newLocation;
    }

    public void setVelocity (Vector newVelocity) {
        velocity = newVelocity;
    }

    public void setAcceleration (Vector newAcceleration) {
        acceleration = newAcceleration;
    }
}
