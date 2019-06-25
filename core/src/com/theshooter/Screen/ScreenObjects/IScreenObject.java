package com.theshooter.Screen.ScreenObjects;

import com.theshooter.Screen.Depth;

public interface IScreenObject extends Comparable<IScreenObject> {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    Depth getDepth();
    int compareTo(IScreenObject s);
}
