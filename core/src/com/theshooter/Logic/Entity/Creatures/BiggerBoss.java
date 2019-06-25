package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Weapon.Bow;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;

public class BiggerBoss extends CreatureEntity {
    public BiggerBoss(int x, int y, Rectangle target) {
        super(x, y, 100, 100, 100, 400, 5000, Depth.ENEMY, false, target);
        Game.getInstance().getAudioController().playSound("boss2");
    }
}
