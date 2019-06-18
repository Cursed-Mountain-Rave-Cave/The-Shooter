package com.theshooter.Screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Player;

public class PlayerScreenObject extends ScreenObject {
    private Player player;

    public PlayerScreenObject(Player player) {
        this.player = player;
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

    public float getScreenX() {
        return player.getLegs().getX() - player.getLegs().getY();
    }

    public float getScreenY() {
        return (player.getLegs().getX() + player.getLegs().getY())/2;
    }

    public Depth getDepth() {
        return player.getLegs().getDepth();
    }
}
