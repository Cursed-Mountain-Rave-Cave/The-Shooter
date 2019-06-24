package com.theshooter.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Game;
import com.theshooter.Screen.ScreenObjects.ScreenObject;

public class MapScreen implements Screen {
    public enum object { floor, wall, tree, water, house }
    private ScreenObjectArray screenObjectArray;
    private boolean[][] visited;
    private object[][] map;
    private ShapeRenderer renderer;

    private int radius;

    OrthographicCamera camera;

    public void load() {
        screenObjectArray = Game.getInstance().getEntityController().getScreenObjectArray();
        renderer = new ShapeRenderer();

        int maxHeight = 0, maxWidth = 0;
        for(int i = 0; i < screenObjectArray.floor.size; ++i) {
            maxHeight = Math.max(maxHeight, screenObjectArray.floor.get(i).getX() / 50);
            maxWidth = Math.max(maxWidth, screenObjectArray.floor.get(i).getY() / 50);
        }

        this.map = new object[maxHeight + 1][maxWidth + 1];
        visited = new boolean[maxHeight + 1][maxWidth + 1];

        System.out.println(maxHeight + " " + maxWidth);

        getMap();

        camera = new OrthographicCamera(1920, 1080);
    }

    private int lastPlayerX = 0;
    private int lastPlayerY = 0;
    public void center() {
        int playerX = Game.getInstance().getEntityController().getPlayer().getX() / 5;
        int playerY = Game.getInstance().getEntityController().getPlayer().getY() / 5;

        camera.translate(playerX - lastPlayerX, playerY - lastPlayerY);

        lastPlayerX = playerX;
        lastPlayerY = playerY;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int playerX = Game.getInstance().getEntityController().getPlayer().getX() / 5;
        int playerY = Game.getInstance().getEntityController().getPlayer().getY() / 5;

        camera.update();
        renderer.setProjectionMatrix(camera.combined);

        int height = map.length;
        int width = map[0].length;

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for(int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (map[i][j] == object.wall)
                    renderer.setColor(Color.BLACK);
                else if(visited[i][j])
                    switch(map[i][j]) {
                        case water:
                            renderer.setColor(Color.BLUE);
                            break;
                        case tree:
                            renderer.setColor(Color.GREEN);
                            break;
                        case floor:
                            renderer.setColor(new Color(Color.argb8888(1.0f, 254.0f, 180.0f, 127.0f)));
                            break;
                        case house:
                            renderer.setColor(Color.BROWN);
                            break;
                    }
                else
                    renderer.setColor(new Color(Color.GRAY));
                renderer.rect(i * 10, j * 10, 10, 10);
            }
        }

        renderer.setColor(Color.FOREST);
        renderer.rect(playerX, playerY, 10, 10);
        renderer.end();
        view();
    }

    public void view() {
        int playerX = Game.getInstance().getEntityController().getPlayer().getX() / 5;
        int playerY = Game.getInstance().getEntityController().getPlayer().getY() / 5;

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        radius = (int) (Game.getInstance().gameScreen.getCameraController().getCamera().zoom * 100);

        for(int i = -radius; i < radius; ++i)
            for(int j = -radius; j < radius; ++j)
                if(i*i + j*j < radius * radius &&
                        (playerX + i) / 10 >= 0 &&
                        (playerX + i) / 10 < map.length &&
                        (playerY + j) / 10 >= 0 &&
                        (playerY + j) / 10 < map[0].length) {
                    visited[(playerX + i) / 10][(playerY + j) / 10] = true;
                    renderer.rect(playerX, playerY, 1, 1);
                }
        renderer.end();
    }

    private void getMap() {
        Array<ScreenObject> floor = screenObjectArray.floor;
        for(int i = 0; i < floor.size; ++i) {
            ScreenObject currentTile = floor.get(i);
            int tileX = currentTile.getX();
            int tileY = currentTile.getY();

            Array<Texture> textures = Game.getInstance().getTextureController().getTextures("floor", "floor17");

            if(textures.get(0).equals(currentTile.getTexture()))
                map[tileX / 50][tileY / 50] = object.water;
            else
                map[tileX / 50][tileY / 50] = object.floor;
        }

        for(int i = 0; i < screenObjectArray.size; ++i) {
            ScreenObject currentObject = screenObjectArray.get(i);
            int objectX = currentObject.getX();
            int objectY = currentObject.getY();

            Array<Texture> textures;

            for(int j = 1; j <= 1; ++j) {
                textures = Game.getInstance().getTextureController().getTextures("wall", "wall" + j);
                for (int k = 0; k < textures.size; ++k)
                    if (textures.get(k).equals(currentObject.getTexture()))
                        map[objectX / 50][objectY / 50] = object.wall;
            }

            textures = Game.getInstance().getTextureController().getTextures("things", "unbreakableThing3");
            if(textures.get(0).equals(currentObject.getTexture()))
                map[objectX / 50][objectY / 50] = object.tree;

            textures = Game.getInstance().getTextureController().getTextures("things", "unbreakableThing6");
            if(textures.get(0).equals(currentObject.getTexture()))
                map[objectX / 50][objectY / 50] = object.tree;

            textures = Game.getInstance().getTextureController().getTextures("things", "unbreakableThing7");
            if(textures.get(0).equals(currentObject.getTexture()))
                map[objectX / 50][objectY / 50] = object.tree;

            textures = Game.getInstance().getTextureController().getTextures("things", "unbreakableThing5");
            if(textures.get(0).equals(currentObject.getTexture()))
                paintArea(objectX, objectY, objectX + currentObject.getWidth(),
                        objectY + currentObject.getHeight(), object.house);

            textures = Game.getInstance().getTextureController().getTextures("things", "unbreakableThing8");
            if(textures.get(0).equals(currentObject.getTexture()))
                paintArea(objectX, objectY, objectX + currentObject.getWidth(),
                        objectY + currentObject.getHeight(), object.house);
        }
    }

    public void paintArea(int x1, int y1, int x2, int y2, object type) {
        for(int k = x1; k < x2; k += 50)
            for(int j = y1; j < y2; j += 50)
                map[k / 50][j / 50] = type;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void zoom(int amount) {
        camera.zoom = Math.max(0.5f, Math.min(camera.zoom + amount* 0.2f, 1.5f));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
