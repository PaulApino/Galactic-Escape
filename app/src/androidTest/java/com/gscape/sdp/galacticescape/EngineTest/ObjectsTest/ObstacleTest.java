package com.gscape.sdp.galacticescape.EngineTest.ObjectsTest;

import com.gscape.sdp.galacticescape.Engine.Objects.ObjectTypes;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.BlackHole;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Meteor;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetEarthLike;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetGas;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetRocky;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetScorched;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Satellite;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

  /*
       Unit Tests for creating new Obstacles
       @author Michelle Extross
    */

public class ObstacleTest {

    private Random rand = new Random();

    @Test
    public void makePlanetEarthLike () {
        for (int i = 0; i < 100000; i++) {

            double mass = rand.nextInt();
            double collisionRadius = rand.nextInt();
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector location = Vector.make2D(x, y);
            Vector velocity = Vector.make2DPolar(10,0);
            Vector acceleration = Vector.make2DPolar(20, 0);
            PlanetEarthLike planetEarthLike = new PlanetEarthLike(mass,collisionRadius,location,velocity,acceleration);

            Assert.assertEquals(mass, planetEarthLike.getMass(),0);
            Assert.assertEquals(collisionRadius, planetEarthLike.getCollisionRadius(),0);
            Assert.assertEquals(10, planetEarthLike.getVelocity().getMagnitude(),0);
            Assert.assertEquals(20, planetEarthLike.getAcceleration().getMagnitude(),0);
            Assert.assertEquals(x, planetEarthLike.getLocation().getX(), 0);
            Assert.assertEquals(y, planetEarthLike.getLocation().getY(), 0);
            Assert.assertEquals(ObjectTypes.PLANET_EARTH_LIKE, planetEarthLike.getObjectType());
        }
    }

    @Test
    public void makePlanetGas () {
        for (int i = 0; i < 100000; i++) {

            double mass = rand.nextInt();
            double collisionRadius = rand.nextInt();
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector location = Vector.make2D(x, y);
            Vector velocity = Vector.make2DPolar(10,0);
            Vector acceleration = Vector.make2DPolar(20, 0);
            PlanetGas planetGas = new PlanetGas(mass,collisionRadius,location,velocity,acceleration);

            Assert.assertEquals(mass, planetGas.getMass(),0);
            Assert.assertEquals(collisionRadius, planetGas.getCollisionRadius(),0);
            Assert.assertEquals(10, planetGas.getVelocity().getMagnitude(),0);
            Assert.assertEquals(20, planetGas.getAcceleration().getMagnitude(),0);
            Assert.assertEquals(x, planetGas.getLocation().getX(), 0);
            Assert.assertEquals(y, planetGas.getLocation().getY(), 0);
            Assert.assertEquals(ObjectTypes.PLANET_GAS, planetGas.getObjectType());
        }
    }

    @Test
    public void makePlanetRocky () {
        for (int i = 0; i < 100000; i++) {

            double mass = rand.nextInt();
            double collisionRadius = rand.nextInt();
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector location = Vector.make2D(x, y);
            Vector velocity = Vector.make2DPolar(10,0);
            Vector acceleration = Vector.make2DPolar(20, 0);
            PlanetRocky planetRocky = new PlanetRocky(mass,collisionRadius,location,velocity,acceleration);

            Assert.assertEquals(mass, planetRocky.getMass(),0);
            Assert.assertEquals(collisionRadius, planetRocky.getCollisionRadius(),0);
            Assert.assertEquals(10, planetRocky.getVelocity().getMagnitude(),0);
            Assert.assertEquals(20, planetRocky.getAcceleration().getMagnitude(),0);
            Assert.assertEquals(x, planetRocky.getLocation().getX(), 0);
            Assert.assertEquals(y, planetRocky.getLocation().getY(), 0);
            Assert.assertEquals(ObjectTypes.PLANET_ROCKY, planetRocky.getObjectType());
        }
    }

