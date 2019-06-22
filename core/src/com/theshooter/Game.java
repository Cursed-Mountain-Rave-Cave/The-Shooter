package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.IEntity;
import com.theshooter.Logic.Entity.Projectile;
import com.theshooter.Logic.EntityController;
import com.theshooter.Logic.InputController;
import com.theshooter.Logic.TextureController;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Utils.Config;
import com.badlogic.gdx.audio.Music;

public class Game extends com.badlogic.gdx.Game {

	private static Game game;

	private Config config;

	public MainScreen mainScreen;
	public GameScreen gameScreen;

	private InputController inputController;
	private EntityController entityController;
	private TextureController textureController;

	public Music SimpleMan;

	private final int FULL_CLIP = 100;
	private boolean isReloading;
	private int ammoSupply, reloadStage;
	private Thread thread;
	public static Sound[] reloadingSound;

	public static Game getInstance(){
		if(game == null)
			game = new Game();
		return game;
	}

	private Game(){
		super();
	}

	private void initSound() {
		reloadingSound = new Sound[15];
		for(int i = 1; i <= 6; ++i)
			reloadingSound[i] = Gdx.audio.newSound(Gdx.files.internal("sound/Reloading/" + i + ".mp3"));
		for(int i = 1; i <= 8; ++i)
			reloadingSound[6 + i] = Gdx.audio.newSound(Gdx.files.internal("sound/Cover/" + i + ".mp3"));
	}
	private void playSound(int num) {
		switch (num) {
			case 1:
			case 7:
			case 9:
			case 10:
			case 12:
			case 13:
				reloadingSound[num].play(0.2f);
				break;

			case 2:
			case 3:
			case 4:
				reloadingSound[num].play(1.0f);
				break;

			case 5:
			case 6:
				reloadingSound[num].play(0.1f);
				break;

			case 8:
				reloadingSound[num].play(0.17f);
				break;

			case 11:
				reloadingSound[num].play(0.15f);
				break;

			case 14:
				reloadingSound[num].play(0.4f);
				break;
		}
	}

