package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;

public class StarFieldChunkView extends View {

    private ArrayList<BackgroundStar> backgroundStars;

    public StarFieldChunkView (Context context) {
        super(context);
    }

    public StarFieldChunkView (Context context, StarFieldChunk chunk) {
        super(context);
        backgroundStars = chunk.getStars();
    }

    public void setNewChunk (StarFieldChunk starFieldChunk) {
        backgroundStars = starFieldChunk.getStars();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!backgroundStars.isEmpty()) {
            for (BackgroundStar aStar : backgroundStars) {
                canvas.drawCircle((float) aStar.getxLoc(), (float) aStar.getyLoc(), (float) aStar.getRadius(), aStar.getPaint());
            }
        }
    }
}
