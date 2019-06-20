package com.theshooter.Logic.Entity;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class HumanEnemy extends HumanEntity {

    private Rectangle target;

    public HumanEnemy(int x, int y, int hp, Rectangle target,  Map map) {
        super(x, y, hp, Depth.ENEMY, map);
        this.target = target;
    }

    public void update() {
        if (!isBroken()) {
            int dx = (int) (target.getX() - getX());
            int dy = (int) (target.getY() - getY());
            float dist = (float) Math.hypot(dx, dy);

            if(dist < 5 * 50)
                moveAt(dx, dy);
            if (dist < 15 * 50)
                lookAt(dx - dy, -(dx + dy)/2);
            else {
                lookAt(0, 0);
                moveAt(0, 0);
            }

        }else{
            moveAt(0, 0);
        }
        super.update();
    }
}
