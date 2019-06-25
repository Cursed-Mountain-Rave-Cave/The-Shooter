package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;

public class Disign extends CreatureEntity {
    public Disign(int x, int y, Rectangle target) {
        super(x, y, 75, 75, 75, 300, 500, Depth.ENEMY, false, target);
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
