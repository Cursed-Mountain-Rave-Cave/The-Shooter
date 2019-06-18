package com.theshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerScreenObject extends ScreenObject {
    private Player player;
    private Rectangle rect;
    private Depth depth = Depth.PLAYER;

    public PlayerScreenObject(Player player) {
        this.player = player;
        this.rect = player.getLegs().getRect();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(player.getTop().getTexture(), player.getTop().getX() - player.getTop().getY(),
                (player.getTop().getX() + player.getTop().getY())/2);
        batch.draw(player.getLegs().getTexture(), player.getLegs().getX() - player.getLegs().getY(),
                (player.getLegs().getX() + player.getLegs().getY())/2);
    }

    public int compareTo(IScreenObject s) {
        return player.getLegs().compareTo(s);
    }

    public int getX() {
        return player.getLegs().getX();
    }


    public int getY() {
        return player.getLegs().getY();
    }

    public Depth getDepth() {
        return player.getLegs().getDepth();
    }
}
