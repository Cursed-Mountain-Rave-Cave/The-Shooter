package com.theshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.*;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture floor, flyingFloor, box;
	private Player player;

	private ScreenObjectArray down;

	@Override
	public void create () {
		batch = new SpriteBatch();

		floor = new Texture("floor.png");
		flyingFloor = new Texture("flyingfloor.png");
		box = new Texture("box.png");

		player = new Player(new Texture("body.png"), new Texture("legs.png"), 22, 22, 50, 50);

		down = new ScreenObjectArray();

		down.add(new PlayerScreenObject(player));


       for (int i = -100; i < 100; i++)
            for (int j = -100; j < 100; j++)
                down.add(new ScreenObject(floor, i*50, j*50, 50, 50, Depth.FLOOR));

		for (int i = 20; i > 10; i--)
			for (int j = 10; j > -10; j--)
				down.add(new ScreenObject(box, i*50, j*50, 50, 50, Depth.THINGS));

		for (int i = 15; i > 10; i -= 2)
			for (int j = 10; j > -10; j -= 3)
				down.add(new ScreenObject(flyingFloor, i*50, j*50, 50, 50, Depth.WALLS));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		player.move(1,0);
		down.draw(batch);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		down.clear();
		floor.dispose();
	}
}
