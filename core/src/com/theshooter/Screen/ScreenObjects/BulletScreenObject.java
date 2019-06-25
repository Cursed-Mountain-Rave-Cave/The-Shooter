package com.theshooter.Screen.ScreenObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Projectile;

public class BulletScreenObject extends ScreenObject {

    public BulletScreenObject(Projectile projectile, Texture texture, int shift) {
        super(projectile, texture, shift);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getScreenX(), getScreenY() + 100);
    }
}
