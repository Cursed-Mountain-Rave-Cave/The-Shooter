package com.theshooter.Screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Screen.ScreenObject;

public class ScreenObjectArray extends Array<ScreenObject> {
    public void draw(SpriteBatch batch) {
        sort();
        for(ScreenObject object: this)
            object.draw(batch);
    }
}
