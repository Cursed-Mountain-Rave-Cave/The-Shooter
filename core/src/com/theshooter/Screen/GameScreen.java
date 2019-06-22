package com.theshooter.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theshooter.Game;
import com.theshooter.Logic.CameraController;

public class GameScreen implements Screen {

    public SpriteBatch batch;

    public ScreenObjectArray screenObjects;

    private CameraController cameraController;
    private CameraController guiCameraController;

    public HumanScreenObject playerScreen;

    private BitmapFont font;

    public String screenMessage;
    public String targetMessage;

    public GameScreen(){
        this.screenMessage = "Ah shit, here we go again.";
        this.targetMessage = "Kill all enemies";

        batch = new SpriteBatch();

        cameraController = new CameraController();
        guiCameraController = new CameraController();
        guiCameraController.translateCamera(960, 540);

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
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
            font.draw(batch, "\n\n\n\n\nFPS: " + Gdx.graphics.getFramesPerSecond() + "\nX: " + Game.getInstance().getEntityController().getPlayer().getX() + " Y: " + Game.getInstance().getEntityController().getPlayer().getY(), 0, 1080);

        font.draw(batch, "Target: " + targetMessage + "\nHP: " + Game.getInstance().getEntityController().getPlayer().getHP() + "\nPatrons: " + Game.getInstance().checkAmmoSuply(), 0, 1080);

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
