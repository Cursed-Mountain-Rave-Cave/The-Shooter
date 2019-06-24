package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.Abstract.ILookable;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Weapon.Boom;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.Weapon;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Depth;
import com.theshooter.Logic.Entity.Creatures.HumanEntity;
import java.util.Map;
import java.util.TreeMap;

public class SuicideEntity extends HumanEntity {
    public SuicideEntity(int x, int y, int w, int h, int hp, int velocity, int radius, Depth depth, boolean passable, Rectangle target){
        super(x, y, w, h, hp, velocity, radius, depth, passable, target);
        this.addWeapon(new Boom(0,this));
        this.selectWeapon(1);
        this.addAmmo(WeaponType.DAGGER, 1);
    }

    public void update() {
        super.update();
        this.getCurrentWeapon().update();
        if(this.getCurrentWeapon().getLastShot() != 0) {
            this.setHp(0);
        }
    }
}
