package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;

public class Tramp extends Enemy {
    private int maxHp;

    public Tramp(int x, int y, Rectangle target, Map map) {
        super(x, y, 200,200,1000,300, target, map);
        maxHp = getHP();
    }

    private void tryMove(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
        if (!map.isAllowed(super.getRectangle())) {
            setX(getX() - x);
            setY(getY() - y);
        }
    }

    @Override
    public void update() {

        //super.update();
        if (!isBroken()) {
            float dx = target.getX() - getX();
            float dy = target.getY() - getY();
            double len = Math.hypot(dx, dy);
            dx /= len;
            dy /= len;

            int changeX = (int) (dx * Gdx.graphics.getDeltaTime() * velocity);
            int changeY = (int) (dy * Gdx.graphics.getDeltaTime() * velocity);

            double dist = Math.hypot(target.getX() - getX(), target.getY() - getY());
            if (getHP() > 2 * maxHp / 3)
                tryMove(changeX, changeY);
            else if (getHP() <= 2 * maxHp / 3 && getHP() > maxHp / 3) {
                tryMove(-changeX, -changeY);
                map.game.shoot1(getRectangle(), target);
              //  setHp(getHP() + 1);
            }
            if (getHP() <= maxHp / 3) {
                for (int i = 0; i < 10; i++) {
                  //  map.game.shoot3(getRectangle(), target);
                    map.game.shoot1(getRectangle(), target);
                }
                if (dist > 15 * 50)
                    tryMove(changeX, changeY);
                //tryMove(MathUtils.random(-50, 50), MathUtils.random(-50, 50));

                this.velocity = 450;
            }
        }
    }
}
