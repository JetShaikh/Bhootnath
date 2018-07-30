package com.missionbit.Actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.missionbit.States.InGame;


public class Player {
    Texture runSheet,attackSheet,jumpSheet;
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> runanim,afkanim,jumpanim,fireanim,atackanim,dedanim;

    private Animation an;
    boolean atkIsActive;
    Rectangle hurtbox2;
    Rectangle hitbox2;
    Vector2 position;
    private Vector2 velocity;
    private static final int GRAVITY = -15;
    private boolean isAttacking;
    private int numJumps;
    private int hp;
    private int maxhp = 100;
    Texture img;
    private float stateTime;
    private float width;
    private float height;

    public Player(int hp, int x, InGame inGame) {

    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private enum AnimState{JUMPING,ATTACKING,RUNING,IDOLING,DEATH,FIRE}
    private AnimState currentState, previousState;
    private boolean isFire;
private Rectangle vorHitbox;
public Rectangle getBounds(){return hurtbox2;}

    public Vector2 getPosition() {
        return position;
    }

    class playerhp {
    }
    public Player(int hp, int x, int y) {
        this.hp = 100;

        currentState = AnimState.IDOLING;
        previousState = currentState;
        Image vortex = new Image(new Texture("VortexChained.png"));
        velocity = new Vector2();
        img = new Texture("Characters/Ghost.png");

        hitbox2 = new Rectangle();
        position = new Vector2();
        hurtbox2 = new Rectangle();
        hurtbox2.setWidth(32);
        hurtbox2.setHeight(32);
        hitbox2.set(x, y, img.getWidth(), img.getHeight());

        isAttacking = false;

        stateTime = 0f;
//Specifing which frames to use
        TextureRegion[][] tmp1 = TextureRegion.split(img, img.getWidth() / 10, img.getHeight() / 5);
        com.badlogic.gdx.utils.Array<TextureRegion> afkFrames = new com.badlogic.gdx.utils.Array<TextureRegion>();
        com.badlogic.gdx.utils.Array<TextureRegion> runFrames = new com.badlogic.gdx.utils.Array<TextureRegion>();
        com.badlogic.gdx.utils.Array<TextureRegion> attackFrames = new com.badlogic.gdx.utils.Array<TextureRegion>();
        com.badlogic.gdx.utils.Array<TextureRegion> fireFrames = new com.badlogic.gdx.utils.Array<TextureRegion>();
        com.badlogic.gdx.utils.Array<TextureRegion> dedFrames = new com.badlogic.gdx.utils.Array<TextureRegion>();

        for (int i = 0; i < 10 ; i++){
            afkFrames.add(tmp1[0][i]);
        }
        for (int i = 0; i < 10 ; i++){
            fireFrames.add(tmp1[1][i]);
        }
        for (int i = 0; i < 10 ; i++){
            runFrames.add(tmp1[2][i]);
        }
        for (int i = 0; i < 10 ; i++){
            attackFrames.add(tmp1[3][i]);
        }
        for (int i = 0; i < 10 ; i++){
            dedFrames.add(tmp1[4][i]);
        }
        afkanim = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(.50f,afkFrames);

        runanim = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(.17f,runFrames);

        atackanim = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(.20f,attackFrames);

        fireanim = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(.25f,fireFrames);

        dedanim = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(.25f,dedFrames);



    }

   public void atack(){

        if (!isAttacking){
            isAttacking = true;
        }
   }


   public void vortex(){



   }

    public void jump() {
        velocity.y = 275;
    }

    public void resetAnim() {
        velocity.x = 0;
    }

    public void moveLeft() {
        velocity.x = -150;

    }

    public void moveRight() {
        velocity.x = 150;

    }

    public void fire(){
        if (isFire = true){
            Image vortex = new Image(new Texture("VortexChained.png"));
            vortex.setPosition(200,100);
    }
    }



    public  TextureRegion getTexture(float dt) {
        TextureRegion region;


        switch (currentState) {
            case ATTACKING:
                region = atackanim.getKeyFrame(stateTime,false);
                break;
            case RUNING:
                region = runanim.getKeyFrame(stateTime,true);
                break;
            case FIRE:
                region = fireanim.getKeyFrame(stateTime,false);
                break;
            case DEATH:
                region = dedanim.getKeyFrame(stateTime,false);
                break;
            case IDOLING:
            case JUMPING:
                default:
                region = afkanim.getKeyFrame(stateTime,true);

        }

        if (velocity.x < 0 && !region.isFlipX()) {
            region.flip(true,false);
        } else if (velocity.x > 0 && region.isFlipX()) {
            region.flip(true,false);
        }



        stateTime = currentState == previousState ? stateTime + dt : 0;
        previousState = currentState;



        return region;
    }
    public void update(float dt) {
        stateTime += Gdx.graphics.getDeltaTime();
//        if ( hp= 0)
//            System.out.println("");
        if (position.y > 0 ){
            velocity.add(0, GRAVITY);
        }

        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1 / dt);

        if (position.y < 0) {
            position.y = 0;
        }

        if (isAttacking){
            currentState = AnimState.ATTACKING;
            if (atackanim.isAnimationFinished(stateTime))
              { System.out.println("sup");
                isAttacking = false;
            }
        }

        else if (isFire){
            currentState = AnimState.FIRE;
            if (fireanim.isAnimationFinished(stateTime)){
                isFire = false;
            }


        }
        else if (position.y != 0){
            currentState = AnimState.JUMPING;
        }
        else if (velocity.x != 0){
            currentState = AnimState.RUNING;
        }
        else {
            currentState = AnimState.JUMPING;
        }
hurtbox2.setPosition(position);


    }




    public void draw(SpriteBatch batch) {


    }
    public void drawDebug(ShapeRenderer sr){

        sr.rect(vorHitbox.x,vorHitbox.y,vorHitbox.width,vorHitbox.height);

    }

}


