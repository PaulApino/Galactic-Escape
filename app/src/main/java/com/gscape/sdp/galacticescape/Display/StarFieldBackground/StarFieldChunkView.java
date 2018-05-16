package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.View;

import com.gscape.sdp.galacticescape.R;

import java.util.ArrayList;

public class StarFieldChunkView extends View {

    private ArrayList<BackgroundStar> backgroundStars;

    public StarFieldChunkView (Context context) {
        super(context);
    }

    public StarFieldChunkView (Context context, ArrayList<BackgroundStar> backgroundStars) {
        super(context);
        this.backgroundStars = backgroundStars;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!backgroundStars.isEmpty()) {
            for (BackgroundStar aStar : backgroundStars) {
                canvas.drawCircle((float) aStar.getLocation().getX(), (float) aStar.getLocation().getY(), (float) aStar.getRadius(), aStar.getPaint());
            }
        }
    }
}
