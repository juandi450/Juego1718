package org.izv.android.juego1718;

import org.izv.android.juego1718.currentgame.Background;
import org.izv.android.juego1718.currentgame.Bird;
import org.izv.android.juego1718.currentgame.BouncingBall;
import org.izv.android.juego1718.generic.GenericGameActivity;

public class GameActivity extends GenericGameActivity {

    //private BouncingBall bouncingBall;
    private Bird bird;

    @Override
    public void paint() {
        super.paint();
    }

    @Override
    public void start() {
        addGameObject(new Background());
        bird = new Bird();
        addGameObject(bird);
    }

    @Override
    public void update() {
        super.update();
    }
}