package com.theshooter;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Screen.ScreenObjectArray;

public class Game extends com.badlogic.gdx.Game {

	public SpriteBatch batch;
	public Map map;

	private MainScreen mainScreen;
	private GameScreen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new Map();

		mainScreen = new MainScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
