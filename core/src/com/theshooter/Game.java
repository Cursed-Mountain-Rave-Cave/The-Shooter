package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.theshooter.Logic.*;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Utils.Config;

import java.io.IOException;

public class Game extends com.badlogic.gdx.Game {

	private static Game game;

	private Config config;

	public MainScreen mainScreen;
	public GameScreen gameScreen;

	private InputController inputController;
	private EntityController entityController;
	private TextureController textureController;
	private AudioController audioController;


	public static Game getInstance(){
		if(game == null)
			game = new Game();
		return game;
	}

	private Game(){
		super();
	}

	@Override
	public void create () {

		config = new Config();

		inputController = new InputController();
		textureController = new TextureController();
		audioController = new AudioController();
		entityController = new EntityController();

		audioController.playMusic("casino", 1f);

		mainScreen = new MainScreen();
		gameScreen = new GameScreen();

		entityController.load("test"); // ----------------------------------------------------------------------------------------------------
//		GameLoader gl = new GameLoader();
//		try { gl.load("test2"); }
//		catch (IOException e) {
//			System.out.println(e.getMessage());
//			Gdx.app.exit();
//		}
//		entityController.load("test2");
		// entityController.load("level1");

		gameScreen.screenObjects = entityController.getScreenObjectArray();
		setScreen(gameScreen);


		Gdx.input.setInputProcessor(inputController);
	}

	public Config getConfig() {
		return config;
	}

	public EntityController getEntityController() {
		return entityController;
	}

	public TextureController getTextureController() {
		return textureController;
	}

	public AudioController getAudioController() {
		return audioController;
	}

	@Override
	public void render () {
		super.render();
		inputController.update();
		entityController.update();
	}

	@Override
	public void dispose () {
		textureController.dispose();
		audioController.dispose();
	}
}
