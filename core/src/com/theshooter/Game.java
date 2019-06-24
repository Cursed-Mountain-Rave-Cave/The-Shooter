package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Logic.*;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Utils.Config;

import java.io.IOException;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Logic.*;
import com.theshooter.Logic.Entity.Abstract.IEntity;
import com.theshooter.Logic.Entity.Weapon.*;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Utils.Config;

public class Game extends com.badlogic.gdx.Game {

	private static Game game;

	private Config config;

	public MainScreen mainScreen;
	public GameScreen gameScreen;

	private InputController inputController;
	private EntityController entityController;
	private TextureController textureController;
	private AudioController audioController;

	private boolean paused;
	private long pausedTime;
	private long pauseBegin;

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

		//audioController.playMusic("casino", 1f);

		mainScreen = new MainScreen();
		gameScreen = new GameScreen();

//		entityController.load("test"); // ----------------------------------------------------------------------------------------------------
//		GameLoader gl = new GameLoader();
//		try { gl.load("test2"); }
//		catch (IOException e) {
//			System.out.println(e.getMessage());
//			Gdx.app.exit();
//		}
//		entityController.load("test2");
		//entityController.load("level1");
		entityController.load("itemsTest");
//		entityController.load("test");

		gameScreen.screenObjects = entityController.getScreenObjectArray();
		setScreen(gameScreen);

		Gdx.input.setInputProcessor(inputController);

		paused = false;
		pausedTime = 0;
		pauseBegin = 0;
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

	public long getGameTime() {
		return TimeUtils.millis() - pausedTime;
	}

	@Override
	public void render () {
		super.render();
		if (!paused) {
			inputController.update();
			entityController.update();

			config.remainingHookahTime -= Gdx.graphics.getDeltaTime();
			if (config.remainingHookahTime <= 0) {
				config.remainingHookahTime = 0;
				config.enemiesVelocityMultiplier = 1;
			}

			config.remainingVelocityUpTime -= Gdx.graphics.getDeltaTime();
			if (config.remainingVelocityUpTime <= 0) {
				config.remainingVelocityUpTime = 0;
				config.playerVelocityMultiplier = 1;

			}
			pauseBegin = TimeUtils.millis();
		} else {
			pausedTime += TimeUtils.millis() - pauseBegin;
			pauseBegin = TimeUtils.millis();
		}
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isPaused() {
		return paused;
	}

	@Override
	public void dispose () {
		textureController.dispose();
		audioController.dispose();
	}
}
