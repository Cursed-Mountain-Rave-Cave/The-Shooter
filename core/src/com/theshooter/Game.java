package com.theshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture img, player, dot;
	Rectangle playerRectangle;
	Array<Rectangle> dots;
	int score, speed = 200;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		player = new Texture("player.png");
		dot = new Texture("dot.png");
		font = new BitmapFont();
		dots = new Array<Rectangle>();
		playerRectangle = new Rectangle(0, 0, 64, 64);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(player, playerRectangle.x, playerRectangle.y);
		for (Rectangle rect: dots){
			batch.draw(dot, rect.x, rect.y);
		}
		font.draw(batch, ""+score, 0, 480);

		batch.end();

		if (dots.size < 5){
			dots.add(new Rectangle(MathUtils.random(0, 800-32), MathUtils.random(0, 480-32), 32, 32));
		}

		Iterator<Rectangle> iter = dots.iterator();
		while (iter.hasNext()){
			if(iter.next().overlaps(playerRectangle)) {
				iter.remove();
				score++;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) playerRectangle.y += speed*Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) playerRectangle.y -= speed*Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) playerRectangle.x += speed*Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) playerRectangle.x -= speed*Gdx.graphics.getDeltaTime();

		if (playerRectangle.x < 0) playerRectangle.x = 0;
		if (playerRectangle.y < 0) playerRectangle.y = 0;
		if (playerRectangle.x > 800 - 64) playerRectangle.x = 800 - 64;
		if (playerRectangle.y > 480 - 64) playerRectangle.y = 480 - 64;
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		player.dispose();
		dot.dispose();
		font.dispose();
	}
}
