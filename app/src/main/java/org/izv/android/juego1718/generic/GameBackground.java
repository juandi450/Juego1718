package org.izv.android.juego1718.generic;

import org.izv.android.juego1718.media.Image;

public class GameBackground extends GameObject {

    public enum Movement {
        MOVING, MOVING_WITH_TOUCH, STILL
    }

    private Movement movement = Movement.STILL;
    private int backgroundX;

    public GameBackground(Image background) {
        super(background);
        this.backgroundX = this.getImage().getBitmap().getWidth();
    }

    private int getBackgroundX() {
        return this.backgroundX;
    }

    private void movingWithTouch() {
        if(getTouchHandler().isMoving()){
            if(getTouchHandler().isMovingLeft()){
                setSpeedX(3);
            } else {
                setSpeedX(-3);
            }
        } else {
            setSpeedX(0);
        }
    }

    @Override
    public void paint() {
        getGraphics().drawImage(this.getImage(), this.getX(), 0);
        getGraphics().drawImage(this.getImage(), this.getBackgroundX(), 0);
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    private void setBackgroundX(int x) {
        this.backgroundX = x;
    }

    @Override
    public void update() {
        switch (movement){
            case MOVING:
                setSpeedX(-3);
                break;
            case MOVING_WITH_TOUCH:
                movingWithTouch();
                break;
            case STILL:
                break;
        }
        this.setX(this.updateBackgroundX(this.getX()));
        this.setBackgroundX(this.updateBackgroundX(this.getBackgroundX()));
    }

    private int updateBackgroundX(int x) {
        x += this.getSpeedX();
        if(this.getSpeedX() < 0) {
            if (x <= - this.getImage().getBitmap().getWidth()) {
                x += this.getImage().getBitmap().getWidth()*2;
            }
        } else if(this.getSpeedX() > 0) {
            if (x > this.getImage().getBitmap().getWidth()) {
                x -= this.getImage().getBitmap().getWidth()*2;
            }
        }
        return x;
    }
}