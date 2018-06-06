package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;

public class BackgroundStar {


    private final int xLoc;
    private final int yLoc;
    private final int radius;
    private final Paint paint;

    private final static int[] STAR_COLORS =
            {Color.WHITE,
            Color.parseColor("#ce96f1e3"),
            Color.parseColor("#cef1ce96")};

    public BackgroundStar(int xLoc, int yLoc, int radius, int colour) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.radius = radius;
        int chosenColour = STAR_COLORS[colour];
        paint = new Paint();

        if (radius > 1) {
            paint.setStrokeWidth(1f);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setShader(new RadialGradient(
                    (float) xLoc,
                    (float) yLoc,
                    (float) radius,
                    chosenColour, Color.TRANSPARENT, Shader.TileMode.MIRROR));
        } else {
            paint.setColor(chosenColour);
        }
    }

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public double getRadius() {
        return radius;
    }

    public Paint getPaint() {
        return paint;
    }
}
