package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.BreakableEntity;
import com.theshooter.Logic.Entity.Vase;

public class BreakbleScreenObject extends ScreenObject{

    private BreakableEntity entity;
    private Texture t1, t2;

    public BreakbleScreenObject(BreakableEntity entity, Texture t1, Texture t2){
        super(entity, t1, 50);

        this.entity = entity;
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(entity.isBroken())
            setTexture(t2);

        super.draw(batch);
    }
}
