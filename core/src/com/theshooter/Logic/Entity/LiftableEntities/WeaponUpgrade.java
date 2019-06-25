package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;

public class WeaponUpgrade extends LiftableEntity {
    public WeaponUpgrade(int x, int y) {
        super(x, y);
    }

    @Override
    public void use() {
        Game.getInstance().getEntityController().getPlayer().getCurrentWeapon().levelUp();
        Game.getInstance().getAudioController().playSound("upgrade");
    }
}
