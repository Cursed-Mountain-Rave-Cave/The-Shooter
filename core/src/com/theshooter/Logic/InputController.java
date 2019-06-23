package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Screen.MapScreen;

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

        Game.getInstance().getEntityController().getPlayer().moveAt(dx, dy);
        float sdx = Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
        float sdy = -Gdx.input.getY() + Gdx.graphics.getHeight() / 2 - 100;

        float deltaX = sdx / 2 + sdy;
        float deltaY = -sdx / 2 + sdy;
        Vector2 vect = new Vector2(deltaX, deltaY);

        if (leftMouseBottomPressed)
            Game.getInstance().getEntityController().getPlayer().getCurrentWeapon().attack(vect);

        if (mapIsOpen)
            Game.getInstance().setScreen(Game.getInstance().mapScreen);
        else
            Game.getInstance().setScreen(Game.getInstance().gameScreen);
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){
            case Input.Keys.ESCAPE:{
                Gdx.app.exit();
                break;
            }
            case Input.Keys.F11:{
                if(Gdx.graphics.isFullscreen())
                    Gdx.graphics.setWindowedMode(1600, 900);
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
                if(mapIsOpen) mapIsOpen = false;
                else mapIsOpen = true;
                break;
            }
            case Input.Keys.NUM_1: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(1);
                break;
            }
            case Input.Keys.NUM_2: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(2);
                break;
            }
            case Input.Keys.NUM_3: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(3);
                break;
            }
            case Input.Keys.NUM_4: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(4);
                break;
            }
            case Input.Keys.NUM_5: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(5);
                break;
            }
            case Input.Keys.NUM_6: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(6);
                break;
            }
            case Input.Keys.NUM_7: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(7);
                break;
            }
            case Input.Keys.NUM_8: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(8);
                break;
            }
            case Input.Keys.NUM_9: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(9);
                break;
            }
            case Input.Keys.NUM_0: {
                Game.getInstance().getEntityController().getPlayer().selectWeapon(0);
                break;
            }
            case Input.Keys.F3: {
                Game.getInstance().getConfig().showAdditionalInfo = !Game.getInstance().getConfig().showAdditionalInfo;
                break;
            }
            case Input.Keys.R: {
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

            Game.getInstance().getEntityController().getPlayer().lookAt(curX, curY);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int curX = screenX - Gdx.graphics.getWidth() / 2;
        int curY = screenY - Gdx.graphics.getHeight() / 2;

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
}
