package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

public class SpaceObject {

    private Context context;
    private PhysicsObject physicsObject;
    private ImageView imageView;

    public SpaceObject(Context context, PhysicsObject physicsObject) {
        this.context = context;
        this.physicsObject = physicsObject;

        int physicsObjectDiameter = (int) physicsObject.getCollisionRadius() * 2;

        Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.temp_space_object);
        Bitmap mResizedBitmap = Bitmap.createScaledBitmap(mBitmap, physicsObjectDiameter, physicsObjectDiameter, true);

        imageView = new ImageView(context);
        imageView.setImageBitmap(mResizedBitmap);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.topMargin = Integer.MIN_VALUE;
        layoutParams.bottomMargin = Integer.MIN_VALUE;
        layoutParams.leftMargin = Integer.MIN_VALUE;
        layoutParams.rightMargin = Integer.MIN_VALUE;
        imageView.setLayoutParams(layoutParams);
    }

    public void setScreenLocation (Vector screenLocation) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)imageView.getLayoutParams();

    }
}
