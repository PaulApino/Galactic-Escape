package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

public class StarFieldBackground {

    private final static int NORTH = 0;
    private final static int EAST = 1;
    private final static int WEST = 2;
    private final static int SOUTH = 3;
    private final static int NORTH_EAST = 4;
    private final static int NORTH_WEST = 5;
    private final static int SOUTH_EAST = 6;
    private final static int SOUTH_WEST = 7;

    private StarFieldChunk[][] starFieldChunks;
    private final StarForge starForge;

    private int centreChunkX;
    private int centreChunkY;

    private final int maxMatrixColumn;
    private final int maxMatrixRow;
    private final int sideChunkCountX;
    private final int sideChunkCountY;

    public StarFieldBackground(StarForge starForge, int centreChunkX, int centreChunkY, int maxMatrixColumn, int maxMatrixRow) {
        this.starForge = starForge;
        this.centreChunkX = centreChunkX;
        this.centreChunkY = centreChunkY;
        this.maxMatrixColumn = maxMatrixColumn;
        this.maxMatrixRow = maxMatrixRow;
        this.sideChunkCountX = maxMatrixColumn / 2;
        this.sideChunkCountY = maxMatrixRow / 2;

        initGenerate();
    }

    private void initGenerate() {
        starFieldChunks = new StarFieldChunk[maxMatrixRow][maxMatrixColumn];

        int currentChunkX = centreChunkX - (sideChunkCountX * 1000);
        int currentChunkY = centreChunkY + (sideChunkCountY * 1000);

        for (int i = 0; i < maxMatrixRow; i++) {
            for (int j = 0; j < maxMatrixColumn; j++) {
                starFieldChunks[i][j] = new StarFieldChunk(currentChunkX, currentChunkY);
                generateChunk(starFieldChunks[i][j]);
                currentChunkX += 1000;
            }
            currentChunkY -= 1000;
        }
    }

    private void generateChunk(StarFieldChunk starFieldChunk) {
        starFieldChunk.generateChunk(starForge, 100);
    }

    public StarFieldChunk[][] getStarFieldChunks() {
        return starFieldChunks;
    }

    public int getCentreChunkX() {
        return centreChunkX;
    }

    public int getCentreChunkY() {
        return centreChunkY;
    }

    public boolean backgroundUpdate (int newCentreChunk) {

        StarFieldChunk[][] newBackground = new StarFieldChunk[maxMatrixRow][maxMatrixColumn];

        switch (newCentreChunk) {
            case NORTH : {
                centreChunkY += 1000;

                int currentNewChunkX = centreChunkX - (sideChunkCountX * 1000);
                int currentNewChunkY = centreChunkY + (sideChunkCountY * 1000);

                for (int i = 1; i < maxMatrixRow; i++) {
                    for (int j = 0; j < maxMatrixColumn; j++) {
                        newBackground[i][j] = starFieldChunks[i - 1][j];
                    }
                }

                for (int j = 0; j < maxMatrixColumn; j++) {
                    newBackground[0][j] = new StarFieldChunk(currentNewChunkX, currentNewChunkY);
                    generateChunk(newBackground[0][j]);
                    currentNewChunkX += 1000;
                }

                starFieldChunks = newBackground;
                return true;
            }

            case EAST : {
                centreChunkX += 1000;

                int currentNewChunkX = centreChunkX + (sideChunkCountX * 1000);
                int currentNewChunkY = centreChunkY + (sideChunkCountY * 1000);

                for (int i = 0; i < maxMatrixRow; i++) {
                    for (int j = 0; j < maxMatrixColumn - 1; j++) {
                        newBackground[i][j] = starFieldChunks[i][j + 1];
                    }
                }

                for (int i = 0; i < maxMatrixRow; i++) {
                    newBackground[i][maxMatrixColumn - 1] = new StarFieldChunk(currentNewChunkX, currentNewChunkY);
                    generateChunk(newBackground[i][maxMatrixColumn -1]);
                    currentNewChunkY -= 1000;
                }

                starFieldChunks = newBackground;
                return true;
            }

            case WEST : {
                centreChunkX -= 1000;

                int currentNewChunkX = centreChunkX - (sideChunkCountX * 1000);
                int currentNewChunkY = centreChunkY + (sideChunkCountY * 1000);

                for (int i = 0; i < maxMatrixRow; i++) {
                    for (int j = 1; j < maxMatrixColumn; j++) {
                        newBackground[i][j] = starFieldChunks[i][j - 1];
                    }
                }

                for (int i = 0; i < maxMatrixRow; i++) {
                    newBackground[i][0] = new StarFieldChunk(currentNewChunkX, currentNewChunkY);
                    generateChunk(newBackground[i][0]);
                    currentNewChunkY -= 1000;
                }

                starFieldChunks = newBackground;
                return true;
            }

            case SOUTH : {
                centreChunkY -= 1000;

                int currentNewChunkX = centreChunkX - (sideChunkCountX * 1000);
                int currentNewChunkY = centreChunkY - (sideChunkCountY * 1000);

                for (int i = 0; i < maxMatrixRow - 1; i++) {
                    for (int j = 0; j < maxMatrixColumn; j++) {
                        newBackground[i][j] = starFieldChunks[i + 1][j];
                    }
                }

                for (int j = 0; j < maxMatrixColumn; j++) {
                    newBackground[maxMatrixRow - 1][j] = new StarFieldChunk(currentNewChunkX, currentNewChunkY);
                    generateChunk(newBackground[maxMatrixRow - 1][j]);
                    currentNewChunkX += 1000;
                }

                starFieldChunks = newBackground;
                return true;
            }

            case NORTH_EAST :
                return backgroundUpdate(NORTH) && backgroundUpdate(EAST);

            case NORTH_WEST :
                return backgroundUpdate(NORTH) && backgroundUpdate(WEST);

            case SOUTH_EAST :
                return backgroundUpdate(SOUTH) && backgroundUpdate(EAST);

            case SOUTH_WEST :
                return backgroundUpdate(SOUTH) && backgroundUpdate(WEST);

            default: return false;
        }
    }
}
