package org.izv.android.juego1718.media;

import android.graphics.Bitmap;

public class Image {

    public Bitmap bitmap;
    public Bitmap.Config format;

    public Image(Bitmap bitmap) {
        this(bitmap, Bitmap.Config.RGB_565);
    }

    public Image(Bitmap bitmap, Bitmap.Config format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public void dispose() {
        bitmap.recycle();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}