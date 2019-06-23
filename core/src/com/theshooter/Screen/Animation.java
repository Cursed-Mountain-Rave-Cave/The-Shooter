package com.theshooter.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Game;

public class Animation {
    private Array<Texture> textures;
    private long frameTime;
    private long currentFrameTime;
    private long lastUpdate;
    private int frame;

    public Animation(Array<Texture> textures, long frameTime) {
        this.textures = textures;
        this.frameTime = frameTime;
        currentFrameTime = 0;
        frame = 0;
    }

    public void update() {
        currentFrameTime += Game.getInstance().getGameTime() - lastUpdate;
        lastUpdate = Game.getInstance().getGameTime();
        if (currentFrameTime > frameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame == textures.size)
            frame = 0;
    }

    public Texture getFrame() {
        return textures.get(frame);
    }

    public void reset() {
        frame = 0;
    }

    public void setFrameTime(long frameTime) {
        this.frameTime = frameTime;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public void dispose() {
        for (Texture texture : textures)
            texture.dispose();
    }
}
