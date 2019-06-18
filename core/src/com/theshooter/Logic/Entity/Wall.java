package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class Wall extends Entity{
    public Wall(int x, int y, int w, int h) {
        super(x, y, w, h, Depth.WALLS, false);
    }
}
