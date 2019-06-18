package com.theshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ScreenObjectArray extends Array<ScreenObject> {
    public void draw(SpriteBatch batch) {
        sort();
        for(ScreenObject object: this)
            object.draw(batch);
    }
}
