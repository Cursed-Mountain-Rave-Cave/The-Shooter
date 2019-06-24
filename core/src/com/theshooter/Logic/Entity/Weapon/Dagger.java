package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Dagger extends MeleeWeapon {
    public Dagger(CreatureEntity owner) {
        super(WeaponType.DAGGER,
                10,
                50,
                50,
                Damage.Type.PHYSICAL,
                500,
                200,
                10,
                owner);
    }
}
