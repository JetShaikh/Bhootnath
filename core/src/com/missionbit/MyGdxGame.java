package com.missionbit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.missionbit.States.PlayState;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.xml.soap.Text;

public class MyGdxGame extends Game {
    public SpriteBatch batch;
    private Texture img;
    private Texture img1;
    public ShapeRenderer sr;
    Texture Background;
    TiledMap map;
    TiledMapRenderer renderer;

    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    public static final String TITLE = "Time-Zone";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
    public BitmapFont font;

    public OrthographicCamera camera;
    ExtendViewport viewport;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        viewport = new ExtendViewport(800, 600, camera);

        //Background = new Texture("landscape.png");
        sr = new ShapeRenderer();


        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);

        //Story
        JOptionPane.showMessageDialog(null, "They banished me, \n I care too much to let them get away with this, \n I need to get back to put them into there place \n Or I might as well be stuck in the past, \n forgotten forever.", TITLE, JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Hello World!!!! - Make sure to update every time you make/complete a task.");

        map = new TmxMapLoader().load("Maps/Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        setScreen(new PlayState(this));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();
//        batch.draw(Background, 0, 0);
//        batch.draw(img, 100, 100);
//        batch.draw(img1, 0, 0);
//
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        img.dispose();
        font.dispose();

    }
}
