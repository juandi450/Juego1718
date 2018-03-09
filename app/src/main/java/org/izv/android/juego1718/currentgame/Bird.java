package org.izv.android.juego1718.currentgame;

import org.izv.android.juego1718.generic.GameObject;
import org.izv.android.juego1718.media.Animation;
import org.izv.android.juego1718.media.Image;

/**
 * Created by Usuario on 24/02/2018.
 */

public class Bird extends GameObject {
    private Animation animation;
    private long tiempo;
    private boolean chocan;
    public Bird() {
        super(Assets.bird);
        this.setSpeedX(-10);
        this.setSpeedY(12);
        animation = new Animation();
        animation.addFrame(Assets.bird, 500);
        animation.addFrame(Assets.bird2, 500);


    }

    @Override
    public void update() {
        animation.update(System.currentTimeMillis() - tiempo);
        tiempo = System.currentTimeMillis();
        this.setImage(animation.getImage());
        this.setSpeedX(-1);


        if (this.getX() > this.getGraphics().getWidth()) {
            this.setSpeedX(- this.getSpeedX());
        }
        this.setX(this.getX() + this.getSpeedX());

    }
}
