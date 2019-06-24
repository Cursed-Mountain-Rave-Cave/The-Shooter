package com.theshooter.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Abstract.IEntity;
import com.theshooter.Logic.Entity.Creatures.Player;
import com.theshooter.Logic.Entity.Wall;
import com.theshooter.Logic.Map;

public class MapScreen implements Screen {
    public enum object { floor, wall }
    private boolean[][] visited;
    private object[][] map;
    private Array<IEntity> entities;
    private ShapeRenderer renderer;

    private int radius;

    OrthographicCamera camera;

    public MapScreen(final Map map) {
        entities = map.getEntities();
        renderer = new ShapeRenderer();

        int maxHeight = 0, maxWidth = 0;
        for(int i = 0; i < entities.size; ++i) {
            maxHeight = Math.max(maxHeight, entities.get(i).getX() / 50);
            maxWidth = Math.max(maxWidth, entities.get(i).getY() / 50);
        }

        this.map = new object[maxHeight + 1][maxWidth + 1];
        visited = new boolean[maxHeight + 1][maxWidth + 1];

        getMap();

        camera = new OrthographicCamera(1920, 1080);
        camera.translate(this.map.length * 5, this.map[0].length * 5);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
                    renderer.setColor(new Color(Color.argb8888(1.0f, 254.0f, 180.0f, 127.0f)));
                else
                    renderer.setColor(new Color(Color.GRAY));
                renderer.rect(i * 10, j * 10, 10, 10);
            }
        }

        int playerX = Game.getInstance().getEntityController().getPlayer().getX() / 5;
        int playerY = Game.getInstance().getEntityController().getPlayer().getY() / 5;

        renderer.end();
        view();
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Color.FOREST);
        renderer.rect(playerX, playerY, 10, 10);
        renderer.end();
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
        for(int i = 0; i < entities.size; ++i)
            if(entities.get(i) instanceof  Wall) {
                int x1 = entities.get(i).getX() , y1 = entities.get(i).getY();
                int x2 = x1 + entities.get(i).getWidth(), y2 = y1 + entities.get(i).getHeight();
                for(int k = x1; k < x2; k += 50)
                    for(int j = y1; j < y2; j += 50)
                        map[k / 50][j / 50] = object.wall;
            }
            else
                map[entities.get(i).getX() / 50][entities.get(i).getY() / 50] = object.floor;
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
