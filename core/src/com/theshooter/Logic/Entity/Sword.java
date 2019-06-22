package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Game;
import com.theshooter.Logic.Damage;

public class Sword extends Weapon {
    public Sword(int damage, long shotTime, IEntity owner) {
        super(damage, Damage.Type.PHYSICAL, 0, true, 10, 2000, shotTime, (long)200, owner);
    }

    public void attack() {
        if (canAttack()) {
            float sdx = Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
            float sdy = -Gdx.input.getY() + Gdx.graphics.getHeight() / 2 - 100;

            float dx = sdx / 2 + sdy;
            float dy = -sdx / 2 + sdy;

            float norm = (float) Math.sqrt(dx * dx + dy * dy);

            dx /= norm;
            dy /= norm;

            float scatter = MathUtils.random(-5, 5);
            float sinAlpha = (float) Math.sin(Math.toRadians((double) scatter));
            float cosAlpha = (float) Math.cos(Math.toRadians((double) scatter));


            Projectile projectile = new Projectile(new Damage(getOwner(), getType(), getDamage()), (int)(getOwner().getX() + 50 * dx + 25 - 250),
                    (int)(getOwner().getY() + 50 * dy + 25 - 250), 500, 500, 0, 0, getVelocity(), getShotLifeTime());
            //System.out.println(projectile.getX() + " " + projectile.getY());
            Game.getInstance().getEntityController().addBullet(projectile);

            lastShot = TimeUtils.millis();
            setCurClipSize(getCurClipSize() - 1);
        }
    }
}
