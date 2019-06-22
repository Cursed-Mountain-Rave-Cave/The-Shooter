package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

        addSoundType("reloading");
        addSoundType("damage");

        addSounds("reloading", "sound/reloading/", 14);
        addSounds("damage", "sound/damage/", 3);

        addMusic("arabian", "music/arabian.mp3");
        addMusic("american", "music/american.mp3");

    }

    private void addSoundType(String type) {
        sound.put(type, new Array<>());
    }

    private void addSounds(String type, String path, int size) {
        for (int i = 1; i <= size; i++) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(path + i + ".mp3"));
            this.sound.get(type).add(sound);
        }
    }

    private void addMusic(String name, String file) {
        this.music.put(name, Gdx.audio.newMusic(Gdx.files.internal(file)));
    }

    public void pauseMusic() {
        if (musicStack.isEmpty())
            return;
        musicStack.peek().pause();
    }

    public void stopMusic() {
        if (musicStack.isEmpty())
            return;
        musicStack.peek().stop();
        musicStack.pop();
        if (musicStack.isEmpty())
            return;
        musicStack.peek().play();
    }

    public void stopAllMusic() {
        if (musicStack.isEmpty())
            return;
        musicStack.peek().stop();
        musicStack.pop();
    }

    public void playMusic(String name, float volume) {
        if (!musicStack.empty())
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
}
