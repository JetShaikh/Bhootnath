package com.missionbit.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.missionbit.Actors.Controller;
import com.missionbit.Actors.Player;
import com.missionbit.Actors.Snake;
import com.missionbit.Actors.Vortex;
import com.missionbit.MyGdxGame;

public class PlayState extends States {
    public World world;

    private Controller controller;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    Player ghost;
    Body groundBody;
    public BodyDef groundDef;
    public PolygonShape groundShape;
    Vortex vortex;
    Array<Snake> snakes;


    public PlayState(MyGdxGame game) {
        super(game);
        tiledMap = new TmxMapLoader().load("Maps/Level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Box2D.init();
        world = new World(new Vector2(0, -9.8f), false);
        ghost = new Player(100, 100, 100);
        snakes = new Array<Snake>();
        for (int i = 0; i < 1; i++) {
            snakes.add(new Snake(500, 100));
        }
        controller = new Controller(camera, ghost);
        groundDef = new BodyDef();
        String bip = "bip.mp3";
        vortex = new Vortex(200, 100, new Image(new Texture("VortexChained.png")));
        groundShape = new PolygonShape();

        Array<Body> grounds = new Array<Body>();
        int counter = 0;

        Gdx.input.setInputProcessor(controller);
        for (PolygonMapObject obj : tiledMap.getLayers().get("Collision").getObjects().getByType(PolygonMapObject.class)) {
            groundDef.position.set(obj.getPolygon().getX() * States.PTM, obj.getPolygon().getY() * States.PTM);
            grounds.add(world.createBody(groundDef));
            float[] vertices = obj.getPolygon().getVertices();
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = vertices[i] * States.PTM;
            }
            groundShape.set(vertices);
            grounds.get(counter).createFixture(groundShape, 0.0f);
            counter++;
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        drawGame();
    }

    @Override
    public void update(float dt) {
        camera.update();
        vortex.update(dt);
        if (controller.isLeftPressed()) {

            ghost.moveLeft();
        } else if (controller.isRightPressed()) {

            ghost.moveRight();

        } else {
            ghost.resetAnim();

        }

        if (controller.isJumpPressed()) {
            ghost.jump();
        }

        if (controller.isAttackPressed()) {
            ghost.atack();
        }
        if (controller.isFirePressed()) {
            vortex.setPosition(new Vector2(200, 100));
            ghost.fire();
        } else {
            vortex.setPosition(new Vector2(2000000000, 100000));
        }

        ghost.update(Gdx.graphics.getDeltaTime());

        for (Snake s : snakes) {

            s.update();

        }

        if (ghost.getBounds().overlaps(vortex.getBound())) {

            System.out.println("You Win!");

        }

//
//        if (Gdx.input.justTouched()) {
//            game.setScreen(new InGame(game));
//            dispose();
//        }
//        world.step(1/60f, 6, 2); //Last Thing in this list
    }

    @Override
    public void drawGame(String s) {

    }

    @Override
    public void drawGame() {
        boolean b = false;

        game.batch.begin();

        batch.draw(vortex.getTexture(), vortex.getPosition().x, vortex.getPosition().y, 200, 100);
        vortex.bound.setSize(200, 100);
        batch.draw(
                ghost.getTexture(Gdx.graphics.getDeltaTime()),
                ghost.getPosition().x,
                ghost.getPosition().y
                , 100, 100
        );
        ghost.getBounds().setSize(100, 100);
        for (Snake s : snakes) {

            //batch.draw(s.getTexture(Gdx.graphics.getDeltaTime()), s.getPosition().x, s.getPosition().y,100,100);


        }

        //  controller.draw(batch);

        game.batch.end();

        controller.stage.draw();

        game.sr.begin(ShapeRenderer.ShapeType.Line);
        game.sr.setColor(Color.RED);

        controller.drawDebug(game.sr);
        game.sr.rect(ghost.getBounds().x, ghost.getBounds().y, ghost.getBounds().width, ghost.getBounds().height);
        game.sr.rect(vortex.getPosition().x, vortex.getPosition().y, vortex.getBound().width, vortex.getBound().height);

        game.sr.end();
    }
}







