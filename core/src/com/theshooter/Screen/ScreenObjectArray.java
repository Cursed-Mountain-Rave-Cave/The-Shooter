package com.theshooter.Screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Screen.ScreenObjects.ScreenObject;

public class ScreenObjectArray extends Array<ScreenObject> {

    Array<ScreenObject> floor;
    Array<ScreenObject> deleteThis;

    public ScreenObjectArray() {
        super();
        floor = new Array<>();
        deleteThis = new Array<>();
    }

    @Override
    public void add(ScreenObject value) {
        if (value.getDepth() == Depth.FLOOR)
            floor.add(value);
        else
            super.add(value);
    }


    public void draw(SpriteBatch batch) {
        sort();

        for (ScreenObject object : floor)
            object.draw(batch);

        for (ScreenObject object : this) {
            if (object.getEntity().isDeleted())
                deleteThis.add(object);
            else
                object.draw(batch);
        }
        this.removeAll(deleteThis,true);
        deleteThis.clear();
    }

    public Array<ScreenObject> getFloor() { return floor; }

    public void clear(){
        super.clear();
        floor.clear();
        deleteThis.clear();
    }
}
