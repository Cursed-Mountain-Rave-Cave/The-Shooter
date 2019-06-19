package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class Tent extends BreakableEntity {

    public Tent(int x, int y){
        super(x, y, 200, 200, Depth.THINGS);
    }
}
