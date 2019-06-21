package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.Entity;
import com.theshooter.Logic.Entity.Wall;

public class WallScreenObject extends ScreenObject {
    private Wall wall;
    private Array<Texture> texture;

    private int x, y;
    private int screenX, screenY;

    public WallScreenObject(Wall wall, int x, int y, Array<Texture> texture) {
        super(wall, texture.get(0), 50);

        this.texture = texture;
        this.wall = wall;

        this.x = x;
        this.y = y;

        this.screenX = x - y;
        this.screenY = (x + y) / 2;
    }

    public WallScreenObject(Wall wall, Array<Texture> texture) {
        this(wall, wall.getX(), wall.getY(), texture);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return 50;
    }
    public int getHeight() {
        return 50;
    }
    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (int i = 0; i < texture.size; i++)
            batch.draw(texture.get(i), screenX - shift, screenY);
    }
}