    @Test
    public void makePlanetScorched () {
        for (int i = 0; i < 100000; i++) {

            double mass = rand.nextInt();
            double collisionRadius = rand.nextInt();
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector location = Vector.make2D(x, y);
            Vector velocity = Vector.make2DPolar(10,0);
            Vector acceleration = Vector.make2DPolar(20, 0);
            PlanetScorched planetScorched = new PlanetScorched(mass,collisionRadius,location,velocity,acceleration);

            Assert.assertEquals(mass, planetScorched.getMass(),0);
            Assert.assertEquals(collisionRadius, planetScorched.getCollisionRadius(),0);
            Assert.assertEquals(10, planetScorched.getVelocity().getMagnitude(),0);
            Assert.assertEquals(20, planetScorched.getAcceleration().getMagnitude(),0);
            Assert.assertEquals(x, planetScorched.getLocation().getX(), 0);
            Assert.assertEquals(y, planetScorched.getLocation().getY(), 0);
            Assert.assertEquals(ObjectTypes.PLANET_SCORCHED, planetScorched.getObjectType());
        }
    }

    @Test
    public void makeBlackHole () {
        for (int i = 0; i < 100000; i++) {

            double mass = rand.nextInt();
            double collisionRadius = rand.nextInt();
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector location = Vector.make2D(x, y);
            Vector velocity = Vector.make2DPolar(10,0);
            Vector acceleration = Vector.make2DPolar(20, 0);
            BlackHole blackHole = new BlackHole(mass,collisionRadius,location,velocity,acceleration);

            Assert.assertEquals(mass, blackHole.getMass(),0);
            Assert.assertEquals(collisionRadius, blackHole.getCollisionRadius(),0);
            Assert.assertEquals(10, blackHole.getVelocity().getMagnitude(),0);
            Assert.assertEquals(20, blackHole.getAcceleration().getMagnitude(),0);
            Assert.assertEquals(x, blackHole.getLocation().getX(), 0);
            Assert.assertEquals(y, blackHole.getLocation().getY(), 0);
            Assert.assertEquals(ObjectTypes.BLACK_HOLE, blackHole.getObjectType());
        }
    }

    @Test
    public void makeMeteor () {
        for (int i = 0; i < 100000; i++) {

            double mass = rand.nextInt();
            double collisionRadius = rand.nextInt();
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector location = Vector.make2D(x, y);
            Vector velocity = Vector.make2DPolar(10,0);
            Vector acceleration = Vector.make2DPolar(20, 0);
            Meteor meteor = new Meteor(mass,collisionRadius,location,velocity,acceleration);

            Assert.assertEquals(mass, meteor.getMass(),0);
            Assert.assertEquals(collisionRadius, meteor.getCollisionRadius(),0);
            Assert.assertEquals(10, meteor.getVelocity().getMagnitude(),0);
            Assert.assertEquals(20, meteor.getAcceleration().getMagnitude(),0);
            Assert.assertEquals(x, meteor.getLocation().getX(), 0);
            Assert.assertEquals(y, meteor.getLocation().getY(), 0);
            Assert.assertEquals(ObjectTypes.METEOR, meteor.getObjectType());
        }
    }

    @Test
    public void makeSatellite () {
        for (int i = 0; i < 100000; i++) {

            double mass = rand.nextInt();
            double collisionRadius = rand.nextInt();
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector location = Vector.make2D(x, y);
            Vector velocity = Vector.make2DPolar(10,0);
            Vector acceleration = Vector.make2DPolar(20, 0);
            Satellite meteor = new Satellite(mass,collisionRadius,location,velocity,acceleration);

            Assert.assertEquals(mass, meteor.getMass(),0);
            Assert.assertEquals(collisionRadius, meteor.getCollisionRadius(),0);
            Assert.assertEquals(10, meteor.getVelocity().getMagnitude(),0);
            Assert.assertEquals(20, meteor.getAcceleration().getMagnitude(),0);
            Assert.assertEquals(x, meteor.getLocation().getX(), 0);
            Assert.assertEquals(y, meteor.getLocation().getY(), 0);
            Assert.assertEquals(ObjectTypes.SATELLITE, meteor.getObjectType());
        }
    }


}

