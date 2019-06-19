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
                screenObjects.add(new ScreenObject(new Entity(i*50, j*50, 50, 50, Depth.FLOOR),
                                  game.t.getTexture("floor", "floor3"), 50));

        for (int i = 20; i > 10; i--)
            for (int j = 10; j > -10; j--)
                screenObjects.add(new ScreenObject(new Entity(i*50, j*50, 50, 50, Depth.THINGS),
                            game.t.getTexture("things", "unbreakableThing1"), 50));

            // change to right texture in the future
        for (int i = 15; i > 10; i -= 1)
            for (int j = 10; j > -10; j -= 1){
                Entity entity = new Entity(i*50, j*50, 50, 50, Depth.WALLS, false);
                game.map.addEntity(entity);
                screenObjects.add(new ScreenObject(entity, game.t.getTexture("floor", "floor7"), 50));
            }

        for (int i = -100; i < 0; i ++)
            for (int j = 50; j > -50; j -= 1){
                Vase entity = new Vase(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000));
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakableScreenObject(entity,
                                  game.t.getTextures("things", "breakableThing1")));
            }

        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 100, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakableScreenObject(entity,
                                  game.t.getTextures("enemy", "enemy2")));
            }
       for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 100, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakableScreenObject(entity,
                                  game.t.getTextures("enemy", "enemy3")));
            }
        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 100, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);;
                screenObjects.add(new BreakableScreenObject(entity,
                                  game.t.getTextures("enemy", "enemy4")));
            }
        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 200, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakableScreenObject(entity,
                                  game.t.getTextures("enemy", "enemy1")));
            }
        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                HumanEnemy entity = new HumanEnemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), game.player.getRectangle(), game.getMap());
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
