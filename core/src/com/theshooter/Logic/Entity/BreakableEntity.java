package com.theshooter.Logic.Entity;
import com.badlogic.gdx.Gdx;
import com.theshooter.Screen.Depth;
import com.badlogic.gdx.audio.Sound;


public class BreakableEntity extends Entity implements IBreakableEntity{

    protected int hp;
    protected boolean broken;
    public Sound Spank = Gdx.audio.newSound(Gdx.files.internal("sound/Spank.mp3"));

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

    public boolean isBroken(){
        return broken;
    }

    @Override
    public int getHP() {
        return hp;
    }

    public void breakDown() {
        if (hp > 0) {
            hp--;
            Spank.play();
        }
        if(hp == 0)
            broken = true;
    }
}
