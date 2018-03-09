package org.izv.android.juego1718.input;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class TouchHandler implements View.OnTouchListener {

    boolean isMoving, isMovingRight, isMovingUp, isTouched, isVertical,isTouch,isMovingLeft;
    float scaleX, scaleY;
    int touchX, touchY;

    public TouchHandler(Context context, View view, float scaleX, float scaleY, boolean isVertical) {
        view.setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.isVertical = isVertical;
    }

    public int getTouchX() {
        return this.touchX;
    }

    public int getTouchY() {
        return this.touchY;
    }

    public boolean isMoving() {
        return this.isMoving;
    }

    public boolean isMovingDown() {
        return !this.isMovingUp;
    }

    public boolean isMovingLeft() {
        return !this.isMovingRight;
    }

    public boolean isMovingRight() {
        return this.isMovingRight;
    }

    public boolean isTouch(){return this.isTouch;}

    public boolean isTouchDown() {
        return this.isTouched;
    }

    public boolean isMovingUp() {
        return this.isMovingUp;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.isMoving = false;
        synchronized(this) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    this.isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    this.isTouched = true;
                    this.isMoving = true;
                    break;
                case MotionEvent.ACTION_UP:
                    this.isTouched = false;
                    break;
            }
            if(this.touchY < event.getY() * this.scaleY ){
                this.isMovingRight = false;
            } else if(!isTouch) {
                this.isMovingRight = true;
            }
            if(!isMovingRight){
                this.isMovingLeft=false;
            }else{
                this.isMovingLeft=true;
            }

            if(this.touchX > event.getX() * this.scaleX  ){
                this.isMovingUp = false;
            } else if(!isTouch){
                this.isMovingUp = true;
            }
            if(this.isTouched){
                this.isTouch=false;
            }else{
                this.isTouch=true;
            }
            this.touchX = (int)(event.getX() * this.scaleX);
            this.touchY = (int)(event.getY() * this.scaleY);
            return true;
        }
    }
}