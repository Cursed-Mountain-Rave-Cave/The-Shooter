package com.theshooter.Logic.Entity.Weapon;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Projectile;

public class UltimateOneShotSuperMegaAnnihilationBow extends Weapon {
    private int shots;

    public UltimateOneShotSuperMegaAnnihilationBow(int level, CreatureEntity owner) {
        super(
                WeaponType.BOW,
                level,
                5,
                20,
                20,
                Damage.Type.PHYSICAL,
                2000,
                false,
                true,
                800,
                1000,
                100,
                2000,
                owner
                );
        shots = 3;
        for (int i = 0; i < level; i++)
            levelUp();
    }

    @Override
    public void attack(Vector2 vect) {
        if (canAttack()) {
            vect.rotate(- 3 * (shots / 2));
            for (int i = 0; i < shots; i++) {
                if (getCurClipSize() > 0) {
                    float angle = MathUtils.random(-1.5f, 1.5f);
                    vect.rotate(angle);
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
                    vect.rotate(-angle);
                    Game.getInstance().getEntityController().addBullet(projectile);
                    setLastShot(Game.getInstance().getGameTime());
                    setCurClipSize(getCurClipSize() - 1);
                    vect.rotate(3);
                }
            }
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        shots += 2;
        setDamage(getDamage() + 1);
        setClipSize(getClipSize() + 2);
    }

    @Override
    public void reload() {
        super.reload();
        if (isReload())
            getOwner().addAmmo(WeaponType.BOW, getClipSize() - getCurClipSize());
    }

    @Override
    public String toString() {
        return "ULTIMATE SUPER DUPER MEGA BOW";
    }
}
