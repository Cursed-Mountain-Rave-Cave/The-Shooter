package com.theshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture floor;

	private Array<ScreenObject> down;

	@Override
	public void create () {
		batch = new SpriteBatch();

		floor = new Texture("floor.png");

		down = new Array<ScreenObject>();

		for (int i = -100; i < 100; i++)
			for (int j = -100; j < 100; j++)
				down.add(new ScreenObject(floor, i*50, j*50, 50, 50));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		for(ScreenObject object: down)
			object.draw(batch);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		down.clear();
		floor.dispose();
	}
}
