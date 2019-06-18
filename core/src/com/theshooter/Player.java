package com.theshooter;

import com.badlogic.gdx.graphics.Texture;
import com.theshooter.Screen.Depth;
import com.theshooter.Screen.ScreenObject;

public class Player {
    private ScreenObject top, legs;

    public Player(Texture t, Texture l, int x, int y, int h, int w) {
        top = new ScreenObject(t, x + 2 * w, y + 2 * h, w, h, Depth.PLAYER);
        legs = new ScreenObject(l, x, y, w, h, Depth.PLAYER);
    }

    public void move(int dx, int dy) {
        top.setX(top.getX() + dx);
        top.setY(top.getY() + dy);
        legs.setX(legs.getX() + dx);
        legs.setY(legs.getY() + dy);
    }

    public ScreenObject getLegs() {
        return legs;
    }

    public ScreenObject getTop() {
        return top;
    }
}
