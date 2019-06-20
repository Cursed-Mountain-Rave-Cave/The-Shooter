package com.theshooter.Logic.Entity;

import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class Player extends HumanEntity{

    public Player(int x, int y, int h, int w, Map map) {
        super(x, y, 400, Depth.PLAYER, map);
    }

}
