package com.theshooter.Logic.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.theshooter.Screen.Depth;
import com.theshooter.Screen.ScreenObject;

public class Player extends Entity{
    public Player(int x, int y, int h, int w) {
        super(x, y, w, h, Depth.PLAYER);
    }

    public void move(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
