package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.Bow;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.Weapon;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;

public class BiggerBoss extends CreatureEntity {
    public BiggerBoss(int x, int y, Rectangle target) {
        super(x, y, 100, 100, 500, 250, 5000000, Depth.ENEMY, false, target);
        Game.getInstance().getAudioController().playSound("boss2");
    }

    @Override
    public void update() {
        setVelocityMultiplier(Game.getInstance().getConfig().enemiesVelocityMultiplier);
        if (!isBroken()) {
            float dx = getTarget().getX() - getX();
            float dy = getTarget().getY() - getY();
            moveAt(-dx, -dy);
            for (Weapon weapon : getWeapons()) {
                weapon.update();
                if (weapon.getCurClipSize() == 0 && !weapon.isReload()) {
                    weapon.reload();
                }
            }
            getCurrentWeapon().attack(new Vector2(dx, dy));
        }else
            moveAt(0, 0);
    }
}
