package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Dagger extends MeleeWeapon {
    public Dagger(int level, CreatureEntity owner) {
        super(
                WeaponType.DAGGER,
                level,
                10,
                50,
                50,
                Damage.Type.PHYSICAL,
                500,
                200,
                200,
                10,
                owner);
    }
}
