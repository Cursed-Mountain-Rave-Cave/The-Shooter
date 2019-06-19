package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.theshooter.Logic.Entity.BreakableEntity;
import com.theshooter.Logic.Entity.Bullet;
import com.theshooter.Logic.Entity.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Screen.Depth;
import com.theshooter.Screen.ScreenObject;
import com.theshooter.Screen.ScreenObjectArray;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameLoader {
    private Map gameMap;
    private File saveFile;
    private ScreenObjectArray screenObjectArray;

    private void findDepth(Depth depth, int depthInt) {
        switch (depthInt) {
            case 1:
                depth = Depth.FLOOR;
                break;
            case 2:
                depth = Depth.THINGS;
                break;
            case 3:
                depth = Depth.PLAYER;
                break;
            case 4:
                depth = Depth.EFFECTS;
                break;
            case 5:
                depth = Depth.ENEMY;
                break;
            case 6:
                depth = Depth.WALLS;
                break;
        }
    }

    private Entity readEntity(Scanner scanner)
    {
        Rectangle r = new Rectangle();
        r.x = scanner.nextInt();
        r.y = scanner.nextInt();
        r.width = scanner.nextInt();
        r.height = scanner.nextInt();
        int depthInt = scanner.nextInt();
        Depth depth = Depth.FLOOR;
        findDepth(depth, depthInt);
        int passable = scanner.nextInt();

        return new Entity(r, depth, passable == 1);
    }

    private String writeEntity(Entity entity) {
        Integer depthInt = entity.getDepth().ordinal();
        String entityInfo = entity.getRectangle().toString() + " " + depthInt.toString();
        if(entity.isPassable())
            entityInfo.concat(" 1 ");
        else
            entityInfo.concat(" 0 ");
        return entityInfo;
    }

    private String scanf(Scanner scanner) {
        StringBuffer s = new StringBuffer();
        char c;
        while(scanner.hasNext()) {
            c = (char) scanner.nextByte();
            if((c < 'a' || c > 'z') && (c < '0' || c > '9') && c != '.')
                break;
            s.append(c);
        }
        return  s.toString();
    }

    public GameLoader(Map m, ScreenObjectArray array, File saveFile) {
        gameMap = m;
        this.saveFile = new File(saveFile.getPath(), saveFile.getName());
        screenObjectArray = array;

        Array<Entity>          entities            = m.getEntities();
        Array<Entity>          notPassableEntities = m.getNotPassableEntities();
        Array<Entity>          bullets             = m.getBullets();
        Array<BreakableEntity> breakableEntities   = m.getBreakableEntities();

        try                   { saveFile.createNewFile(); }
        catch (IOException e) { System.out.println(e.getMessage()); }

        try (FileOutputStream fout = new FileOutputStream(saveFile)){
            String entityInfo;
            for(Entity e : entities) {
                entityInfo = "e " + writeEntity(e) + "\n";
                fout.write(entityInfo.getBytes());
            }
            for(Entity e : notPassableEntities) {
                entityInfo = "p " + writeEntity(e) + "\n";
                fout.write(entityInfo.getBytes());
            }
            for(Entity e : bullets) {
                entityInfo = "b " + writeEntity(e) + "\n";
                fout.write(entityInfo.getBytes());
            }
            for(BreakableEntity be : breakableEntities) {
                entityInfo = "d " + writeEntity(be);
                if(be.isBroken())
                    entityInfo.concat(" 1 ");
                else
                    entityInfo.concat(" 0 ");
                entityInfo.concat("\n");
                fout.write(entityInfo.getBytes());
            }
            for(ScreenObject s : array) {
                entityInfo = "s " + writeEntity(s.getEntity()) + s.getTexture().toString() + "\n";
                fout.write(entityInfo.getBytes());
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            Gdx.app.exit();
        }
    }

    public void load() {
        char whichArray;

        try (Scanner scanner = new Scanner(saveFile)) {
            while(scanner.hasNext()) {
                whichArray = (char) scanner.nextByte();
                switch (whichArray) {
                    case 'e':
                        Entity entity = readEntity(scanner);
                        gameMap.addEntity(entity);
                        break;
                    case 'p':
                         entity = readEntity(scanner);
                         gameMap.addEntity(entity);
                        break;
                    case 'b':
                        entity = readEntity(scanner);
                        gameMap.addBullet(entity);
                        break;
                    case 'd':
                        BreakableEntity breakableEntity = (BreakableEntity) readEntity(scanner);
                        int breakable = scanner.nextInt();
                        if(breakable == 1)
                            breakableEntity.breakDown();
                        gameMap.addBreakableEntitu(breakableEntity);
                        break;
                    case 's':
                        entity = readEntity(scanner);
                        String textureName = scanf(scanner);
                        Texture texture = new Texture(textureName);
                        ScreenObject screenObject = new ScreenObject(entity, texture);
                        screenObjectArray.add(screenObject);
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            Gdx.app.exit();
        }
    }
}
