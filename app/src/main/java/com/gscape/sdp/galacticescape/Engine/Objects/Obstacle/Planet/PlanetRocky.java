package com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class PlanetRocky extends Planet {
    /**
     * Makes a Rocky Planet from the given values.
     *
     * @param mass            mass of the object
     * @param collisionRadius the radius which the object is considered colliding with another object.
     * @param location        the location of the object in a coordinate system.
     * @param velocity        the velocity of the object.
     * @param acceleration    the acceleration of the object.
     */
    public PlanetRocky(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration) {
        super(mass, collisionRadius, location, velocity, acceleration);
    }
}
