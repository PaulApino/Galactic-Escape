package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class StarForge {

    private final String starSeed;
    private String starStringSequence;
    private int s_start, s_end, s_current;

    public StarForge (int seed) {
        int tempSeed = Math.abs(seed);
        String s_tempSeed = Integer.toString(tempSeed);
        if (tempSeed > 999) {
            starSeed = s_tempSeed.substring(0, 4);
        } else if (tempSeed < 10) {
            starSeed = s_tempSeed.concat("000");
        } else if (tempSeed < 100) {
            starSeed = s_tempSeed.concat("00");
        } else if (tempSeed < 1000) {
            starSeed = s_tempSeed.concat("0");
        } else {
            starSeed = s_tempSeed.substring(0,4);
        }
        starStringSequence = "";
    }

    public void generateChunk (StarFieldChunk chunk, int starCount) {
        initializeChunk(chunk.getChunkLocation());

        int starStringsCounter = (starCount * 9) - 1;
        int starValuesCounter = 0;

        for (int i = 1; starStringSequence.length() <= starStringsCounter; i++) {
            s_current = getNextNum();
            fixCurrent();
            starStringSequence += s_current;
            starValuesCounter++;

            if (starValuesCounter >= 3 && starValuesCounter % 3 == 0) {
                chunk.addStar(getStar(i));
            }
            s_start = s_end;
            s_end = s_current;
        }
    }

    private void initializeChunk (Vector chunkLocation) {
        String chunkX = Integer.toString(Math.abs((int)chunkLocation.getX()) + 1000).substring(0,2);
        String chunkY = Integer.toString(Math.abs((int)chunkLocation.getY()) + 1000).substring(2, 4);
        s_start = Integer.parseInt(starSeed.substring(0, 2)) + Integer.parseInt(chunkX);
        s_end = Integer.parseInt(starSeed.substring(2, 4)) + Integer.parseInt(chunkY);
    }

    private int getNextNum () {
        return s_start + s_end;
    }

    private void fixCurrent() {
        if (s_current > 999) {
            s_current = Integer.parseInt(Integer.toString(s_current).substring(1, 4));
        } else if (s_current < 10) {
            starStringSequence += 00;
        } else if (s_current < 100) {
            starStringSequence += 0;
        }
    }

    private BackgroundStar getStar (int index) {
        return new BackgroundStar(Vector.make2D(
                Double.parseDouble(starStringSequence.substring(index - 9, index - 6)),
                Double.parseDouble(starStringSequence.substring(index - 6, index - 3))),
                Double.parseDouble(starStringSequence.substring(index - 3, index)));
    }
}
