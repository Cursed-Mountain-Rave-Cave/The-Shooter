package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Boom extends MeleeWeapon {
    public Boom(CreatureEntity owner) {
        super(WeaponType.DAGGER,
                100,
                1000,
                1000,
                Damage.Type.PHYSICAL,
                0,
                100000000,
                200,
                5,
                owner);
    }
}
