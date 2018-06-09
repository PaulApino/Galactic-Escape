package com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

/*Creates a EarthLike Planet object with attributes that will allow movement based on realistic gravity in space
@author Michelle Extross
*/

public class PlanetEarthLike extends Planet{

    /**
     * Makes a EarthLike Planet from the given values.
     *
     * @param mass            mass of the object
     * @param collisionRadius the radius which the object is considered colliding with another object.
     * @param location        the location of the object in a coordinate system.
     * @param velocity        the velocity of the object.
     * @param acceleration    the acceleration of the object.
     */
    public PlanetEarthLike(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration) {
        super(mass, collisionRadius, location, velocity, acceleration);

    }


}
