package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Game;
import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Abstract.IMovable;
import com.theshooter.Logic.Entity.BreakableEntity;
import com.theshooter.Logic.Entity.Weapon.Weapon;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;

import java.util.Map;
import java.util.TreeMap;

public class CreatureEntity extends BreakableEntity implements IMovable {

    private Rectangle target;
    private int velocity;
    private boolean damaged;
    private float movedx, movedy;
    private int radius;
    private Array<Weapon> weapons;
    private Weapon currentWeapon;
    private Map<WeaponType, Integer> ammo;

    public CreatureEntity(int x, int y, int w, int h, int hp, int velocity, int radius, Depth depth, boolean passable, Rectangle target){
        super(x, y, w, h, hp, depth, passable);
        this.target = target;
        this.velocity = velocity;
        this.damaged = false;
        this.radius = radius;
        weapons = new Array<>();
        ammo = new TreeMap<>();
    }

    @Override
    public void update() {
        if (!isBroken()) {
            float dx = target.getX() - getX();
            float dy = target.getY() - getY();
            double len = Math.hypot(dx, dy);
            if (len < radius * 50 || damaged)
                moveAt(dx, dy);
            else
                moveAt(0, 0);
        }else
            moveAt(0, 0);
    }

    public void breakDown(Damage damage) {
        super.breakDown(damage);
        damaged = true;
        Game.getInstance().getAudioController().playSound("damage");
    }

    @Override
    public void move() {
        int changeX = (int) (movedx * Gdx.graphics.getDeltaTime() * velocity);
        int changeY = (int) (movedy * Gdx.graphics.getDeltaTime() * velocity);

        setX(getX() + changeX);
        setY(getY() + changeY);

        if (!Game.getInstance().getEntityController().getMap().isAllowed(getRectangle())){
            setX(getX() - changeX);
            setY(getY() - changeY);
        }
    }

    public void moveAt(float dx, float dy) {
        if(dx == 0 && dy == 0){
            movedx = 0;
            movedy = 0;
            return;
        }
        double len = Math.hypot(dx, dy);
        dx /= len;
        dy /= len;

        movedx = dx;
        movedy = dy;
    }

    public Array<Weapon> getWeapons() {
        return weapons;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void selectWeapon(int n) {
        if (n > weapons.size)
            return;
        currentWeapon = weapons.get(n - 1);
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public int getAmmo(WeaponType type) {
        if (ammo.containsKey(type))
            return ammo.get(type);
        return 0;
    }

    public void addAmmo(WeaponType type, int ammo) {
        this.ammo.put(type, getAmmo(type) + ammo);
    }

    public boolean isDamaged() {
        return damaged;
    }
    public float getMovedx() {
        return movedx;
    }
    public float getMovedy() {
        return movedy;
    }
    public Rectangle getTarget() {
        return target;
    }
    public int getVelocity() {
        return velocity;
    }
    public int getRadius() {
        return radius;
    }

    public void setTarget(Rectangle target) {
        this.target = target;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
}
