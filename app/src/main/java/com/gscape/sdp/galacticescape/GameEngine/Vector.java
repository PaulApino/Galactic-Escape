package com.gscape.sdp.galacticescape.GameEngine;

public class Vector {

    private final double x, y, magnitude;

    private Vector (double x, double y) {
        this.x = x;
        this.y = y;
        magnitude = Math.sqrt((x * x) + (y * y));
    }

    public static Vector makeCoordinate (double x, double y) {
        return new Vector(x, y);
    }

    public static Vector makePolar (double r, double theta) {
        double tanTheta = Math.tan(theta);
        return new Vector(tanTheta / r * Math.sin(theta), r * Math.cos(theta) * tanTheta);
    }

    public double x () { return x; }

    public double y () { return y; }

    public double magnitude () { return magnitude; }

    public Vector add (Vector aVector) {
        return new Vector(x + aVector.x, y + aVector.y);
    }

    public Vector subtract (Vector aVector) {
        return new Vector(x - aVector.x, y - aVector.y);
    }

    public Vector unitVector () {
        if (magnitude == 0) return new Vector(0, 0);
        return new Vector(x / magnitude, y / magnitude);
    }

    public Vector projectTo (Vector aVector) {
        if (aVector.magnitude == 0) return new Vector(0,0);
        Vector unitV = aVector.unitVector();
        return new Vector(magnitude * unitV.x, magnitude * unitV.y);
    }

    public Vector projectFrom (double magnitude) {
        Vector unitV = unitVector();
        return new Vector(magnitude * unitV.x, magnitude * unitV.y);
    }
}
