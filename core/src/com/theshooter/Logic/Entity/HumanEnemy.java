package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class HumanEnemy extends HumanEntity {

    private Rectangle target;

    public HumanEnemy(int x, int y, Rectangle target,  Map map) {
        super(x, y, Depth.ENEMY, map);
        this.target = target;
    }

    public void update() {
        if (!isBroken()) {
            int dx = (int) (target.getX() - getX());
            int dy = (int) (target.getY() - getY());
            moveAt(dx, dy);
            lookAt(dx - dy, -(dx + dy)/2);
        }else{
            moveAt(0, 0);
        }
        super.update();
    }
}
