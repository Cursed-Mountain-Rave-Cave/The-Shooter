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

    public InputController(Game game){
        this.game = game;
        keyWPressed = false;
        keySPressed = false;
        keyAPressed = false;
        keyDPressed = false;
        leftMouseBottomPressed = false;
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

        float norm = (float) Math.hypot(dx, dy);

        dx = (int)(dx * 1000 * Gdx.graphics.getDeltaTime() / norm);
        dy = (int)(dy * 1000 * Gdx.graphics.getDeltaTime() / norm);

        Rectangle place = game.player.getRectangle();

        place.x += dx;
        place.y += dy;

        if(!game.map.isAllowed(place)){
            place.x -= dx;
            place.y -= dy;
        }

        game.gameScreen.playerScreen.setCurrentLegs(dx, dy);

        if (leftMouseBottomPressed)
            game.shoot();
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
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float curX = screenX - Gdx.graphics.getWidth() / 2;
        float curY = screenY - Gdx.graphics.getHeight() / 2;

        game.gameScreen.playerScreen.setCurrentBody(curX, curY);

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        game.gameScreen.getCameraController().zoom(amount);
        return false;
    }
}
