package com.theshooter.Logic.Entity;

import com.theshooter.Logic.Entity.Abstract.IEntity;

public class Damage {
    public enum Type{
        PHYSICAL
    }

    private IEntity owner;
    private Type type;
    private int value;

    public Damage(IEntity owner, Type type, int value) {
        this.owner = owner;
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public IEntity getOwner() {
        return owner;
    }

    public int getValue() {
        return value;
    }
}
