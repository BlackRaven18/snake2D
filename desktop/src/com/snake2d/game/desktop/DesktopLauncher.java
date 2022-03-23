package com.snake2d.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.snake2d.game.Snake2D;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Snake2D(), config);

		config.resizable = false;
		config.width = 1260;
		config.height = 700;
		config.title = "Snake2D - Psk";
	}
}
