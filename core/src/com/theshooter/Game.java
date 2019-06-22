package com.theshooter;


import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.*;
import com.theshooter.Logic.Entity.*;
import com.theshooter.Logic.Entity.Abstract.IEntity;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Utils.Config;
import com.badlogic.gdx.audio.Music;

import java.io.File;

public class Game extends com.badlogic.gdx.Game {

	private static Game game;

	private Config config;

	public MainScreen mainScreen;
	public GameScreen gameScreen;

	private InputController inputController;
	private EntityController entityController;
	private TextureController textureController;
	private AudioController audioController;
	private Weapon weapon;

	private final int FULL_CLIP = 100;
	private boolean isReloading;
	private int ammoSupply, reloadStage;
	private Thread thread;

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
		isReloading = false;
		/*thread = new Thread( ()-> {
			while(true) {
				while(!isReloading) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ie) {
						System.out.println(ie.getMessage());
						Gdx.app.exit();
					}
				}
				audioController.playSound("reloading");
				ammoSupply = 0;
				reloadStage = 0;
				try {
					for(int i = 0; i < 5; ++i) {
						reloadStage += 20;
						Thread.sleep(300);
					}
				} catch (InterruptedException ie) {
					System.out.println(ie.getMessage());
					Gdx.app.exit();
				}
				ammoSupply = FULL_CLIP;
				isReloading = false;
			}
		});
		thread.setDaemon(true);
		thread.start();*/



		config = new Config();

		inputController = new InputController();
		textureController = new TextureController();
		audioController = new AudioController();
		entityController = new EntityController();

		audioController.playMusic("casino", 1f);

		mainScreen = new MainScreen();
		gameScreen = new GameScreen();

		entityController.load("test");

		gameScreen.screenObjects = entityController.getScreenObjectArray();
		setScreen(gameScreen);

		ammoSupply = FULL_CLIP;

		Gdx.input.setInputProcessor(inputController);

		weapon = new Sword(10, (long)200, entityController.getPlayer());

	}

	public void reload() {
		weapon.reload();
		System.out.println("i'm here");
	}

	public String checkAmmoSuply() {
		if(isReloading)
			return "reloading " + reloadStage + "%";
		return Integer.valueOf(weapon.getCurClipSize()).toString();
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

	public void shoot1(IEntity owner){
		weapon.attack();
		System.out.println(weapon.getCurClipSize());
    }

	@Override
	public void render () {
		super.render();
		inputController.update();
		entityController.update();
		weapon.update();
	}

	@Override
	public void dispose () {
		textureController.dispose();
		audioController.dispose();
	}
}
