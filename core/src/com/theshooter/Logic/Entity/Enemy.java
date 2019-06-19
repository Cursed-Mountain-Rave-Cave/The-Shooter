package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class Enemy extends BreakableEntity {
    private Rectangle target;
    private Map map;
    private int velocity;

    public Enemy(int x, int y, int velocity, Rectangle player, Map map){
        super(x, y, 50, 50, Depth.ENEMY, false);
        target = player;
        this.velocity = velocity;
        this.map = map;
    }

    @Override
    public void update() {
        super.update();
        if (!isBroken()) {
            float dx = target.getX() - getX();
            float dy = target.getY() - getY();
            double len = Math.hypot(dx, dy);
            dx /= len;
            dy /= len;

            int changeX = (int) (dx * Gdx.graphics.getDeltaTime() * velocity);
            int changeY = (int) (dy * Gdx.graphics.getDeltaTime() * velocity);
            setX(getX() + changeX);
            setY(getY() + changeY);
            if (!map.isAllowed(super.getRectangle())) {
                setX(getX() - changeX);
                setY(getY() - changeY);
            }
        }
    }
}
