package com.gscape.sdp.galacticescape.Engine.Objects;

import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Obstacle;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;
import java.util.Random;

//make it into a singleton
public class ObstacleManager {

    private ArrayList obstaclesList;
    private Random r;

    private double magnitudeMax;
    private double magnitudeMin;
    private double angleMax;
    private double angleMin;

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

        magnitudeMax = 5000;//Unit is in pixels(1 pixel represents 1 Megametre)
        magnitudeMin = 2000;
        angleMax = 180;//Unit is in degrees
        angleMin = 0;

        collisionRadiusMin = 0;
        collisionRadiusMax = 0;
        collisionRadius = 0;


        xMin = 300;
        xMax = 900;

        yMin = 300;
        yMax = 900;



    }

    private Vector randLoc()
    {
        double randomMagnitude = magnitudeMin + (magnitudeMax - magnitudeMin) * r.nextDouble();
        double randomAngle = angleMin + (angleMax - angleMin) * r.nextDouble();
        return Vector.make2DPolar(randomMagnitude,randomAngle);
    }

//    private ArrayList<PhysicsObject> populateObstacles(Player player)
//    {
//       Vector playerLocation = player.getLocation();
//        Vector actualLocation = getActualSpawnLocation(magnitudeMin + (magnitudeMax - magnitudeMin) * r.nextDouble(),
//                angleMin + (angleMax - angleMin) * r.nextDouble()).add(playerLocation);
//      // obstaclesList.add(new BlackHole())
//        return obstaclesList;
//    }


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
}
