package com.gscape.sdp.galacticescape.Engine.Objects;

import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Obstacle;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.Planet;
import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Planet.PlanetEarthLike;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;
import java.util.Random;

//make it into a singleton
public class ObstacleManager {

    private ArrayList obstaclesList;
    private Random r;

    private double collisionRadiusMin;
    private double collisionRadiusMax;
    private double collisionRadius;

    private double massMin;
    private double massMax;
    private double mass;

    private Vector location;
    private double xMin;
    private double xMax;

    private double yMin;
    private double yMax;

   // Vector location, Vector velocity, Vector acceleration

    public ObstacleManager()
    {
        //based on smartphone screen size
        obstaclesList = new ArrayList<Obstacle>();
        r = new Random();

        collisionRadiusMin = 0;
        collisionRadiusMax = 0;
        collisionRadius = 0;

        xMin = 300;
        xMax = 900;

        yMin = 300;
        yMax = 900;

//        r = new Random();
//        collisionRadiusMin = 10;
//        collisionRadiusMax = 10;
//        massMin = 10;
//        massMax = 10;
//
//        collisionRadius = (collisionRadiusMin + (collisionRadiusMax - collisionRadiusMin) * r.nextDouble());
//        mass = (massMin + (massMax - massMin) * r.nextDouble());


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

    public Vector generateObstacleAtLoc(Player p)
    {
        Vector spawnLoc = Vector.make2D(randomX(),randomY());
        Vector playerDirection = p.getVelocity();
        double getTransformAngle= playerDirection.angleFromYOrigintoYPointAntiClock();
        Vector rotated = spawnLoc.transformRotate(getTransformAngle);
        Vector transformed = rotated.add(p.getLocation());
        return transformed;
    }

    public Obstacle generateObstacle(Player p)
    {
        int pickObstacle = r.nextInt(8)+1;

        if (pickObstacle == 1)
        {
            massMax = 400;
            massMin = 100;
            double earthLikeMass = (collisionRadiusMin + (collisionRadiusMax - collisionRadiusMin) * r.nextDouble());

            collisionRadiusMax = 600;
            collisionRadiusMin = 50;
            double collradius = (collisionRadiusMin + (collisionRadiusMax - collisionRadiusMin) * r.nextDouble());

            Vector earthLikeLocation = generateObstacleAtLoc(p);

            Vector v = Vector.make2DPolar(, )
            Vector a = Vector.make2D(2, 3);

           return new PlanetEarthLike(earthLikeMass,collradius,earthLikeLocation,v,a);
        }

    else return null;

    }
}
