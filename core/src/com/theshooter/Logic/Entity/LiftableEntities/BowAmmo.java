package com.theshooter.Logic.Entity.LiftableEntities;

import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.WeaponType;

public class BowAmmo extends LiftableEntity {
    public BowAmmo(int x, int y) {
        super(x, y);
    }

    @Override
    public void use() {
        Game.getInstance().getEntityController().getPlayer().addAmmo(WeaponType.BOW, MathUtils.random(10, 20));
    }
}
