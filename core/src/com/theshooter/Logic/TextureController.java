package com.theshooter.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Screen.Animation;

import java.util.Map;
import java.util.TreeMap;

public class TextureController {
    private Map<String, Map<String, Array<Texture>>> textures;
    private Map<String, Map<String, Array<Animation>>> animations;

    private final int WEAPONS = 4;

    public TextureController() {
        textures = new TreeMap<>();
        animations = new TreeMap<>();

        addTextureType("player");
        addTextureType("floor");
        addTextureType("things");
        addTextureType("enemy");
        //addTextureType("walls");
        addTextureType("projectiles");
        addTextureType("wall");

        addAnimationType("player");

        addTextureArray("floor", "floor", "floor/", 18, 1);

        //addTextureArray("walls", "wall", "walls/", 3, 5);
        addTextureArray("wall", "wall", "wall/", 1, 1);

        addTextureArray("things", "unbreakableThing", "environment/unbreakable/", 15, 1);
        addTextureArray("things", "breakableThing", "environment/breakable/", 6, 2);

        addBodyAnimationArray("player", "body", "player/bodies/", 5, 9, 3);
        addAnimationArray("player", "legs", "player/legs/", 12, 9, 4);

        addTextureArray("enemy", "enemy", "enemies/", 5, 2);

        addTextureArray("projectiles", "projectile", "projectiles/", 5, 1);
    }

    private void addTextureType(String type) {
        Map<String, Array<Texture>> map = new TreeMap<>();
        textures.put(type, map);
    }

    private void addAnimationType(String type) {
        Map<String, Array<Animation>> map = new TreeMap<>();
        animations.put(type, map);
    }

    private void addTextureArray(String type, String name, String path, int size, int frames) {
        if (frames > 1) {
            for (int i = 1; i <= size; i++) {
                Array<Texture> textures = new Array<>();
                for (int j = 1; j <= frames; j++) {
                    textures.add(new Texture(path + i + "/" + j + ".png"));
                    this.textures.get(type).put(name + i, textures);
                }
            }
        } else {
            for (int i = 1; i <= size; i++) {
                Array<Texture> textures = new Array<>();
                textures.add(new Texture(path + i + ".png"));
                this.textures.get(type).put(name + i, textures);
            }
        }
    }

    private void addAnimationArray(String type, String name, String path, int size, int states, int frames) {
        for (int i = 1; i <= size; i++) {
            Array<Animation> animations = new Array<>();
            for (int j = 1; j <= states; j++) {
                Array<Texture> textures = new Array<>();
                for (int k = 1; k <= frames; k++)
                    textures.add(new Texture(path + i + "/" + j + "/" + k + ".png"));
                Animation animation = new Animation(textures, 300);
                animations.add(animation);

            }
            this.animations.get(type).put(name + i, animations);
        }
    }

    private void addBodyAnimationArray(String type, String name, String path, int size, int states, int frames) {
        for (int i = 1; i <= size; i++)
            for (int j = 0; j < WEAPONS; j++) {
                Array<Animation> animations = new Array<>();
                for (int k = 1; k <= states; k++) {
                    Array<Texture> textures = new Array<>();
                    for (int l = 1; l <= frames; l++)
                        textures.add(new Texture(path + i + "/" + WeaponType.fromInt(j).toString() + "/" + k + "/" + l + ".png"));
                    Animation animation = new Animation(textures, 300);
                    animations.add(animation);
                    this.animations.get(type).put(name + i + "/" + WeaponType.fromInt(j).toString(), animations);
                }
        }
    }

    public Map<WeaponType, Array<Animation>> getBody(String type, String name) {
        Map<WeaponType, Array<Animation>> animations = new TreeMap<>();
        for (int i = 0; i < WEAPONS; i++)
            animations.put(WeaponType.fromInt(i), getAnimations(type, name + "/" + WeaponType.fromInt(i).toString()));
        return animations;
    }

    public Array<Texture> getTextures(String type, String name) { return textures.get(type).get(name); }

    public Array<Animation> getAnimations(String type, String name) {
        Array<Animation> animations = new Array<>();
        for (Animation animation : this.animations.get(type).get(name))
            animations.add(new Animation(animation));
        return animations;
    }

    public Texture getTexture(String type, String name, int n) { return textures.get(type).get(name).get(n); }

    public Texture getTexture(String type, String name) { return this.getTexture(type, name, 0); }

    public void dispose() {
        for (String s : textures.keySet())
            for (String s1 : textures.get(s).keySet())
                for (Texture t : textures.get(s).get(s1))
                    t.dispose();

        for (String s : animations.keySet())
            for (String s1 : animations.get(s).keySet())
                for (Animation a : animations.get(s).get(s1))
                    a.dispose();
    }
}
