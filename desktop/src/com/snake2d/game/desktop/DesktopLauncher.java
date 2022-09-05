package com.snake2d.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.snake2d.game.Snake2D;

/**
 * klasa obsługująca tworzenia okna gry i wyświetlanie go
 */
public class DesktopLauncher {

	/**
	 * jest to główna metoda pozwalająca inicjacje atrybutów okna aplikacji i uruchomienie jej.
	 * @param arg
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = false;
		config.addIcon("icon.png", Files.FileType.Internal);
		config.width = 1280;
		config.height = 720;
		config.title = "Snake2D";

		new LwjglApplication(new Snake2D(), config);
	}

}

