package com.theshooter.Logic.Entity.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Projectile;

public class OneShotWeapon extends Weapon {
    public OneShotWeapon(WeaponType weaponType,
                         int            damage,
                         int            w,
                         int            h,
                         Damage.Type    type,
                         int            velocity,
                         boolean        needAmmo,
                         long           shotTime,
                         long           shotLifeTime,
                         CreatureEntity owner) {
        super(
                weaponType,
                damage,
                w,
                h,
                type,
                velocity,
                true,
                needAmmo,
                1,
                0,
                shotTime,
                shotLifeTime,
                owner);
    }

    public void attack(Vector2 vect) {
        if (canAttack()) {
            Damage damage = new Damage(getOwner(), getType(), getDamage());
            Projectile projectile =
                    new Projectile(
                            damage,
                            getOwner().getX() + getOwner().getWidth() / 2 - getW() / 2,
                            getOwner().getY() + getOwner().getHeight() / 2 - getH() / 2,
                            getW(),
                            getH(),
                            vect.x,
                            vect.y,
                            getVelocity(),
                            getShotLifeTime());

            Game.getInstance().getEntityController().addBullet(projectile);
            setLastShot(Game.getInstance().getGameTime());
            setCurClipSize(getCurClipSize() - 1);
        }
    }
}
