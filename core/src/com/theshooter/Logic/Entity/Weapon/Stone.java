package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Stone extends OneShotWeapon {
    public Stone(CreatureEntity owner) {
        super(
                5,
                15,
                15,
                Damage.Type.PHYSICAL,
                1000,
                500,
                100_000_000,
                owner

        );
    }
}
