package org.izv.android.juego1718.generic;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import org.izv.android.juego1718.input.TouchHandler;
import org.izv.android.juego1718.currentgame.Assets;
import org.izv.android.juego1718.media.Graphics;
import org.izv.android.juego1718.view.RenderView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class GenericGameActivity extends AppCompatActivity {

    private Graphics graphics;
    private List<GameObject> gameObjects;
    private RenderView renderView;
    private TouchHandler touchHandler;
    private WakeLock wakeLock;

    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public TouchHandler getTouchHandler() {
        return touchHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Juego");

        boolean isVertical = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int bufferWidth = isVertical ? 480: 800;
        int bufferHeight = isVertical ? 800: 480;
        Bitmap frameBuffer = Bitmap.createBitmap(bufferWidth, bufferHeight, Config.RGB_565);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int width = point.x; //getWindowManager().getDefaultDisplay().getWidth(); //deprecated
        int height = point.y;  //getWindowManager().getDefaultDisplay().getHeight(); //deprecated
        float scaleX = (float) bufferWidth / width;
        float scaleY = (float) bufferHeight / height;

        renderView = new RenderView(this, frameBuffer);
        graphics = new Graphics(getResources(), getAssets(), frameBuffer);
        touchHandler = new TouchHandler(this, renderView, scaleX, scaleY, isVertical);
        Assets.load(graphics);
        GameObject.setGraphics(graphics);
        GameObject.setTouchHandler(getTouchHandler());

        setContentView(renderView);
        gameObjects = new ArrayList<>();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        renderView.resume();
    }

    public void paint() {
        for(GameObject gameObject: gameObjects) {
            gameObject.paint();
        }
    }

    public void removeGameObjects (){
        GameObject gameObject;
        Iterator<GameObject> i = gameObjects.iterator();
        while (i.hasNext()) {
            gameObject = i.next();
            if (!gameObject.isVisible()) {
                i.remove();
            }
        }
    }

    public abstract void start();

    public void update() {
        removeGameObjects();
        for(GameObject gameObject: gameObjects) {
            gameObject.update();
        }
    }
}