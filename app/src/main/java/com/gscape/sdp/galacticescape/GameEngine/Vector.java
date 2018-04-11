package com.gscape.sdp.galacticescape.GameEngine;

public class Vector {

    private final double x, y, z, magnitude;

    private Vector (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        magnitude = Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public static Vector makeCoordinate2D (double x, double y) {
        return new Vector(x, y, 0);
    }

    public static Vector makePolar2D (double r, double theta) {
        double tanTheta = Math.tan(theta);
        return new Vector(tanTheta / r * Math.sin(theta), r * Math.cos(theta) * tanTheta, 0);
    }

    public static Vector makeCoordinate3D (double x, double y, double z) { return new Vector(x, y, z); }

    public double x () { return x; }

    public double y () { return y; }

    public double magnitude () { return magnitude; }

    public Vector add (Vector aVector) {
        return new Vector(x + aVector.x, y + aVector.y, z + aVector.z);
    }

    public Vector subtract (Vector aVector) {
        return new Vector(x - aVector.x, y - aVector.y, z + aVector.z);
    }

    public Vector unitVector () {
        if (magnitude == 0) return new Vector(0, 0, 0);
        return new Vector(x / magnitude, y / magnitude, z / magnitude);
    }

    public Vector projectTo (Vector aVector) {
        if (aVector.magnitude == 0) return new Vector(0,0, 0);
        Vector unitV = aVector.unitVector();
        return new Vector(magnitude * unitV.x, magnitude * unitV.y, magnitude * unitV.z);
    }

    public Vector projectFrom (double magnitude) {
        Vector unitV = unitVector();
        return new Vector(magnitude * unitV.x, magnitude * unitV.y, magnitude * unitV.z);
    }
}
