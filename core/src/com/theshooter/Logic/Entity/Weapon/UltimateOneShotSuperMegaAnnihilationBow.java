package com.theshooter.Logic.Entity.Weapon;

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
                50,
                20,
                20,
                Damage.Type.PHYSICAL,
                2000,
                true,
                true,
                200,
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
            vect.rotate(- 5 * (shots / 2));
            for (int i = 0; i < shots; i++) {
                if (getCurClipSize() > 0) {
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
                    vect.rotate(5);
                }
            }
        }
    }

    @Override
    public void levelUp() {
        shots += 2;
    }

    @Override
    public String toString() {
        return "ULTIMATE SUPER DUPER MEGA BOW";
    }
}
