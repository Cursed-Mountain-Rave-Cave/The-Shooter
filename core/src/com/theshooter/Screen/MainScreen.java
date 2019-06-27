package com.theshooter.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.theshooter.Game;

public class MainScreen implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture backGround;
    private Texture startButton;
    private Texture exitButton;
    private Texture menuBack;
    private Texture resumeButton;

    private Texture classic;
    private Texture clown;
    private Texture meat;
    private Texture leather;
    private Texture smth1;
    private Texture smth2;

    private Sprite backGroundSprite;

    private boolean firstClick;
    private boolean isPlaying;
    private boolean selectShowed;


    public MainScreen() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(1920, 1080);
        camera.setToOrtho(false);

        backGround = new Texture("background.png");
        startButton = new Texture("start.png");
        resumeButton = new Texture("resume.png");
        exitButton = new Texture("exit.png");
        menuBack = new Texture("menuback.png");

        classic = new Texture("classic.png");
        clown = new Texture("clown.png");
        meat = new Texture("meat.png");
        leather = new Texture("leather.png");
        smth1 = new Texture("cheto1.png");
        smth2 = new Texture("cheto2.png");

        backGroundSprite = new Sprite(backGround);

        backGroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backGroundSprite.setPosition(0, 0);

        firstClick = false;
        isPlaying = false;
        selectShowed = false;
    }

    @Override
    public void show() {

    }

    private float startX;
    private float startY;
    private float exitX;
    private float exitY;
    private float classicX;
    private float classicY;


    @Override
    public void render(float delta) {

        if ((Game.getInstance().isPaused() || !Game.getInstance().isStarted())  && !isPlaying) {
            isPlaying = true;
            Game.getInstance().getAudioController().playMusic("awaken", 0.3f);
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        backGroundSprite.draw(batch);

        startX = camera.viewportWidth / 2 - startButton.getWidth() / 2f;
        startY = camera.viewportHeight / 2 - startButton.getHeight() / 2f - 260;

        exitX = camera.viewportWidth / 2 - exitButton.getWidth() / 2f;
        exitY = camera.viewportHeight / 2 - exitButton.getHeight() / 2f - 450;

        classicX = camera.viewportWidth / 2 - classic.getWidth() / 2f - 340;
        classicY = camera.viewportHeight / 2 - classic.getHeight() / 2f - 280;

        if (firstClick) {
            batch.draw(menuBack, camera.viewportWidth / 2 - menuBack.getWidth() / 2f, 0);
            if (!selectShowed || Game.getInstance().isPaused()) {
                if (!Game.getInstance().isStarted())
                    batch.draw(startButton, startX, startY);
                else
                    batch.draw(resumeButton, startX, startY);
                batch.draw(exitButton, exitX, exitY);
            } else  if (selectShowed && !Game.getInstance().isStarted()){
                batch.draw(classic, classicX + 340, classicY);
                batch.draw(meat, classicX, classicY);
                batch.draw(leather, classicX + 680, classicY);
                batch.draw(smth1, classicX, classicY - classic.getHeight() - 20);
                batch.draw(clown, classicX + 340, classicY - classic.getHeight() - 20);
                batch.draw(smth2, classicX + 680, classicY - classic.getHeight() - 20);
            }

        }

        batch.end();
    }

    public void isTouched() {
        Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touched);
        float touchX = touched.x;
        float touchY = touched.y;
        if (firstClick && (!selectShowed || Game.getInstance().isStarted())) {
            if (contain(exitButton, touchX, touchY, exitX, exitY))
                Gdx.app.exit();
            if (contain(startButton, touchX, touchY, startX, startY)) {
                if (!Game.getInstance().isStarted())
                    selectShowed = true;
                if (Game.getInstance().isPaused()) {
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setPaused(false);
                }

            }
        } else if (selectShowed && !Game.getInstance().isStarted()) {
                if (contain(classic, touchX, touchY, classicX + 340, classicY)) {
                    Game.getInstance().getTextureController().style = "classic";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(meat, touchX, touchY, classicX, classicY)) {
                    Game.getInstance().getTextureController().style = "meat";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(leather, touchX, touchY, classicX + 680, classicY)) {
                    Game.getInstance().getTextureController().style = "leather";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(clown, touchX, touchY, classicX + 340, classicY - classic.getHeight() - 20)) {
                   /****/ Game.getInstance().getTextureController().style = "clown";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(smth1, touchX, touchY, classicX, classicY - classic.getHeight() - 20)) {
                    Game.getInstance().getTextureController().style = "smth1";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(smth2, touchX, touchY, classicX + 680, classicY - classic.getHeight() - 20)) {
                    Game.getInstance().getTextureController().style = "smth2";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
            }
        firstClick = true;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    private boolean contain(Texture texture, float x1, float y1, float x2, float y2) {
        return  x1 >= x2 && x1 <= x2 + texture.getWidth() && y1 >= y2 && y1 <= y2 + texture.getHeight();
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
        classic.dispose();
        clown.dispose();
        meat.dispose();
        leather.dispose();
        smth1.dispose();
        smth2.dispose();
    }
}
