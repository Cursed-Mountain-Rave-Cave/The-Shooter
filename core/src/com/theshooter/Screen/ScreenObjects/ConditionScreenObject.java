package com.theshooter.Screen.ScreenObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.ConditionEntities.ConditionEntity;

public class ConditionScreenObject extends ScreenObject {
    private ConditionEntity entity;
    private Array<Texture> t;

    public ConditionScreenObject(ConditionEntity entity, Array<Texture> t, int shift){
        super(entity, t.get(0), shift);

        this.entity = entity;
        this.t = t;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(entity.isActive())
            setTexture(t.get(1));
        else
            setTexture(t.get(0));
        super.draw(batch);
    }
}
