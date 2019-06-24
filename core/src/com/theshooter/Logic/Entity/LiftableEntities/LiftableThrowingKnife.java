package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.ThrowingKnife;
import com.theshooter.Logic.Entity.Weapon.Weapon;
import com.theshooter.Logic.Entity.Weapon.WeaponType;

public class LiftableThrowingKnife extends LiftableEntity {
    private int level;
    public LiftableThrowingKnife(int level, int x, int y) {super(x, y);}

    @Override
    public void use() {
        for (Weapon weapon : Game.getInstance().getEntityController().getPlayer().getWeapons())
            if (weapon.toString().equals(WeaponType.THROWING_KNIFE.toString()))
                return;
        Game.getInstance().getEntityController().getPlayer().addWeapon(new ThrowingKnife(level, Game.getInstance().getEntityController().getPlayer()));
    }
}
