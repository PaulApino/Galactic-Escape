package com.gscape.sdp.galacticescape.Engine.Physics;

/**
 * Vector contains x, y, z coordinates in a 3 dimensional
 * coordinate system where the origin is (0, 0, 0).
 * Vector allows for Vector addition and subtraction,
 * it can also project scalar values to an existing vector.
 * @author Paul Apino
 */
public class Vector {

    private final double x, y, z;

    private Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Makes a 2 dimensional vector.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return returns a 2 dimensional vector object.
     */
    public static Vector make2D(double x, double y) {
        return new Vector(x, y, 0);
    }

    /**
     * Makes a 3 dimensional vector.
     * @param x x coordinate.
     * @param y y coordinate.
     * @param z z coordinate.
     * @return returns a 3 dimensional vector object.
     */
    public static Vector make3D(double x, double y, double z) {
        return new Vector(x, y, z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    /**
     * Calculates the magnitude of the vector.
     * @return returns the magnitude of the vector.
     */
    public double getMagnitude() {
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    /**
     * Calculates the sum of two vectors.
     * @param aVector the vector to be added to this vector.
     * @return returns the sum of this and aVector as a Vector.
     */
    public Vector add (Vector aVector) {
        return new Vector(x + aVector.x, y + aVector.y, z + aVector.z);
    }

    /**
     * Calculates the difference of two vectors.
     * @param aVector the minuend vector of the subtraction.
     * @return returns the difference of this - aVector as a Vector.
     */
    public Vector minus (Vector aVector) {
        return new Vector(x - aVector.x, y - aVector.y, z - aVector.z);
    }

    /**
     * Projects the scalar value to the unit vector of this vector.
     * @param magnitude the scalar value to be projected to this vector.
     * @return returns a new vector with the magnitude parameter with the same unit vector as this vector.
     * @throws IllegalArgumentException throws if this vector magnitude is 0.
     */
    public Vector projectWith (double magnitude) {
        if (x == 0 && y == 0 && z == 0) throw new IllegalArgumentException("Vector doesn't have direction.");
        double mag = getMagnitude();
        Vector unitVector = new Vector(x / mag, y / mag, z / mag);
        return new Vector(unitVector.x * magnitude, unitVector.y * magnitude, unitVector.z * magnitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector aVector = (Vector)obj;
            return (x == aVector.x && y == aVector.y && z == aVector.z);
        } return false;
    }
}
