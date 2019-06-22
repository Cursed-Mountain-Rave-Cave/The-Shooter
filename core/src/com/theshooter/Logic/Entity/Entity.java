package com.theshooter.Logic.Entity;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Entity.Abstract.IEntity;
import com.theshooter.Logic.Entity.Abstract.IUpdateble;
import com.theshooter.Screen.Depth;

public class Entity implements IEntity {
    private Rectangle rect;
    private Depth depth;
    boolean passable;
    boolean isDelete;

    public Entity(Rectangle r, Depth depth, boolean passable) {
        rect = new Rectangle(r);
        this.depth = depth;
        this.passable = passable;
    }
    public Entity(int x, int y, int w, int h, Depth depth, boolean passable){
        this(new Rectangle(x, y, w, h), depth, passable);
    }
    public Entity(int x, int y, int w, int h, Depth depth){
        this(x, y, w, h, depth, true);
    }

    public void update() {}

    public void delete(){
        this.isDelete = true;
    }
    public boolean isDeleted(){
        return this.isDelete;
    }

    public Depth getDepth() {
        return depth;
    }
    public int getX(){
        return (int) rect.getX();
    }
    public int getY(){
        return (int) rect.getY();
    }
    public int getWidth(){
        return (int) rect.getWidth();
    }
    public int getHeight(){
        return (int) rect.getHeight();
    }
    public Rectangle getRectangle(){
        return rect;
    }
    public boolean isPassable(){
        return passable;
    }

    public void setDepth(Depth depth) {
        this.depth = depth;
    }
    public void setX(int x){
        rect.setX(x);
    }
    public void setY(int y){
        rect.setY(y);
    }
    public void setWidth(int width){
        rect.setWidth(width);
    }
    public void setHeight(int height){
        rect.setHeight(height);
    }
    public void setRectangle(Rectangle rect){
        this.rect = rect;
    }
    public void setPassable(boolean passable){
        this.passable = passable;
    }
}