	@Override
	public void create () {
		initSound();
		isReloading = false;
		thread = new Thread( ()-> {
			while(true) {
				while(!isReloading) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ie) {
						System.out.println(ie.getMessage());
						Gdx.app.exit();
					}
				}
				int rand = MathUtils.random(1, 14);
				playSound(rand);
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
		thread.start();

		config = new Config();

        SimpleMan = Gdx.audio.newMusic(Gdx.files.internal("music/SimpleMan.mp3"));

        SimpleMan.setVolume(0.03f);
        SimpleMan.setLooping(true);
        SimpleMan.play();

		inputController = new InputController();
		textureController = new TextureController();
		entityController = new EntityController();

		mainScreen = new MainScreen();
		gameScreen = new GameScreen();

		entityController.load("level1");

		gameScreen.screenObjects = entityController.getScreenObjectArray();
		setScreen(gameScreen);

		ammoSupply = FULL_CLIP;

		Gdx.input.setInputProcessor(inputController);
	}

	public void reload() {
		if(!isReloading && ammoSupply < FULL_CLIP)
			isReloading = true;
	}

	public String checkAmmoSuply() {
		if(isReloading)
			return "reloading " + reloadStage + "%";
		return Integer.valueOf(ammoSupply).toString();
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

	private int scatter;
	private float sinAlpha, cosAlpha;

	public void shoot1(IEntity owner){
		if(ammoSupply <= 0) return;
		ammoSupply--;
		
	    float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
	    float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2 - 100;

	    float dx = sdx/2 + sdy;
	    float dy = -sdx/2 + sdy;

	    float norm = (float) Math.sqrt(dx*dx + dy*dy);

	    dx /= norm;
	    dy /= norm;

	    scatter = MathUtils.random(-5, 5);
		sinAlpha = (float) Math.sin(Math.toRadians((double) scatter));
		cosAlpha = (float) Math.cos(Math.toRadians((double) scatter));

		float dx1 = dx*cosAlpha - dy*sinAlpha;
		float dy1 = dx*sinAlpha + dy*cosAlpha;

        Projectile projectile = new Projectile(new Damage(owner, Damage.Type.PHYSICAL, 100), entityController.getPlayer().getX() + 25,
									entityController.getPlayer().getY() + 25, dx1, dy1, 2000);
        entityController.addBullet(projectile);
    }
    
	public void shoot1(IEntity owner, Rectangle shooter, Rectangle target){
		float dx = target.getX() - shooter.getX();
		float dy = target.getY() - shooter.getY();

		float norm = (float) Math.sqrt(dx*dx + dy*dy);

		dx /= norm;
		dy /= norm;

		scatter = MathUtils.random(-5, 5);
		sinAlpha = (float) Math.sin(Math.toRadians((double) scatter));
		cosAlpha = (float) Math.cos(Math.toRadians((double) scatter));

		float dx1 = dx*cosAlpha - dy*sinAlpha;
		float dy1 = dx*sinAlpha + dy*cosAlpha;

		Projectile projectile = new Projectile(new Damage(owner, Damage.Type.PHYSICAL, 100), (int)(shooter.getX() + shooter.getWidth()/2), (int)(shooter.getY() + shooter.getHeight()/2), dx1, dy1, 2000);
		entityController.addBullet(projectile);
	}

    public void shoot2(IEntity owner) {
		if(ammoSupply < 3) {
			shoot1(owner);
			return;
		}
		ammoSupply -= 3;

		float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
		float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2 - 100;

		float dx = sdx/2 + sdy;
		float dy = -sdx/2 + sdy;

		float norm = (float) Math.sqrt(dx*dx + dy*dy);

		dx /= norm;
		dy /= norm;

		scatter = MathUtils.random(-10, 10);
		sinAlpha = (float) Math.sin(Math.toRadians((double) (15 + scatter)));
		cosAlpha = (float) Math.cos(Math.toRadians((double) (15 + scatter)));

		float dx1 = dx*cosAlpha - dy*sinAlpha;
		float dy1 = dx*sinAlpha + dy*cosAlpha;

		scatter = MathUtils.random(-10, 10);
		sinAlpha = (float) Math.sin(Math.toRadians((double) (15 + scatter)));
		cosAlpha = (float) Math.cos(Math.toRadians((double) (15 + scatter)));

		float dx2 = dx*cosAlpha - dy*sinAlpha;
		float dy2 = dx*sinAlpha + dy*cosAlpha;


		scatter = MathUtils.random(-10, 10);
		sinAlpha = (float) Math.sin(Math.toRadians((double) (15 + scatter)));
		cosAlpha = (float) Math.cos(Math.toRadians((double) (15 + scatter)));

		float dx3 = dx*cosAlpha + dy*sinAlpha;
		float dy3 = -dx*sinAlpha + dy*cosAlpha;

		Projectile projectile1 = new Projectile(new Damage(owner, Damage.Type.PHYSICAL, 100), entityController.getPlayer().getX() + 25,
									entityController.getPlayer().getY() + 25, dx1, dy1, 2000);
		Projectile projectile2 = new Projectile(new Damage(owner, Damage.Type.PHYSICAL, 100), entityController.getPlayer().getX() + 25,
									entityController.getPlayer().getY() + 25, dx2, dy2, 2000);
		Projectile projectile3 = new Projectile(new Damage(owner, Damage.Type.PHYSICAL, 100), entityController.getPlayer().getX() + 25,
									entityController.getPlayer().getY() + 25, dx3, dy3, 2000);

		entityController.addBullet(projectile1); entityController.addBullet(projectile2); entityController.addBullet(projectile3);
	}

	public void shoot3(IEntity owner) {
		if(ammoSupply < 8) {
			shoot2(owner);
			return;
		}

		ammoSupply -= 8;

		float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
		float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2 - 100;

		float dx = sdx/2 + sdy;
		float dy = -sdx/2 + sdy;

		float norm = (float) Math.sqrt(dx*dx + dy*dy);

		dx /= norm;
		dy /= norm;

		float newDx, newDy;
		for(int i = 0; i < 8; ++i) {
			scatter = MathUtils.random(-2, 2);
			sinAlpha = (float) Math.sin(Math.toRadians((double) (45 + scatter)));
			cosAlpha = (float) Math.cos(Math.toRadians((double) (45 + scatter)));

			newDx = dx*cosAlpha - dy*sinAlpha;
			newDy = dx*sinAlpha + dy*cosAlpha;

			Projectile projectile = new Projectile(new Damage(owner, Damage.Type.PHYSICAL, 100), (int)(entityController.getPlayer().getX() + 25), (int)(entityController.getPlayer().getY() + 25), newDx, newDy, 2000);
			entityController.addBullet(projectile);

			dx = newDx;
			dy = newDy;
		}
	}

	public void shoot3(IEntity owner, Rectangle shooter, Rectangle target) {
		//float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
		//float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2 - 100;

		float dx = shooter.getX() + target.getX();
		float dy = shooter.getY() + target.getY();

		float norm = (float) Math.sqrt(dx*dx + dy*dy);

		dx /= norm;
		dy /= norm;

		float newDx, newDy;
		for(int i = 0; i < 8; ++i) {
			scatter = MathUtils.random(-2, 2);
			sinAlpha = (float) Math.sin(Math.toRadians((double) (45 + scatter)));
			cosAlpha = (float) Math.cos(Math.toRadians((double) (45 + scatter)));

			newDx = dx*cosAlpha - dy*sinAlpha;
			newDy = dx*sinAlpha + dy*cosAlpha;

			Projectile projectile = new Projectile(new Damage(owner, Damage.Type.PHYSICAL, 100), (int)(shooter.getX() + shooter.getWidth()/2), (int)(shooter.getY() + shooter.getHeight()/2), newDx, newDy, 2000);
			entityController.addBullet(projectile);

			dx = newDx;
			dy = newDy;
		}
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
		for(int i = 1; i < 15; ++i)
			reloadingSound[i].dispose();
	}
}
