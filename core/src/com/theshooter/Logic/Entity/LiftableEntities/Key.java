package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Game;

public class Key extends LiftableEntity {

    private String flag;
    private boolean value;

    public Key(int x, int y, String flag, boolean value) {
        super(x, y);
        this.flag = flag;
        this.value = value;
    }

    @Override
    public void use() {
        Game.getInstance().getEventController().addFlag(flag, value);
    }
}
