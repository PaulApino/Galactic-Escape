package com.gscape.sdp.galacticescape.PhysicsEngine;

public class SpaceObject {

    private final double mass;
    private final double collisionRadius;
    private Vector location, velocity, acceleration;

    public SpaceObject(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration) {
        this.mass = mass;
        this.collisionRadius = collisionRadius;
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public double mass () { return mass; }

    public double collisionRadius () { return collisionRadius; }

    public Vector location () { return location; }

    public Vector velocity () { return velocity; }

    public Vector acceleration () { return acceleration; }
}
