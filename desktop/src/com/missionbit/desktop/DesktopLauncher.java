package com.missionbit.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.missionbit.MyGdxGame;

import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyGdxGame.WIDTH;
		config.height = MyGdxGame.HEIGHT;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
