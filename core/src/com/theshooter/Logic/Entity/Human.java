package com.theshooter.Logic.Entity;

import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class Human extends HumanEntity{

    public Human(int x, int y, int h, int w, Map map) {
        super(x, y, Depth.PLAYER, map);
    }

}
