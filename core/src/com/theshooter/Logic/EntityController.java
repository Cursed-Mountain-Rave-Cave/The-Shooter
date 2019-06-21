package com.theshooter.Logic;

import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.*;
import com.theshooter.Screen.*;

public class EntityController {

    private Map map;
    private ScreenObjectArray array;

    public EntityController(Map map, ScreenObjectArray array){
        this.map = map;
        this.array = array;
    }

    public void gener(){
        generFloor();
        generWalls();
        generEnvironment();
        generEnemies();
    }



    private void generFloor(){
        Game.getInstance().getEntityController().placeFloors(0, 0, 49, 10, 12);
        Game.getInstance().getEntityController().placeFloors(49, 0, 100, 4, 12);
        Game.getInstance().getEntityController().placeFloors(51, 3, 100, 10, 12);
        Game.getInstance().getEntityController().placeFloors(49,4,100,6,4);
        Game.getInstance().getEntityController().placeFloors(49, 6, 51, 10, 4);
        Game.getInstance().getEntityController().placeFloors(0, 10, 89, 100, 12);

        Game.getInstance().getEntityController().placeFloors(-40, -40, 100, 0, 12);
        Game.getInstance().getEntityController().placeFloors(100, -40, 140, 4, 12);
        Game.getInstance().getEntityController().placeFloors(100, 4, 140, 6, 4);
        Game.getInstance().getEntityController().placeFloors(100, 6, 140, 10, 12);
        Game.getInstance().getEntityController().placeFloors(89, 10, 140, 140, 7);
        Game.getInstance().getEntityController().placeFloors(-40, 0, 0, 100, 12);
        Game.getInstance().getEntityController().placeFloors(-40, 100, 89, 140, 12);

        Game.getInstance().getEntityController().placeFloors(-200, -200, -110, -110, 12);

    }
    private void generWalls(){
        Game.getInstance().getEntityController().placeWall(0, 10, 48, 11);
        Game.getInstance().getEntityController().placeWall(0, 11, 1, 100);
        Game.getInstance().getEntityController().placeWall(52, 10, 90, 11);
        Game.getInstance().getEntityController().placeWall(89, 11, 90, 100);
        Game.getInstance().getEntityController().placeWall(1, 99, 90, 100);
        Game.getInstance().getEntityController().placeWall(30, 11, 31, 28);
        Game.getInstance().getEntityController().placeWall(70, 11, 71, 28);
        Game.getInstance().getEntityController().placeWall(30, 27, 55, 28);
        Game.getInstance().getEntityController().placeWall(58, 27, 71, 28);
        Game.getInstance().getEntityController().placeWall(54, 27, 55, 37);
        Game.getInstance().getEntityController().placeWall(14, 36, 55, 37);
        Game.getInstance().getEntityController().placeWall(58, 27, 59, 45);
        Game.getInstance().getEntityController().placeWall(58, 44, 82, 45);
        Game.getInstance().getEntityController().placeWall(81, 44, 82, 76);
        Game.getInstance().getEntityController().placeWall(13, 36, 14, 46);
        Game.getInstance().getEntityController().placeWall(13, 52, 14, 93);
        Game.getInstance().getEntityController().placeWall(70, 58, 71, 82);
        Game.getInstance().getEntityController().placeWall(13, 58, 49, 59);
        Game.getInstance().getEntityController().placeWall(58, 58, 71, 59);
        Game.getInstance().getEntityController().placeWall(0, 63, 14, 64);
        Game.getInstance().getEntityController().placeWall(14, 82, 82, 83);

        Game.getInstance().getEntityController().placeInvisibleWall(-1, -1, 0, 101);
        Game.getInstance().getEntityController().placeInvisibleWall(-1, -1, 101, 0);
        Game.getInstance().getEntityController().placeInvisibleWall(89, 10, 101, 11);
        Game.getInstance().getEntityController().placeInvisibleWall(100, -1, 101, 101);


        Game.getInstance().getEntityController().placeInvisibleWall(-161, -160, -160, -151);
        Game.getInstance().getEntityController().placeInvisibleWall(-151, -160, -150, -151);

        Game.getInstance().getEntityController().placeInvisibleWall(-160, -161, -151, -160);
        Game.getInstance().getEntityController().placeInvisibleWall(-160, -151, -151, -150);

    }
    private void generEnvironment(){
        Game.getInstance().getEntityController().placeHome(76* 50,16 * 50);
        Game.getInstance().getEntityController().placeHome(63* 50,33 * 50);
        Game.getInstance().getEntityController().placeHome(64* 50,63 * 50);
        Game.getInstance().getEntityController().placeHome(41* 50,76 * 50);
        Game.getInstance().getEntityController().placeHome(22* 50,76 * 50);
        Game.getInstance().getEntityController().placeHome(13* 50,30 * 50);
        Game.getInstance().getEntityController().placeHome(75* 50,51 * 50);
        Game.getInstance().getEntityController().placeHome(7* 50,69 * 50);
        Game.getInstance().getEntityController().placeHome(7* 50,75 * 50);
        Game.getInstance().getEntityController().placeHome(7* 50,81 * 50);
        Game.getInstance().getEntityController().placeHome(7* 50,87 * 50);

        for(int i = -200; i < -110; i+=2)
            for(int j = -151; j < -110; j+=2)
                Game.getInstance().getEntityController().placeNotPassablePalm(i * 50, j * 50);

        for(int i = -200; i < -110; i+=2)
            for(int j = -200; j < -160; j+=2)
                Game.getInstance().getEntityController().placeNotPassablePalm(i * 50, j * 50);

        for(int i = -150; i < -110; i+=2)
            for(int j = -160; j < -151; j+=2)
                Game.getInstance().getEntityController().placeNotPassablePalm(i * 50, j * 50);

        for(int i = -200; i < -160; i+=2)
            for(int j = -160; j < -151; j+=2)
                Game.getInstance().getEntityController().placeNotPassablePalm(i * 50, j * 50);




        for(int i = -40; i < 140; i+=2)
            for(int j = -40; j < 0; j+=2)
                Game.getInstance().getEntityController().placeNotPassablePalm(i * 50, j * 50);

        for(int i = -40; i < 0; i+=2)
            for(int j = 0; j < 140; j+=2)
                Game.getInstance().getEntityController().placeNotPassablePalm(i * 50, j * 50);

        for(int i = 35; i < 37; i++)
            for(int j = 26; j < 27; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 54; i < 56; i++)
            for(int j = 19; j < 21; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 52; i < 55; i++)
            for(int j = 26; j < 27; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 58; i < 61; i++)
            for(int j = 26; j < 27; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 72; i < 74; i++)
            for(int j = 36; j < 38; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 75; i < 77; i++)
            for(int j = 32; j < 34; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 40; i < 44; i++)
            for(int j = 87; j < 95; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 58; i < 60; i++)
            for(int j = 65; j < 67; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 48; i < 51; i++)
            for(int j = 69; j < 72; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 8; i < 10; i++)
            for(int j = 53; j < 55; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 5; i < 7; i++)
            for(int j = 18; j < 20; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        for(int i = 26; i < 27; i++)
            for(int j = 24; j < 27; j++)
                Game.getInstance().getEntityController().placeVase(i * 50, j * 50);

        Game.getInstance().getEntityController().placeVase(62 * 50, 57 * 50);
        Game.getInstance().getEntityController().placeVase(66 * 50, 57 * 50);

        Game.getInstance().getEntityController().placeGate(50 * 48, 50 * 10);

        Game.getInstance().getEntityController().placeTend(50 * 5, 50 * 55);
        Game.getInstance().getEntityController().placeTend(50 * 2, 50 * 93);
        Game.getInstance().getEntityController().placeTend(50 * 76, 50 * 33);
        Game.getInstance().getEntityController().placeTend(50 * 55, 50 * 73);
        Game.getInstance().getEntityController().placeTend(50 * 60, 50 * 69);
        Game.getInstance().getEntityController().placeTend(50 * 63, 50 * 55);
        Game.getInstance().getEntityController().placeTend(50 * 32, 50 * 21);
        Game.getInstance().getEntityController().placeTend(50 * 34, 50 * 15);
        Game.getInstance().getEntityController().placeTend(50 * 41, 50 * 16);
        Game.getInstance().getEntityController().placeTend(50 * 41, 50 * 21);
        Game.getInstance().getEntityController().placeTend(50 * 47, 50 * 21);
        Game.getInstance().getEntityController().placeTend(50 * 47, 50 * 21);
        Game.getInstance().getEntityController().placeTend(50 * 53, 50 * 13);
        Game.getInstance().getEntityController().placeTend(50 * 60, 50 * 15);
        Game.getInstance().getEntityController().placeTend(50 * 63, 50 * 20);

        Game.getInstance().getEntityController().placePalm(46 * 50, 2 * 50);
        Game.getInstance().getEntityController().placePalm(50 * 90, 50 * 8);
        Game.getInstance().getEntityController().placePalm(50 * 72, 50 * 2);
        Game.getInstance().getEntityController().placePalm(50 * 66, 50 * 9);
        Game.getInstance().getEntityController().placePalm(50 * 34, 50 * 8);
        Game.getInstance().getEntityController().placePalm(50 * 8, 50 * 2);

        Game.getInstance().getEntityController().placePalm(50 * 3, 50 * 5);
        Game.getInstance().getEntityController().placePalm(50 * 4, 50 * 7);

        //secret
        Game.getInstance().getEntityController().spawnArabinWarrior(50 * 3, 50 * 7);
        Game.getInstance().getEntityController().placeHookah(50 * 2, 50 * 7);

        //finish
        Game.getInstance().getEntityController().placeHookah(50 * (-157), 50 * ( -154));
        Game.getInstance().getEntityController().placeHookah(50 * (-157), 50 * ( -153));
        Game.getInstance().getEntityController().placeWoman(50 * (-153), 50 * ( -155));
    }
    private void generEnemies(){
        /*
            Arabin warrior
        */
        Game.getInstance().getEntityController().spawnArabinWarrior(87 * 50, 8 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(86 * 50, 3 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(85 * 50, 6 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(82 * 50, 2 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(79 * 50, 6 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(76 * 50, 2 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(70 * 50, 8 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(64 * 50, 2 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(59 * 50, 7 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(55 * 50, 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(52 * 50, 8 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(43 * 50, 6 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(42 * 50, 3 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(37 * 50, 6 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(33 * 50, 3 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(64 * 50, 16 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(60 * 50, 22 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(57 * 50, 15 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(51 * 50, 23 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(51 * 50, 13 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(47 * 50, 11 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(46 * 50, 22 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(43 * 50, 25 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(36 * 50, 19 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(31 * 50, 25 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(7 * 50, 34 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(4 * 50, 44 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(6 * 50, 52 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(21 * 50, 54 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(23 * 50, 44 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(28 * 50, 47 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(30 * 50, 55 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(35 * 50, 50 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(38 * 50, 43 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(43 * 50, 52 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(46 * 50, 43 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(48 * 50, 48 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(60 * 50, 54 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(65 * 50, 46 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(67 * 50, 51 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(56 * 50, 65 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(51 * 50, 65 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(38 * 50, 72 * 50);
        Game.getInstance().getEntityController().spawnArabinWarrior(37 * 50, 71 * 50);
        for(int i = 83; i < 86; i++)
            for(int j = 28; j < 32; j++)
                Game.getInstance().getEntityController().spawnArabinWarrior(i * 50, j * 50);
        for(int i = 39; i < 45; i++)
            for(int j = 30; j < 33; j++)
                Game.getInstance().getEntityController().spawnArabinWarrior(i * 50, j * 50);
        for(int i = 69; i < 77; i++)
            for(int j = 86; j < 95; j++)
                Game.getInstance().getEntityController().spawnArabinWarrior(i * 50, j * 50);
        for(int i = 83; i < 85; i++)
            for(int j = 45; j < 47; j++)
                Game.getInstance().getEntityController().spawnArabinWarrior(i * 50, j * 50);
        for(int i = 44; i < 46; i++)
            for(int j = 62; j < 66; j++)
                Game.getInstance().getEntityController().spawnArabinWarrior(i * 50, j * 50);
        /*
            Train
        */

        Game.getInstance().getEntityController().spawnTrain(55 * 50, 30 * 50);
        Game.getInstance().getEntityController().spawnTrain(55 * 50, 34 * 50);
        Game.getInstance().getEntityController().spawnTrain(60 * 50,49 * 50);
        Game.getInstance().getEntityController().spawnTrain(54 * 50,45 * 50);
        Game.getInstance().getEntityController().spawnTrain(49 * 50,41 * 50);
        Game.getInstance().getEntityController().spawnTrain(27 * 50,64 * 50);
        Game.getInstance().getEntityController().spawnTrain(23 * 50,66 * 50);
        Game.getInstance().getEntityController().spawnTrain(19 * 50,70 * 50);
        Game.getInstance().getEntityController().spawnTrain(83 * 50,57 * 50);
        for(int i = 61; i < 65; i+=2)
            for(int j = 87; j < 91; j+=2)
                Game.getInstance().getEntityController().spawnTrain(i * 50, j * 50);
        /*
            Keanu
        */
        Game.getInstance().getEntityController().spawnKeanu(60 * 50, 59 * 50);
        Game.getInstance().getEntityController().spawnKeanu(42 * 50, 59 * 50);
        Game.getInstance().getEntityController().spawnKeanu(11 * 50, 52 * 50);
        Game.getInstance().getEntityController().spawnKeanu(11 * 50, 44 * 50);
        Game.getInstance().getEntityController().spawnKeanu(80 * 50, 83 * 50);
        Game.getInstance().getEntityController().spawnKeanu(82 * 50, 74 * 50);
        Game.getInstance().getEntityController().spawnKeanu(79 * 50, 42 * 50);
        for(int i = 46; i < 50; i+=2)
            for(int j = 88; j < 92; j+= 2)
                Game.getInstance().getEntityController().spawnKeanu(i * 50, j * 50);
        /*
            Plane
        */
        Game.getInstance().getEntityController().spawnPlane(84 * 50, 66 * 50);
        Game.getInstance().getEntityController().spawnPlane(54 * 50, 61 * 50);
        Game.getInstance().getEntityController().spawnPlane(49 * 50, 61 * 50);
        Game.getInstance().getEntityController().spawnPlane(4 * 50, 28 * 50);
        Game.getInstance().getEntityController().spawnPlane(10 * 50, 18 * 50);
        Game.getInstance().getEntityController().spawnPlane(13 * 50, 24 * 50);
        Game.getInstance().getEntityController().spawnPlane(18 * 50, 19 * 50);
        Game.getInstance().getEntityController().spawnPlane(22 * 50, 24 * 50);

        for(int i = 53; i < 57; i+=2)
            for(int j = 92; j < 96; j+= 2)
                Game.getInstance().getEntityController().spawnPlane(i * 50, j * 50);
        /*
            Boss
        */
        Game.getInstance().getEntityController().spawnBoss(5 * 50, 65 * 50);
    }



    public void placeFloor(int x, int y, int type){
        array.add(new ScreenObject(new Entity(x*50, y*50, 50, 50, Depth.FLOOR),
                Game.getInstance().t.getTexture("floor", "floor" + type), 50));
    }
    public void placeWall(int x, int y){
        Wall entity = new Wall(x*50, y*50, 50, 50);
        map.addEntity(entity);
        array.add(new WallScreenObject(entity, Game.getInstance().t.getTextures("walls", "wall2")));
    }
    public void placeFloors(int x0, int y0, int x1, int y1, int type){
        for(int i = x0; i < x1; i++)
            for(int j = y0; j < y1; j++)
                placeFloor(i, j, type);
    }

    public void placeWall(int x0, int y0, int x1, int y1){
        Wall entity = new Wall(x0*50, y0*50, 50 * (x1 - x0), 50 * (y1 - y0));
        map.addEntity(entity);

        for(int x = x0; x < x1; x++)
            for (int y = y0; y < y1; y++){
                array.add(new WallScreenObject(entity, 50 * x, 50 * y, Game.getInstance().t.getTextures("walls", "wall2")));
            }
    }

    public void placeInvisibleWall(int x0, int y0, int x1, int y1) {
        InvisibleWall entity = new InvisibleWall(x0*50, y0*50, 50 * (x1 - x0), 50 * (y1 - y0));
        map.addEntity(entity);
    }

    public void placeVase(int x, int y){
        Vase entity = new Vase(x, y);
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("things", "breakableThing1"), 50));
    }
    public void placeTend(int x, int y){
        Tent entity = new Tent(x, y);
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("things", "breakableThing" + MathUtils.random(2, 3)), 150));
    }
    public void placeGate(int x, int y){
        Gate entity = new Gate(x, y);
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("things", "breakableThing" + MathUtils.random(4, 5)), 0));
    }
    public void placeHookah(int x, int y) {
        Hookah entity = new Hookah(x, y);
        map.addEntity(entity);
        array.add(new ScreenObject(entity,
                Game.getInstance().t.getTexture("things", "unbreakableThing2"), 0));
    }
    public void placePalm(int x, int y) {
        Palm entity = new Palm(x, y);
        map.addEntity(entity);
        array.add(new ScreenObject(entity,
                Game.getInstance().t.getTexture("things", "unbreakableThing3"), 120));
    }

    public void placeHome(int x, int y) {
        Home entity = new Home(x, y);
        map.addEntity(entity);
        array.add(new ScreenObject(entity,
                Game.getInstance().t.getTexture("things", "unbreakableThing5"), 200));
    }

    public void placeWoman(int x, int y) {
        Woman entity = new Woman(x, y);
        map.addEntity(entity);
        array.add(new ScreenObject(entity,
                Game.getInstance().t.getTexture("things", "unbreakableThing4"), 150));
    }
    public void placeNotPassablePalm(int x, int y) {
        Palm entity = new Palm(x + MathUtils.random(-25, 25), y + MathUtils.random(-25, 25), true);
        map.addEntity(entity);
        array.add(new ScreenObject(entity,
                Game.getInstance().t.getTexture("things", "unbreakableThing3"), 120));
    }

    public void spawnArabinWarrior(int x, int y) {
        HumanEnemy entity = new HumanEnemy(x, y, 15, 300, Game.getInstance().player.getRectangle(), Game.getInstance().getMap());
        map.addBreakableEntity(entity);
        array.add(new HumanScreenObject(entity,
                Game.getInstance().t.getTextures("player", "body" + MathUtils.random(2, 6)),
                Game.getInstance().t.getTextures("player", "legs" + MathUtils.random(1, 6))));
    }
    public void spawnBoss(int x, int y) {
        Enemy entity = new Enemy(x, y,75, 75, 100, 100, Game.getInstance().player.getRectangle(), Game.getInstance().getMap());
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("enemy", "enemy1"), 84));
    }
    public void spawnTramp(int x, int y) {
        Enemy entity = new Tramp(x, y, Game.getInstance().player.getRectangle(), Game.getInstance().getMap());
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("enemy", "enemy5"), 250));
    }
    public void spawnTrain(int x, int y) {
        Enemy entity = new Enemy(x, y,75,75,10,200, Game.getInstance().player.getRectangle(), Game.getInstance().getMap());
        entity.setRadius(1000);
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("enemy", "enemy4"), 75));
    }
    public void spawnPlane(int x, int y) {
        Enemy entity = new Enemy(x, y, 75,75, 10,100, Game.getInstance().player.getRectangle(), Game.getInstance().getMap());
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("enemy", "enemy3"), 150));
    }
    public void spawnKeanu(int x, int y) {
        Enemy entity = new Enemy(x, y,75,75, 50,100, Game.getInstance().player.getRectangle(), Game.getInstance().getMap());
        map.addBreakableEntity(entity);
        array.add(new BreakableScreenObject(entity,
                Game.getInstance().t.getTextures("enemy", "enemy2"), 112));
    }
}
