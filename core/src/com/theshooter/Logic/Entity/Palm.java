package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class Palm extends Entity  {
    public Palm(int x, int y) { super(x, y, 30, 30, Depth.THINGS, false); }
    public Palm(int x, int y, boolean passable) { super(x, y, 30, 30, Depth.THINGS, passable); }
}
