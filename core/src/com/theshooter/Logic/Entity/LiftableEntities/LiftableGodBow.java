package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.UltimateOneShotSuperMegaAnnihilationBow;
import com.theshooter.Logic.Entity.Weapon.Weapon;
import com.theshooter.Logic.Entity.Weapon.WeaponType;

public class LiftableGodBow extends LiftableEntity {
    private int level;
    public LiftableGodBow(int level, int x, int y) {super(x, y);}

    @Override
    public void use() {
        for (Weapon weapon : Game.getInstance().getEntityController().getPlayer().getWeapons())
            if (weapon.toString().equals("ULTIMATE SUPER DUPER MEGA BOW"))
                return;
        Game.getInstance().getEntityController().getPlayer().addWeapon(new UltimateOneShotSuperMegaAnnihilationBow(level, Game.getInstance().getEntityController().getPlayer()));
    }
}
