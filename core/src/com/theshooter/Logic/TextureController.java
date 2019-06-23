package com.theshooter.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.Map;
import java.util.TreeMap;

public class TextureController {
    private Map<String, Map<String, Array<Texture>>> textures;

    public TextureController() {
        textures = new TreeMap<>();

        addType("player");
        addType("floor");
        addType("things");
        addType("enemy");
        addType("walls");
        addType("projectiles");

        addTextureArray("floor", "floor", "floor/", 17, 1);

        addTextureArray("walls", "wall", "walls/", 3, 5);

        addTextureArray("things", "unbreakableThing", "environment/unbreakable/", 8, 1);
        addTextureArray("things", "breakableThing", "environment/breakable/", 5, 2);

        addTextureArray("player", "body", "player/bodies/", 7, 9);
        addTextureArray("player", "legs", "player/legs/", 7, 9);

        addTextureArray("enemy", "enemy", "enemies/", 5, 2);

        addTextureArray("projectiles", "projectile", "projectiles/", 5, 1);
    }


    private void addType(String type) {
        Map<String, Array<Texture>> map = new TreeMap<>();
        textures.put(type, map);
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

    public Array<Texture> getTextures(String type, String name) {
        return textures.get(type).get(name);
    }

    public Texture getTexture(String type, String name, int n) {
        return textures.get(type).get(name).get(n);
    }

    public Texture getTexture(String type, String name) {
        return this.getTexture(type, name, 0);
    }

    public void dispose() {
        for (String s : textures.keySet())
            for (String s1 : textures.get(s).keySet())
                for (Texture t : textures.get(s).get(s1))
                    t.dispose();
    }
}
