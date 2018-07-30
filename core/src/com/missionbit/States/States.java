package com.missionbit.States;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.missionbit.MyGdxGame;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public abstract class States implements Screen {
    public MyGdxGame game;
    public static final float PTM = 1/32f; //PTM = Pixels to Meters
    public OrthographicCamera camera;
    public Texture background;
    public Texture playBtn;
    public boolean win = false;
    public boolean lose = false;
    SpriteBatch batch;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;


    String[] Levels = new String[]{"Map", "LevelOne", "LevelTwo", "LevelThree", "LevelFour", "LevelFive", "LevelSix", "LevelSeven", "Present"};
    private AtomicIntegerFieldUpdater<String> properties;

    public States(Camera gameCamera) {

    }

    public States() {

    }

    public void game(MyGdxGame game) {
        this.game = game;
        camera= new OrthographicCamera(MyGdxGame.WIDTH * PTM, MyGdxGame.HEIGHT * PTM);
        camera.translate(camera.viewportWidth / 2 , camera.viewportHeight / 2, 0);
        camera.update();

    }

    protected abstract void drawGame();

    public BitmapFont font;


    public States(MyGdxGame game) {
        this.game = game;
        batch = game.batch;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

    }

    public abstract void update(float dt);
    public abstract void drawGame(String s);

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
//        camera.update();
//        batch.setProjectionMatrix(camera.combined);
        drawGame();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }

    public void update() {

    }

    public AtomicIntegerFieldUpdater<String> getProperties() {
        return properties;
    }
}
