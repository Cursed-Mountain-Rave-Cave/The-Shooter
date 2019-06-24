package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Bow extends OneShotWeapon {
    public Bow(int level, CreatureEntity owner) {
        super(
                WeaponType.BOW,
                level,
                15,
                10,
                10,
                Damage.Type.PHYSICAL,
                2000,
                true,
                800,
                1500,
                owner);
        for (int i = 0; i < level; i++)
            levelUp();
    }

    @Override
    public void levelUp() {
        setDamage(getDamage() + 5);
    }
}
