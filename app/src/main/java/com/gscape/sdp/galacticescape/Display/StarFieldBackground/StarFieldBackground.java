package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.content.Context;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;

public class StarFieldBackground {

    public final static int NORTH = 0;
    public final static int EAST = 1;
    public final static int WEST = 2;
    public final static int SOUTH = 3;
    public final static int NORTH_EAST = 4;
    public final static int NORTH_WEST = 5;
    public final static int SOUTH_EAST = 6;
    public final static int SOUTH_WEST = 7;

    private final Context context;
    private final StarFieldChunkView[][] starFieldChunkViews;
    private StarFieldChunk[][] starFieldChunks;
    private final StarForge starForge;

    private int centreChunkX;
    private int centreChunkY;

    private final int maxMatrixColumn;
    private final int maxMatrixRow;
    private final int sideChunkCountX;
    private final int sideChunkCountY;

    public StarFieldBackground(Context context, StarForge starForge, ScreenValues screenValues) {
        this.starForge = starForge;
        this.centreChunkX = ((int)(screenValues.getScreenCentreLocation().getX() / 1000)) * 1000;
        this.centreChunkY = ((int)(screenValues.getScreenCentreLocation().getY() / 1000)) * 1000;
        this.sideChunkCountX = (int)Math.round(screenValues.getScreenSize().getX() / 1000);
        this.sideChunkCountY = (int)Math.round(screenValues.getScreenSize().getY() / 1000);
        this.maxMatrixColumn = sideChunkCountX * 2 + 1;
        this.maxMatrixRow = sideChunkCountY * 2 + 1;

        this.context = context;
        starFieldChunkViews = new StarFieldChunkView[maxMatrixRow][maxMatrixColumn];

        initGenerate();
    }

    private void initGenerate() {
        starFieldChunks = new StarFieldChunk[maxMatrixRow][maxMatrixColumn];

        int topLeftChunkX = centreChunkX - (sideChunkCountX * 1000);
        int currentChunkY = centreChunkY + (sideChunkCountY * 1000);

        for (int i = 0; i < maxMatrixRow; i++) {
            int currentChunkX = topLeftChunkX;
            for (int j = 0; j < maxMatrixColumn; j++) {
                starFieldChunks[i][j] = new StarFieldChunk(currentChunkX, currentChunkY);
                generateChunk(starFieldChunks[i][j]);
                currentChunkX += 1000;
            }
            currentChunkY -= 1000;
        }

        for (int i = 0; i < maxMatrixRow; i++) {
            for (int j = 0; j < maxMatrixColumn; j++) {
                starFieldChunkViews[i][j] = new StarFieldChunkView(context, starFieldChunks[i][j]);
            }
        }
    }

    private void generateChunk(StarFieldChunk starFieldChunk) {
        starFieldChunk.generateChunk(starForge, 40);
    }

    public StarFieldChunk[][] getStarFieldChunks() {
        return starFieldChunks;
    }

    public StarFieldChunkView[][] getStarFieldChunkViews () {
        return starFieldChunkViews;
    }

    public void updateStarFieldChunkViews () {
        for (int i = 0; i < maxMatrixRow; i++) {
            for (int j = 0; j < maxMatrixColumn; j++) {
                starFieldChunkViews[i][j].setNewChunk(starFieldChunks[i][j]);
                starFieldChunkViews[i][j].postInvalidate();
            }
        }
    }


    public int getCentreChunkX() {
        return centreChunkX;
    }

    public int getCentreChunkY() {
        return centreChunkY;
    }

    public int getMaxMatrixColumn() {
        return maxMatrixColumn;
    }

    public int getMaxMatrixRow() {
        return maxMatrixRow;
    }

    public int getSideChunkCountX() {
        return sideChunkCountX;
    }

    public int getSideChunkCountY() {
        return sideChunkCountY;
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
