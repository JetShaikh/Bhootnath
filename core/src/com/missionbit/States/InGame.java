package com.missionbit.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.missionbit.Actors.Character;
import com.missionbit.Actors.Player;
import com.missionbit.MyGdxGame;
import com.missionbit.Objects.Controls;
import com.missionbit.Actors.Ghost;
import com.missionbit.Actors.Thug;

import javax.xml.soap.Text;

import static jdk.nashorn.internal.runtime.Context.DEBUG;

public class InGame extends States {
    private Character character;
    private Controls controller;
    private PolygonShape rect;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    private Player player;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    public World world;
    private Box2DDebugRenderer debugRenderer;

    private BodyDef platformDef;
    PolygonShape platformShape;
    private Array<Body> platforms;
    private int counter = 0;
    public Ghost ghost;
    BodyDef ghostDef;
    Body ghostBody;
    public Thug thug;
    BodyDef thugDef;
    Body thugBody;

    public InGame(MyGdxGame game) {
        super(game);

        tiledMap = new TmxMapLoader().load("Maps/Map.xml");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        Box2D.init();
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();

        ghost = new Ghost();
        ghost.getPosition();
        thug = new Thug(100, 200, this);
        player = new Player(1, 4, this);
        controller = new Controls(camera);

        tiledMap = new TmxMapLoader().load("Maps/Map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, States.PTM);

        //Ghost Box2d
        ghostDef = new BodyDef();
        ghostDef.type = BodyDef.BodyType.DynamicBody;
        ghostDef.position.set(ghost.getX(), ghost.getY() + 50);

        ghostBody = world.createBody(ghostDef);

        rect = new PolygonShape();
        rect.set(new float[]{player.getWidth() / 3, 0, player.getWidth() / 3 + player.getWidth() / 2 - 10, 0,
                player.getWidth() / 3 + player.getWidth() / 2 - 10, player.getHeight(), player.getWidth() / 3, player.getHeight()});

        fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixture = ghostBody.createFixture(fixtureDef);

        rect.dispose();

        //Thug Box2D
        thugDef = new BodyDef();
        thugDef.type = BodyDef.BodyType.DynamicBody;
        thugDef.position.set(ghost.getX(), ghost.getY() + 50);

        thugBody = world.createBody(ghostDef);

        rect = new PolygonShape();
        rect.set(new float[]{thug.getWidth() / 3, 0, thug.getWidth() / 3 + thug.getWidth() / 2 - 10, 0,
                thug.getWidth() / 3 + thug.getWidth() / 2 - 10, thug.getHeight(), thug.getWidth() / 3, thug.getHeight()});

        fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixture = ghostBody.createFixture(fixtureDef);

        rect.dispose();

        //Platform Box2d
        platforms = new Array<Body>();

        platformDef = new BodyDef();
        platformShape = new PolygonShape();

        for (PolygonMapObject obj : tiledMap.getLayers().get("Collision").getObjects().getByType(PolygonMapObject.class)) {
            platformDef.position.set(obj.getPolygon().getX() * States.PTM, obj.getPolygon().getY() * States.PTM);
            platforms.add(world.createBody((platformDef)));
            float[] vertices = obj.getPolygon().getVertices();

            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = vertices[i] * States.PTM;
            }

            platformShape.set(vertices);
            platforms.get(counter).createFixture(platformShape, 0.0f);
            counter++;
        }

        for (PolygonMapObject obj : tiledMap.getLayers().get("Tools").getObjects().getByType(PolygonMapObject.class)) {
            platformDef.position.set(obj.getPolygon().getX() * States.PTM, obj.getPolygon().getY() * States.PTM);
            platforms.add(world.createBody((platformDef)));
            float[] vertices = obj.getPolygon().getVertices();

            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = vertices[i] * States.PTM;
            }

            platformShape.set(vertices);
            platforms.get(counter).createFixture(platformShape, 0.0f);
            counter++;
        }

        for (PolygonMapObject obj : tiledMap.getLayers().get("Killers").getObjects().getByType(PolygonMapObject.class)) {
            platformDef.position.set(obj.getPolygon().getX() * States.PTM, obj.getPolygon().getY() * States.PTM);
            platforms.add(world.createBody((platformDef)));
            float[] vertices = obj.getPolygon().getVertices();

            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = vertices[i] * States.PTM;
            }

            platformShape.set(vertices);
            platforms.get(counter).createFixture(platformShape, 0.0f);
            counter++;
        }

    }

    public InGame() {

    }


    public void handleInput() {
        if (controller.isLeftPressed()) {
            if (controller.isRightPressed()) {
                character.moveRight();
            } else {
                character.resetAnim();
            }
        } else {
            character.moveLeft();
        }

        if (controller.isJumpPressed()) {
        } else {
            character.jump();
        }

        if (controller.isAttackPressed()) {
        } else {
            character.attack();
        }
        for (int i = 0; i < 5; i++) {
            if (Gdx.input.isTouched(i)) {
                Vector3 touchPos = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);
            }

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        character.update(dt);
        if (character.getX() > MyGdxGame.WIDTH || character.getX() + character.getWidth() < 0) {
            game.setScreen(new PlayState(game));
            dispose();
        }
        thug.update(dt);
    }

    @Override
    public void drawGame(String s) {

    }

    @Override
    public void drawGame() {
        game.batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        game.batch.begin();

        game.batch.draw(character.getTexture(), character.getX(), character.getY(), character.getTexture.getRegionWidth() * States.PTM, character.getTexture().getRegionHeight() * States.PTM);
        game.batch.end();

        debugRenderer.render(world, camera.combined);
        world.step(1 / 60f, 6, 2);

        font.draw(batch, this.getClass().toString(), 0, MyGdxGame.HEIGHT);
        batch.draw(character.getTexture(), character.getPosition().x, character.getPosition().y);
        controller.draw(batch);
        batch.end();

        if (DEBUG){
            ShapeRenderer sr = new ShapeRenderer();
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.RED);
            controller.drawDebug(sr);
//            character.drawDebug(sr);
            sr.end();

        }
    }

    @Override
    public void render(float delta) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        super.render(delta);

    }

    public void dispose() {
        super.dispose();
        character.dispose();
        debugRenderer.dispose();
        game.dispose();
        world.dispose();
    }
}

