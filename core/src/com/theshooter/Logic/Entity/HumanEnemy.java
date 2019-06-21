package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class HumanEnemy extends HumanEntity {

    private Rectangle target;
    private boolean damaged;
    public static Sound Spank = Gdx.audio.newSound(Gdx.files.internal("sound/Spank.mp3"));
    public static Sound Spank1 = Gdx.audio.newSound(Gdx.files.internal("sound/Spank1.mp3"));
    public static Sound Spank2 = Gdx.audio.newSound(Gdx.files.internal("sound/Spank2.mp3"));

    public HumanEnemy(int x, int y, int hp, int velocity, Rectangle target,  Map map) {
        super(x, y, hp, Depth.ENEMY, map);
        this.velocity = velocity;
        this.target = target;
        damaged = false;
    }

    public void update() {
        if (!isBroken()) {
            int dx = (int) (target.getX() - getX());
            int dy = (int) (target.getY() - getY());
            float dist = (float) Math.hypot(dx, dy);

            if(dist < 5 * 50 || damaged)
                moveAt(dx, dy);
            if (dist < 15 * 50)
                lookAt(dx - dy, -(dx + dy)/2);
            else if (!damaged) {
                lookAt(0, 0);
                moveAt(0, 0);
            }

        }else{
            moveAt(0, 0);
        }
        super.update();
    }

    @Override
    public void breakDown() {
        super.breakDown();
        int randomID = MathUtils.random(1,3);
        if(randomID == 1)
            Spank.play(0.2f);
        if(randomID == 2)
            Spank1.play(0.2f);
        if(randomID == 3)
            Spank2.play(0.2f);
        damaged = true;
    }
}
