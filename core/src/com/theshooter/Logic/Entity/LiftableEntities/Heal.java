package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;

public class Heal extends LiftableEntity {
    public Heal(int x, int y) {
        super(x, y);
    }

    @Override
    public void use() {
        Game.getInstance().getEntityController().getPlayer().setHp(Game.getInstance().getEntityController().getPlayer().getHP() + 100);
    }
}
