package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Entity;

public class ScreenObject implements IScreenObject {

    Entity entity;
    int shift;
    private Texture texture;

    public ScreenObject(Entity entity, Texture texture, int shift){
        this.entity = entity;
        this.texture = texture;
        this.shift = shift;
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, getScreenX()- shift, getScreenY() );
    }

    @Override
    public int compareTo(IScreenObject s) {
        if(getDepth() != Depth.FLOOR || s.getDepth() != Depth.FLOOR){
            if (getDepth() == Depth.FLOOR)
                return -1;
            if (s.getDepth() == Depth.FLOOR)
                return 1;
        }

        if (s.getX() + s.getWidth() / 2 + s.getY() + s.getHeight() / 2 <
                getX() + getWidth() / 2 + getY() + getHeight() / 2)
            return -1;
        if (s.getX() + s.getWidth() / 2 + s.getY() + s.getHeight() / 2 >
                getX() + getWidth() / 2 + getY() + getHeight() / 2)
            return 1;

        return getDepth().compareTo(s.getDepth());
    }


    public int getX() {
        return entity.getX();
    }
    public int getY() {
        return entity.getY();
    }
    public int getWidth() {
        return entity.getWidth();
    }
    public int getHeight() {
        return entity.getHeight();
    }
    public int getScreenX() {
        return entity.getX() - entity.getY();
    }
    public int getScreenY() {
        return (entity.getX() + entity.getY())/2;
    }
    public Texture getTexture() {
        return texture;
    }
    public Depth getDepth() {
        return entity.getDepth();
    }
    public Entity getEntity() { return entity; }

    public void setX(int x) {
        entity.setX(x);
    }
    public void setY(int y) {
        entity.setY(y);
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public void setDepth(Depth depth){
        entity.setDepth(depth);
    }
}

