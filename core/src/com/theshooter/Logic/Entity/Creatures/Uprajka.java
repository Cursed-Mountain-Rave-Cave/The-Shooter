package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Weapon.*;
import com.theshooter.Screen.Depth;

public class Uprajka extends CreatureEntity{
    public Uprajka(int x, int y, Rectangle target) {
        super(x, y, 100, 100, 100, 400, 500, Depth.ENEMY, false, target);
    }

    @Override
    public void breakDown(Damage damage) {
        if (getHP() > 0)
            setHp(getHP() - damage.getValue());
        if (getHP() <= 0)
            setBroken(true);
        setDamaged(true);
        Game.getInstance().getAudioController().playSound("boss1");
    }
}
