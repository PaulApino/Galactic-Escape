package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class StarFieldChunkView extends View {

    private ArrayList<BackgroundStar> backgroundStars;
    private final Paint PAINT = new Paint(Color.WHITE);

    public StarFieldChunkView (Context context) {
        super(context);
    }

    public StarFieldChunkView (Context context, ArrayList<BackgroundStar> backgroundStars) {
        super(context);
        this.backgroundStars = backgroundStars;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (BackgroundStar aStar : backgroundStars) {
            canvas.drawCircle((float)aStar.getLocation().getX(), (float)aStar.getLocation().getY(), (float)aStar.getRadius(), PAINT);
        }
    }
}
