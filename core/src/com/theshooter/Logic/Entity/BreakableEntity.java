package com.theshooter.Logic.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Abstract.IBreakable;
import com.theshooter.Screen.Depth;
import com.badlogic.gdx.audio.Sound;


public class BreakableEntity extends Entity implements IBreakable {

    protected int hp;
    protected boolean broken;
    public static Sound Spank = Gdx.audio.newSound(Gdx.files.internal("sound/Spank.mp3"));
    public static Sound Spank1 = Gdx.audio.newSound(Gdx.files.internal("sound/Spank1.mp3"));
    public static Sound Spank2 = Gdx.audio.newSound(Gdx.files.internal("sound/Spank2.mp3"));

    public BreakableEntity(int x, int y, int w, int h, int hp, Depth depth, boolean passable) {
        super(x, y, w, h, depth, passable);
        this.hp = hp;
        this.broken = false;
    }

    public BreakableEntity(int x, int y, int w, int h, Depth depth, boolean passable) {
        this(x, y, w, h, 1, depth, passable);
    }


    public BreakableEntity(int x, int y, int w, int h, Depth depth) {
        this(x, y, w, h, depth, true);
    }

    public boolean isBroken() {
        return broken;
    }

    @Override
    public int getHP() {
        return hp;
    }

    public void breakDown(Damage damage) {
        if (hp > 0) {
            hp -= damage.getValue();
            int randomID = MathUtils.random(1, 3);
            if (randomID == 1)
                Spank.play(0.2f);
            if (randomID == 2)
                Spank1.play(0.2f);
            if (randomID == 3)
                Spank2.play(0.2f);
        }
        if (hp <= 0)
            broken = true;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
