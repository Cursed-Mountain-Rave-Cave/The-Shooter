package com.theshooter;

import com.badlogic.gdx.math.Rectangle;

public interface IScreenObject extends Comparable<IScreenObject> {
    int getX();
    int getY();
    Depth getDepth();
    int compareTo(IScreenObject s);
}
