package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;

public class StarFieldChunk {

    private final ArrayList<BackgroundStar> stars;
    private final Vector chunkLocation;
    private final Paint paint;

    public StarFieldChunk(Vector chunkLocation) {
        this.chunkLocation = chunkLocation;
        stars = new ArrayList<>(50);
        paint = new Paint(Color.WHITE);
    }

    public Vector getChunkLocation() {
        return chunkLocation;
    }

    public void addStar (BackgroundStar aStar) {
        stars.add(aStar);
    }

    public View getChunkView (Context context) {
        StarFieldChunkView chunkView = new StarFieldChunkView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(1000, 1000);
        chunkView.setLayoutParams(layoutParams);
        chunkView.setBackgroundColor(Color.BLACK);
        return chunkView;
    }

    private class StarFieldChunkView extends View {

        public StarFieldChunkView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (BackgroundStar aStar : stars) {
                canvas.drawCircle((float)aStar.getLocation().getX(), (float)aStar.getLocation().getY(), stars.size(), paint);
            }
        }
    }
}
