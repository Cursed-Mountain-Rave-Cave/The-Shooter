package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Entity;
import com.theshooter.Logic.Entity.Wall;

public class WallScreenObject extends ScreenObject {
    private Wall wall;

    public WallScreenObject(Wall wall, Texture texture) {
        super(wall, texture, 50);

        this.wall = wall;
    }
}
