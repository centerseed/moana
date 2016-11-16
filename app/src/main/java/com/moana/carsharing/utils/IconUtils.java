package com.moana.carsharing.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class IconUtils {
    public static BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        int w = (int) (drawable.getIntrinsicWidth() * 1.4);
        int h = (int) (drawable.getIntrinsicHeight() * 1.4);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(bitmap);

        return descriptor;
    }
}
