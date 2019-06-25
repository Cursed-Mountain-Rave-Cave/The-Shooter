package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Weapon.Bow;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.UltimateOneShotSuperMegaAnnihilationBow;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;

public class Tramp extends CreatureEntity {
    private int maxHp;

    public Tramp(int x, int y, Rectangle target) {
        super(x, y, 150,150,1000,300, 1, Depth.ENEMY, false, target);
        addWeapon(new Dagger(0, this));
        addWeapon(new Bow(0, this));
        addWeapon(new UltimateOneShotSuperMegaAnnihilationBow(2, this));
        selectWeapon(1);
        addAmmo(WeaponType.DAGGER, 1);
        addAmmo(WeaponType.BOW, 500000);
    }

    @Override
    public void update() {
        if (!isBroken()) {
            float dx = getTarget().getX() - getX();
            float dy = getTarget().getY() - getY();
            double dist = Math.hypot(getTarget().getX() - getX(), getTarget().getY() - getY());
            if (getHP() > 2 * getMaxHp() / 3)
                moveAt(dx, dy);
            else if (getHP() <= 2 * getMaxHp() / 3 && getHP() > getMaxHp() / 10) {
                moveAt(-dx, -dy);
                selectWeapon(2);
                getCurrentWeapon().setDamage(5);
                getCurrentWeapon().setLastShot(50);
                getCurrentWeapon().setCurClipSize(1000);
                getCurrentWeapon().setNeedAmmo(false);
                getCurrentWeapon().setReloadable(true);
                getCurrentWeapon().attack(new Vector2(dx, dy));
            }
            if (getHP() <= getMaxHp() / 10) {
                moveAt(dx, dy);
                selectWeapon(3);
                getCurrentWeapon().setDamage(7);
                getCurrentWeapon().setCurClipSize(1000);
                getCurrentWeapon().setNeedAmmo(false);
                getCurrentWeapon().setReloadable(true);
                setVelocity(450);
                getCurrentWeapon().attack(new Vector2(dx, dy));
            }
        } else
            moveAt(0, 0);
    }
}
