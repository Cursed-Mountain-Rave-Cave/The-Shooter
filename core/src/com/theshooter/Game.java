package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Bullet;
import com.theshooter.Logic.Entity.Player;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;
import com.theshooter.Screen.ScreenObject;
import com.theshooter.Screen.ScreenObjectArray;

public class Game extends com.badlogic.gdx.Game {
    public Player player;

	public SpriteBatch batch;
	public Map map;

	private MainScreen mainScreen;
	private GameScreen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new Map();
        player = new Player(20, 20, 25, 25);

		mainScreen = new MainScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(gameScreen);
	}

	public void shoot(){
	    float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
	    float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2;

	    float dx = sdx/2 + sdy;
	    float dy = -sdx/2 + sdy;

	    float norm = (float) Math.sqrt(dx*dx + dy*dy);

	    dx /= norm;
	    dy /= norm;

        Bullet bullet = new Bullet(player.getX(), player.getY(), dx, dy);

        map.addBullet(bullet);
        gameScreen.addBullet(bullet);
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
