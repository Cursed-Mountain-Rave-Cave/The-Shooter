package com.theshooter.Logic.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.theshooter.Screen.Depth;

public class Vase extends BreakableEntity{

    public Vase(int x, int y){
        super(x, y, 50, 50, Depth.THINGS);

    }
}
