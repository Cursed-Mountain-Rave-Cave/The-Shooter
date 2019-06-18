package com.theshooter.Logic.Entity;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Screen.Depth;

public class Entity {
    private Rectangle rect;
    private Depth depth;
    boolean passable;

    public Entity(int x, int y, int w, int h, Depth depth, boolean passable){
        this.rect = new Rectangle(x, y, w, h);
        this.depth = depth;
        this.passable = passable;
    }

    public Entity(int x, int y, int w, int h, Depth depth){
        this(x, y, w, h, depth, true);
    }

    public void update(){

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
