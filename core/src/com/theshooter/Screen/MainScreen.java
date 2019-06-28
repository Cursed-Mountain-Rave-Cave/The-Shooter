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

    private Sprite backGroundSprite;
    private Sprite menuBackSprite;
    private Sprite startButtonSprite;
    private Sprite resumeButtonSprite;
    private Sprite exitButtonSprite;

    private Sprite classicSprite;
    private Sprite clownSprite;
    private Sprite meatSprite;
    private Sprite leatherSprite;
    private Sprite smth1Sprtie;
    private Sprite smth2Sprite;


    private boolean firstClick;
    private boolean isPlaying;
    private boolean selectShowed;


    public MainScreen() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(1920, 1080);
        camera.setToOrtho(false);

        backGroundSprite = new Sprite(new Texture("background.png"));
        menuBackSprite = new Sprite(new Texture("menuback.png"));
        startButtonSprite = new Sprite(new Texture("start.png"));
        resumeButtonSprite = new Sprite(new Texture("resume.png"));
        exitButtonSprite = new Sprite(new Texture("exit.png"));


        classicSprite = new Sprite(new Texture("classic.png"));
        clownSprite = new Sprite(new Texture("clown.png"));
        meatSprite = new Sprite(new Texture("meat.png"));
        leatherSprite = new Sprite(new Texture("leather.png"));
        smth1Sprtie = new Sprite(new Texture("cheto1.png"));
        smth2Sprite = new Sprite(new Texture("cheto2.png"));

        menuBackSprite.setSize(Gdx.graphics.getWidth() / 1.791f, Gdx.graphics.getHeight() / 3.167f );
        menuBackSprite.setPosition(Gdx.graphics.getWidth() / 2f - menuBackSprite.getWidth() / 2f, 0);

        backGroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backGroundSprite.setPosition(0, 0);

        startButtonSprite.setSize(Gdx.graphics.getWidth() / 5.828f, Gdx.graphics.getHeight() / 8.308f);
        startButtonSprite.setPosition(Gdx.graphics.getWidth() / 2f - startButtonSprite.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - 2.7f * startButtonSprite.getHeight());

        resumeButtonSprite.setSize(startButtonSprite.getWidth(), startButtonSprite.getHeight());
        resumeButtonSprite.setPosition(startButtonSprite.getX(), startButtonSprite.getY());

        exitButtonSprite.setSize(startButtonSprite.getWidth(), startButtonSprite.getHeight());
        exitButtonSprite.setPosition(startButtonSprite.getX(),
                Gdx.graphics.getHeight() / 2f - 4 *exitButtonSprite.getHeight());

        classicSprite.setSize(Gdx.graphics.getWidth() / 6.4f, Gdx.graphics.getHeight() / 7.2f);
        classicSprite.setPosition(Gdx.graphics.getWidth() / 2f - classicSprite.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - 2.4f * classicSprite.getHeight());

        clownSprite.setSize(classicSprite.getWidth(), classicSprite.getHeight());
        clownSprite.setPosition(classicSprite.getX(),
                Gdx.graphics.getHeight() / 2f - 3.5f * clownSprite.getHeight());

        smth1Sprtie.setSize(classicSprite.getWidth(), classicSprite.getHeight());
        smth1Sprtie.setPosition(Gdx.graphics.getWidth() / 2f - (3.2f * smth1Sprtie.getWidth()) / 2f,
                clownSprite.getY());

        smth2Sprite.setSize(classicSprite.getWidth(), classicSprite.getHeight());
        smth2Sprite.setPosition(Gdx.graphics.getWidth() / 2f + (1.2f * smth2Sprite.getWidth()) / 2f,
                clownSprite.getY());

        meatSprite.setSize(classicSprite.getWidth(), classicSprite.getHeight());
        meatSprite.setPosition(smth1Sprtie.getX(),
                classicSprite.getY());

        leatherSprite.setSize(classicSprite.getWidth(), classicSprite.getHeight());
        leatherSprite.setPosition(smth2Sprite.getX(),
                classicSprite.getY());


        firstClick = false;
        isPlaying = false;
        selectShowed = false;
    }

    @Override
    public void show() {

    }

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

        if (firstClick) {
            menuBackSprite.draw(batch);
            if (!selectShowed || Game.getInstance().isPaused()) {
                if (!Game.getInstance().isStarted())
                    startButtonSprite.draw(batch);
                else
                    resumeButtonSprite.draw(batch);
                exitButtonSprite.draw(batch);
            } else  if (selectShowed && !Game.getInstance().isStarted()){
                meatSprite.draw(batch);
                classicSprite.draw(batch);
                leatherSprite.draw(batch);
                smth1Sprtie.draw(batch);
                clownSprite.draw(batch);
                smth2Sprite.draw(batch);
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
            if (contain(exitButtonSprite, touchX, touchY))
                Gdx.app.exit();
            if (contain(startButtonSprite, touchX, touchY)) {
                if (!Game.getInstance().isStarted())
                    selectShowed = true;
                if (Game.getInstance().isPaused()) {
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setPaused(false);
                }

            }
        } else if (selectShowed && !Game.getInstance().isStarted()) {
                if (contain(classicSprite, touchX, touchY)) {
                    Game.getInstance().getTextureController().style = "classic";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(meatSprite, touchX, touchY)) {
                    Game.getInstance().getTextureController().style = "meat";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(leatherSprite, touchX, touchY)) {
                    Game.getInstance().getTextureController().style = "leather";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(clownSprite, touchX, touchY)) {
                    Game.getInstance().getTextureController().style = "clown";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(smth1Sprtie, touchX, touchY)) {
                    Game.getInstance().getTextureController().style = "smth1";
                    Game.getInstance().getAudioController().stopMusic();
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().setStarted();
                }
                if (contain(smth2Sprite, touchX, touchY)) {
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

    private boolean contain(Sprite sprite, float x1, float y1) {
        return  x1 >= sprite.getX() && x1 <= sprite.getX() + sprite.getWidth() && y1 >= sprite.getY() && y1 <= sprite.getY() + sprite.getHeight();
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
