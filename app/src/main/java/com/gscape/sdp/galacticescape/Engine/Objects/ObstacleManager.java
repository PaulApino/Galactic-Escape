package com.gscape.sdp.galacticescape.Engine.Objects;

import com.gscape.sdp.galacticescape.Engine.Objects.Obstacle.Obstacle;

import java.util.ArrayList;

public class ObstacleManager {

    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int color;
    private long startTime;

    public ObstacleManager(int playerGap, int obstacleGap,int color)
    {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        startTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        populateObstacles();
    }

    private void populateObstacles()
    {


    }


}
