package com.theshooter.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Game;
import com.theshooter.Logic.CameraController;
import com.theshooter.Logic.Entity.*;

public class GameScreen implements Screen {

    public SpriteBatch batch;

    public ScreenObjectArray screenObjects;

    private CameraController cameraController;
    private CameraController guiCameraController;

    public HumanScreenObject playerScreen;

    private BitmapFont font;

    private boolean bossHere;

    public String screenMessage;
    public String targetMessage;


    public void bossFight() {
        Game.getInstance().SimpleMan.stop();
        Game.getInstance().SimpleMan = Gdx.audio.newMusic(Gdx.files.internal("music/Trump.mp3"));
        Game.getInstance().SimpleMan.setVolume(0.3f);
        Game.getInstance().SimpleMan.play();
        Game.getInstance().getEntityController().spawnTramp(30 * 50, 4 * 50);
        Game.getInstance().player.setX(99*50);
        Game.getInstance().player.setY(3*50);
        bossHere = true;

        targetMessage = "Be SLAV !!!";
    }

    public GameScreen(){
        this.screenMessage = "Ah shit, here we go again.";
        this.targetMessage = "Kill all enemies";
        bossHere = false;
        batch = new SpriteBatch();

        cameraController = new CameraController();
        guiCameraController = new CameraController();
        guiCameraController.translateCamera(960, 540);

        playerScreen = new HumanScreenObject(Game.getInstance().player, Game.getInstance().t.getTextures("player", "body7"),
                Game.getInstance().t.getTextures("player", "legs7"));

        screenObjects = new ScreenObjectArray();

        screenObjects.add(playerScreen);

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
    }


    public void addBullet(Bullet bullet){
        screenObjects.add(new BulletScreenObject(bullet, Game.getInstance().t.getTexture("bullets", "bullet" + MathUtils.random(1, 5)), 5));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cameraController.lookAt(playerScreen.getScreenX(), playerScreen.getScreenY());

        cameraController.update();
        guiCameraController.update();

        Gdx.gl.glClearColor(0xDC / 265f, 0xC2 / 265f, 0x76 / 265f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //World

        batch.setProjectionMatrix(cameraController.getCamera().combined);

        screenObjects.draw(batch);

        //GUI

        batch.setProjectionMatrix(guiCameraController.getCamera().combined);

        font.getData().setScale(2);
        if(Game.getInstance().getConfig().showAdditionalInfo)
            font.draw(batch, "\n\n\n\n\nFPS: " + Gdx.graphics.getFramesPerSecond() + "\nX: " + Game.getInstance().player.getX() + " Y: " + Game.getInstance().player.getY(), 0, 1080);

        font.draw(batch, "Target: " + targetMessage + "\nHP: " + Game.getInstance().player.getHP() + "\nPatrons: " + Game.getInstance().checkAmmoSuply(), 0, 1080);

        if(bossHere){
            font.getData().setScale(5);
            font.draw(batch, screenMessage, 500, 780);
        }


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
