package org.izv.android.juego1718.currentgame;

import org.izv.android.juego1718.generic.GameObject;
import org.izv.android.juego1718.media.Image;

/**
 * Created by Usuario on 24/02/2018.
 */

public class Shooting extends GameObject {
    public Shooting() {
        super(Assets.shooting);
        this.setSpeedX(2);
       // this.setSpeedY(2);*
    }

    @Override
    public void update() {
        if ( this.getX() < 0) {
            this.setSpeedX(2);
        }
        this.setX(this.getX() + this.getSpeedX());
    }
}
