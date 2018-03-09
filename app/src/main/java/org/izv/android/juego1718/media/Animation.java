package org.izv.android.juego1718.media;

import java.util.ArrayList;
import java.util.List;

public class Animation {

    class Frame {

        Image image;
        long time;

        public Frame(Image image, long time) {
            this.image = image;
            this.time = time;
        }
    }

    private final List<Frame> frames;
    private int currentFrame;
    private long animationTime;
    private long totalTime;

    public Animation() {
        frames = new ArrayList<>();
        totalTime = 0;
        animationTime = 0;
        currentFrame = 0;
    }

    public void addFrame(Image image, long duration) {
        totalTime += duration;
        frames.add(new Frame(image, totalTime));
    }

    public Image getImage() {
        if (frames.isEmpty()) {
            return null;
        } else {
            return frames.get(currentFrame).image;
        }
    }

    public void update(long spendedTime) {
        if (frames.size() > 1) {
            animationTime += spendedTime;
            if (animationTime >= totalTime) {
                animationTime = animationTime % totalTime;
                currentFrame = 0;
            }
            while (animationTime > frames.get(currentFrame).time) {
                currentFrame++;
            }
        }
    }

}