package com.theshooter.Logic.Entity.Weapon;


import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Projectile;

public class ThrowingKnife extends OneShotWeapon {
    public ThrowingKnife(int level, CreatureEntity owner) {
        super(
                WeaponType.THROWING_KNIFE,
                level,
                10,
                10,
                10,
                Damage.Type.PHYSICAL,
                1500,
                true,
                400,
                1500,
                owner
        );
        for (int i = 0; i < level; i++)
            levelUp();
        setClipSize(3);
    }

    @Override
    public void attack(Vector2 vect) {
        int attacks = 3 + (getLevel() > 4 ? 2 : 0);
        float angle = (attacks == 3 ? 15 : 7.5f);
        vect.rotate(-15);
        if (canAttack()) {
            for (int i = 0; i < attacks; i++) {
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
                    vect.rotate(angle);
                }
            }
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        setDamage(getDamage() + 1);
        if (getLevel() > 4)
            setClipSize(5);
    }
}
