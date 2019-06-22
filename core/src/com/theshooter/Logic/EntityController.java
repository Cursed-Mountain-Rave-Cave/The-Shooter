package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.*;
import com.theshooter.Screen.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class EntityController {

    private Player player;
    private Map map;
    private ScreenObjectArray screenObjectArray;

    public EntityController(){
        this.map = new Map();
        this.screenObjectArray = new ScreenObjectArray();
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public ScreenObjectArray getScreenObjectArray() {
        return screenObjectArray;
    }

    public void update(){
        map.update();
    }

    public void load(String name){
        map.clear();
        screenObjectArray.clear();

        loadPlayer(name);
        loadFloors(name);
        loadWalls(name);
        loadEnvironment(name);
        loadEnemies(name);
    }

    public void loadPlayer(String name){
        Scanner scanner = getScanner(name, "player");

        player = new Player(scanner.nextInt(), scanner.nextInt(), 25, 25, map);
        map.addBreakableEntity(player);
        Game.getInstance().gameScreen.playerScreen = new HumanScreenObject(player, Game.getInstance().getTextureController().getTextures("player", "body7"),
                Game.getInstance().getTextureController().getTextures("player", "legs7"));
                        screenObjectArray.add(Game.getInstance().gameScreen.playerScreen);

        scanner.close();
    }

    public void loadFloors(String name){
        Scanner scanner = getScanner(name, "floor");

        java.util.Map<Character, Integer> table = new HashMap<>();
        String command = new String();
        int x = 0, y = 0,  type = 0;
        boolean inputTable = true;

        while(scanner.hasNext()){
            if(inputTable){
                command = scanner.next();
                if(command.equals("map")){
                    inputTable = false;
                    scanner.nextLine();
                    continue;
                }
                type = scanner.nextInt();
                table.put(command.charAt(0), type);
            }else{
                command = scanner.nextLine();
                for (char c: command.toCharArray()){
                    placeFloor(x, y, table.get(c));
                    y++;
                }
                y = 0;
                x++;
            }
        }
        scanner.close();
    }

    public void loadWalls(String name){
        Scanner scanner = getScanner(name, "walls");

        String command;
        int x0, y0;
        int x1, y1;

        while(scanner.hasNext()){
            command = scanner.next();

            if(command.equals("end"))
                break;

            x0 = scanner.nextInt();
            y0 = scanner.nextInt();
            x1 = scanner.nextInt();
            y1 = scanner.nextInt();

            if(command.equals("placeWall"))
                placeWall(x0, y0, x1, y1);
            if(command.equals("placeInvisibleWall"))
                placeInvisibleWall(x0, y0, x1, y1);
        }
    }

    public void loadEnvironment(String name){
        Scanner scanner = getScanner(name, "environment");

        String command;
        int x, y;

        while(scanner.hasNext()){
            command = scanner.next();

            if(command.equals("end"))
                break;

            x = 50 * scanner.nextInt();
            y = 50 * scanner.nextInt();


            if(command.equals("placeHome"))
                placeHome(x, y);
            if(command.equals("placeVase"))
                placeVase(x, y);
            if(command.equals("placeTend"))
                placeTend(x, y);
            if(command.equals("placePalm"))
                placePalm(x, y);
            if(command.equals("placeHookah"))
                placeHookah(x, y);
            if(command.equals("placeWoman"))
                placeWoman(x, y);
            if(command.equals("placeGate"))
                placeGate(x, y);
            if(command.equals("placeNotPassablePalm"))
                placeNotPassablePalm(x, y);
        }
        scanner.close();
    }

    public void loadEnemies(String name){
        Scanner scanner = getScanner(name, "enemies");

        String command;
        int x, y;

        while(scanner.hasNext()){
            command = scanner.next();

            if(command.equals("end"))
                break;

            x = 50 * scanner.nextInt();
            y = 50 * scanner.nextInt();

            if(command.equals("spawnPlane"))
                spawnPlane(x, y);
            if(command.equals("spawnKeanu"))
                spawnKeanu(x, y);
            if(command.equals("spawnTrain"))
                spawnTrain(x, y);
            if(command.equals("spawnArabinWarrior"))
                spawnArabinWarrior(x, y);
            if(command.equals("spawnBoss"))
                spawnBoss(x, y);
            if(command.equals("spawnTramp"))
                spawnTramp(x, y);
        }
        scanner.close();
    }

    public void addBullet(Bullet bullet){
        screenObjectArray.add(new BulletScreenObject(bullet, Game.getInstance().getTextureController().getTexture("bullets", "bullet" + MathUtils.random(1, 5)), 5));
    }

    public void placeFloor(int x, int y, int type){
        screenObjectArray.add(new ScreenObject(new Entity(x*50, y*50, 50, 50, Depth.FLOOR),
                Game.getInstance().getTextureController().getTexture("floor", "floor" + type), 50));
    }

    public void placeWall(int x, int y){
        Wall entity = new Wall(x*50, y*50, 50, 50);
        map.addEntity(entity);
        screenObjectArray.add(new WallScreenObject(entity, Game.getInstance().getTextureController().getTextures("walls", "wall2")));
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
            for (int y = y0; y < y1; y++)
                screenObjectArray.add(new WallScreenObject(entity, 50 * x, 50 * y, Game.getInstance().getTextureController().getTextures("walls", "wall2")));
    }

    public void placeInvisibleWall(int x0, int y0, int x1, int y1) {
        InvisibleWall entity = new InvisibleWall(x0*50, y0*50, 50 * (x1 - x0), 50 * (y1 - y0));
        map.addEntity(entity);
    }

    public void placeVase(int x, int y){
        Vase entity = new Vase(x, y);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("things", "breakableThing1"), 50));
    }
    public void placeTend(int x, int y){
        Tent entity = new Tent(x, y);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("things", "breakableThing" + MathUtils.random(2, 3)), 150));
    }
    public void placeGate(int x, int y){
        Gate entity = new Gate(x, y);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("things", "breakableThing" + MathUtils.random(4, 5)), 0));
    }
    public void placeHookah(int x, int y) {
        Hookah entity = new Hookah(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing2"), 0));
    }
    public void placePalm(int x, int y) {
        Palm entity = new Palm(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing3"), 120));
    }

    public void placeHome(int x, int y) {
        Home entity = new Home(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing5"), 200));
    }

    public void placeWoman(int x, int y) {
        Woman entity = new Woman(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing4"), 150));
    }
    public void placeNotPassablePalm(int x, int y) {
        Palm entity = new Palm(x + MathUtils.random(-25, 25), y + MathUtils.random(-25, 25), true);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing3"), 120));
    }

    public void spawnArabinWarrior(int x, int y) {
        HumanEnemy entity = new HumanEnemy(x, y, 15, 300, player.getRectangle(), map);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new HumanScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("player", "body" + MathUtils.random(2, 6)),
                Game.getInstance().getTextureController().getTextures("player", "legs" + MathUtils.random(1, 6))));
    }
    public void spawnBoss(int x, int y) {
        Enemy entity = new Enemy(x, y,75, 75, 100, 100, player.getRectangle(), map);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy1"), 84));
    }
    public void spawnTramp(int x, int y) {
        Enemy entity = new Tramp(x, y, player.getRectangle(), map);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy5"), 250));
    }
    public void spawnTrain(int x, int y) {
        Enemy entity = new Enemy(x, y,75,75,10,200, player.getRectangle(), map);
        entity.setRadius(1000);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy4"), 75));
    }
    public void spawnPlane(int x, int y) {
        Enemy entity = new Enemy(x, y, 75,75, 10,100, player.getRectangle(), map);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy3"), 150));
    }
    public void spawnKeanu(int x, int y) {
        Enemy entity = new Enemy(x, y,75,75, 50,100, player.getRectangle(), map);
        map.addBreakableEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy2"), 112));
    }

    private Scanner getScanner(String name, String type){
        String path = "levels/" + name + "/" + type + ".txt";
        
        return new Scanner(Gdx.files.internal(path).read());
    }
}