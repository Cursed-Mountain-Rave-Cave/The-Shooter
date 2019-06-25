package com.theshooter.Logic.Entity.Weapon;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Projectile;

public class Bow extends OneShotWeapon {
    public Bow(int level, CreatureEntity owner) {
        super(
                WeaponType.BOW,
                level,
                15,
                10,
                10,
                Damage.Type.PHYSICAL,
                2000,
                true,
                800,
                1500,
                owner);
        for (int i = 0; i < level; i++)
            levelUp();
    }

    @Override
    public void levelUp() {
        if (getLevel() < 8) {
            super.levelUp();
            setDamage(getDamage() + 2);
            setShotTime(Math.max(getShotTime() - 50, 200));
            if (getLevel() > 4)
                setClipSize(3);
        }
    }

    @Override
    public void attack(Vector2 vect) {
        if (getLevel() < 5)
            super.attack(vect);
        else {
            if (canAttack()) {
                for (int i = 0; i < 3; i++) {
                    if (getCurClipSize() > 0) {
                        Damage damage = new Damage(getOwner(), getType(), getDamage() / 3);
                        vect.rotate((float)Math.pow(-1, i) * i * 7.5f);
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
        }
    }

}
