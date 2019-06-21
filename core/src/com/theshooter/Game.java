package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Entity.Bullet;
import com.theshooter.Logic.Entity.Player;
import com.theshooter.Logic.InputController;
import com.theshooter.Logic.Map;
import com.theshooter.Logic.TextureController;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Utils.Config;
import com.badlogic.gdx.audio.Music;

import java.io.IOException;

public class Game extends com.badlogic.gdx.Game {

	public static Config config;

	static {
		config = new Config();
	}

    public Player player;

	public Map map;

	public MainScreen mainScreen;
	public GameScreen gameScreen;
	public TextureController t;
	public Music SimpleMan;
	public boolean bossFight = false;

	private InputController inputController;

	private final int FULL_CLIP = 100;
	private boolean isReloading;
	private int ammoSupply, reloadStage;
	private Thread thread;
	public static Sound[] reloadingSound;

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

		map = new Map(this);
        player = new Player(99*50, 3*50, 25, 25, map);

        SimpleMan = Gdx.audio.newMusic(Gdx.files.internal("music/SimpleMan.mp3"));

        SimpleMan.setVolume(0.03f);
        SimpleMan.setLooping(true);
        SimpleMan.play();
		t = new TextureController();
		mainScreen = new MainScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(gameScreen);

		map.addBreakableEntity(player);

		inputController = new InputController(this);

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

	private int scatter;
	private float sinAlpha, cosAlpha;
	public void shoot1(){
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

        Bullet bullet = new Bullet((int)(player.getX() + 25 + dx1 * 25), (int)(player.getY() + 25 + dy1 * 25), dx1, dy1);
        map.addBullet(bullet);
        gameScreen.addBullet(bullet);
    }
    
	public void shoot1(Rectangle shooter, Rectangle target){
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

		Bullet bullet = new Bullet((int)(shooter.getX() + 25 + dx1 * 200), (int)(shooter.getY() + 25 + dy1 * 200), dx1, dy1);
		map.addBullet(bullet);
		gameScreen.addBullet(bullet);
	}

    public void shoot2() {
		if(ammoSupply < 3) {
			shoot1();
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

		Bullet bullet1 = new Bullet((int)(player.getX() + 25 + dx1 * 25), (int)(player.getY() + 25 + dy1 * 25), dx1, dy1);
		Bullet bullet2 = new Bullet((int)(player.getX() + 25 + dx2 * 25), (int)(player.getY() + 25 + dy2 * 25), dx2, dy2);
		Bullet bullet3 = new Bullet((int)(player.getX() + 25 + dx3 * 25), (int)(player.getY() + 25 + dy3 * 25), dx3, dy3);

		map.addBullet(bullet1); 	   map.addBullet(bullet2);        map.addBullet(bullet3);
		gameScreen.addBullet(bullet1); gameScreen.addBullet(bullet2); gameScreen.addBullet(bullet3);
	}

	public void shoot3() {
		if(ammoSupply < 8) {
			shoot2();
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

			Bullet bullet = new Bullet((int)(player.getX() + 25 + newDx * 25), (int)(player.getY() + 25 + newDy * 25), newDx, newDy);
			map.addBullet(bullet);
			gameScreen.addBullet(bullet);

			dx = newDx;
			dy = newDy;
		}
	}

	public void shoot3(Rectangle shooter, Rectangle target) {
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

			Bullet bullet = new Bullet((int)(shooter.getX() + 25 + newDx * 200), (int)(shooter.getY() + 25 + newDy * 200), newDx, newDy);
			map.addBullet(bullet);
			gameScreen.addBullet(bullet);

			dx = newDx;
			dy = newDy;
		}
	}

	@Override
	public void render () {
		super.render();
		inputController.update();
		map.update();
	}

	@Override
	public void dispose () {
		for(int i = 1; i < 15; ++i)
			reloadingSound[i].dispose();
		t.dispose();
	}

	public Map getMap() {
		return map;
	}
}
