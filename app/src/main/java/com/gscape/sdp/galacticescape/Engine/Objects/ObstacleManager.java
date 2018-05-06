package com.gscape.sdp.galacticescape.Engine.Objects;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;
import java.util.Random;

//make it into a singleton
public class ObstacleManager {

    private double magnitudeMax;
    private double magnitudeMin;
    private double angleMax;
    private double angleMin;
    private Random r;
    private ArrayList obstaclesList;
    double massMin;
    double massMax;
    double collisionRadiusMin;
    double collisionRadiusMax;
    double collisionRadius;

    public ObstacleManager()
    {
        //based on smartphone screen size
        magnitudeMax = 5000;//Unit is in pixels(1 pixel represents 1 Megametre)
        magnitudeMin = 2000;
        angleMax = 180;//Unit is in degrees
        angleMin = 0;
        r = new Random();
        collisionRadiusMin = 400;
        collisionRadiusMax = 450;
        collisionRadius = collisionRadiusMin + (collisionRadiusMax - collisionRadiusMin) * r.nextDouble();
    }

    private Vector randLoc()
    {
        double randomMagnitude = magnitudeMin + (magnitudeMax - magnitudeMin) * r.nextDouble();
        double randomAngle = angleMin + (angleMax - angleMin) * r.nextDouble();
        return Vector.make2DPolar(randomMagnitude,randomAngle);
    }

    private ArrayList<PhysicsObject> populateObstacles(Player player)
    {
       Vector playerLocation = player.getLocation();
        Vector actualLocation = getActualSpawnLocation(magnitudeMin + (magnitudeMax - magnitudeMin) * r.nextDouble(),
                angleMin + (angleMax - angleMin) * r.nextDouble()).add(playerLocation);
      // obstaclesList.add(new BlackHole())
        return obstaclesList;

    }

    public Vector getActualSpawnLocation (double magnitude, double angle)
    {
        Vector polarLocation = Vector.make2DPolar(magnitude,angle);
        Vector transformedLocation = polarLocation.transformRotate(20);
        return transformedLocation;
    }


}
