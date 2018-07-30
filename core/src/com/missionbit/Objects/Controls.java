package com.missionbit.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;
import com.missionbit.MyGdxGame;

public class Controls implements InputProcessor{

    private OrthographicCamera camera;
    private Array<Image> buttons;
    private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();
    private Rectangle leftHitbox, rightHitbox, jumpHitbox, attackHitbox;
    public boolean leftPressed, rightPressed, jumpPressed, attackPressed;



    public class TouchInfo {

        float touchx = 0;

        float touchy = 0;

        public boolean touched = false;

    }



    public Controls(OrthographicCamera camera) {

        this.camera = camera;

        buttons = new Array<Image>();



        Image left = new Image(new Texture("Controls/LeftButton.png"));

        left.setPosition(0, 0);

//        leftHitbox = new Rectangle(left.getX(), left.getY(), left.getWidth(), left.getHeight());

        buttons.add(left);



        Image right = new Image(new Texture("Controls/RightButton.png"));

        right.setPosition(left.getWidth() + 4, 0);

//        rightHitbox = new Rectangle(right.getX(), right.getY(), right.getWidth(), right.getHeight());

        buttons.add(right);



        Image jump = new Image(new Texture("Controls/Jump/png"));

        jump.setPosition(MyGdxGame.WIDTH - jump.getWidth(), 0);

//        jumpHitbox = new Rectangle(jump.getX(), jump.getY(), jump.getWidth(), jump.getHeight());

        buttons.add(jump);



        Image attack = new Image(new Texture("Controls/AttackAction.png"));

        attack.setPosition(MyGdxGame.WIDTH - attack.getWidth(), jump.getHeight() +4);

//        attackHitbox = new Rectangle(attack.getX(), attack.getY(), attack.getWidth(), attack.getHeight());

        buttons.add(attack);



        for (int i= 0; i < 5; i++) {

            touches.put(i, new TouchInfo());

        }

    }



    public void draw(SpriteBatch batch) {

        for (Image i : buttons) {

            i.draw(batch, 0.8f);

        }

    }



    public void drawDebug(ShapeRenderer sr) {

        sr.rect(leftHitbox.x, leftHitbox.y, leftHitbox.width, leftHitbox.height);

        sr.rect(rightHitbox.x, rightHitbox.y, rightHitbox.width, leftHitbox.height);

        sr.rect(jumpHitbox.x, jumpHitbox.y, jumpHitbox.width, jumpHitbox.height);

        sr.rect(attackHitbox.x, attackHitbox.y, attackHitbox.width, attackHitbox.height);

    }



    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {

        return rightPressed;

    }

    public boolean isJumpPressed() {

        return jumpPressed;

    }

    public boolean isAttackPressed() {
        return attackPressed;
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;

    }

    @Override
    public boolean keyTyped(char character) {

        return false;

    }

    @Override
    public boolean keyUp(int keycode) {

        return false;

    }

    private void checkCollisions(TouchInfo t) {

        if (leftHitbox.contains(t.touchx, t.touchy)) {

            leftPressed = true;
            rightPressed = false;
        } else if (rightHitbox.contains(t.touchx, t.touchy)){
            rightPressed = true;
            leftPressed = false;
        } else if (jumpHitbox.contains(t.touchx, t.touchy)) {
            jumpPressed = true;
        } else if (attackHitbox.contains(t.touchx, t.touchy)) {
            attackPressed = true;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);
        if (pointer < 5) {
            touches.get(pointer).touchx = touchPos.x;
            touches.get(pointer).touchy = touchPos.y;
            checkCollisions(touches.get(pointer));
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);
        if (pointer < 5){
            touches.get(pointer).touchx = 0;
            touches.get(pointer).touchy = 0;
            leftPressed = false;
            rightPressed = false;
            jumpPressed = false;
            attackPressed = false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        Vector3 touchPos = new Vector3(screenX, screenY, 0);

        camera.unproject(touchPos);



        if (pointer < 5) {

            touches.get(pointer).touchx = touchPos.x;

            touches.get(pointer).touchy = touchPos.y;



            checkCollisions(touches.get(pointer));

        }

        return true;

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}