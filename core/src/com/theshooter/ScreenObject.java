package com.theshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ScreenObject implements IScreenObject {
    private Rectangle rect;
    private Texture texture;
    private Depth depth;

    public ScreenObject(Texture texture, int x, int y, int w, int h, Depth d){
        this.texture = texture;
        this.depth = d;
        this.rect = new Rectangle(x, y, w, h);
    }

    public ScreenObject() {}

    public void draw(SpriteBatch batch){
        batch.draw(texture, rect.x - rect.y, (rect.x + rect.y)/2);
    }

    @Override
    public int compareTo(IScreenObject s) {
        if (getDepth() == Depth.FLOOR)
            return -1;
        if (s.getDepth() == Depth.FLOOR)
            return 1;
        if (s.getY() < getY())
            return -1;
        if (s.getY() > getY())
            return 1;
        if (s.getX() < getX())
            return -1;
        if (s.getX() > getX())
            return 1;
        return depth.compareTo(s.getDepth());
    }

    public int getX() {
        return (int)rect.x;
    }

    public int getY() {
        return (int)rect.y;
    }

    public void setX(int x) {
        rect.x = x;
    }

    public void setY(int y) {
        rect.y = y;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getRect() {
        return  rect;
    }

    public Depth getDepth() {
        return depth;
    }
}

