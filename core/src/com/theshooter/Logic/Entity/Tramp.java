package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;

public class Tramp extends Enemy {
    private long lastAttck;

    public Tramp(int x, int y, Rectangle target, Map map) {
        super(x, y, 200,200,30,900, target, map);
    }

    @Override
    public void update() {
        super.update();
        if (!broken /*&& (Math.hypot(target.getX() - getX(), target.getY() - getY()) < 5 * 50 || damaged)*/)
            map.game.shoot1(getRectangle(), target);
    }
}
