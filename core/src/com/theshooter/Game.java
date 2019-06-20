package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

import java.util.Random;

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

	private InputController inputController;

	@Override
	public void create () {
		map = new Map(this);
        player = new Player(99*50, 3*50, 25, 25, map);

        SimpleMan = Gdx.audio.newMusic(Gdx.files.internal("music/SimpleMan.mp3"));

        SimpleMan.setVolume(0.2f);
        SimpleMan.setLooping(true);
        SimpleMan.play();

		t = new TextureController();
		mainScreen = new MainScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(gameScreen);

		map.addBreakableEntity(player);

		inputController = new InputController(this);

		Gdx.input.setInputProcessor(inputController);
	}

	private int scatter;
	private float sinAlpha, cosAlpha;
	public void shoot1(){
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
		// float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
		// float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2;

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

		Bullet bullet = new Bullet((int)(shooter.getX() + 25 + dx1 * 150), (int)(shooter.getY() + 25 + dy1 * 150), dx1, dy1);
		map.addBullet(bullet);
		gameScreen.addBullet(bullet);
	}

    public void shoot2() {
		float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
		float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2 - 100;

		float dx1 = sdx/2 + sdy;
		float dy1 = -sdx/2 + sdy;

		float norm = (float) Math.sqrt(dx1*dx1 + dy1*dy1);

		dx1 /= norm;
		dy1 /= norm;

		scatter = MathUtils.random(-10, 10);
		sinAlpha = (float) Math.sin(Math.toRadians((double) (15 + scatter)));
		cosAlpha = (float) Math.cos(Math.toRadians((double) (15 + scatter)));

		float dx2 = dx1*cosAlpha - dy1*sinAlpha;
		float dy2 = dx1*sinAlpha + dy1*cosAlpha;


		scatter = MathUtils.random(-10, 10);
		sinAlpha = (float) Math.sin(Math.toRadians((double) (15 + scatter)));
		cosAlpha = (float) Math.cos(Math.toRadians((double) (15 + scatter)));

		float dx3 = dx1*cosAlpha + dy1*sinAlpha;
		float dy3 = -dx1*sinAlpha + dy1*cosAlpha;

		Bullet bullet1 = new Bullet((int)(player.getX() + 25 + dx1 * 25), (int)(player.getY() + 25 + dy1 * 25), dx1, dy1);
		Bullet bullet2 = new Bullet((int)(player.getX() + 25 + dx2 * 25), (int)(player.getY() + 25 + dy2 * 25), dx2, dy2);
		Bullet bullet3 = new Bullet((int)(player.getX() + 25 + dx3 * 25), (int)(player.getY() + 25 + dy3 * 25), dx3, dy3);

		map.addBullet(bullet1); 	   map.addBullet(bullet2);        map.addBullet(bullet3);
		gameScreen.addBullet(bullet1); gameScreen.addBullet(bullet2); gameScreen.addBullet(bullet3);
	}

	public void shoot3() {
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

	@Override
	public void render () {
		super.render();
		inputController.update();
		map.update();
	}

	@Override
	public void dispose () {
		t.dispose();
	}

	public Map getMap() {
		return map;
	}
}
