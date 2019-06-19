package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.Entity;
import com.theshooter.Logic.Entity.Wall;

public class WallScreenObject extends ScreenObject {
    private Wall wall;
    private Array<Texture> texture;

    public WallScreenObject(Wall wall, Array<Texture> texture) {
        super(wall, texture.get(0), 50);

        this.texture = texture;
        this.wall = wall;
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (int i = 0; i < texture.size; i++)
            batch.draw(texture.get(i), getScreenX() - shift, getScreenY());
    }
}
