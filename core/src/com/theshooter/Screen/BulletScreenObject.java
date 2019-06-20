package com.theshooter.Screen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Bullet;

public class BulletScreenObject extends ScreenObject {

    public BulletScreenObject(Bullet bullet, Texture texture, int shift) {
        super(bullet, texture, shift);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getScreenX(), getScreenY() + 100);
    }
}
