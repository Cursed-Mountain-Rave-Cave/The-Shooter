package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.Gdx;
import com.theshooter.Logic.Damage;
import com.theshooter.Screen.Depth;

public class Player extends HumanEntity {

    public Player(int x, int y, int w, int h) {
        super(x, y, w, h, 1000, 400, 0,  Depth.PLAYER, false, null);
    }

    public void update(){

    }

    @Override
    public void breakDown(Damage damage) {
        super.breakDown(damage);
        if(isBroken()){
            delete();
            Gdx.app.exit();
        }
    }
}
