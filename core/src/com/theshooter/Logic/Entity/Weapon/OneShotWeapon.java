package com.theshooter.Logic.Entity.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Game;
import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Projectile;

public class OneShotWeapon extends Weapon {
    public OneShotWeapon(int            damage,
                         int            w,
                         int            h,
                         Damage.Type    type,
                         int            velocity,
                         long           shotTime,
                         long           shotLifeTime,
                         CreatureEntity owner) {
        super(
                damage,
                w,
                h,
                type,
                velocity,
                true,
                1,
                0,
                shotTime,
                shotLifeTime,
                owner);
    }


    public void attack(float dx, float dy) {
        if (canAttack()) {
            Damage damage = new Damage(getOwner(), getType(), getDamage());
            Projectile projectile =
                    new Projectile(
                            damage,
                            getOwner().getX() + getOwner().getWidth() / 2 - getW() / 2,
                            getOwner().getY() + getOwner().getHeight() / 2 - getH() / 2,
                            getW(),
                            getH(),
                            dx,
                            dy,
                            getVelocity(),
                            getShotLifeTime());

            Game.getInstance().getEntityController().addBullet(projectile);
            setLastShot(TimeUtils.millis());
            setCurClipSize(getCurClipSize() - 1);
        }
    }
}
