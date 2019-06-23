package com.theshooter.Logic.Entity.Weapon;


import com.badlogic.gdx.math.Vector2;
import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class ThrowingKnife extends OneShotWeapon {
    public ThrowingKnife(CreatureEntity owner) {
        super(
                WeaponType.THROWING_KNIFE,
                10,
                10,
                10,
                Damage.Type.PHYSICAL,
                1500,
                true,
                0,
                100_000_000,
                owner
        );
        setClipSize(3);
        setReloadingTime(500);
    }

    @Override
    public void attack(Vector2 vect) {
        for (int i = 0; i < 3; i++)
            if (canAttack()) {
                vect.rotate((float)Math.pow(-1, i) * i * 15);
                super.attack(vect);
                setLastShot(0);
            }
    }

    @Override
    public void levelUp() {
        setDamage(getDamage() + 5);
    }
}
