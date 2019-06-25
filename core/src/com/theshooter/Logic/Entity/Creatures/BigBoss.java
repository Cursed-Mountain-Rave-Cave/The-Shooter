package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.UltimateOneShotSuperMegaAnnihilationBow;
import com.theshooter.Logic.Entity.Weapon.Weapon;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;

public class BigBoss extends CreatureEntity {
    public BigBoss(int x, int y, Rectangle target) {
        super(x, y, 75, 75, 100, 400, 50000, Depth.ENEMY, false, target);
        Game.getInstance().getAudioController().playSound("boss2");
    }

    @Override
    public void update() {
        setVelocityMultiplier(Game.getInstance().getConfig().enemiesVelocityMultiplier);
        if (!isBroken()) {
            setVelocityMultiplier(Game.getInstance().getConfig().enemiesVelocityMultiplier);
            moveAt(0, 0);
            float dx = getTarget().getX() - getX();
            float dy = getTarget().getY() - getY();
            for (Weapon weapon : getWeapons()) {
                weapon.update();
                if (weapon.getCurClipSize() == 0 && !weapon.isReload()) {
                    weapon.reload();
                }
            }
            getCurrentWeapon().attack(new Vector2(dx, dy));
        }
    }
}
