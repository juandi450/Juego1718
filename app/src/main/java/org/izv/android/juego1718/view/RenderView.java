package org.izv.android.juego1718.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.izv.android.juego1718.generic.GenericGameActivity;

public class RenderView extends SurfaceView implements Runnable {

    private static final long FPS = 60;
    private static final long TPS = 1000 / FPS; // Time to render frame

    private volatile boolean running = false;
    private Bitmap frameBuffer;
    private GenericGameActivity gameActivity;
    private SurfaceHolder holder; // Acceso con latencia mínima a la interfaz de usuario
    private Thread renderThread = null;

    public RenderView(GenericGameActivity gameActivity, Bitmap frameBuffer) {
        super(gameActivity);
        this.frameBuffer = frameBuffer;
        this.holder = getHolder();
        this.gameActivity = gameActivity;
    }

    public void pause() {
        running = false;
        while (true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
            }
        }
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    public void run() {
        long startTime;
        while (running) {
            startTime = System.nanoTime();
            if (!holder.getSurface().isValid()) {
                continue;
            }
            gameActivity.update();
            gameActivity.paint();

            // Este bloque se ejecuta de manera atómica
            synchronized (this) {
                try {
                    Canvas canvas = holder.lockCanvas(); // Acceso exclusivo al lienzo
                    Rect targetRectangle = new Rect();
                    canvas.getClipBounds(targetRectangle);
                    canvas.drawBitmap(frameBuffer, null, targetRectangle, null);
                    holder.unlockCanvasAndPost(canvas);
                    sleep(System.nanoTime(), startTime); // Evitar que el juego vaya demasiado rápido
                } finally {
                }
            }
        }
    }

    private void sleep(long stop, long start) {
        long difference = (stop - start) / 1000000;
        if (difference < TPS) {
            try {
                Thread.sleep(TPS - difference);
            } catch (InterruptedException e) {
            }
        }
    }
}