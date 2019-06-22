package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Logic.Damage;
import com.theshooter.Screen.Depth;

public class Projectile extends Entity {

    private long lifeTime;
    private Damage damage;

    private float dx;
    private float dy;
    private int velocity;

    public Projectile(Damage damage, int x, int y, int w, int h, float dx, float dy, int velocity, long lifeTime){
        super(x, y, w, h, Depth.EFFECTS);
        this.damage = damage;
        this.dx = dx;
        this.dy = dy;
        this.velocity = velocity;
        if (lifeTime == 0)
            delete();
        this.lifeTime = lifeTime + TimeUtils.millis();
    }

    @Override
    public void update() {
        if (TimeUtils.millis() > lifeTime)
            delete();
        super.update();
        setX((int) (getX() + dx * Gdx.graphics.getDeltaTime() * velocity));
        setY((int) (getY() + dy * Gdx.graphics.getDeltaTime() * velocity));
    }

    public float getDx() { return dx; }
    public float getDy() { return dy; }

    public Damage getDamage(){
        return damage;
    }
}
