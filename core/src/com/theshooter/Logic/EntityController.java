package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.*;
import com.theshooter.Logic.Entity.ConditionEntities.ConditionEntity;
import com.theshooter.Logic.Entity.ConditionEntities.Gate;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Creatures.HumanEntity;
import com.theshooter.Logic.Entity.Creatures.Player;
import com.theshooter.Logic.Entity.Creatures.Tramp;
import com.theshooter.Logic.Entity.LiftableEntities.*;
import com.theshooter.Logic.Entity.Weapon.Dagger;
import com.theshooter.Logic.Entity.Weapon.ThrowingKnife;
import com.theshooter.Logic.Entity.Weapon.WeaponType;
import com.theshooter.Logic.Event.Event;
import com.theshooter.Logic.Event.Place;
import com.theshooter.Screen.*;
import com.theshooter.Screen.ScreenObjects.*;

import java.util.*;

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
        Game.getInstance().getEventController().clear();

        loadPlayer(name);
        loadFloors(name);
        loadWalls(name);
        loadEnvironment(name);
        loadEnemies(name);
        loadEvents(name);
    }

    public void loadEvents(String name){
        Scanner scanner = getScanner(name, "events");
        while (scanner.hasNext()){
            String head = scanner.next();
            if (head.equals("event")){
                Event event = new Event();
                scanner.next();
                while (true){
                    String flag;
                    boolean value;

                    flag = scanner.next();

                    if (flag.equals("]")) break;

                    value = scanner.nextBoolean();

                    event.addFlag(flag, value);
                }
                scanner.next();
                while (true){
                    String command;

                    command = scanner.next();
                    if (command.equals("}")) break;
                    if (command.equals("sout")){
                        String text = scanner.nextLine();
                        Array<Object> params = new Array<>();
                        params.add(command);
                        params.add(text);
                        event.addCommand(params);
                    }
                    if (command.equals("tp")){
                        Array<Object> params = new Array<>();
                        params.add(command);
                        params.add(scanner.nextInt());
                        params.add(scanner.nextInt());
                        event.addCommand(params);
                    }
                    if (command.equals("set")){
                        Array<Object> params = new Array<>();
                        params.add(command);
                        params.add(scanner.next());
                        params.add(scanner.nextBoolean());
                        event.addCommand(params);
                    }
                    if (command.equals("spawn")){
                        Array<Object> params = new Array<>();
                        params.add(command + scanner.next());
                        params.add(scanner.nextInt());
                        params.add(scanner.nextInt());
                        event.addCommand(params);
                    }
                    if (command.equals("place")){
                        Array<Object> params = new Array<>();
                        params.add(command + scanner.next());
                        params.add(scanner.nextInt());
                        params.add(scanner.nextInt());
                        event.addCommand(params);
                    }
                    if (command.equals("music")){
                        Array<Object> params = new Array<>();
                        params.add(command);
                        params.add(scanner.next());
                        params.add(scanner.nextFloat());
                        event.addCommand(params);
                    }

                }

                Game.getInstance().getEventController().addEvent(event);
            }
            if (head.equals("place")){
                scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int r = scanner.nextInt();
                String flag = scanner.next();
                boolean value = scanner.nextBoolean();
                scanner.next();

                Place place = new Place(x, y, r, flag, value);
                Game.getInstance().getEventController().addPlace(place);
            }
        }
    }

    public void loadPlayer(String name){
        Scanner scanner = getScanner(name, "player");

        player = new Player(scanner.nextInt(), scanner.nextInt(), 25, 25);
        map.addEntity(player);
        Game.getInstance().gameScreen.playerScreen = new HumanScreenObject(player,
                Game.getInstance().getTextureController().getBody("player", "body1"),
                Game.getInstance().getTextureController().getAnimations("player", "legs1"));
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
        String flag;
        boolean value;
        int x, y, x1, y1;

        while(scanner.hasNext()){
            command = scanner.next();

            if(command.equals("end"))
                break;
            if(command.equals("placePalms")) {
                x = 50 * scanner.nextInt();
                y = 50 * scanner.nextInt();
                x1 = 50 * scanner.nextInt();
                y1 = 50 * scanner.nextInt();
                placePalms(x, y, x1, y1);
            }else if(command.equals("placeKey")) {
                x = 50 * scanner.nextInt();
                y = 50 * scanner.nextInt();
                flag = scanner.next();
                value =  scanner.nextBoolean();
                placeKey(x, y, flag, value);
            }else if(command.equals("placeConditionGate")) {
                x = 50 * scanner.nextInt();
                y = 50 * scanner.nextInt();
                flag = scanner.next();
                value =  scanner.nextBoolean();
                placeConditionGate(x, y, flag, value);
            }else{
                x = scanner.nextInt();
                y = scanner.nextInt();
                place(command, x, y);
            }
        }
        scanner.close();
    }

    public void loadEnemies(String name){
        Scanner scanner = getScanner(name, "enemies");

        String command;
        int x, y, x1, y1;

        while(scanner.hasNext()){
            command = scanner.next();

            if(command.equals("end"))
                break;

            x = scanner.nextInt();
            y = scanner.nextInt();

            spawn(command, x, y);
        }
        scanner.close();
    }


    public void spawn(String command, int x, int y){
        x *= 50;
        y *= 50;
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
        if(command.equals("spawnKnifeJuggler"))
            spawnKnifeJuggler(x, y);
    }

    public void place(String command, int x, int y){
        x *= 50;
        y *= 50;
        if(command.equals("placeHome"))
            placeHome(x, y);
        if(command.equals("placeVase"))
            placeVase(x, y);
        if(command.equals("placeBigHome"))
            placeBigHome(x, y);
        if(command.equals("placeTend"))
            placeTend(x, y);
        if(command.equals("placePalm"))
            placePalm(x, y);
        if(command.equals("placeHookah"))
            placeHookah(x, y);
        if(command.equals("placeWoman"))
            placeWoman(x, y);
        if(command.equals("placeHeal"))
            placeHeal(x, y);
        if(command.equals("placeCoverAirplane"))
            placeCoverAirplane(x, y);
        if(command.equals("placeWeaponUpgrade"))
            placeWeaponUpgrade(x, y);
        if(command.equals("placeGate"))
            placeGate(x, y);
        if(command.equals("placePassablePalm"))
            placePassablePalm(x, y);
    }

    public void addBullet(Projectile projectile){
        map.addEntity(projectile);
        screenObjectArray.add(new BulletScreenObject(projectile, Game.getInstance().getTextureController().getTexture("projectiles", "projectile" + MathUtils.random(1, 5)), 5));
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
        BreakableEntity entity = new BreakableEntity(x, y, 50, 50, Depth.THINGS);
        map.addEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("things", "breakableThing1"), 50));
    }
    public void placeTend(int x, int y){
        BreakableEntity entity = new BreakableEntity(x, y, 150, 150, Depth.THINGS, false);
        map.addEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("things", "breakableThing" + MathUtils.random(2, 3)), 150));
    }
    public void placeGate(int x, int y){
        BreakableEntity entity = new BreakableEntity(x, y, 200, 50, 300, Depth.WALLS, false);
        map.addEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("things", "breakableThing" + MathUtils.random(4, 5)), 0));
    }
    public void placeConditionGate(int x, int y, String flag, boolean value){
        ConditionEntity entity = new Gate(x, y, flag, value);
        map.addEntity(entity);
        screenObjectArray.add(new ConditionScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("things", "breakableThing6"), 0));
    }
    public void placeWeaponUpgrade(int x, int y) {
        LiftableEntity entity = new WeaponUpgrade(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing2"), 50));
    }
    public void placeHookah(int x, int y) {
        LiftableEntity entity = new Hookah(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing2"), 50));
    }
    public void placeHeal(int x, int y) {
        LiftableEntity entity = new Heal(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing9"), 25));
    }
    public void placeKey(int x, int y, String flag, boolean value) {
        LiftableEntity entity = new Key(x, y, flag, value);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing10"), 25));
    }
    public void placeCoverAirplane(int x, int y) {
        LiftableEntity entity = new CoverAirplane(x, y);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing9"), 25));
    }
    public void placePalm(int x, int y) {
        Entity entity = new Entity(x, y, 30, 30, Depth.THINGS, false);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing3"), 120));
    }
    public void placeHome(int x, int y) {
        Entity entity = new Entity(x, y, 200, 200, Depth.THINGS, false);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing5"), 213));
    }
    public void placeBigHome(int x, int y) {
        Entity entity = new Entity(x , y , 8 * 50 , 10 * 50, Depth.WALLS, false);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing8"), 514));
    }
    public void placeWoman(int x, int y) {
        Entity entity = new Entity(x, y, 50, 150, Depth.THINGS, false);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing4"), 150));
    }
    public void placePassablePalm(int x, int y) {
        Entity entity = new Entity(x + MathUtils.random(-25, 25), y + MathUtils.random(-25, 25), 30, 30, Depth.THINGS, true);
        map.addEntity(entity);
        screenObjectArray.add(new ScreenObject(entity,
                Game.getInstance().getTextureController().getTexture("things", "unbreakableThing3"), 120));
    }

    public void spawnArabinWarrior(int x, int y) {
        HumanEntity entity = new HumanEntity(x, y, 30, 30, 15, 300, 10,Depth.ENEMY, false, player.getRectangle());
        entity.addWeapon(new Dagger(entity));
        entity.selectWeapon(1);
        map.addEntity(entity);
        screenObjectArray.add(new HumanScreenObject(entity,
                        Game.getInstance().getTextureController().getBody("player", "body2"),
                        Game.getInstance().getTextureController().getAnimations("player", "legs2")));
    }
    public void spawnKnifeJuggler(int x, int y) {
        HumanEntity entity = new HumanEntity(x, y, 30, 30, 15, 300, 10,Depth.ENEMY, false, player.getRectangle());
        entity.addWeapon(new ThrowingKnife(entity));
        entity.selectWeapon(1);
        entity.addAmmo(WeaponType.THROWING_KNIFE, 50000);
        map.addEntity(entity);
        screenObjectArray.add(new HumanScreenObject(entity,
                Game.getInstance().getTextureController().getBody("player", "body3"),
                Game.getInstance().getTextureController().getAnimations("player", "legs3")));
    }
    public void spawnBoss(int x, int y) {
        CreatureEntity entity = new CreatureEntity(x, y,75, 75, 100, 100, 6, Depth.ENEMY, false,  player.getRectangle());
        map.addEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy1"), 84));
    }
    public void spawnTramp(int x, int y) {
        Tramp entity = new Tramp(x, y, player.getRectangle());
        map.addEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy5"), 250));
    }
    public void spawnTrain(int x, int y) {
        CreatureEntity entity = new CreatureEntity(x, y,75,75,10,200, 100, Depth.ENEMY, false, player.getRectangle());
        map.addEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy4"), 75));
    }
    public void spawnPlane(int x, int y) {
        CreatureEntity entity = new CreatureEntity(x, y, 75,75, 10,100, 5, Depth.ENEMY, false, player.getRectangle());
        map.addEntity(entity);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy3"), 150));
    }
    public void spawnKeanu(int x, int y) {
        CreatureEntity entity = new CreatureEntity(x, y,75,75, 50,100, 5, Depth.ENEMY, false,  player.getRectangle());
        map.addEntity(entity);
        entity.addWeapon(new Dagger(entity));
        entity.selectWeapon(1);
        screenObjectArray.add(new BreakableScreenObject(entity,
                Game.getInstance().getTextureController().getTextures("enemy", "enemy2"), 112));
    }

    public void placePalms(int x, int y, int x1, int y1){
        for(int i = x; i < x1; i+= 50){
            for(int j = y; j < y1; j+= 50){
                placePassablePalm(i,j);
            }
        }
    }

    private Scanner getScanner(String name, String type){
        String path = "levels/" + name + "/" + type + ".txt";
        return new Scanner(Gdx.files.internal(path).read());
    }
}
