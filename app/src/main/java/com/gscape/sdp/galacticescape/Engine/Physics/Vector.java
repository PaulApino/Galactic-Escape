package com.gscape.sdp.galacticescape.Engine.Physics;

/**
 * Vector contains x, y, z coordinates in a 3 dimensional
 * coordinate system where the origin is (0, 0, 0).
 * Vector allows for Vector addition and subtraction,
 * it can also project scalar values to an existing vector.
 * @author Paul Apino
 */
public class Vector {

    private double x, y;

    private Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Makes a 2 dimensional vector.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return returns a 2 dimensional vector object.
     */
    public static Vector make2D(double x, double y) {
        return new Vector(x, y);
    }

    public static Vector make2DPolar(double magnitude, double angleFromX) {
        return new Vector(magnitude * Math.cos(angleFromX), magnitude * Math.sin(angleFromX));
    }

    public double getX() {
        synchronized (this) {
            return x;
        }
    }

    public double getY() {
        synchronized (this) {
            return y;
        }
    }

    /**
     * Calculates the magnitude of the vector.
     * @return returns the magnitude of the vector.
     */
    public double getMagnitude() {
        synchronized (this) {
            return Math.sqrt((x * x) + (y * y));
        }
    }

    /**
     * Calculates the sum of two vectors.
     * @param aVector the vector to be added to this vector.
     * @return returns the sum of this and aVector as a Vector.
     */
    public Vector add (Vector aVector) {
        synchronized (this) {
            return new Vector(x + aVector.x, y + aVector.y);
        }
    }

    /**
     * Calculates the difference of two vectors.
     * @param aVector the minuend vector of the subtraction.
     * @return returns the difference of this - aVector as a Vector.
     */
    public Vector minus (Vector aVector) {
        synchronized (this) {
            return new Vector(x - aVector.x, y - aVector.y);
        }
    }

    /**
     * Projects the scalar value to the unit vector of this vector.
     * @param magnitude the scalar value to be projected to this vector.
     * @return returns a new vector with the magnitude parameter with the same unit vector as this vector.
     * @throws IllegalArgumentException throws if this vector magnitude is 0.
     */
    public Vector projectWith (double magnitude) {
        synchronized (this) {
            if (x == 0 && y == 0)
                throw new IllegalArgumentException("Vector doesn't have direction.");
            Vector unitVector = unitVector();
            return new Vector(unitVector.x * magnitude, unitVector.y * magnitude);
        }
    }

    public Vector unitVector () {
        synchronized (this) {
            double mag = getMagnitude();
            return new Vector(x / mag, y / mag);
        }
    }

    public Vector transformRotate (double angleFromYAntiClock) {
        synchronized (this) {
            double sinAngle = Math.sin(angleFromYAntiClock);
            double cosAngle = Math.cos(angleFromYAntiClock);
            return new Vector((x * cosAngle) - (y * sinAngle), (x * sinAngle) + (y * cosAngle));
        }
    }

    public double angleFromYOrigintoYPointAntiClock () {
        if (y > 0) {
            if (x > 0) {
                return 360 - Math.tan(x / y);
            } else if (x < 0) {
                return Math.tan(x / y);
            } else return 0;
        } else if (y < 0) {
            if (x > 0) {
                return 180 + Math.tan(x / y);
            } else if (x < 0) {
                return 180 - Math.tan(x / y);
            } else return 180;
        } else return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector aVector = (Vector)obj;
            return (x == aVector.x && y == aVector.y);
        } return false;
    }
}
