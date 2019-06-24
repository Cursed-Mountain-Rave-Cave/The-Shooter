package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Damage;

public class Fireball extends OneShotWeapon {
    public Fireball(int level, CreatureEntity owner) {
        super(
                WeaponType.STONE,
                level,
                25,
                30,
                30,
                Damage.Type.PHYSICAL,
                1500,
                false,
                500,
                1500,
                owner

        );
        for (int i = 0; i < level; i++)
            levelUp();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        setDamage(getDamage() + 5);
    }
}
