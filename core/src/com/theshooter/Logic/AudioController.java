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

        FileHandle levels = Gdx.files.internal("sound");
        FileHandle[] path = levels.list();

        for(FileHandle handle: path){
            addSoundType(handle.name());
            FileHandle soundsPath = Gdx.files.internal("sound/" + handle.name());
            FileHandle[] sounds = soundsPath.list();
            addSounds(handle.name(), "sound/" + handle.name() + "/", sounds.length);
        }

        levels = Gdx.files.internal("music");
        path = levels.list();

        for(FileHandle handle: path)
            addMusic(handle.name().replace(".mp3", ""), "music/" + handle.name());

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
