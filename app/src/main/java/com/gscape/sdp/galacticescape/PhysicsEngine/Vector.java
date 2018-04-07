package com.gscape.sdp.galacticescape.PhysicsEngine;

public class Vector {

    private final double x, y, magnitude;

    private Vector (double x, double y) {
        this.x = x;
        this.y = y;
        magnitude = Math.sqrt((x * x) + (y * y));
    }

    public static Vector make (double x, double y) {
        return new Vector(x, y);
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
}
