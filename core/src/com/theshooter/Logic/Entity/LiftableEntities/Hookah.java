package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;

public class Hookah extends LiftableEntity {
    public Hookah(int x, int y) {
        super(x, y);
    }

    @Override
    public void use() {
        Game.getInstance().getConfig().remainingHookahTime += 10;
        Game.getInstance().getConfig().enemiesVelocityMultiplier = 0.33f;
    }
}
