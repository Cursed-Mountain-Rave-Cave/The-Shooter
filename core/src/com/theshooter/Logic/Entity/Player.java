package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class Player extends HumanEntity{

    public Player(int x, int y, int h, int w, Map map) {
        super(x, y, 1000, Depth.PLAYER, map);
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
