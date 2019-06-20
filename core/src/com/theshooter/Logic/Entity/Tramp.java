package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;

public class Tramp extends Enemy {
    private long lastAttck;
    private boolean isLooked = false;
    public Tramp(int x, int y, Rectangle target, Map map) {
        super(x, y, 200,200,1000,390, target, map);
    }

    @Override
    public void update() {

            super.update();
            this.velocity = 100;
            if (!isBroken()) {
                float dx = target.getX() - getX();
                float dy = target.getY() - getY();
                double len = Math.hypot(dx, dy);
                dx /= len;
                dy /= len;

                int changeX = (int) (dx * Gdx.graphics.getDeltaTime() * velocity);
                int changeY = (int) (dy * Gdx.graphics.getDeltaTime() * velocity);

                if (damaged || isLooked) {
                    setX(getX() + changeX);
                    setY(getY() + changeY);
                }

                if (!map.isAllowed(super.getRectangle())) {
                    setX(getX() - changeX);
                    setY(getY() - changeY);
                }


                if (Math.hypot(target.getX() - getX(), target.getY() - getY()) < 5 * 50) {
                    isLooked = true;
                    map.game.shoot1(getRectangle(), target);
                }


            }
        }
}
