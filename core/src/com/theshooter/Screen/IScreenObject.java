package com.theshooter.Screen;

public interface IScreenObject extends Comparable<IScreenObject> {
    int getX();
    int getY();
    Depth getDepth();
    int compareTo(IScreenObject s);
}
