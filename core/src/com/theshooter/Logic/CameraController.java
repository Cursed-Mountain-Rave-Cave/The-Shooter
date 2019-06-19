package com.theshooter.Logic;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {

    private OrthographicCamera camera;

    public CameraController(){
        camera = new OrthographicCamera(1920, 1080);
    }

    public void update(){
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void translateCamera(int dx, int dy){
        camera.translate(dx, dy);
    }

    public void lookAt(int x, int y){
        camera.position.set(x, y, 0);
    }

    public void zoom(int ammount){
        camera.zoom = Math.max(0.1f, Math.min(camera.zoom + ammount* 0.2f, 100f));
    }
}
