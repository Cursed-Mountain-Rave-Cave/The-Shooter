package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Damage;

public class Boom extends MeleeWeapon {
    public Boom(int level, CreatureEntity owner) {
        super(WeaponType.DAGGER,
                level,
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
