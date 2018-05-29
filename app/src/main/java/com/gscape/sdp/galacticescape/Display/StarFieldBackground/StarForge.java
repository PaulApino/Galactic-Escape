package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

/**
 * Generates star in 1000 x 1000 px area using a given seed,
 * a chunk will generate stars in the same location and size
 * with the same seed.
 * @author Paul Apino
 */
public class StarForge {

    //the seed split into two numbers.
    private final int seedStart, seedEnd;

    /**
     * An instance of StarForge that uses the given seed to
     * generate a given StarFieldChunk.
     * @param seed
     */
    public StarForge (int seed) {
        int tempSeed = Math.abs(seed);
        String s_tempSeed = Integer.toString(tempSeed);
        //if seed is 1 digit long, append three 0 to make 4 digit seed.
        if (tempSeed < 10) {
            s_tempSeed = s_tempSeed.concat("000");
        }

        //if seed is 2 digit long, append two 0 to make 3 digit seed.
        else if (tempSeed < 100) {
            s_tempSeed = s_tempSeed.concat("00");
        }

        //if seed is 3 digit long, append a 0 to make 4 digit seed.
        else if (tempSeed < 1000) {
            s_tempSeed = s_tempSeed.concat("0");
        }

        //if seed is greater or equal to 4 digits, take the first 4 numbers as seed.
        else {
            s_tempSeed = s_tempSeed.substring(0,4);
        }

        seedStart = Integer.parseInt(s_tempSeed.substring(0, 2));   //first 2 digits of the seed.
        seedEnd = Integer.parseInt(s_tempSeed.substring(2, 4));     //last 2 digits of the seed.
    }

    /**
     * Takes in a StarFieldChunk and populates the chunk pseudo-randomly
     * using the seed as a starting number for the Fibonacci sequence number
     * generator.
     * @param chunk the chunk to be populated with stars.
     * @param starCount the amount of stars to be generated in the chunk.
     */
    public void generateStars(StarFieldChunk chunk, int starCount) {
        //3 values required to generate a star. [0] = xLocation, [1] = yLocation, [2] = radius and color.
        int[] starValues = new int[3];
        //two numbers for the Fibonacci sequence, makes the initial values base on chunk constants.
        int[] sequenceValues = initializeChunk(chunk.getxLoc(), chunk.getyLoc());
        //a generated star value.
        int currentStarValue;

        //tracks the amount of stars generated.
        int starCounter = 0;

        //loops until chunk is populated with the specified star count
        while(starCounter < starCount) {

            //loops to generate 3 star values
            for (int i = 0; i < 3; i++) {
                currentStarValue = getNextNum(sequenceValues);
                currentStarValue = fixValue(currentStarValue);
                starValues[i] = currentStarValue;
                sequenceValues[0] = sequenceValues[1];
                sequenceValues[1] = currentStarValue;
            }

            //adds the star to chunk
            chunk.addStar(getStar(starValues));
            starCounter++;
        }
    }

    /**
     * Starts the generation of the chunk by returning the first 2
     * sequence numbers that is base on constant chunk location.
     * @param xLoc the x-coordinate location of the chunk.
     * @param yLoc the y-coordinate location of the chunk.
     * @return returns the first 2 sequence numbers.
     */
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

    /**
     * Gives the next number in the current sequence.
     * @param sequenceValues the last 2 numbers in the current sequence.
     * @return return the next number in the sequence.
     */
    private int getNextNum(int[] sequenceValues) {
        return sequenceValues[0] + sequenceValues[1];
    }

    /**
     * Fixes the new sequence number to be 3 digits long.
     * @param aStarValue the generated number to be fixed to 3 digit star value.
     * @return returns the fixed star value.
     */
    private int fixValue (int aStarValue) {
        //if star has more that 3 digits, return the last 3 digits of the number.
        if (aStarValue > 999) {
            return Integer.parseInt(Integer.toString(aStarValue).substring(1,4));
        } else return aStarValue;
    }

    /**
     * Generates a star from the given values.
     * @param starValues the values used to make a new star
     * @return returns a star with the given values.
     */
    private BackgroundStar getStar (int[] starValues) {
        //uses the 3rd value for radius ranging from 1 - 10.
        int radius = starValues[2] % 10 + 1;
        //uses the 3rd value for choosing 3 colors ranging from 0 - 2;
        int colour = starValues[2] % 3;
        return new BackgroundStar(starValues[0], starValues[1], radius, colour);
    }
}
