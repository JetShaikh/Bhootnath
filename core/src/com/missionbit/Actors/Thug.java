package com.missionbit.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.missionbit.States.InGame;

public class Thug extends Character {

    private Character character;

    private BodyDef playerDef;
    public Body playerBody;
    private PolygonShape polygon;
    private FixtureDef fixtureDef;

    public Thug(int x, int y, InGame game) {
        super(x, y, game);

        playerBody.setFixedRotation(false);

        playerDef = new BodyDef();
        fixtureDef = new FixtureDef();
        polygon = new PolygonShape();

        playerDef.position.set(100, 300);

        img = new Texture("Thug.png");

        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(x, y);

        character = new Character(x, y, this);

        playerBody = game.world.createBody(playerDef);

        polygon.set(new float[] {0, 0, width, 0, width, height, 0, height});

        fixtureDef.shape = polygon;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        playerBody.createFixture(fixtureDef);
        polygon.dispose();

        position.set(playerBody.getPosition());

        playerBody.applyLinearImpulse(new Vector2(5, 0), position, true);

        if(playerBody.getLinearVelocity().x < MAX_VELOCITY) {
            playerBody.applyLinearImpulse(position, point, true);
        }

    }

    @Override
    public void update(float dt) {
        super.update(dt);

        playerBody.applyLinearImpulse(impulse, point, wake);
    }
}
