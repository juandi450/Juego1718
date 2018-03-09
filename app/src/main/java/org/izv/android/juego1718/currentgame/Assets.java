package org.izv.android.juego1718.currentgame;

import org.izv.android.juego1718.media.Graphics;
import org.izv.android.juego1718.media.Image;

public class Assets {

    public static Image background, ball, ball2, bk2, jump, right, stand,bg,Pquieto,Pgun1,Pguns2,Pguns3,Pdie,Pdie2,
            Pdie3,bird,Pslum,Pslum2,Pslum3,shooting,birdDie,blood,gameOver,bird2;

    public static void load(Graphics g) {
        Assets.background = g.newImage("background.jpg");
        Assets.bg=g.newImage("bg002.jpg");
        Assets.bk2 = g.newImage("bk2.jpg");
        Assets.ball = g.newImage("ball.png");
        Assets.ball2 = g.newImage("ball2.png");
        Assets.jump = g.newImage("jump.png");
        Assets.right = g.newImage("right.png");
        Assets.stand = g.newImage("stand.png");
        Assets.Pquieto=g.newImage("Pquieto.png");
        Assets.Pgun1=g.newImage("Pgun1.png");
        Assets.Pguns2=g.newImage("Pgun2.png");
        Assets.Pguns3=g.newImage("Pguns3.png");
        Assets.Pdie=g.newImage("Pdie.png");
        Assets.Pdie2=g.newImage("Pdie2.png");
        Assets.Pdie3=g.newImage("Pdie3.png");
        Assets.Pslum=g.newImage("Pslum1.png");
        Assets.Pslum2=g.newImage("Pslum2.png");
        Assets.Pslum3=g.newImage("Pslum3.png");
        Assets.bird=g.newImage("bird1.png");
        Assets.shooting=g.newImage("shooting.png");
        Assets.birdDie=g.newImage("birdDie.png");
        Assets.bird2=g.newImage("bird3.png");
        Assets.blood=g.newImage("sangre1.png");

        //Assets.blood=g.newImage("sangre.png");


    }
}