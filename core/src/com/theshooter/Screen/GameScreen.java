package com.theshooter.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Game;
import com.theshooter.Logic.CameraController;
import com.theshooter.Logic.Entity.*;

public class GameScreen implements Screen {

    final private Game game;

    public SpriteBatch batch;

    private ScreenObjectArray screenObjects;

    private CameraController cameraController;

    public HumanScreenObject playerScreen;

    public void placeFloor(int x, int y, int type){
        screenObjects.add(new ScreenObject(new Entity(x*50, y*50, 50, 50, Depth.FLOOR),
                game.t.getTexture("floor", "floor" + type), 50));
    }

    public void placeWall(int x, int y){
        Wall entity = new Wall(x*50, y*50, 50, 50);
        game.map.addEntity(entity);
        screenObjects.add(new WallScreenObject(entity, game.t.getTextures("walls", "wall2")));
    }

    public void placeVase(int x, int y){
        Vase entity = new Vase(x, y);
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("things", "breakableThing1"), 50));
    }

    public void placeTend(int x, int y){
        Tent entity = new Tent(x, y);
        game.map.addBreakableEntity(entity);
        int rand = MathUtils.random(2, 3);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("things", "breakableThing" + rand), 150));
    }

    public GameScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();


        cameraController = new CameraController();

        playerScreen = new HumanScreenObject(game.player, game.t.getTextures("player", "body1"),
                                                          game.t.getTextures("player", "legs1"));

        screenObjects = new ScreenObjectArray();

        screenObjects.add(playerScreen);

        for (int i = -100; i < 100; i++)
            for (int j = -100; j < 100; j++)
                placeFloor(i, j, 3);

        for (int i = 15; i > 10; i -= 1)
            for (int j = 10; j > -10; j -= 1)
                placeWall(i, j);

        for (int i = 0; i < 10000; i ++)
            placeVase(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000));


        for (int i = 0; i < 100; i ++)
            placeTend(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000));



        for (int i = 0; i < 50; i ++){
            Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000),75,75, 50,100, game.player.getRectangle(), game.getMap());
            game.map.addBreakableEntity(entity);
            screenObjects.add(new BreakableScreenObject(entity,
                              game.t.getTextures("enemy", "enemy2"), 112));
        }

       for (int i = 0; i < 50; i ++){
            Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 75,75, 10,100, game.player.getRectangle(), game.getMap());
            game.map.addBreakableEntity(entity);
            screenObjects.add(new BreakableScreenObject(entity,
                              game.t.getTextures("enemy", "enemy3"), 150));
        }

        for (int i = 0; i < 50; i ++){
            Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000),75,75,10,200, game.player.getRectangle(), game.getMap());
            game.map.addBreakableEntity(entity);;
            screenObjects.add(new BreakableScreenObject(entity,
                              game.t.getTextures("enemy", "enemy4"), 75));
        }

        for (int i = 0; i < 50; i ++){
            Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000),75, 75, 100, 100,game.player.getRectangle(), game.getMap());
            game.map.addBreakableEntity(entity);
            screenObjects.add(new BreakableScreenObject(entity,
                              game.t.getTextures("enemy", "enemy1"), 84));
        }

        for (int i = 0; i < 50; i ++){
            HumanEnemy entity = new HumanEnemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 15, game.player.getRectangle(), game.getMap());
            game.map.addBreakableEntity(entity);
            screenObjects.add(new HumanScreenObject(entity,
                    game.t.getTextures("player", "body2"), game.t.getTextures("player", "legs2")));
        }
    }

    public void addBullet(Bullet bullet){
        screenObjects.add(new ScreenObject(bullet, game.t.getTexture("bullets", "bullet1"), 0));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cameraController.lookAt(playerScreen.getScreenX(), playerScreen.getScreenY());

        cameraController.update();
        batch.setProjectionMatrix(cameraController.getCamera().combined);

        Gdx.gl.glClearColor(0.3f, 0.3f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        screenObjects.draw(batch);

        batch.end();
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
        screenObjects.clear();
        batch.dispose();
    }

    public CameraController getCameraController() {
        return cameraController;
    }
}
