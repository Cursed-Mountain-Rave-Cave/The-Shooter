package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Logic.*;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainMenu;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Screen.MapScreen;
import com.theshooter.Utils.Config;

import java.io.IOException;

public class Game extends com.badlogic.gdx.Game {

	private static Game game;

	private Config config;

	public MainScreen mainScreen;
	public GameScreen gameScreen;
	public MapScreen mapScreen;
	public MainMenu mainMenu;

	public String level;

	private InputController inputController;
	private EntityController entityController;
	private TextureController textureController;
	private AudioController audioController;
	private EventController eventController;

	private boolean started;
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
		level = "lvl1";
		config = new Config();

		inputController = new InputController();
		textureController = new TextureController();
		audioController = new AudioController();
		entityController = new EntityController();
		eventController = new EventController();

		mainMenu = new MainMenu();
		mapScreen = new MapScreen();
		mainScreen = new MainScreen();
		gameScreen = new GameScreen();

		setScreen(mainMenu);

		entityController.load(level);

		gameScreen.screenObjects = entityController.getScreenObjectArray();

		//setScreen(gameScreen);

		Gdx.input.setInputProcessor(inputController);

		pausedTime = 0;
		pauseBegin = TimeUtils.millis();
		started = false;
		paused = false;
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

	public EventController getEventController() {
		return eventController;
	}

	public long getGameTime() {
		return TimeUtils.millis() - pausedTime;
	}

	@Override
	public void render () {
		super.render();
		if (started && !paused) {
			mapScreen.view();
			inputController.update();
			entityController.update();
			eventController.update();

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
			if (!paused)
				pauseBegin = TimeUtils.millis();
			else {
				pausedTime += TimeUtils.millis() - pauseBegin;
				pauseBegin = TimeUtils.millis();
			}
		}
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isStarted() {
		return started;
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
