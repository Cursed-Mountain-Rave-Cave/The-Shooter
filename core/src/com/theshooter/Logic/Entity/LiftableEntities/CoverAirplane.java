package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;

public class CoverAirplane extends LiftableEntity {

    public CoverAirplane(int x, int y) {
        super(x, y);
    }

    @Override
    public void use() {
        Game.getInstance().getConfig().remainingVelocityUpTime += 10;
        Game.getInstance().getConfig().playerVelocityMultiplier = 3;
    }
}
