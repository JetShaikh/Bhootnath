package com.missionbit.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime, currentFrameTime;
    private int frameCount, frame;

    Animation(TextureRegion region, int frameCount, float cycleTime, int row, int col) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / col;
        int frameHeight = region.getRegionHeight() / row;

        /*
        Add frames from the spritesheet.
        The reason why I have a count is because my spritesheet looks like:

            [1][2][3]
            [4][5][6]
            [7][8][X]

         where X is wasted space. Without the count check, my nested for loop will add that wasted
         space as a frame, bulking up the size of my animation array.
         */
        int count = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (count < frameCount) {
                    frames.add(
                            new TextureRegion(
                                    region,
                                    c * frameWidth,
                                    r * frameHeight,
                                    frameWidth,
                                    frameHeight
                            )
                    );
                    count++;
                }
            }
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

    public void flipFrames() {
        for (TextureRegion textureRegion : frames) {
            textureRegion.flip(true, false);
        }
    }




}