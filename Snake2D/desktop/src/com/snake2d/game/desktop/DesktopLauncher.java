package com.snake2d.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.snake2d.game.Snake2D;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = false;
		config.addIcon("icon.png", Files.FileType.Internal);
		config.width = 1260;
		config.height = 700;
		//config.width = 1610;
		//config.height = 980;
		config.title = "Snake2D";

		new LwjglApplication(new Snake2D(), config);

	}
}
