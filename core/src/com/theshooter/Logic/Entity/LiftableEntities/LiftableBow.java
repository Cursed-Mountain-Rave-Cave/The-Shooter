package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.Bow;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.Weapon;
import com.theshooter.Logic.Entity.Weapon.WeaponType;

public class LiftableBow extends LiftableEntity {
    private int level;
    public LiftableBow(int level, int x, int y) {super(x, y);}

    @Override
    public void use() {
        for (Weapon weapon : Game.getInstance().getEntityController().getPlayer().getWeapons())
            if (weapon.toString().equals(WeaponType.BOW.toString()))
                return;
        Game.getInstance().getEntityController().getPlayer().addWeapon(new Bow(level, Game.getInstance().getEntityController().getPlayer()));
    }
}
