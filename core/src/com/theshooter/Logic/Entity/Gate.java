package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class Gate extends BreakableEntity {
    public Gate(int x, int y) {
        super(x, y, 200, 50, 300, Depth.WALLS, false);
    }
}
