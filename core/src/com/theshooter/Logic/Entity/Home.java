package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class Home extends Entity  {
    public Home(int x, int y) { super(x, y, 210, 210, Depth.THINGS, false); }
    public Home(int x, int y, boolean passable) { super(x, y, 200, 200, Depth.THINGS, passable); }
}