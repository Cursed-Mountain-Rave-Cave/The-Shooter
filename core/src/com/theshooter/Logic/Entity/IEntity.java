package com.theshooter.Logic.Entity;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Screen.Depth;

public interface IEntity {

    void update();

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
