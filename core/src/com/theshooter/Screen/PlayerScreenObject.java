package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Player;

public class PlayerScreenObject extends ScreenObject {
    private Player player;

    private Texture[] body;
    private Texture[] legs;

    private int currentBody;
    private int currentLegs;

    public PlayerScreenObject(Player player, Texture[] body, Texture[] legs) {
        this.player = player;
        this.body = body;
        this.legs = legs;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(legs[currentLegs], player.getX() - player.getY(), (player.getX() + player.getY())/2);
        batch.draw(body[currentBody], player.getX() - player.getY(), (player.getX() + player.getY())/2);
    }

    public void setCurrentLegs(int dx, int dy) {
        if (dx == 0 && dy < 0)
            this.currentLegs = 0;
        if (dx > 0 && dy == 0)
            this.currentLegs = 1;
        if (dx == 0 && dy > 0)
            this.currentLegs = 2;
        if (dx < 0 && dy == 0)
            this.currentLegs = 3;
        if (dx > 0 && dy > 0)
            this.currentLegs = 4;
        if (dx < 0 && dy > 0)
            this.currentLegs = 5;
        if (dx > 0 && dy < 0)
            this.currentLegs = 6;
        if (dx < 0 && dy < 0)
            this.currentLegs = 7;
    }

    public void setCurrentBody(float dx, float dy) {
        double angle = Math.atan2(dy, dx) * 180 / 3.14;
        if (angle > 112.5 && angle < 157.5)
            this.currentBody = 7;
        else if (angle > 67.5 && angle < 112.5)
            this.currentBody = 0;
        else if (angle > 22.5 && angle < 67.5)
            this.currentBody = 6;
        else if (angle > -22.5 && angle < 22.5)
            this.currentBody = 1;
        else if (angle < -22.5 && angle > -67.5)
            this.currentBody = 4;
        else if (angle < -67.5 && angle > -112.5)
            this.currentBody = 2;
        else if (angle < -112.5 && angle > -157.5)
            this.currentBody = 5;
        else
            this.currentBody = 3;
    }

    public int getX() {
        return player.getX();
    }

    public int getY() {
        return player.getY();
    }

    public Depth getDepth() {
        return player.getDepth();
    }
}
