package com.theshooter.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.theshooter.Game;

public class MainMenu implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture backGround;
    private Texture startButton;
    private Texture exitButton;

    private Sprite backGroundSprite;

    public MainMenu() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(1920, 1080);
        camera.setToOrtho(false);

        backGround = new Texture("background.png");
        startButton = new Texture("start.png");
        exitButton = new Texture("exit.png");

        backGroundSprite = new Sprite(backGround);
    }

    @Override
    public void show() {

    }

    private float startX;
    private float startY;
    private float exitX;
    private float exitY;


    @Override
    public void render(float delta) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        backGroundSprite.draw(batch);

        startX = camera.viewportWidth / 2 - startButton.getWidth() / 2f;
        startY = camera.viewportHeight / 2 - startButton.getHeight() / 2f - 100;
        exitX = camera.viewportWidth / 2 - startButton.getWidth() / 2f;
        exitY = camera.viewportHeight / 2 - startButton.getHeight() / 2f - 350;

        batch.draw(startButton, startX, startY);
        batch.draw(exitButton, exitX, exitY);

        isTouched();

        batch.end();
    }

    public void isTouched() {
        if (Gdx.input.isTouched()) {
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);
            float touchX = touched.x;
            float touchY = touched.y;

            if (touchX >= exitX && touchX <= exitX + exitButton.getWidth() &&
                    touchY >= exitY && touchY <= exitY + exitButton.getHeight())
                Gdx.app.exit();

            if (touchX >= startX && touchX <= touchX + startButton.getWidth() &&
                touchY >= startX && touchX <= touchX + startButton.getHeight()) {
                Game.getInstance().setStarted(true);
                Game.getInstance().setScreen(Game.getInstance().gameScreen);
            }
        }
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

    }
}
