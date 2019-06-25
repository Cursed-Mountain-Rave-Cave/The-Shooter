package com.theshooter.Logic.Entity.ConditionEntities;

import com.theshooter.Game;
import com.theshooter.Logic.Entity.Entity;
import com.theshooter.Screen.Depth;

public class ConditionEntity extends Entity {

    private String flag;
    private boolean value;
    private boolean active;

    public ConditionEntity(int x, int y, int w, int h, Depth depth, boolean passable, String flag, boolean value) {
        super(x, y, w, h, depth, passable);
        this.flag = flag;
        this.value = value;
        this.active = false;
    }

    public void update(){
        super.update();
        active = Game.getInstance().getEventController().checkFlag(flag, value);
    }

    public boolean isActive(){
        return active;
    }
}
