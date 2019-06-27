package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;

public class InputController implements InputProcessor {

    private boolean keyWPressed;
    private boolean keySPressed;
    private boolean keyAPressed;
    private boolean keyDPressed;
    private boolean mapIsOpen;

    private boolean leftMouseBottomPressed;

    public InputController(){
        keyWPressed = false;
        keySPressed = false;
        keyAPressed = false;
        keyDPressed = false;
        leftMouseBottomPressed = false;
        mapIsOpen = false;
    }

    public void update(){
        int dx = 0, dy = 0;

        if (keyWPressed){
            dx++;
            dy++;
        }
        if (keySPressed){
            dx--;
            dy--;
        }
        if (keyAPressed){
            dx--;
            dy++;
        }
        if (keyDPressed){
            dx++;
            dy--;
        }
        if (Game.getInstance().isStarted())
            Game.getInstance().getEntityController().getPlayer().moveAt(dx, dy);
        float sdx = Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
        float sdy = -Gdx.input.getY() + Gdx.graphics.getHeight() / 2 - 100;

        float deltaX = sdx / 2 + sdy;
        float deltaY = -sdx / 2 + sdy;
        Vector2 vect = new Vector2(deltaX, deltaY);

        if (leftMouseBottomPressed && (Game.getInstance().isStarted()))
            Game.getInstance().getEntityController().getPlayer().getCurrentWeapon().attack(vect);

        if (mapIsOpen && Game.getInstance().isStarted())
            Game.getInstance().setScreen(Game.getInstance().mapScreen);
        else if (Game.getInstance().isStarted())
            Game.getInstance().setScreen(Game.getInstance().gameScreen);
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){
            case Input.Keys.ESCAPE:{
                if (!Game.getInstance().isPaused() && Game.getInstance().isStarted()) {
                    Game.getInstance().setPaused(true);
                    Game.getInstance().mainMenu.setPlaying(false);
                    Game.getInstance().setScreen(Game.getInstance().mainMenu);
                } else if (Game.getInstance().isStarted() && Game.getInstance().isPaused()){
                    Game.getInstance().setPaused(false);
                    Game.getInstance().setScreen(Game.getInstance().gameScreen);
                    Game.getInstance().getAudioController().stopMusic();

                }
                break;
            }
            case Input.Keys.F11:{
                if(Gdx.graphics.isFullscreen())
                    Gdx.graphics.setWindowedMode(1920, 1080);
                else
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                break;
            }
            case Input.Keys.W:{
                keyWPressed = true;
                break;
            }
            case Input.Keys.S:{
                keySPressed = true;
                break;
            }
            case Input.Keys.A:{
                keyAPressed = true;
                break;
            }
            case Input.Keys.D:{
                keyDPressed = true;
                break;
            }
            case Input.Keys.M: {
                if (Game.getInstance().isStarted()) {
                    if (mapIsOpen) mapIsOpen = false;
                    else {
                        mapIsOpen = true;
                        Game.getInstance().mapScreen.center();
                    }
                }
                break;
            }
            case Input.Keys.NUM_1: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(1);
                break;
            }
            case Input.Keys.NUM_2: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(2);
                break;
            }
            case Input.Keys.NUM_3: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(3);
                break;
            }
            case Input.Keys.NUM_4: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(4);
                break;
            }
            case Input.Keys.NUM_5: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(5);
                break;
            }
            case Input.Keys.NUM_6: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(6);
                break;
            }
            case Input.Keys.NUM_7: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(7);
                break;
            }
            case Input.Keys.NUM_8: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(8);
                break;
            }
            case Input.Keys.NUM_9: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(9);
                break;
            }
            case Input.Keys.NUM_0: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().selectWeapon(10);
                break;
            }
            case Input.Keys.F3: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getConfig().showAdditionalInfo = !Game.getInstance().getConfig().showAdditionalInfo;
                break;
            }
            case Input.Keys.R: {
                if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().getCurrentWeapon().reload();
                break;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.W:{
                keyWPressed = false;
                break;
            }
            case Input.Keys.S:{
                keySPressed = false;
                break;
            }
            case Input.Keys.A:{
                keyAPressed = false;
                break;
            }
            case Input.Keys.D:{
                keyDPressed = false;
                break;
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    private int lastScreenX, lastScreenY;
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            leftMouseBottomPressed = true;
            lastScreenX = screenX;
            lastScreenY = screenY;
        }
        if (Game.getInstance().isPaused() || !Game.getInstance().isStarted()) {
            Game.getInstance().mainMenu.isTouched();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            leftMouseBottomPressed = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(mapIsOpen) {
            Game.getInstance().mapScreen.getCamera().translate((-screenX + lastScreenX) * Game.getInstance().mapScreen.getCamera().zoom,
                    (screenY - lastScreenY) * Game.getInstance().mapScreen.getCamera().zoom);
            lastScreenX = screenX;
            lastScreenY = screenY;
        }
        else {
            int curX = screenX - Gdx.graphics.getWidth() / 2;
            int curY = screenY - Gdx.graphics.getHeight() / 2;
            if (Game.getInstance().isStarted())
                Game.getInstance().getEntityController().getPlayer().lookAt(curX, curY);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int curX = screenX - Gdx.graphics.getWidth() / 2;
        int curY = screenY - Gdx.graphics.getHeight() / 2;

        if (Game.getInstance().isStarted())
            Game.getInstance().getEntityController().getPlayer().lookAt(curX, curY);

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if(mapIsOpen)
            Game.getInstance().mapScreen.zoom(amount);
        else
            Game.getInstance().gameScreen.getCameraController().zoom(amount);
        return false;
    }

    public boolean isLeftMouseBottomPressed() {
        return leftMouseBottomPressed;
    }
}
