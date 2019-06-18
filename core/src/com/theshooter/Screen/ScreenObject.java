package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Entity;

public class ScreenObject implements IScreenObject {

    Entity entity;
    private Texture texture;

    public ScreenObject(Entity entity, Texture texture){
        this.entity = entity;
        this.texture = texture;
    }

    public ScreenObject() {}

    public void draw(SpriteBatch batch){
        batch.draw(texture, entity.getX() - entity.getY(), (entity.getX() + entity.getY())/2);
    }

    @Override
    public int compareTo(IScreenObject s) {
        if(getDepth() != Depth.FLOOR && s.getDepth() != Depth.FLOOR){
            if (getDepth() == Depth.FLOOR)
                return -1;
            if (s.getDepth() == Depth.FLOOR)
                return 1;
        }

        if (s.getY() < getY())
            return -1;
        if (s.getY() > getY())
            return 1;
        if (s.getX() < getX())
            return -1;
        if (s.getX() > getX())
            return 1;

        return getDepth().compareTo(s.getDepth());
    }


    public int getX() {
        return entity.getX();
    }
    public int getY() {
        return entity.getY();
    }
    public Texture getTexture() {
        return texture;
    }
    public Depth getDepth() {
        return entity.getDepth();
    }

    public void setX(int x) {
        entity.setX(x);
    }
    public void setY(int y) {
        entity.setY(y);
    }
    public void setDepth(Depth depth){
        entity.setDepth(depth);
    }
}

