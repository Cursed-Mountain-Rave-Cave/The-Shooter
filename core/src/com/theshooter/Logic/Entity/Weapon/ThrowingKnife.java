package com.theshooter.Logic.Entity.Weapon;


import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class ThrowingKnife extends OneShotWeapon {
    public ThrowingKnife(CreatureEntity owner) {
        super(
                10,
                10,
                10,
                Damage.Type.PHYSICAL,
                1500,
                350,
                100_000_000,
                owner

        );
    }
}
