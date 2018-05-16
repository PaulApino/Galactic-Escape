package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class StarForge {

    private final String starSeed;

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
    }

    public void generateChunk (StarFieldChunk chunk, int starCount) {
        String starStringSequence = "";

        int arrStartNum[] = initializeChunk(chunk.getChunkLocation());
        int s_start = arrStartNum[0];
        int s_end = arrStartNum[1];
        int s_current;

        int starStringsCounter = (starCount * 9) - 1;
        int starValuesCounter = 0;

        while (starStringSequence.length() < starStringsCounter) {
            s_current = getNextNum(s_start, s_end);
            s_current = fixCurrent(starStringSequence, s_current);
            starStringSequence += s_current;
            starValuesCounter++;

            if (starValuesCounter >= 3 && starValuesCounter % 3 == 0) {
                chunk.addStar(getStar(starStringSequence.length(), starStringSequence));
            }
            s_start = s_end;
            s_end = s_current;
        }
    }

    private int[] initializeChunk (Vector chunkLocation) {
        String chunkX = Integer.toString(Math.abs((int)chunkLocation.getX()) + 1000).substring(0,2);
        String chunkY = Integer.toString(Math.abs((int)chunkLocation.getY()) + 1000).substring(2, 4);
        int startNum = Integer.parseInt(starSeed.substring(0, 2)) + Integer.parseInt(chunkX);
        int endNum = Integer.parseInt(starSeed.substring(2, 4)) + Integer.parseInt(chunkY);
        int[] arrInt = {startNum, endNum};
        return arrInt;
    }

    private int getNextNum (int s_start, int s_end) {
        return s_start + s_end;
    }

    private int fixCurrent(String starStringSequence, int s_current) {
        if (s_current > 999) {
            return Integer.parseInt(Integer.toString(s_current).substring(1, 4));
        }
        if (s_current < 10) {
            starStringSequence.concat("00");
        } else if (s_current < 100) {
            starStringSequence.concat("0");
        }
        return s_current;
    }

    private BackgroundStar getStar (int index, String starStringSequence) {
        double radius = Double.parseDouble(starStringSequence.substring(index - 1, index)) + 3;
        return new BackgroundStar(Vector.make2D(
                Double.parseDouble(starStringSequence.substring(index - 9, index - 6)),
                Double.parseDouble(starStringSequence.substring(index - 6, index - 3))),
                radius);
    }
}
