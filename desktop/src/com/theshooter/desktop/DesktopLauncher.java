package com.theshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.theshooter.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1600;
		config.height = 900;
		config.vSyncEnabled = true;

		config.foregroundFPS = 120;
		config.backgroundFPS = 120;

		config.title = "The Shooter";
		config.forceExit = false;

		new LwjglApplication(new Game(), config);
	}
}