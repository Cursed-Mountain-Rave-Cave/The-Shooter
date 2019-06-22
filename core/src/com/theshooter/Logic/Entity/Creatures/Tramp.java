package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Screen.Depth;

public class Tramp extends CreatureEntity {
    private int maxHp;

    public Tramp(int x, int y, Rectangle target) {
        super(x, y, 150,150,1000,300, 1, Depth.ENEMY, false, target);
        maxHp = getHP();
    }

    @Override
    public void update() {
        if (!isBroken()) {
            float dx = getTarget().getX() - getX();
            float dy = getTarget().getY() - getY();
            double dist = Math.hypot(getTarget().getX() - getX(), getTarget().getY() - getY());
            if (getHP() > 2 * maxHp / 3)
                moveAt(dx, dy);
            else if (getHP() <= 2 * maxHp / 3 && getHP() > maxHp / 10) {
                moveAt(-dx, -dy);
                Game.getInstance().shoot1(this, getRectangle(), getTarget());
              //  setHp(getHP() + 1);
            }
            if (getHP() <= maxHp / 10) {
                for (int i = 0; i < 10; i++) {
                  //  map.game.shoot3(getRectangle(), target);
                    Game.getInstance().shoot1(this, getRectangle(), getTarget());
                }
                if (dist > 15 * 50)
                    moveAt(dx, dy);
                //tryMove(MathUtils.random(-50, 50), MathUtils.random(-50, 50));

                setVelocity(450);
            }
        }
    }
}
