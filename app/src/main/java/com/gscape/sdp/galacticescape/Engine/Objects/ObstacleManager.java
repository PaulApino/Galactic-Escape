package com.gscape.sdp.galacticescape.Engine.Objects;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.BlackHole;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Meteor;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Obstacle;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetEarthLike;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetGas;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetRocky;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetScorched;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Satellite;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;
import java.util.Random;

/*Manages spawning of different obstacles in space.
@author Michelle Extross
*/

public class ObstacleManager {

    private ArrayList<Obstacle> currentObstaclesList;
    private Random r;

    private double collisionRadiusMin;
    private double collisionRadiusMax;

    private double massMin;
    private double massMax;
    private double mass;

    private Vector location;
    private double xMin;
    private double xMax;

    private double yMin;
    private double yMax;

    private Vector initialVelocity;
    private Vector initialAcceleration;

   // Vector location, Vector velocity, Vector acceleration

    public ObstacleManager()
    {
        currentObstaclesList = new ArrayList<Obstacle>();
        r = new Random();

        collisionRadiusMin = 0;
        collisionRadiusMax = 0;

        xMin = 300;
        xMax = 900;

        yMin = 300;
        yMax = 900;

        initialVelocity = Vector.make2DPolar(0,0);
        initialAcceleration = Vector.make2DPolar(0,0);

    }

    public double randomX()
    {
        int n = r.nextInt(2);
        if (n==0)
        {
            n = -1;
        }
        return (xMin + (xMax - xMin) * r.nextDouble())*n;
    }

    public double randomY()
    {
        return yMin + (yMax - yMin) * r.nextDouble();
    }

    public Vector generateObstacleSpawnLocation(Player p)
    {
        Vector spawnLoc = Vector.make2D(randomX(),randomY());
        Vector playerDirection = p.getVelocity();
        double getTransformAngle= playerDirection.angleFromYOrigintoYPointAntiClock();
        Vector rotatedLocation = spawnLoc.transformRotate(getTransformAngle);
        Vector transformedLocation = rotatedLocation.add(p.getLocation());
        return transformedLocation;
    }

    public double generateCollisionRadius(double collisionRadiusMax, double collisionRadiusMin)
    {
        return (collisionRadiusMin + (collisionRadiusMax - collisionRadiusMin) * r.nextDouble());

    }

    public double generateObstacleMass (double massMax, double massMin)
    {
        return (massMin + (massMax - massMin) * r.nextDouble());
    }

    /**
     * Calculates the gravitational reaction between all objects based on Newton's Law of Gravitation.
     * @param p An instance of a player
     */
    public Obstacle generateObstacle(Player p)
    {
        int pickObstacle = r.nextInt(7)+1;
        if (pickObstacle == 1)
        {
            massMax = 600;
            massMin = 50;
            collisionRadiusMax = 600;
            collisionRadiusMin = 50;

            double planetEarthLikeMass = generateObstacleMass(massMax, massMin);
            double planetEarthLikeCollisionRadius = generateCollisionRadius(collisionRadiusMax, collisionRadiusMin);
            Vector planetEarthLikeLocation = generateObstacleSpawnLocation(p);

            PlanetEarthLike newEarthLikePlanet = new PlanetEarthLike(planetEarthLikeMass,planetEarthLikeCollisionRadius,planetEarthLikeLocation,initialVelocity,initialAcceleration);
            currentObstaclesList.add(newEarthLikePlanet);
            return newEarthLikePlanet;
        }

       else if (pickObstacle == 2)
        {
            massMax = 3000;
            massMin = 1500;
            collisionRadiusMax = 700;
            collisionRadiusMin = 500;

            double planetGasMass = generateObstacleMass(massMax, massMin);
            double planetGasCollisionRadius = generateCollisionRadius(collisionRadiusMax, collisionRadiusMin);
            Vector planetGasSpawnLocation = generateObstacleSpawnLocation(p);

            return new PlanetGas(planetGasMass,planetGasCollisionRadius,planetGasSpawnLocation,initialVelocity,initialAcceleration);
        }
        else if (pickObstacle == 3)
        {
            massMax = 40;
            massMin = 10;
            collisionRadiusMax = 100;
            collisionRadiusMin = 50;

            double planetRockyMass = generateObstacleMass(massMax, massMin);
            double planetRockyCollisionRadius = generateCollisionRadius(collisionRadiusMax, collisionRadiusMin);
            Vector planetRockySpawnLocation = generateObstacleSpawnLocation(p);
            return new PlanetRocky(planetRockyMass,planetRockyCollisionRadius,planetRockySpawnLocation,initialVelocity,initialAcceleration);
        }
        else if (pickObstacle == 4)
        {
            massMax = 40;
            massMin = 10;
            collisionRadiusMax = 100;
            collisionRadiusMin = 50;

            double planetScorchedMass = generateObstacleMass(massMax, massMin);
            double planetScorchedCollisionRadius = generateCollisionRadius(collisionRadiusMax, collisionRadiusMin);
            Vector planetScorchedSpawnLocation = generateObstacleSpawnLocation(p);
            return new PlanetScorched(planetScorchedMass,planetScorchedCollisionRadius,planetScorchedSpawnLocation,initialVelocity,initialAcceleration);
        }

        else if (pickObstacle == 5)
        {
            massMax = 5000*Math.pow(10, 6);
            massMin = 1000*Math.pow(10, 6);
            collisionRadiusMax = 700;
            collisionRadiusMin = 500;

            double blackHoleMass = generateObstacleMass(massMax, massMin);
            double blackHoleCollisionRadius = generateCollisionRadius(collisionRadiusMax, collisionRadiusMin);
            Vector blackHoleSpawnLocation = generateObstacleSpawnLocation(p);
            return new BlackHole(blackHoleMass,blackHoleCollisionRadius,blackHoleSpawnLocation,initialVelocity,initialAcceleration);
        }

        else if (pickObstacle == 6)
        {
            massMax = 5;
            massMin = 0.1;
            collisionRadiusMax = 70;
            collisionRadiusMin = 50;

            double meteorMass = generateObstacleMass(massMax, massMin);
            double meteorCollisionRadius = generateCollisionRadius(collisionRadiusMax, collisionRadiusMin);
            Vector meteorSpawnLocation = generateObstacleSpawnLocation(p);
            return new Meteor(meteorMass,meteorCollisionRadius,meteorSpawnLocation,initialVelocity,initialAcceleration);
        }

        else if (pickObstacle == 7)
        {
            massMax = 0.01;
            massMin = 0.001;
            collisionRadiusMax = 75;
            collisionRadiusMin = 50;

            double satelliteMass = generateObstacleMass(massMax, massMin);
            double satelliteCollisionRadius = generateCollisionRadius(collisionRadiusMax, collisionRadiusMin);
            Vector satelliteSpawnLocation = generateObstacleSpawnLocation(p);
            return new Satellite(satelliteMass,satelliteCollisionRadius,satelliteSpawnLocation,initialVelocity,initialAcceleration);
        }

    else return null;
    }
}
