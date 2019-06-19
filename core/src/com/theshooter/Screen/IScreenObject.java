package com.theshooter.Screen;

public interface IScreenObject extends Comparable<IScreenObject> {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    Depth getDepth();
    int compareTo(IScreenObject s);
}
