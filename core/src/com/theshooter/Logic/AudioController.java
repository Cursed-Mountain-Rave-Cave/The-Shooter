package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class AudioController {
    private Map<String, Array<Sound>> sound;
    private Map<String, Music> music;

    private Stack<Music> musicStack;

    public AudioController() {
        sound = new TreeMap<>();
        music = new TreeMap<>();
        musicStack = new Stack<>();

        addSounds("reloading", 14);
        addSounds("damage", 3);
        addSounds("healing", 10);
        addSounds("upgrade", 1);
        addSounds("boss1", 1);
        addSounds("boss2", 1);

        addMusic("arabian");
        addMusic("american");
        addMusic("casino");
        addMusic("vdamki");
        addMusic("awaken");

    }

    private void addSounds(String type, int size) {
        sound.put(type, new Array<>());
        for (int i = 1; i <= size; i++) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/" + type + "/" + i + ".mp3"));
            this.sound.get(type).add(sound);
        }
    }

    private void addMusic(String name) {
        this.music.put(name, Gdx.audio.newMusic(Gdx.files.internal("music/" + name + ".mp3")));
    }

    public void pauseMusic() {
        if (musicStack.empty())
            return;
        musicStack.peek().pause();
    }

    public void resumeMusic() {
        if (musicStack.empty())
            return;
        musicStack.peek().play();
    }

    public void stopMusic() {
        if (musicStack.empty())
            return;
        musicStack.pop().stop();
        if (musicStack.empty())
            return;
        musicStack.peek().play();
    }

    public void stopAllMusic() {
        while (!musicStack.empty())
            musicStack.pop().stop();
    }

    public void playMusic(String name, float volume) {
        pauseMusic();
        musicStack.push(music.get(name));
        musicStack.peek().play();
        musicStack.peek().setVolume(volume);
        musicStack.peek().setLooping(true);
    }

    public void playSound(String type) {
        int size = sound.get(type).size;
        sound.get(type).get(MathUtils.random(0, size - 1)).play();
    }

    public void dispose() {
        for (String s : sound.keySet())
            for (Sound sound : this.sound.get(s))
                sound.dispose();

        for (String s : music.keySet())
            music.get(s).dispose();
    }
}
