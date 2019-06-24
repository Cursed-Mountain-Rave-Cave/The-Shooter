package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.Gdx;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Weapon.*;
import com.theshooter.Screen.Depth;

public class Player extends HumanEntity {

    public Player(int x, int y, int w, int h) {
        super(x, y, w, h, 1000, 400, 0,  Depth.PLAYER, false, null);
        addWeapon(new Stone(0,this));
        addWeapon(new Bow(0, this));
        addWeapon(new ThrowingKnife(0,this));
        addWeapon(new Dagger(0,this));
        addWeapon(new UltimateOneShotSuperMegaAnnihilationBow(100,this));

        addAmmo(WeaponType.BOW, 10000);
        addAmmo(WeaponType.THROWING_KNIFE, 20000);
        selectWeapon(1);
    }

    public void update(){
        setVelocityMultiplier(Game.getInstance().getConfig().playerVelocityMultiplier);
        for (Weapon weapon : getWeapons())
            weapon.update();
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
