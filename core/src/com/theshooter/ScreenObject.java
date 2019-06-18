package com.theshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ScreenObject implements Comparable<ScreenObject> {
    private Rectangle rect;
    private Texture texture;
    private Depth depth;


    public ScreenObject(Texture texture, int x, int y, int w, int h, Depth d){
        this.texture = texture;
        this.depth = d;
        this.rect = new Rectangle(x, y, w, h);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, rect.x - rect.y, (rect.x + rect.y)/2);
    }

    public int compareTo(ScreenObject s) {
        if (s.rect.y < rect.y)
            return -1;
        if (s.rect.y > rect.y)
            return 1;
        if (s.rect.x < rect.x)
            return -1;
        if (s.rect.x > rect.x)
            return 1;
        return depth.compareTo(s.depth);
    }

}

