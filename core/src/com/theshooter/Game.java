package com.theshooter;


import com.badlogic.gdx.Gdx;
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
	private Weapon weapon;

	private boolean isReloading;

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


		Gdx.input.setInputProcessor(inputController);

		weapon = new ThrowingKnife(entityController.getPlayer());

	}

	public void reload() {
		weapon.reload();
	}

	public String checkAmmoSuply() {
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
		float sdx = Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
		float sdy = -Gdx.input.getY() + Gdx.graphics.getHeight() / 2 - 100;

		float dx = sdx / 2 + sdy;
		float dy = -sdx / 2 + sdy;
		weapon.attack(dx, dy);
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
