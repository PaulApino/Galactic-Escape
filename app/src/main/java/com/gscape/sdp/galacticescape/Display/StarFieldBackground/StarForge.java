package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class StarForge {

    private final String starSeed;
    private final int seedStart, seedEnd;

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

        seedStart = Integer.parseInt(starSeed.substring(0, 2));
        seedEnd = Integer.parseInt(starSeed.substring(2, 4));
    }

    public void generateChunk (StarFieldChunk chunk, int starCount) {
        int[] starValues = new int[3];
        int[] sequenceValues = initializeChunk(chunk.getChunkLocation());
        int currentStarValue;

        int starCounter = 0;

        while(starCounter <= starCount) {
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



//        String starStringSequence = "";
//
//        int arrStartNum[] = initializeChunk(chunk.getChunkLocation());
//        int s_start = arrStartNum[0];
//        int s_end = arrStartNum[1];
//        int s_current;
//
//        int starStringsCounter = (starCount * 9) - 1;
//        int starValuesCounter = 0;
//
//        while (starStringSequence.length() < starStringsCounter) {
//            s_current = getNextNum(s_start, s_end);
//            s_current = fixCurrent(starStringSequence, s_current);
//            starStringSequence += s_current;
//            starValuesCounter++;
//
//            if (starValuesCounter >= 3 && starValuesCounter % 3 == 0) {
//                chunk.addStar(getStar(starStringSequence.length(), starStringSequence));
//            }
//            s_start = s_end;
//            s_end = s_current;
//        }
    }

    private int[] initializeChunk (Vector chunkLocation) {
        //first fibonacci number.
        int startNum = seedStart
                //the x location of the chunk so the generator always produces the same stars for this chunk.
                + Integer.parseInt(Integer.toString(Math.abs((int)chunkLocation.getX()) + 1000).substring(0,2));

        //second fibonacci number.
        int endNum = seedEnd
                //the y location of the chunk so the generator always produces the same stars for this chunk.
                + Integer.parseInt(Integer.toString(Math.abs((int)chunkLocation.getY()) + 1000).substring(0, 2));

        //array of two fibonacci numbers.
        int[] arrSequence = {startNum, endNum};
        return arrSequence;
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
//        String value3;
//        if (starValues[2] < 10) {
//            value3 = "00" + starValues[2];
//        } else if (starValues[2] < 100) {
//            value3 = "0" + starValues[2];
//        } else value3 = Integer.toString(starValues[2]);
//
//        double radius = Integer.parseInt(value3.substring(2, 3)) + 1;
//        int colour = Integer.parseInt(value3.substring(1, 2)) % 3;

        double radius = starValues[2] % 10 + 1;
        int colour = starValues[2] % 3;
        return new BackgroundStar(Vector.make2D(starValues[0], starValues[1]), radius, colour);
    }

//    private int getNextNum (int s_start, int s_end) {
//        return s_start + s_end;
//    }
//
//    private int fixCurrent(String starStringSequence, int s_current) {
//        if (s_current > 999) {
//            return Integer.parseInt(Integer.toString(s_current).substring(1, 4));
//        }
//        if (s_current < 10) {
//            starStringSequence.concat("00");
//        } else if (s_current < 100) {
//            starStringSequence.concat("0");
//        }
//        return s_current;
//    }
//
//    private BackgroundStar getStar (int index, String starStringSequence) {
//        double radius = Double.parseDouble(starStringSequence.substring(index - 1, index)) + 3;
//        return new BackgroundStar(Vector.make2D(
//                Double.parseDouble(starStringSequence.substring(index - 9, index - 6)),
//                Double.parseDouble(starStringSequence.substring(index - 6, index - 3))),
//                radius);
//    }
}
