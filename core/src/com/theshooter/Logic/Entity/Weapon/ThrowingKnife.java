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
                350,
                100_000_000,
                owner
        );
    }

    @Override
    public void attack(Vector2 vect) {
        if (canAttack()) {
            super.attack(vect);
            setLastShot(0);
            setReload(false);
            setCurClipSize(1);
            vect.rotate(15f);
            super.attack(vect);
            setLastShot(0);
            setReload(false);
            setCurClipSize(1);
            vect.rotate(-30f);
            super.attack(vect);
        }
    }
}
