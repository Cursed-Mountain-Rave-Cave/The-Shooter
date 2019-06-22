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
        addType("bullets");


        /**
         * floor adding
         */

        for (int i = 1; i <= 14; i++) {
            Array<Texture> textures = new Array<>();
            textures.add(new Texture("floor/" + i + ".png"));
            addTextureArray("floor", "floor" + i, textures);
        }

        /**
         * walls adding
         */

        for (int i = 1; i <= 3; i++) {
            Array<Texture> textures = new Array<>();
            for (int j = 1; j <= 5; j++)
                textures.add(new Texture("walls/wall" + i + "/" + j + ".png"));
            addTextureArray("walls", "wall" + i, textures);
        }

        /**
         * unbreakable things adding
         */

        for (int i = 1; i <= 6; i++) {
            Array<Texture> textures = new Array<>();
            textures.add(new Texture("environment/unbreakable/" + i + ".png"));
            addTextureArray("things", "unbreakableThing" + i, textures);
        }

        /**
         * breakable things adding
         */

        for (int i = 1; i <= 5; i++) {
            Array<Texture> textures = new Array<>();
            for (int j = 1; j <= 2; j++)
                textures.add(new Texture("environment/breakable/breakable" + i + "/" + j + ".png"));
            addTextureArray("things", "breakableThing" + i, textures);
        }

        /**
         * player adding
         */

        for (int i = 1; i <= 7; i++) {
            Array<Texture> texturesBody = new Array<>();
            Array<Texture> texturesLegs = new Array<>();
            for (int j = 1; j <= 9; j++) {
                texturesBody.add(new Texture("player/bodies/body" + i + "/" + j + ".png"));
                texturesLegs.add(new Texture("player/legs/legs" + i + "/" + j + ".png"));
            }
            addTextureArray("player", "legs" + i, texturesLegs);
            addTextureArray("player", "body" + i, texturesBody);
        }


        /**
         * enemies adding
         */

        for (int i = 1; i <= 5; i++) {
            Array<Texture> textures = new Array<>();
            for (int j = 1; j <= 2; j++)
                textures.add(new Texture("enemies/enemy" + i + "/" + j + ".png"));
            addTextureArray("enemy", "enemy" + i, textures);
        }

        /**
         * bullets adding
         */

        for (int i = 1; i <= 5; i++) {
            Array<Texture> textures = new Array<>();
            textures.add(new Texture("bullets/" + i + ".png"));
            addTextureArray("bullets", "bullet" + i, textures);
        }
    }

    private void addType(String type) {
        Map<String, Array<Texture>> map = new TreeMap<>();
        textures.put(type, map);
    }

    private void addTextureArray(String type, String name, Array<Texture> textures) {
        this.textures.get(type).put(name, textures);
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
