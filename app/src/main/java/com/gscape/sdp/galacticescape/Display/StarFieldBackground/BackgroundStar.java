package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class BackgroundStar {

    private final Vector location;
    private final double radius;

    public BackgroundStar(Vector location, double radius) {
        this.location = location;
        this.radius = radius;
    }

    public Vector getLocation() {
        return location;
    }

    public double getRadius() {
        return radius;
    }
}
