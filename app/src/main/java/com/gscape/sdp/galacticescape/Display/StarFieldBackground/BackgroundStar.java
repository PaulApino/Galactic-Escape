package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.graphics.Color;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class BackgroundStar {

    private final Vector location;
    private final double radius;
    private final Color colour;

    public BackgroundStar(Vector location, double radius, Color colour) {
        this.location = location;
        this.radius = radius;
        this.colour = colour;
    }

    public Vector getLocation() {
        return location;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColour() {
        return colour;
    }
}
