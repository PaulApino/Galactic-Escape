package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.Random;

public class BackgroundStar {

    private final Vector location;
    private final double radius;
    private final Paint paint;

    private final static int[] STAR_COLORS =
            {Color.WHITE,
            Color.parseColor("#9bf2e7"),
            Color.parseColor("#f1ce96")};

    public BackgroundStar(Vector location, double radius, int colour) {
        this.location = location;
        this.radius = radius;
        Random rand = new Random();
        int chosenColour = STAR_COLORS[colour];
        paint = new Paint();

        if (radius > 1) {
            paint.setStrokeWidth(1f);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setShader(new RadialGradient(
                    (float) location.getX(),
                    (float) location.getY(),
                    (float) radius,
                    chosenColour, Color.TRANSPARENT, Shader.TileMode.MIRROR));
        } else {
            paint.setColor(chosenColour);
        }
    }

    public Vector getLocation() {
        return location;
    }

    public double getRadius() {
        return radius;
    }

    public Paint getPaint() {
        return paint;
    }
}
