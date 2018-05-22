package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

public class StarForge {

    private final int seedStart, seedEnd;

    public StarForge (int seed) {
        int tempSeed = Math.abs(seed);
        String s_tempSeed = Integer.toString(tempSeed);
        if (tempSeed > 999) {
            s_tempSeed = s_tempSeed.substring(0, 4);
        } else if (tempSeed < 10) {
            s_tempSeed = s_tempSeed.concat("000");
        } else if (tempSeed < 100) {
            s_tempSeed = s_tempSeed.concat("00");
        } else {
            s_tempSeed = s_tempSeed.substring(0,4);
        }

        seedStart = Integer.parseInt(s_tempSeed.substring(0, 2));
        seedEnd = Integer.parseInt(s_tempSeed.substring(2, 4));
    }

    public void generateStars(StarFieldChunk chunk, int starCount) {
        int[] starValues = new int[3];
        int[] sequenceValues = initializeChunk(chunk.getxLoc(), chunk.getyLoc());
        int currentStarValue;

        int starCounter = 0;

        while(starCounter < starCount) {
            for (int i = 0; i < 3; i++) {
                currentStarValue = getNextNum(sequenceValues);
                currentStarValue = fixValue(currentStarValue);
                starValues[i] = currentStarValue;
                sequenceValues[0] = sequenceValues[1];
                sequenceValues[1] = currentStarValue;
            }

            chunk.addStar(getStar(starValues));
            starCounter++;
        }
    }

    private int[] initializeChunk (int xLoc, int yLoc) {
        //first fibonacci number.
        int startNum = seedStart
                //the x location of the chunk so the generator always produces the same stars for this chunk.
                + Integer.parseInt(Integer.toString(Math.abs(xLoc) + 1000).substring(0, 2));

        //second fibonacci number.
        int endNum = seedEnd
                //the y location of the chunk so the generator always produces the same stars for this chunk.
                + Integer.parseInt(Integer.toString(Math.abs(yLoc) + 1000).substring(0, 2));

        //array of two fibonacci numbers.
        return new int[]{startNum, endNum};
    }

    private int getNextNum(int[] sequenceValues) {
        return sequenceValues[0] + sequenceValues[1];
    }

    private int fixValue (int aStarValue) {
        if (aStarValue > 999) {
            return Integer.parseInt(Integer.toString(aStarValue).substring(1,4));
        } else return aStarValue;
    }

    private BackgroundStar getStar (int[] starValues) {
        int radius = starValues[2] % 10 + 1;
        int colour = starValues[2] % 3;
        return new BackgroundStar(starValues[0], starValues[1], radius, colour);
    }
}
