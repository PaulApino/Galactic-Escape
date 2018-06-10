package com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet;

import com.gscape.sdp.galacticescape.Engine.Objects.ObjectTypes;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Obstacle;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.Random;

/*Creates a planet object with attributes that will allow movement based on realistic gravity in space
@author Michelle Extross
*/

public class Planet extends Obstacle{

    /**
     * Makes a Planet from the given values.
     *
     * @param mass            mass of the object (unit in x10^24 kg)
     * @param collisionRadius the radius which the object is considered colliding with another object. (in Megametres(Mm))
     * @param location        the location of the object in a coordinate system.
     * @param velocity        the velocity of the object.
     * @param acceleration    the acceleration of the object.
     */

    public Planet(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration, ObjectTypes objectType) {
        super(mass, collisionRadius, location, velocity, acceleration, objectType);
    }
}
