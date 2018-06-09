package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import java.util.ArrayList;

public class StarFieldChunk {

    private final ArrayList<BackgroundStar> stars;
    private final int xLoc;
    private final int yLoc;

    public StarFieldChunk(int xLoc, int yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        stars = new ArrayList<>(100);
    }

    public int getXLoc() {
        return xLoc;
    }

    public int getYLoc() {
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
    }
}
