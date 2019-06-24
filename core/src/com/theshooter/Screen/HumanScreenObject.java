package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Creatures.HumanEntity;
import com.theshooter.Logic.Entity.Weapon.WeaponType;

import java.util.Map;

public class HumanScreenObject extends ScreenObject {

    private HumanEntity human;

    private BitmapFont font;

    private Map<WeaponType, Array<Animation>> body;
    private Array<Animation> legs;

    private int currentBody;
    private int currentLegs;
    private WeaponType lastWeapon;

    public HumanScreenObject(HumanEntity human, Map<WeaponType, Array<Animation>> body, Array<Animation> legs) {
        super(human, legs.get(0).getFrame(), 50);

        this.human = human;
        this.body = body;
        this.legs = legs;

        this.font = new BitmapFont();
        this.font.setColor(Color.BLACK);
        this.font.getData().setScale(2);

        lastWeapon = human.getCurrentWeapon().getWeaponType();
    }

    public void draw(SpriteBatch batch) {
        if (human.isBroken()) {
            this.currentBody = 8;
            this.currentLegs = 8;
        }

        setCurrentLegs();
        setCurrentBody();

        batch.draw(legs.get(currentLegs).getFrame(), getScreenX() - shift, getScreenY());
        batch.draw(body.get(human.getCurrentWeapon().getWeaponType()).get(currentBody).getFrame(), getScreenX() - shift, getScreenY());

        if(Game.getInstance().getConfig().showAdditionalInfo && !human.isBroken())
            font.draw(batch, "" + human.getHP(), entity.getX() - entity.getY() - shift, (entity.getX() + entity.getY()) / 2 + getTexture().getWidth());
    }

    public void setCurrentLegs() {
        if (human.isBroken()) {
            legs.get(currentLegs).update();
            return;
        }

        float dx = human.getMovedx();
        float dy = human.getMovedy();

        int last = currentLegs;

        if (dx == 0 && dy > 0)
            this.currentLegs = 3;
        if (dx == 0 && dy < 0)
            this.currentLegs = 7;
        if (dx > 0 && dy == 0)
            this.currentLegs = 5;
        if (dx < 0 && dy == 0)
            this.currentLegs = 1;
        if (dx > 0 && dy > 0)
            this.currentLegs = 4;
        if (dx < 0 && dy > 0)
            this.currentLegs = 2;
        if (dx > 0 && dy < 0)
            this.currentLegs = 6;
        if (dx < 0 && dy < 0)
            this.currentLegs = 0;

        if ((dx == 0 && dy == 0) || last != currentLegs)
            for (Animation current : legs)
                current.reset();

        else /*if (last == currentLegs)*/
            legs.get(last).update();
    }

    public void setCurrentBody() {
        if (human.isBroken()) {
            body.get(lastWeapon).get(currentBody).update();
            return;
        }

        float dx = human.getLookdx();
        float dy = human.getLookdy();

        double angle = Math.atan2(dy, dx) * 180 / 3.14;

        int last = currentBody;

        if (angle > 112.5 && angle < 157.5)
            this.currentBody = 1;
        else if (angle > 67.5 && angle < 112.5)
            this.currentBody = 0;
        else if (angle > 22.5 && angle < 67.5)
            this.currentBody = 7;
        else if (angle > -22.5 && angle < 22.5)
            this.currentBody = 6;
        else if (angle < -22.5 && angle > -67.5)
            this.currentBody = 5;
        else if (angle < -67.5 && angle > -112.5)
            this.currentBody = 4;
        else if (angle < -112.5 && angle > -157.5)
            this.currentBody = 3;
        else
            this.currentBody = 2;

        if (last != currentBody || !lastWeapon.equals(human.getCurrentWeapon().getWeaponType())) {
            for (int i = 0; i < body.size(); i++)
                for (int j = 0; j < body.get(WeaponType.fromInt(i)).size; j++)
                    body.get(WeaponType.fromInt(i)).get(j).reset();
        }

        if (last == currentBody && lastWeapon.equals(human.getCurrentWeapon().getWeaponType())) {
            long end = human.getCurrentWeapon().getShotTime();
            if (end != 0) {
                float current = Game.getInstance().getGameTime() - human.getCurrentWeapon().getLastShot();
                float percent = current * 100 / end;
                if (percent < 40)
                    body.get(lastWeapon).get(currentBody).setFrame(0);
                else if (percent >= 50 && percent < 80)
                    body.get(lastWeapon).get(currentBody).setFrame(1);
                else if (percent >= 80)
                    body.get(lastWeapon).get(currentBody).setFrame(2);
                else
                    body.get(lastWeapon).get(currentBody).reset();

            }
        }
        lastWeapon = human.getCurrentWeapon().getWeaponType();
    }

    public void setLegsAnimationFrameTime(int frameTime) {
        for (Animation animation : legs)
            animation.setFrameTime(frameTime);
    }

    public void setBodyAnimationFrameTime(int frameTime) {
        for (WeaponType type : body.keySet())
            for (Animation animation : body.get(type))
                animation.setFrameTime(frameTime);
    }
}
