package com.missionbit.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
public class Vortex {

    Vector2 position;
    public Rectangle bound;
    Image notv;
    private Animation<TextureRegion> textr;
    private Texture vortexSheet;
    private float stateTime;
    public Rectangle getBound(){return bound;}

    public Vortex(int x, int y, Image n) {
        vortexSheet = new Texture("VortexChained.png");
        TextureRegion[][] tmp1 = TextureRegion.split(vortexSheet, vortexSheet.getWidth()/2,vortexSheet.getHeight()/3);
        Array<TextureRegion> vortexFrames = new Array<TextureRegion>();
        int count = 0;
        for (int i = 0 ; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                if(count < 5) {
                    vortexFrames.add(tmp1[i][j]);
                    count++;

                }
            }
        }
        stateTime = 0f;
        textr = new Animation<TextureRegion>(.5f, vortexFrames );
        position = new Vector2(x, y);
        notv = n;
        bound = new Rectangle(notv.getX(), notv.getY(), notv.getWidth(), notv.getHeight());


    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public TextureRegion getTexture() {
        TextureRegion region = new TextureRegion();
        region = textr.getKeyFrame(stateTime, true);
        return region;
    }
    public void update(float dt) {
        stateTime += Gdx.graphics.getDeltaTime();

        bound.setPosition(position);

    }
    public Vector2 getPosition() {
        return position;
    }


}
