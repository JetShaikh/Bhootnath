package com.missionbit.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.missionbit.States.InGame;
import com.missionbit.States.States;

public class Character extends InGame{
    public World world;

    public static final int GRAVITY = -15;
    private Polygon polyBounds;
    public States state;
    private boolean debug;
    private int hp, maxHp;
    private PolygonShape polygon;

    public Vector2 position, offset, velocity;
    public Rectangle bounds, hitbox, hurtbox;
    public float moveSpeed, jumpHeight;
    public int numJumps;
    public Texture img, camera;
    public int width, height, x, y;
    public final float MAX_VELOCITY = 1.0f;

    Vector2 vertices, impulse, point;
    boolean wake;
    private Character tapPosition;
    public TextureRegion getTexture;


    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public float getHeight() {
        return height;
    }

    private Texture texture;
    private Vector2 force;
    private Animation anim;
    private boolean faceRight, needFlip;
    private Vector3 tap;
    private final InGame game;
    private BodyDef playerDef;
    public Body playerBody;
    private PolygonShape rect;
    private FixtureDef fixtureDef;



    public Character(int i, int i1, InGame game) {
        super();
        setDebug(true);
        this.game = game;

        texture = new Texture("Characters/Ghost.png");
        force = new Vector2(0.5f, 0);
        anim = new Animation(new TextureRegion(texture), 8, 1f, 3, 3);
        setBounds(x, y, texture.getWidth() /  3, texture.getHeight() / 3);
        faceRight = true;
        tap = new Vector3();

        playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(x, y);

        setPosition(playerDef.position.x, playerDef.position.y);

        playerBody = game.world.createBody(playerDef);
        playerBody.setFixedRotation(true);

        rect = new PolygonShape();
        rect.set(new float[]{(getWidth() / 3) * States.PTM, 0, (getWidth() / 3 + getWidth() / 2 - 10) * States.PTM, 0, (getWidth() / 3 + getWidth() / 2 - 10) * States.PTM, getHeight() * States.PTM, (getWidth() / 3) * States.PTM, getHeight() * States.PTM});

        fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.density = 0.9f;
        fixtureDef.friction = 1f;

        playerBody.createFixture(fixtureDef);
        rect.dispose();
    }

    public void update(float dt) {
        if (Gdx.input.isTouched()) {
            if (getTapPosition().x > playerBody.getPosition().x + (anim.getFrame().getRegionWidth() / 2 * States.PTM)) {
                if (!faceRight) { needFlip = true; }
                faceRight = true;
                playerBody.applyLinearImpulse(force, new Vector2(getX(), getY()), true);
            } else {
                if (faceRight) { needFlip = true; }
                faceRight = false;
                playerBody.applyLinearImpulse(-force.x, force.y, getX(), getY(), true);
            }
            if (needFlip) {
                anim.flipFrames();
                needFlip = false;
            }
            anim.update(dt);
        } else {
            anim.setFrame(0);
        }
        setPosition(playerBody.getPosition().x, playerBody.getPosition().y);

        position.set(playerBody.getPosition());
        if (hp == 0) {
            System.out.println("GAME OVER");
        }
    }
    private Vector3 getTapPosition() {
        tap.set(Gdx.input.getX() , Gdx.input.getY() , 0);
        return game.camera.unproject(tap);
    }

    private void setBounds(int x, int y, int i, int i1) {

    }

    public void setPosition(float x, float y) {

    }


    public Character(int hp, InGame game, int x, int y) {
        this.game = game;
        this.hp = 100;

        hitbox = new Rectangle();
        position = new Vector2(x, y);
        hurtbox = new Rectangle();
        hitbox.set(x, y, img.getWidth(), img.getHeight());
        anim = new Animation(new TextureRegion(img), 50, 7f, 5, 10);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(img, position.x, position.y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }


        public void dispose(float dt){
            if (Gdx.input.isTouched()) {
                if (getTapPosition().x > playerBody.getPosition().x + (anim.getFrame().getRegionWidth() / 2 * States.PTM)) {

                    if (!faceRight) {
                        needFlip = true;
                    }
                    faceRight = true;
                    playerBody.applyLinearImpulse(force, new Vector2(getX(), getY()), true);
                } else {
                    if (faceRight) {
                        needFlip = true;
                    }
                    faceRight = false;
                    playerBody.applyLinearImpulse(-force.x, force.y, getX(), getY(), true);
                }
                if (needFlip) {
                    anim.flipFrames();
                    needFlip = false;
                }
                anim.update(dt);
            } else {
                anim.setFrame(0);
            }
            setPosition(playerBody.getPosition().x, playerBody.getPosition().y);
        }

        public TextureRegion getTexture () {
            return anim.getFrame();
        }
        public void dispose () {
            texture.dispose();
        }

    public Vector2 getPosition() {
        return position;
    }

    public void moveLeft() {

    }

    public void moveRight() {

    }

    public void resetAnim() {

    }

    public void attack() {

    }

    public void jump() {

    }
}