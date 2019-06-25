package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Stone extends OneShotWeapon {
    public Stone(int level, CreatureEntity owner) {
        super(
                WeaponType.STONE,
                level,
                5,
                15,
                15,
                Damage.Type.PHYSICAL,
                1000,
                false,
                500,
                100_000_000,
                owner

        );
        for (int i = 0; i < level; i++)
            levelUp();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        setDamage(getDamage() + 1);
    }
}
