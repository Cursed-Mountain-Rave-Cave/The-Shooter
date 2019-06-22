package com.theshooter.Logic.Entity.Abstract;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Screen.Depth;

public interface IEntity extends IUpdateble {
    Depth getDepth();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    Rectangle getRectangle();
    boolean isPassable();

    void delete();
    boolean isDeleted();

    void setDepth(Depth depth);
    void setX(int x);
    void setY(int y);
    void setWidth(int width);
    void setHeight(int height);
    void setRectangle(Rectangle rect);
    void setPassable(boolean passable);

}
