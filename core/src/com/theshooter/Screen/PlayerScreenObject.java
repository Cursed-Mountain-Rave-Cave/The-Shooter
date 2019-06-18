package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Player;

public class PlayerScreenObject extends ScreenObject {
    private Player player;

    private Texture body;
    private Texture legs;

    public PlayerScreenObject(Player player, Texture body, Texture legs) {
        this.player = player;
        this.body = body;
        this.legs = legs;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(legs, player.getX() - player.getY(), (player.getX() + player.getY())/2);
        batch.draw(body, player.getX() - player.getY(), (player.getX() + player.getY())/2);
    }

    public int getX() {
        return player.getX();
    }

    public int getY() {
        return player.getY();
    }

    public float getScreenX() {
        return player.getX() - player.getY();
    }

    public float getScreenY() {
        return (player.getX() + player.getY())/2;
    }

    public Depth getDepth() {
        return player.getDepth();
    }
}
