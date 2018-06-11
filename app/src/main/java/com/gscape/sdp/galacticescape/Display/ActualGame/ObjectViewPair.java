package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.R;

import java.util.Random;

public class ObjectViewPair {

//    private static final Bitmap image

    private static final Random rand = new Random();

    private final PhysicsObject physicsObject;
    private final ImageView imageView;

    public ObjectViewPair(PhysicsObject physicsObject, ImageView imageView) {
        this.physicsObject = physicsObject;
        this.imageView = imageView;
    }

    public PhysicsObject getPhysicsObject() {
        return physicsObject;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public static ObjectViewPair getObjectValuePair (Context context, PhysicsObject physicsObject) {
        if (physicsObject == null) throw new NullPointerException("physicsObject cannot be null.");

        int imageSize = (int)physicsObject.getCollisionRadius() * 2;
        Bitmap image;

        switch (physicsObject.getObjectType()) {
            case PLAYER: {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
                return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
            }

            case PLANET_GAS: {
                switch (rand.nextInt(2)) {
                    case 0: {
                        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.gas_planet_1);
                        return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
                    }
                    case 1: {
                        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.gas_planet_2);
                        return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
                    }
                }
            }

            case PLANET_EARTH_LIKE: {
                switch (rand.nextInt(2)) {
                    case 0: {
                        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.earth_like_1);
                        return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
                    }
                    case 1: {
                        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.earth_like_2);
                        return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
                    }
                }
            }

            case PLANET_ROCKY: {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocky_planet);
                return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
            }

            case PLANET_SCORCHED: {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.scorched_planet);
                return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
            }

            case BLACK_HOLE: {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.black_hole);
                return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
            }

            case METEOR: {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocky_planet);
                return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
            }

            case SATELLITE: {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.satellite);
                return new ObjectViewPair(physicsObject, ObjectViewPair.getImageView(context, image, imageSize));
            }

            default: throw new NullPointerException("Cannot make ImageObject pair.");
        }
    }

    private static ImageView getImageView (Context context, Bitmap image, int imageSize) {
        Bitmap resizedImage = Bitmap.createScaledBitmap(image, imageSize, imageSize, true);

        ImageView anObjectView = new ImageView(context);
        anObjectView.setImageBitmap(resizedImage);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.topMargin = Integer.MIN_VALUE;
        layoutParams.bottomMargin = Integer.MIN_VALUE;
        layoutParams.leftMargin = Integer.MIN_VALUE;
        layoutParams.rightMargin = Integer.MIN_VALUE;
        anObjectView.setLayoutParams(layoutParams);
        return anObjectView;
    }
}
