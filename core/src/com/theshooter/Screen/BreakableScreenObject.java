package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.BreakableEntity;
import com.theshooter.Logic.Entity.Vase;

public class BreakableScreenObject extends ScreenObject{

    private BreakableEntity entity;
    private Array<Texture> t;

    public BreakableScreenObject(BreakableEntity entity, Array<Texture> t){
        super(entity, t.get(0), 50);

        this.entity = entity;
        this.t = t;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(entity.isBroken())
            setTexture(t.get(1));

        super.draw(batch);
    }
}
