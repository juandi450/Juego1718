package org.izv.android.juego1718.media;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

public class Graphics {

    private AssetManager assets;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Resources resources;

    public Graphics(Resources resources, AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
        this.resources = resources;
    }

    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    public void drawImage(Image image, int x, int y) {
        canvas.drawBitmap(image.bitmap, x, y, null);
    }

    public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        Rect srcRect = new Rect();
        Rect dstRect = new Rect();
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;
        canvas.drawBitmap(image.bitmap, srcRect, dstRect, null);
    }

    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    public void drawScaledImage(Image image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight) {
        Rect srcRect = new Rect();
        Rect dstRect = new Rect();
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;
        canvas.drawBitmap(image.bitmap, srcRect, dstRect, null);
    }

    public void drawString(String text, int x, int y, Paint paint) {
        canvas.drawText(text, x, y, paint);
    }

    public int getHeight() {
        return frameBuffer.getHeight();
    }

    public int getWidth() {
        return frameBuffer.getWidth();
    }

    public Image newImage(String fileName) {
        return this.newImage(fileName, Bitmap.Config.RGB_565);
    }

    public Image newImage(String fileName, Bitmap.Config format) {
        Options options = new Options();
        options.inPreferredConfig = format;
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = this.assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null) {
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return new Image(bitmap, bitmap.getConfig());
    }

    public Image newImage(int resourceId){
        return new Image(BitmapFactory.decodeResource(resources, resourceId), null);
    }

}