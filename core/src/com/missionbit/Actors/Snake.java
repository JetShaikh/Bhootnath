package com.missionbit.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Snake {

    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animation;


    boolean atkIsActive;
   public Rectangle hurtbox;
   public Rectangle hitbox;
    Vector2 position;

    Texture img;


    public Snake(int x, int y) {
        img = new Texture("Characters/Snake.png");
        hitbox = new Rectangle();
        position = new Vector2(x, y);
        hurtbox = new Rectangle();
        hitbox.set(x, y, img.getWidth(), img.getHeight());
        com.badlogic.gdx.utils.Array<TextureRegion> animFrames = new com.badlogic.gdx.utils.Array<TextureRegion>();


        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 2, img.getHeight() / 2);
        for (int i = 0; i < 2 ; i++){
            for (int j = 0; i < 2; i++) {
                animFrames.add(tmp[i][j]);
            }
        }
        animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(.50f,animFrames);

    }

    public void update() {


        if (Gdx.input.justTouched()) {
            atkIsActive = true;


        }



//            if (hitbox.contains())

//            int hp = - 10 int hp;

//if (hitbox.contains(hurtbox))
//int hp = playerhp -10;


     //   public void draw (SpriteBatch batch){


       //     batch.draw(img, position.x, position.y);
        //}

    }

    public TextureRegion getTexture(float dt){
        return animation.getKeyFrame(dt);
    }

    public Vector2 getPosition(){ return position; }
}