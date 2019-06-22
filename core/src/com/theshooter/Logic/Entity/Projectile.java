package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.theshooter.Logic.Damage;
import com.theshooter.Screen.Depth;

public class Projectile extends Entity{


    private Damage damage;

    private float dx;
    private float dy;
    private int velocity;

    public Projectile(Damage damage, int x, int y, float dx, float dy, int velocity){
        super(x, y, 10, 10, Depth.EFFECTS);
        this.damage = damage;
        this.dx = dx;
        this.dy = dy;
        this.velocity = velocity;
    }

    @Override
    public void update() {
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
