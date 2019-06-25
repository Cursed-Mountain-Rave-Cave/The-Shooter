package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Weapon.Bow;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;

public class Garbage extends CreatureEntity {
    public Garbage(int x, int y, Rectangle target) {
        super(x, y, 75, 75, 5, 100, 500, Depth.ENEMY, false, target);
    }

    @Override
    public void breakDown(Damage damage) {
        if (getHP() > 0)
            setHp(getHP() - damage.getValue());
        if (getHP() <= 0)
            setBroken(true);
        setDamaged(true);
        if (MathUtils.random(1, 100) > 50)
            Game.getInstance().getAudioController().playSound("boss1");
    }

    @Override
    public void update() {
        setVelocityMultiplier(Game.getInstance().getConfig().enemiesVelocityMultiplier);
        if (!isBroken()) {
            float dx = getTarget().getX() - getX();
            float dy = getTarget().getY() - getY();
            moveAt(-dx, -dy);
        }else
            moveAt(0, 0);
    }
}
