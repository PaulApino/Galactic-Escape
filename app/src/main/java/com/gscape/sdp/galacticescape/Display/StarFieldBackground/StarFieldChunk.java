package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;

public class StarFieldChunk {

    private final ArrayList<BackgroundStar> stars;
    private final int xLoc;
    private final int yLoc;

    private boolean generated;

    public StarFieldChunk(int xLoc, int yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        stars = new ArrayList<>(100);

        generated = false;
    }

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void addStar (BackgroundStar aStar) {
        stars.add(aStar);
    }

    public ArrayList<BackgroundStar> getStars() {
        return stars;
    }

    public void generateChunk (StarForge theForge, int starCount) {
        theForge.generateStars(this, starCount);
        generated = true;
    }

    public boolean isGenerated() {
        return generated;
    }
}
