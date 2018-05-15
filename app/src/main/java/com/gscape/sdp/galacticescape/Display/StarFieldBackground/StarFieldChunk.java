package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;

public class StarFieldChunk {

    private final ArrayList<BackgroundStar> stars;
    private final Vector chunkLocation;

    public StarFieldChunk(Vector chunkLocation) {
        this.chunkLocation = chunkLocation;
        stars = new ArrayList<>(50);
    }

    public Vector getChunkLocation() {
        return chunkLocation;
    }

    public void addStar (BackgroundStar aStar) {
        stars.add(aStar);
    }
}
