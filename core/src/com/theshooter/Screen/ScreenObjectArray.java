package com.theshooter.Screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Screen.ScreenObject;

public class ScreenObjectArray extends Array<ScreenObject> {

    Array<ScreenObject> floor;

    public ScreenObjectArray(){
        super();
        floor = new Array<>();
    }

    @Override
    public void add(ScreenObject value) {
        if(value.getDepth() == Depth.FLOOR)
            floor.add(value);
        else
            super.add(value);
    }

    public void draw(SpriteBatch batch) {
        sort();

        for(ScreenObject object: floor)
            object.draw(batch);

        for(ScreenObject object: this)
            object.draw(batch);
    }
}