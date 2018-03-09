package org.izv.android.juego1718;

import org.izv.android.juego1718.currentgame.Assets;
import org.izv.android.juego1718.currentgame.Bird;
import org.izv.android.juego1718.currentgame.BouncingBall;
import org.izv.android.juego1718.currentgame.Fondo;
import org.izv.android.juego1718.currentgame.Shooting;
import org.izv.android.juego1718.currentgame.Tiillo;
import org.izv.android.juego1718.generic.GenericGameActivity;
import org.izv.android.juego1718.media.Animation;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class JuegoNuevo extends GenericGameActivity {

    //private BouncingBall b1,b2;
    private Animation animation;
    private Bird bird;
    private Tiillo t;
    private Shooting shooting1,shooting;
    private long time;
    private boolean isShooting=false;
    //public ArrayList<Shooting> listShooting;
    private long tiempo;

    @Override
    public void start() {
        animation=new Animation();
        addGameObject(new Fondo());

        shooting1=new Shooting();
        shooting1.setSpeedX(5);

        addGameObject(shooting1);
        bird=new Bird();
        bird.setSpeedX(10);
        bird.setSpeedY(12);
        addGameObject(bird);


        t = new Tiillo();
        addGameObject(t);
    }

    @Override
    public void update() {
        if(t.isTouched()){
            shooting=new Shooting();
            shooting.setX(t.getX()+63);
            shooting.setY(t.getY()+40);
            shooting.setSpeedX(5);
            addGameObject(shooting);
            isShooting=true;
        }



        if (bird.getX() < 0 ||bird.getY()>400 ) {

            Random r = new Random();
            bird.setX(680);
            System.out.println("velocidad" + bird.getSpeedX());

            bird.setY(r.nextInt(this.getGraphics().getHeight()));
            //bird.setSpeedX(1);
            addGameObject(bird);

        }
        super.update();
        if(isShooting){
            chocaBala(shooting,bird);
        }

        chocaTio(t,bird);
    }

    private boolean chocaBala(Shooting s,Bird b){
        boolean chocan=b.instersects(s);
        if(chocan){
            System.out.println("chocan");
            b.setSpeedX(0);
            animation.addFrame(Assets.birdDie,500);
            animation.addFrame(Assets.blood,500);
            animation.update(System.currentTimeMillis() - tiempo);
            b.setImage(animation.getImage());
            //b.setX(200);

            //int n=b.getX();
            /*if(bird.getX()<n+200){
                b.setY(500);
            }
            /*for(int i=0;i<100;i++){
                System.out.println("entra en el bucle");
                //b.setImage(Assets.blood);
                b.setX(b.getX()+(10*i));
                b.setY(b.getY()+(10*i));
            }*/
            //b.setY(500);
            b.setSpeedY(2);
            if(b.getY()>400){
                s.setVisible(false);
            }

        }
        return chocan;
    }

    int count=0;
    private boolean chocaTio(Tiillo t, Bird b) {
        boolean chocan = t.instersects(b);
        if (chocan) {
                t.setImage(Assets.Pdie3);
        }
        return chocan;
    }
}
