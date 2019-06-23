package com.theshooter.Logic.Entity.Weapon;


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
                400,
                100_000_000,
                owner
        );
    }

    @Override
    public void levelUp() {
        setDamage(getDamage() + 3);
    }
}
