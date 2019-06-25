package com.theshooter.Logic.Entity.LiftableEntities;

import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.WeaponType;

public class KnifeAmmo extends LiftableEntity {
    public KnifeAmmo(int x, int y) {
        super(x, y);
    }

    @Override
    public void use() {
        Game.getInstance().getEntityController().getPlayer().addAmmo(WeaponType.THROWING_KNIFE, MathUtils.random(30, 60));
    }
}
