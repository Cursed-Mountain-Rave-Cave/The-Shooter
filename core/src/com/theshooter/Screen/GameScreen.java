package com.theshooter.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

    private Texture floor;
    private Texture flyingFloor;
    private Texture box;

    private Texture body[];
    private Texture legs[];

    private Texture bullet;

    private Texture vase1;
    private Texture vase2;

    private Texture dog1;
    private Texture dog2;

    private Texture plane1;
    private Texture plane2;

    private Texture thomas1;
    private Texture thomas2;

    private Texture boss1;
    private Texture boss2;

    public HumanScreenObject playerScreen;


    public GameScreen(Game game){
        batch = new SpriteBatch();
        this.game = game;

        cameraController = new CameraController();

        floor = new Texture("floor/floor2.png");
        flyingFloor = new Texture("floor/flyingfloor.png");
        box = new Texture("box.png");
        body = new Texture[8];
        for (int i = 0; i < 8; i++)
            body[i] = new Texture("player/bodies/body" + Integer.valueOf(i + 1).toString() + ".png");
        legs = new Texture[8];
        for (int i = 0; i < 8; i++)
            legs[i] = new Texture("player/legs/legs" + Integer.valueOf(i + 1).toString() + ".png");

        bullet = new Texture("bullet.png");
        vase1 = new Texture("environment/exportVase1.png");
        vase2 = new Texture("environment/exportVase2.png");

        dog1 = new Texture("enemies/dog/alive.png");
        dog2 = new Texture("enemies/dog/dead.png");

        plane1 = new Texture("enemies/plane/alive.png");
        plane2 = new Texture("enemies/plane/dead.png");

        thomas1 = new Texture("enemies/thomas/alive.png");
        thomas2 = new Texture("enemies/thomas/dead.png");

        boss1 = new Texture("enemies/boss/alive.png");
        boss2 = new Texture("enemies/boss/dead.png");

        playerScreen = new HumanScreenObject(game.player, body, legs);

        screenObjects = new ScreenObjectArray();

        screenObjects.add(playerScreen);

        for (int i = -100; i < 100; i++)
            for (int j = -100; j < 100; j++)
                screenObjects.add(new ScreenObject(new Entity(i*50, j*50, 50, 50, Depth.FLOOR), floor, 50));

        for (int i = 20; i > 10; i--)
            for (int j = 10; j > -10; j--)
                screenObjects.add(new ScreenObject(new Entity(i*50, j*50, 50, 50, Depth.THINGS), box, 50));

        for (int i = 15; i > 10; i -= 1)
            for (int j = 10; j > -10; j -= 1){
                Entity entity = new Entity(i*50, j*50, 50, 50, Depth.WALLS, false);
                game.map.addEntity(entity);
                screenObjects.add(new ScreenObject(entity, flyingFloor, 50));
            }

        for (int i = -100; i < 0; i ++)
            for (int j = 50; j > -50; j -= 1){
                Vase entity = new Vase(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000));
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakbleScreenObject(entity, vase1, vase2));
            }

        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 100, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakbleScreenObject(entity, dog1, dog2));
            }
        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 100, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakbleScreenObject(entity, plane1, plane2));
            }
        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 100, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);;
                screenObjects.add(new BreakbleScreenObject(entity, thomas1, thomas2));
            }
        for (int i = -10; i < 0; i ++)
            for (int j = 5; j > 0; j -= 1){
                Enemy entity = new Enemy(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000), 1000, game.player.getRectangle(), game.getMap());
                game.map.addBreakableEntity(entity);
                screenObjects.add(new BreakbleScreenObject(entity, boss1, boss2));
            }
    }

    public void addBullet(Bullet bullet){
        screenObjects.add(new ScreenObject(bullet, this.bullet, 0));
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
        box.dispose();

        flyingFloor.dispose();
        floor.dispose();
        for (Texture cur : body)
            cur.dispose();
        for (Texture cur : legs)
            cur.dispose();
        vase1.dispose();
        vase2.dispose();
        bullet.dispose();

        screenObjects.clear();
        batch.dispose();
    }

    public CameraController getCameraController() {
        return cameraController;
    }
}
