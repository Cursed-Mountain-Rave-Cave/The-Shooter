package com.theshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ScreenObject {

    public ScreenObject(Texture texture, int x, int y, int w, int h){
        this.texture = texture;
        this.rect = new Rectangle(x, y, w, h);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, rect.x - rect.y, (rect.x + rect.y)/2);
    }

    private Rectangle rect;
    private Texture texture;
}
