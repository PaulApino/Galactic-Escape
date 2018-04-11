package com.gscape.sdp.galacticescape.GameEngineTest;

import com.gscape.sdp.galacticescape.GameEngine.Engine;
import com.gscape.sdp.galacticescape.GameEngine.SpaceObject;
import com.gscape.sdp.galacticescape.GameEngine.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class EngineTest {

    private ArrayList<SpaceObject> physicsObjects;
    private Random rand;

    @Before
    public void setUp () throws Exception {
        rand = new Random(42);

        double massSun = 1.989 * Math.pow(10, 30);
        double collisionSun = 695508;
        Vector locationSun = Vector.makeCoordinate2D(0, 0);
        Vector velocitySun = Vector.makeCoordinate2D(0, 0);
        Vector accelSun = Vector.makeCoordinate2D(0, 0);
        SpaceObject sun = new SpaceObject(massSun, collisionSun, locationSun, velocitySun, accelSun);

        double massEarth = 5.97 * Math.pow(10, 24);
        double collisionEarth = 6378;
        Vector locationEarth = Vector.makePolar2D(149.6 * Math.pow(10, 6), 118);
        Vector velocityEarth = Vector.makeCoordinate2D(0, 0);
        Vector accelEarth = Vector.makeCoordinate2D(0.089, -0.225);
        SpaceObject earth = new SpaceObject(massEarth, collisionEarth, locationEarth, velocityEarth, accelEarth);

        double massMars = 0.642 * Math.pow(10, 24);
        double collisionMars = 3371;
        Vector locationMars = Vector.makePolar2D(227.9 * Math.pow(10, 6), 33.5);
        Vector velocityMars = Vector.makeCoordinate2D(-0.5, 0.32);
        Vector accelMars = Vector.makeCoordinate2D(0, 0);
        SpaceObject mars = new SpaceObject(massMars, collisionMars, locationMars, velocityMars, accelMars);

        double massShip = 3214;
        double collisionShip = 0.003;
        Vector locationShip = Vector.makePolar2D(160.9 * Math.pow(10, 6), 103);
        Vector velocityShip = Vector.makeCoordinate2D(1.3, -2.7);
        Vector accelShip = Vector.makeCoordinate2D(0.8, -0.934);
        SpaceObject ship = new SpaceObject(massShip, collisionShip, locationShip, velocityShip, accelShip);

        physicsObjects = new ArrayList<>(10);
        physicsObjects.add(sun);
        physicsObjects.add(earth);
        physicsObjects.add(mars);
//        physicsObjects.add(ship);
    }

    @After
    public void tearDown () throws Exception {
        physicsObjects.clear();
    }

    @Test
    public void testRunOneCycle () {
        Engine myEngine =  new Engine(physicsObjects);

        myEngine.run(1);

        assertEquals(true, true);
    }
}
