package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.BreakableEntity;
import com.theshooter.Logic.Entity.Vase;

public class BreakableScreenObject extends ScreenObject{

    private BitmapFont font;

    private BreakableEntity entity;
    private Array<Texture> t;

    public BreakableScreenObject(BreakableEntity entity, Array<Texture> t, int shift){
        super(entity, t.get(0), shift);

        this.entity = entity;
        this.t = t;

        this.font = new BitmapFont();
        this.font.setColor(Color.BLACK);
        this.font.getData().setScale(2);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(entity.isBroken())
            setTexture(t.get(1));

        if(Game.config.showAdditionalInfo && !entity.isBroken())
            font.draw(batch, "" + entity.getHP(), entity.getX() - entity.getY() - shift, (entity.getX() + entity.getY()) / 2 + getTexture().getWidth());

        super.draw(batch);
    }
}
