package com.theshooter.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Game;
import com.theshooter.Logic.CameraController;
import com.theshooter.Logic.Entity.*;

public class GameScreen implements Screen {

    final private Game game;

    public SpriteBatch batch;

    private ScreenObjectArray screenObjects;

    private CameraController cameraController;

    public HumanScreenObject playerScreen;

    private boolean showAdditionalInfo;
    private BitmapFont font;

    public void switchAdditionalInfo() {
        if(showAdditionalInfo == true)
            showAdditionalInfo = false;
        else
            showAdditionalInfo = true;
    }

    public void placeFloor(int x, int y, int type){
        screenObjects.add(new ScreenObject(new Entity(x*50, y*50, 50, 50, Depth.FLOOR),
                game.t.getTexture("floor", "floor" + type), 50));
    }

    public void placeWall(int x, int y){
        Wall entity = new Wall(x*50, y*50, 50, 50);
        game.map.addEntity(entity);
        screenObjects.add(new WallScreenObject(entity, game.t.getTextures("walls", "wall2")));
    }

    public void placeVase(int x, int y){
        Vase entity = new Vase(x, y);
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("things", "breakableThing1"), 50));
    }

    public void placeTend(int x, int y){
        Tent entity = new Tent(x, y);
        game.map.addBreakableEntity(entity);
        int rand = MathUtils.random(2, 3);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("things", "breakableThing" + rand), 150));
    }

    private void spawnArabinWarrior(int x, int y) {
        HumanEnemy entity = new HumanEnemy(x, y, 15, game.player.getRectangle(), game.getMap());
        game.map.addBreakableEntity(entity);
        screenObjects.add(new HumanScreenObject(entity,
                game.t.getTextures("player", "body2"), game.t.getTextures("player", "legs2")));
    }

    private void spawnBoss(int x, int y) {
        Enemy entity = new Enemy(x, y,75, 75, 100, 100,game.player.getRectangle(), game.getMap());
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("enemy", "enemy1"), 84));
    }

    private void spawnTrain(int x, int y) {
        Enemy entity = new Enemy(x, y,75,75,10,200, game.player.getRectangle(), game.getMap());
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("enemy", "enemy4"), 75));
    }

    private void spawnPlane(int x, int y) {
        Enemy entity = new Enemy(x, y, 75,75, 10,100, game.player.getRectangle(), game.getMap());
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("enemy", "enemy3"), 150));
    }

    private void spawnKeanu(int x, int y) {
        Enemy entity = new Enemy(x, y,75,75, 50,100, game.player.getRectangle(), game.getMap());
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("enemy", "enemy2"), 112));
    }

    public GameScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();

        cameraController = new CameraController();

        playerScreen = new HumanScreenObject(game.player, game.t.getTextures("player", "body1"),
                                                          game.t.getTextures("player", "legs1"));

        screenObjects = new ScreenObjectArray();

        screenObjects.add(playerScreen);

        showAdditionalInfo = false;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);

        for(int i = 0; i < 49; i++)
            for(int j = 0; j < 10; j++)
                placeFloor(i, j, 2);

        for(int i = 49; i < 100; i++)
            for(int j = 0; j < 4; j++)
                placeFloor(i, j, 2);

        for(int i = 51; i < 100; i++)
            for(int j = 3; j < 10; j++)
                placeFloor(i, j, 2);


        for(int i = 49; i < 100; i++)
            for(int j = 4; j < 6; j++)
                placeFloor(i, j, 4);

        for(int i = 49; i < 51; i++)
            for(int j = 6; j < 10; j++)
                placeFloor(i, j, 4);

        for(int i = 0; i < 89; i++)
            for(int j = 10; j < 100; j++)
                placeFloor(i, j, 2);//city


        for(int i = 89; i < 100; i++)
            for(int j = 10; j < 100; j++)
                placeFloor(i, j, 7);






        for(int i = 0; i < 48; i++)
            for(int j = 10; j < 11; j++)
                placeWall(i, j);

        spawnArabinWarrior(87 * 50, 8 * 50);
        spawnArabinWarrior(86 * 50, 3 * 50);
        spawnArabinWarrior(85 * 50, 6 * 50);
        spawnArabinWarrior(82 * 50, 2 * 50);
        spawnArabinWarrior(79 * 50, 6 * 50);
        spawnArabinWarrior(76 * 50, 2 * 50);
        spawnArabinWarrior(70 * 50, 8 * 50);
        spawnArabinWarrior(64 * 50, 2 * 50);
        spawnArabinWarrior(59 * 50, 7 * 50);
        spawnArabinWarrior(55 * 50, 50);
        spawnArabinWarrior(52 * 50, 8 * 50);
        spawnArabinWarrior(43 * 50, 6 * 50);
        spawnArabinWarrior(42 * 50, 3 * 50);
        spawnArabinWarrior(37 * 50, 6 * 50);
        spawnArabinWarrior(33 * 50, 3 * 50);

        for(int i = 0; i < 1; i++)
            for(int j = 11; j < 100; j++)
                placeWall(i, j);

        for(int i = 52; i < 90; i++)
            for(int j = 10; j < 11; j++)
                placeWall(i, j);

        for(int i = 89; i < 90; i++)
            for(int j = 11; j < 100; j++)
                placeWall(i, j);

        for(int i = 1; i < 90; i++)
            for(int j = 99; j < 100; j++)
                placeWall(i, j);

        for(int i = 30; i < 31; i++)
            for(int j = 11; j < 28; j++)
                placeWall(i, j);

        for(int i = 70; i < 71; i++)
            for(int j = 11; j < 28; j++)
                placeWall(i, j);

        for(int i = 30; i < 55; i++)
            for(int j = 27; j < 28; j++)
                placeWall(i, j);

        for(int i = 58; i < 71; i++)
            for(int j = 27; j < 28; j++)
                placeWall(i, j);

        for(int i = 54; i < 55; i++)
            for(int j = 27; j < 37; j++)
                placeWall(i, j);

        for(int i = 14; i < 55; i++)
            for(int j = 36; j < 37; j++)
                placeWall(i, j);

        for(int i = 58; i < 59; i++)
            for(int j = 27; j < 45; j++)
                placeWall(i, j);

        for(int i = 58; i < 82; i++)
            for(int j = 44; j < 45; j++)
                placeWall(i, j);

        for(int i = 81; i < 82; i++)
            for(int j = 44; j < 76; j++)
                placeWall(i, j);


        for(int i = 13; i < 14; i++)
            for(int j = 36; j < 46; j++)
                placeWall(i, j);

        for(int i = 13; i < 14; i++)
            for(int j = 52; j < 93; j++)
                placeWall(i, j);

        for(int i = 70; i < 71; i++)
            for(int j = 58; j < 82; j++)
                placeWall(i, j);

        for(int i = 13; i < 49; i++)
            for(int j = 58; j < 59; j++)
                placeWall(i, j);

        for(int i = 58; i < 71; i++)
            for(int j = 58; j < 59; j++)
                placeWall(i, j);

        for(int i = 0; i < 14; i++)
            for(int j = 63; j < 64; j++)
                placeWall(i, j);

        for(int i = 14; i < 82; i++)
            for(int j = 82; j < 83; j++)
                placeWall(i, j);

        placeTend(50 * 7, 50 * 22);
        placeTend(50 * 5, 50 * 55);
        placeTend(50 * 2, 50 * 93);
        placeTend(50 * 76, 50 * 33);
        placeTend(50 * 55, 50 * 73);
        placeTend(50 * 60, 50 * 69);
        placeTend(50 * 63, 50 * 55);
        placeTend(50 * 32, 50 * 21);
        placeTend(50 * 34, 50 * 15);
        placeTend(50 * 41, 50 * 16);
        placeTend(50 * 41, 50 * 21);
        placeTend(50 * 47, 50 * 21);
        placeTend(50 * 47, 50 * 21);
        placeTend(50 * 53, 50 * 13);
        placeTend(50 * 60, 50 * 15);
        placeTend(50 * 63, 50 * 20);
    }

    public void addBullet(Bullet bullet){
        screenObjects.add(new ScreenObject(bullet, game.t.getTexture("bullets", "bullet1"), 0));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cameraController.lookAt(playerScreen.getScreenX(), playerScreen.getScreenY());

        cameraController.update();
        batch.setProjectionMatrix(cameraController.getCamera().combined);

        Gdx.gl.glClearColor(0xDC / 265f, 0xC2 / 265f, 0x76 / 265f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        screenObjects.draw(batch);

        if(showAdditionalInfo == true)
            font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(),
                    cameraController.getCamera().position.x - Gdx.graphics.getWidth() / 2 - 150,
                    Gdx.graphics.getHeight() / 2 + cameraController.getCamera().position.y + 60);

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
