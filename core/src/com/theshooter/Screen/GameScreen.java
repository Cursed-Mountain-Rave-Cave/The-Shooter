package com.theshooter.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.*;

public class GameScreen implements Screen {

    final private Game game;
    private ScreenObjectArray screenObjects;

    private Texture floor;
    private Texture flyingFloor;
    private Texture box;

    private Texture body[];
    private Texture legs[];

    private Texture bullet;

    private Texture vase1;
    private Texture vase2;

    private PlayerScreenObject playerScreen;

    private OrthographicCamera camera;

    public GameScreen(Game game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.translate(-960, -540);

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

        playerScreen = new PlayerScreenObject(game.player, body, legs);

        screenObjects = new ScreenObjectArray();

        screenObjects.add(playerScreen);

        for (int i = -100; i < 100; i++)
            for (int j = -100; j < 100; j++)
                screenObjects.add(new ScreenObject(new Entity(i*50, j*50, 50, 50, Depth.FLOOR), floor));

        for (int i = 20; i > 10; i--)
            for (int j = 10; j > -10; j--)
                screenObjects.add(new ScreenObject(new Entity(i*50, j*50, 50, 50, Depth.THINGS), box));

        for (int i = 15; i > 10; i -= 1)
            for (int j = 10; j > -10; j -= 1){
                Entity entity = new Entity(i*50, j*50, 50, 50, Depth.WALLS, false);
                game.map.addEntity(entity);
                screenObjects.add(new ScreenObject(entity, flyingFloor));
            }

        for (int i = -100; i < 0; i ++)
            for (int j = 50; j > -50; j -= 1){
                Vase entity = new Vase(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000));
                game.map.addBreakableEntitu(entity);
                screenObjects.add(new VaseScreenObject(entity, vase1, vase2));
            }
    }

    public void addBullet(Bullet bullet){
        screenObjects.add(new ScreenObject(bullet, this.bullet));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.3f, 0.3f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        screenObjects.draw(game.batch);

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();

        if (Gdx.input.isKeyPressed(Input.Keys.F11)){
            if(Gdx.graphics.isFullscreen())
                Gdx.graphics.setWindowedMode(1600, 900);
            else
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }



        int dx = 0, dy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            dy += 1000;

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            dy -= 1000;

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            dx -= 1000;

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            dx += 1000;

        dx *= Gdx.graphics.getDeltaTime();
        dy *= Gdx.graphics.getDeltaTime();

        if(dx != 0 && dy != 0){
            dx /= Math.sqrt(2);
            dy /= Math.sqrt(2);
        }

        Rectangle place = game.player.getRectangle();
        place.x += dx/2 + dy;
        place.y += -dx/2 + dy;

        if(game.map.isAllowed(place)){
            camera.translate(dx, dy);
        }else{
            place.x -= dx/2 + dy;
            place.y -= -dx/2 + dy;
        }

        float curX = Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
        float curY = Gdx.input.getY() - Gdx.graphics.getHeight() / 2;

        playerScreen.setCurrentBody(curX, curY);
        playerScreen.setCurrentLegs(dx, dy);

        if(Gdx.input.isTouched())
            game.shoot();

        game.map.update();
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
    }
}
