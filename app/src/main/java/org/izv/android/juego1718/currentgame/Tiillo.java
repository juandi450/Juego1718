package org.izv.android.juego1718.currentgame;

import org.izv.android.juego1718.generic.GameObject;
import org.izv.android.juego1718.media.Animation;

public class Tiillo extends GameObject {

    private static final int MAXSALTO = 20;

    private Animation animation,animation2;
    private long tiempo;
    private boolean saltando = false;
    private int salto = 0;

    public Tiillo() {
        super(Assets.Pquieto);
        this.setX(23);
        this.setY(400);
        animation = new Animation();
        animation.addFrame(Assets.Pgun1, 500);
        animation.addFrame(Assets.Pguns2, 500);
        animation.addFrame(Assets.Pguns3, 500);
        tiempo = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (getTouchHandler().isMoving() && getTouchHandler().isMovingUp()) {
            System.out.println("salto");
            this.setSpeedX(0);
            if (!saltando) {
                salto = -MAXSALTO;
                setSpeedY(salto);
                saltando = true;
            }
        }else {
            animation.update(System.currentTimeMillis() - tiempo);
            tiempo = System.currentTimeMillis();
            this.setImage(animation.getImage());
            this.setSpeedX(0);
        }
        if (saltando) {


            setY(getY() + getSpeedY());
            setX(getX() + getSpeedX());
            this.setImage(Assets.Pslum2);
            //this.setImage(Assets.Pslum);
           /* animation2.addFrame(Assets.Pslum,500);
            this.setImage(animation2.getImage());
            animation2.addFrame(Assets.Pslum2,500);
            this.setImage(animation2.getImage());*/

            setSpeedX(getSpeedX()+1);
            setSpeedY(getSpeedY() + 1);

            salto++;
            if (salto > MAXSALTO) {

                saltando = false;
                setSpeedY(0);
            }
            //this.setImage(Assets.Pslum3);
        }
        if (this.getX() + this.getSpeedX() > 350) {
            this.setX(350);
        } else {
            this.setX(this.getX() + this.getSpeedX());
        }
    }
}
