package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.theshooter.Logic.Entity.Bullet;
import com.theshooter.Logic.Entity.Player;
import com.theshooter.Logic.InputController;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;

public class Game extends com.badlogic.gdx.Game {
    public Player player;

	public Map map;

	public MainScreen mainScreen;
	public GameScreen gameScreen;

	private InputController inputController;

	@Override
	public void create () {
		map = new Map();
        player = new Player(0, 0, 25, 25, map);

		mainScreen = new MainScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(gameScreen);

		map.addEntity(player);

		inputController = new InputController(this);

		Gdx.input.setInputProcessor(inputController);
	}

	public void shoot(){
	    float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
	    float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2;

	    float dx = sdx/2 + sdy;
	    float dy = -sdx/2 + sdy;

	    float norm = (float) Math.sqrt(dx*dx + dy*dy);

	    dx /= norm;
	    dy /= norm;

        Bullet bullet = new Bullet(player.getX() + 25, player.getY() + 25, dx, dy);
        map.addBullet(bullet);
        gameScreen.addBullet(bullet);
    }

	@Override
	public void render () {
		super.render();
		inputController.update();
		map.update();
	}

	@Override
	public void dispose () {

	}

	public Map getMap() {
		return map;
	}
}
