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
    final private Game game;

    public SpriteBatch batch;

    private ScreenObjectArray screenObjects;

    private CameraController cameraController;
    private CameraController guiCameraController;

    public HumanScreenObject playerScreen;

    private BitmapFont font;


    public void placeFloor(int x, int y, int type){
        screenObjects.add(new ScreenObject(new Entity(x*50, y*50, 50, 50, Depth.FLOOR),
                game.t.getTexture("floor", "floor" + type), 50));
    }
    public void placeWall(int x, int y){
        Wall entity = new Wall(x*50, y*50, 50, 50);
        game.map.addEntity(entity);
        screenObjects.add(new WallScreenObject(entity, game.t.getTextures("walls", "wall2")));
    }
    public void placeFloors(int x0, int y0, int x1, int y1, int type){
        for(int i = x0; i < x1; i++)
            for(int j = y0; j < y1; j++)
                placeFloor(i, j, type);
    }
    public void placeFloors(int x0, int y0, int x1, int y1){
        for(int i = x0; i < x1; i++)
            for(int j = y0; j < y1; j++)
                placeFloor(i, j, MathUtils.random(10, 11));
    }

    public void placeWalls(int x0, int y0, int x1, int y1){
        Wall entity = new Wall(x0*50, y0*50, 50 * (x1 - x0), 50 * (y1 - y0));
        game.map.addEntity(entity);

        for(int x = x0; x < x1; x++)
            for (int y = y0; y < y1; y++){
                screenObjects.add(new WallScreenObject(entity, 50 * x, 50 * y, game.t.getTextures("walls", "wall2")));
            }

    }
    public void placeInvisibleWall(int x0, int y0, int x1, int y1) {
        InvisibleWall entity = new InvisibleWall(x0*50, y0*50, 50 * (x1 - x0), 50 * (y1 - y0));
        game.map.addEntity(entity);
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
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("things", "breakableThing" + MathUtils.random(2, 3)), 0));
    }
    public void placeGate(int x, int y){
        Gate entity = new Gate(x, y);
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("things", "breakableThing" + MathUtils.random(4, 5)), 0));
    }
    public void placeHookah(int x, int y) {
        Hookah entity = new Hookah(x, y);
        game.map.addEntity(entity);
        screenObjects.add(new ScreenObject(entity,
                game.t.getTexture("things", "unbreakableThing2"), 0));
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
    private void spawnTramp(int x, int y) {
        Enemy entity = new Tramp(x, y, game.player.getRectangle(), game.getMap());
        game.map.addBreakableEntity(entity);
        screenObjects.add(new BreakableScreenObject(entity,
                game.t.getTextures("enemy", "enemy5"), 200));
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

    private void generFloor(){
        placeFloors(0, 0, 49, 10);
        placeFloors(49, 0, 100, 4);
        placeFloors(51, 3, 100, 10);
        placeFloors(49,4,100,6,4);
        placeFloors(49, 6, 51, 10, 4);
        placeFloors(0, 10, 89, 100);
        placeFloors(89, 10, 100, 100, 7);
    }
    private void generWalls(){
        placeWalls(0, 10, 48, 11);
        placeWalls(0, 11, 1, 100);
        placeWalls(52, 10, 90, 11);
        placeWalls(89, 11, 90, 100);
        placeWalls(1, 99, 90, 100);
        placeWalls(30, 11, 31, 28);
        placeWalls(70, 11, 71, 28);
        placeWalls(30, 27, 55, 28);
        placeWalls(58, 27, 71, 28);
        placeWalls(54, 27, 55, 37);
        placeWalls(14, 36, 55, 37);
        placeWalls(58, 27, 59, 45);
        placeWalls(58, 44, 82, 45);
        placeWalls(81, 44, 82, 76);
        placeWalls(13, 36, 14, 46);
        placeWalls(13, 52, 14, 93);
        placeWalls(70, 58, 71, 82);
        placeWalls(13, 58, 49, 59);
        placeWalls(58, 58, 71, 59);
        placeWalls(0, 63, 14, 64);
        placeWalls(14, 82, 82, 83);

        placeInvisibleWall(-1, -1, 0, 101);
        placeInvisibleWall(-1, -1, 101, 0);
        placeInvisibleWall(89, 10, 101, 11);
        placeInvisibleWall(100, -1, 101, 101);
    }
    private void generEnvironment(){
        for(int i = 35; i < 37; i++)
            for(int j = 26; j < 27; j++)
                placeVase(i * 50, j * 50);

        for(int i = 54; i < 56; i++)
            for(int j = 19; j < 21; j++)
                placeVase(i * 50, j * 50);

        for(int i = 52; i < 55; i++)
            for(int j = 26; j < 27; j++)
                placeVase(i * 50, j * 50);

        for(int i = 58; i < 61; i++)
            for(int j = 26; j < 27; j++)
                placeVase(i * 50, j * 50);

        for(int i = 72; i < 74; i++)
            for(int j = 36; j < 38; j++)
                placeVase(i * 50, j * 50);

        for(int i = 75; i < 77; i++)
            for(int j = 32; j < 34; j++)
                placeVase(i * 50, j * 50);

        for(int i = 89; i < 100; i++)
            for(int j = 10; j < 100; j++)
                placeFloor(i, j, 9);
        for(int i = 40; i < 44; i++)
            for(int j = 87; j < 95; j++)
                placeVase(i * 50, j * 50);

        for(int i = 58; i < 60; i++)
            for(int j = 65; j < 67; j++)
                placeVase(i * 50, j * 50);

        for(int i = 48; i < 51; i++)
            for(int j = 69; j < 72; j++)
                placeVase(i * 50, j * 50);

        for(int i = 8; i < 10; i++)
            for(int j = 53; j < 55; j++)
                placeVase(i * 50, j * 50);

        for(int i = 5; i < 7; i++)
            for(int j = 18; j < 20; j++)
                placeVase(i * 50, j * 50);

        for(int i = 26; i < 27; i++)
            for(int j = 24; j < 27; j++)
                placeVase(i * 50, j * 50);

        placeVase(62 * 50, 57 * 50);
        placeVase(66 * 50, 57 * 50);

        placeGate(50 * 48, 50 * 10);

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
    private void generEnemies(){
        /*
            Tramp
        */
        spawnTramp(87 * 50, 5 * 50);
        spawnTramp(87 * 50, 1 * 50);
        spawnTramp(87 * 50, -5 * 50);
        /*
            Arabin warrior
        */
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
        spawnArabinWarrior(64 * 50, 16 * 50);
        spawnArabinWarrior(60 * 50, 22 * 50);
        spawnArabinWarrior(57 * 50, 15 * 50);
        spawnArabinWarrior(51 * 50, 23 * 50);
        spawnArabinWarrior(51 * 50, 13 * 50);
        spawnArabinWarrior(47 * 50, 11 * 50);
        spawnArabinWarrior(46 * 50, 22 * 50);
        spawnArabinWarrior(43 * 50, 25 * 50);
        spawnArabinWarrior(36 * 50, 19 * 50);
        spawnArabinWarrior(31 * 50, 25 * 50);
        spawnArabinWarrior(7 * 50, 34 * 50);
        spawnArabinWarrior(4 * 50, 44 * 50);
        spawnArabinWarrior(6 * 50, 52 * 50);
        spawnArabinWarrior(21 * 50, 54 * 50);
        spawnArabinWarrior(23 * 50, 44 * 50);
        spawnArabinWarrior(28 * 50, 47 * 50);
        spawnArabinWarrior(30 * 50, 55 * 50);
        spawnArabinWarrior(35 * 50, 50 * 50);
        spawnArabinWarrior(38 * 50, 43 * 50);
        spawnArabinWarrior(43 * 50, 52 * 50);
        spawnArabinWarrior(46 * 50, 43 * 50);
        spawnArabinWarrior(48 * 50, 48 * 50);
        spawnArabinWarrior(60 * 50, 54 * 50);
        spawnArabinWarrior(65 * 50, 46 * 50);
        spawnArabinWarrior(67 * 50, 51 * 50);
        spawnArabinWarrior(56 * 50, 65 * 50);
        spawnArabinWarrior(51 * 50, 65 * 50);
        spawnArabinWarrior(38 * 50, 72 * 50);
        spawnArabinWarrior(37 * 50, 71 * 50);
        for(int i = 83; i < 86; i++)
            for(int j = 28; j < 32; j++)
                spawnArabinWarrior(i * 50, j * 50);
        for(int i = 39; i < 45; i++)
            for(int j = 30; j < 33; j++)
                spawnArabinWarrior(i * 50, j * 50);
        for(int i = 69; i < 77; i++)
            for(int j = 86; j < 95; j++)
                spawnArabinWarrior(i * 50, j * 50);
        for(int i = 83; i < 85; i++)
            for(int j = 45; j < 47; j++)
                spawnArabinWarrior(i * 50, j * 50);
        for(int i = 44; i < 46; i++)
            for(int j = 62; j < 66; j++)
                spawnArabinWarrior(i * 50, j * 50);
        /*
            Train
        */
        spawnTrain(55 * 50, 30 * 50);
        spawnTrain(55 * 50, 34 * 50);
        spawnTrain(60 * 50,49 * 50);
        spawnTrain(54 * 50,45 * 50);
        spawnTrain(49 * 50,41 * 50);
        spawnTrain(27 * 50,64 * 50);
        spawnTrain(23 * 50,66 * 50);
        spawnTrain(19 * 50,70 * 50);
        spawnTrain(83 * 50,57 * 50);
        for(int i = 61; i < 65; i+=2)
            for(int j = 87; j < 91; j+=2)
                spawnTrain(i * 50, j * 50);
        /*
            Keanu
        */
        spawnKeanu(60 * 50, 59 * 50);
        spawnKeanu(42 * 50, 59 * 50);
        spawnKeanu(11 * 50, 52 * 50);
        spawnKeanu(11 * 50, 44 * 50);
        spawnKeanu(80 * 50, 83 * 50);
        spawnKeanu(82 * 50, 74 * 50);
        spawnKeanu(79 * 50, 42 * 50);
        for(int i = 46; i < 50; i+=2)
            for(int j = 88; j < 92; j+= 2)
                spawnKeanu(i * 50, j * 50);
        /*
            Plane
        */
        spawnPlane(84 * 50, 66 * 50);
        spawnPlane(84 * 50, 66 * 50);
        spawnPlane(54 * 50, 61 * 50);
        spawnPlane(49 * 50, 61 * 50);
        spawnPlane(4 * 50, 28 * 50);
        spawnPlane(10 * 50, 18 * 50);
        spawnPlane(13 * 50, 24 * 50);
        spawnPlane(18 * 50, 19 * 50);
        spawnPlane(22 * 50, 24 * 50);
        for(int i = 53; i < 57; i+=2)
            for(int j = 92; j < 96; j+= 2)
                spawnPlane(i * 50, j * 50);
        /*
            Boss
        */
        spawnBoss(5 * 50, 65 * 50);
    }

    public GameScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();

        cameraController = new CameraController();
        guiCameraController = new CameraController();
        guiCameraController.translateCamera(960, 540);

        playerScreen = new HumanScreenObject(game.player, game.t.getTextures("player", "body1"),
                                                          game.t.getTextures("player", "legs1"));

        screenObjects = new ScreenObjectArray();

        screenObjects.add(playerScreen);

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);

        generFloor();
        generWalls();
        generEnvironment();
        generEnemies();
    }

    public void addBullet(Bullet bullet){
        screenObjects.add(new BulletScreenObject(bullet, game.t.getTexture("bullets", "bullet" + MathUtils.random(1, 5)), 5));
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

        if(Game.config.showAdditionalInfo == true)
            font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, 1080);

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
