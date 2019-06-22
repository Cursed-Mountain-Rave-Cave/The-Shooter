package com.theshooter.Logic.Entity;

import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Logic.Damage;

abstract public class Weapon {
    private int damage;
    private Damage.Type type;
    private int velocity;
    private boolean reloadable;
    private int clipSize;
    private long reloadingTime;
    private long shotTime;
    private long shotLifeTime;
    private IEntity owner;

    protected long lastShot;
    private int curClipSize;
    private long reloadingEnd;
    private boolean reload;

    public Weapon(int damage, Damage.Type type, int velocity, boolean reloadable, int clipSize, long reloadingTime, long shotTime, long shotLifeTime, IEntity owner) {
        this.damage = damage;
        this.type = type;
        this.velocity = velocity;
        this.clipSize = clipSize;
        this.curClipSize = clipSize;
        this.reloadingTime = reloadingTime;
        this.shotTime = shotTime;
        this.shotLifeTime = shotLifeTime;
        this.owner = owner;
        this.reloadable = reloadable;

        lastShot = 0;
        reload = false;
    }

    public void update() {
        if (reload && TimeUtils.millis() > reloadingEnd) {
            curClipSize = clipSize;
            reload = false;
        }
        if (curClipSize == 0 && reloadable) {
            reload();
        }
    }

    public void reload() {
        if (!reload && curClipSize < clipSize) {
            reloadingEnd = TimeUtils.millis() + reloadingTime;
            reload = true;
        }
    }

    abstract public void attack();

    public boolean canAttack() {
        return TimeUtils.millis() > lastShot + getShotTime() && getCurClipSize() > 0 && !isReload();
    }

    public int getDamage() {
        return damage;
    }

    public Damage.Type getType() {
        return type;
    }

    public int getVelocity() {
        return velocity;
    }

    public boolean isReloadable() {
        return reloadable;
    }

    public int getClipSize() {
        return clipSize;
    }

    public int getCurClipSize() {
        return curClipSize;
    }

    public long getReloadingTime() {
        return reloadingTime;
    }

    public long getShotTime() {
        return shotTime;
    }

    public long getShotLifeTime() {
        return shotLifeTime;
    }

    public IEntity getOwner() {
        return owner;
    }

    public long getReloadingEnd() {
        return reloadingEnd;
    }

    public boolean isReload() {
        return reload;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setType(Damage.Type type) {
        this.type = type;
    }

    public void setReloadable(boolean reloadable) {
        this.reloadable = reloadable;
    }

    public void setClipSize(int clipSize) {
        this.clipSize = clipSize;
    }

    public void setCurClipSize(int curClipSize) {
        this.curClipSize = curClipSize;
    }

    public void setReloadingTime(long reloadingTime) {
        this.reloadingTime = reloadingTime;
    }

    public void setShotTime(long shotTime) {
        this.shotTime = shotTime;
    }

    public void setShotLifeTime(long shotLifeTime) {
        this.shotLifeTime = shotLifeTime;
    }

    public void setOwner(IEntity owner) {
        this.owner = owner;
    }

    public void setReloadingEnd(long reloadingEnd) {
        this.reloadingEnd = reloadingEnd;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }
}
