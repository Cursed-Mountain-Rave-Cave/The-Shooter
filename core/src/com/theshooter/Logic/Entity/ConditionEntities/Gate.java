package com.theshooter.Logic.Entity.ConditionEntities;

import com.theshooter.Screen.Depth;

public class Gate extends ConditionEntity{
    public Gate(int x, int y, String flag, boolean value) {
        super(x, y, 4 * 50, 1 * 50, Depth.WALLS, false, flag, value);
    }

    public void update(){
        super.update();
        if(isActive())
            setPassable(true);
        else
            setPassable(false);
    }
}
