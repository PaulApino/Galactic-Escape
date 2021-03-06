package com.gscape.sdp.galacticescape.Engine.Objects.Obstacle;

import com.gscape.sdp.galacticescape.Engine.Objects.ObjectTypes;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.Random;

/*Creates a Blackhole Object with attributes that will allow movement based on realistic gravity in space
@author Michelle Extross
*/
public class BlackHole extends Obstacle{

    /**
     * Makes a BlackHole from the given values.
     *
     * @param mass            mass of the object
     * @param collisionRadius the radius which the object is considered colliding with another object.
     * @param location        the location of the object in a coordinate system.
     * @param velocity        the velocity of the object.
     * @param acceleration    the acceleration of the object.
     */

    public BlackHole(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration) {
        super(mass, collisionRadius, location, velocity, acceleration, ObjectTypes.BLACK_HOLE);
//
//        collisionRadiusMin = 400;
//        collisionRadiusMax = 450;
//        this.collisionRadius = collisionRadiusMin + (collisionRadiusMax - collisionRadiusMin) * r.nextDouble();



    }
}
