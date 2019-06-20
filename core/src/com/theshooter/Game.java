package com.theshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.theshooter.Logic.Entity.Bullet;
import com.theshooter.Logic.Entity.Player;
import com.theshooter.Logic.InputController;
import com.theshooter.Logic.Map;
import com.theshooter.Logic.TextureController;
import com.theshooter.Screen.GameScreen;
import com.theshooter.Screen.MainScreen;

public class Game extends com.badlogic.gdx.Game {
    public Player player;

	public Map map;

	public MainScreen mainScreen;
	public GameScreen gameScreen;
	public TextureController t;

	private InputController inputController;

	@Override
	public void create () {
		map = new Map();
        player = new Player(99*50, 3*50, 25, 25, map);

		t = new TextureController();
		mainScreen = new MainScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(gameScreen);

		map.addEntity(player);

		inputController = new InputController(this);

		Gdx.input.setInputProcessor(inputController);
	}

	public void shoot1(){
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

	private final static float SIN_ALPHA = (float) Math.sin((double) 45);
	private final static float COS_ALPHA = (float) Math.cos((double) 45);
    public void shoot2() {
		float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
		float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2;

		float dx1 = sdx/2 + sdy;
		float dy1 = -sdx/2 + sdy;

		float norm = (float) Math.sqrt(dx1*dx1 + dy1*dy1);

		dx1 /= norm;
		dy1 /= norm;

		float dx2 = dx1*COS_ALPHA - dy1*SIN_ALPHA;
		float dy2 = dx1*SIN_ALPHA + dy1*COS_ALPHA;

		float dx3 = dx1*COS_ALPHA + dy1*SIN_ALPHA;
		float dy3 = -dx1*SIN_ALPHA + dy1*COS_ALPHA;

		Bullet bullet1 = new Bullet(player.getX() + 25, player.getY() + 25, dx1, dy1);
		Bullet bullet2 = new Bullet(player.getX() + 25, player.getY() + 25, dx2, dy2);
		Bullet bullet3 = new Bullet(player.getX() + 25, player.getY() + 25, dx3, dy3);

		map.addBullet(bullet1); 	   map.addBullet(bullet2);        map.addBullet(bullet3);
		gameScreen.addBullet(bullet1); gameScreen.addBullet(bullet2); gameScreen.addBullet(bullet3);
	}

	public void shoot3() {
		float sdx = Gdx.input.getX() - Gdx.graphics.getWidth()/2;
		float sdy = - Gdx.input.getY() + Gdx.graphics.getHeight()/2;

		float dx = sdx/2 + sdy;
		float dy = -sdx/2 + sdy;

		float norm = (float) Math.sqrt(dx*dx + dy*dy);

		dx /= norm;
		dy /= norm;

		float newDx, newDy;
		for(int i = 0; i < 8; ++i) {
			newDx = dx*COS_ALPHA - dy*SIN_ALPHA;
			newDy = dx*SIN_ALPHA + dy*COS_ALPHA;

			Bullet bullet = new Bullet(player.getX() + 25, player.getY() + 25, newDx, newDy);
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
