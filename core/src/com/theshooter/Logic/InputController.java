package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Game;

public class InputController implements InputProcessor {

    final private Game game;

    private boolean keyWPressed;
    private boolean keySPressed;
    private boolean keyAPressed;
    private boolean keyDPressed;

    private boolean leftMouseBottomPressed;

    int typeOfShooting;

    public InputController(Game game){
        this.game = game;
        keyWPressed = false;
        keySPressed = false;
        keyAPressed = false;
        keyDPressed = false;
        leftMouseBottomPressed = false;
        typeOfShooting = 1;
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

        game.player.moveAt(dx, dy);

        if (leftMouseBottomPressed)
            switch (typeOfShooting) {
                case 1:
                    game.shoot1();
                    break;
                case 2:
                    game.shoot2();
                    break;
                case 3:
                    game.shoot3();
                    break;
            }
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
            case Input.Keys.NUM_1: {
                typeOfShooting = 1;
                break;
            }
            case Input.Keys.NUM_2: {
                typeOfShooting = 2;
                break;
            }
            case Input.Keys.NUM_3: {
                typeOfShooting = 3;
                break;
            }
            case Input.Keys.F3: {
                Game.config.showAdditionalInfo = !Game.config.showAdditionalInfo;
                break;
            }
            case Input.Keys.R: {
                game.reload();
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            leftMouseBottomPressed = true;
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
        int curX = screenX - Gdx.graphics.getWidth() / 2;
        int curY = screenY - Gdx.graphics.getHeight() / 2;

        game.player.lookAt(curX, curY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int curX = screenX - Gdx.graphics.getWidth() / 2;
        int curY = screenY - Gdx.graphics.getHeight() / 2;

        game.player.lookAt(curX, curY);

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        game.gameScreen.getCameraController().zoom(amount);
        return false;
    }
}
