package com.gscape.sdp.galacticescape.GameEngineTest;

import com.gscape.sdp.galacticescape.GameEngine.SpaceObject;
import com.gscape.sdp.galacticescape.GameEngine.Vector;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class EngineTest {

    private ArrayList<SpaceObject> physicsObjects;
    private Random rand;

    @Before
    public void testSetup () {
        rand = new Random(42);

        double massSun = 1.989 * Math.pow(10, 30);
        double collisionSun = 695508;
        Vector locationSun = Vector.makeCoordinate(0, 0);
        Vector velocitySun = Vector.makeCoordinate(0, 0);
        Vector accelSun = Vector.makeCoordinate(0, 0);
        SpaceObject sun = new SpaceObject(massSun, collisionSun, locationSun, velocitySun, accelSun);

        double massEarth = 5.97 * Math.pow(10, 24);
        double collisionEarth = 6378;
        Vector locationEarth = Vector.makePolar(149.6 * Math.pow(10, 6), 118);
        Vector velocityEarth = Vector.makeCoordinate(0, 0);
        Vector accelEarth = Vector.makeCoordinate(0.089, -0.225);
        SpaceObject earth = new SpaceObject(massEarth, collisionEarth, locationEarth, velocityEarth, accelEarth);

        double massMars = 0.642 * Math.pow(10, 24);
        double collisionMars = 3371;
        Vector locationMars = Vector.makePolar(227.9 * Math.pow(10, 6), 33.5);
        Vector velocityMars = Vector.makeCoordinate(-0.5, 0.32);
        Vector accelMars = Vector.makeCoordinate(0, 0);
        SpaceObject mars = new SpaceObject(massMars, collisionMars, locationMars, velocityMars, accelMars);

        double massShip = 3214;
        double collisionShip = 0.003;
        Vector locationShip = Vector.makePolar(160.9 * Math.pow(10, 6), 103);
        Vector velocityShip = Vector.makeCoordinate(1.3, -2.7);
        Vector accelShip = Vector.makeCoordinate(0.8, -0.934);
        SpaceObject ship = new SpaceObject(massShip, collisionShip, locationShip, velocityShip, accelShip);

        physicsObjects = new ArrayList<>(10);
        physicsObjects.add(sun);
        physicsObjects.add(earth);
        physicsObjects.add(mars);
        physicsObjects.add(ship);
    }

    @Test
    public void testRunOneCycle () {

    }
}
