package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Logic.Entity.Vase;

public class VaseScreenObject extends ScreenObject{

    private Vase vase;
    private Texture t1, t2;

    public VaseScreenObject(Vase vase, Texture t1, Texture t2){
        super(vase, t1, 50);

        this.vase = vase;
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(vase.isBroken())
            setTexture(t2);

        super.draw(batch);
    }
}
