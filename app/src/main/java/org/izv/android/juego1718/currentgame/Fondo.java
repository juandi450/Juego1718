package org.izv.android.juego1718.currentgame;

import org.izv.android.juego1718.generic.GameBackground;

public class Fondo extends GameBackground {

    public Fondo() {
        super(Assets.bg);
        this.setMovement(Movement.MOVING);
    }
}
