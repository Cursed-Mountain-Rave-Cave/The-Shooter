package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.*;

public class Map {

    private Array<IEntity> entities;
    private Array<IEntity> notPassableEntities;
    private Array<IEntity> bullets;
    private Array<IBreakableEntity> breakableEntities;
    private Array<IEntity> entitiesDelete;
    private Array<IBreakableEntity> enemies;
    private Array<IBreakableEntity> players;

    public Map(){
        entities = new Array<>();
        notPassableEntities = new Array<>();
        bullets = new Array<>();
        breakableEntities = new Array<>();
        entitiesDelete = new Array<>();
        enemies = new Array<>();
        players = new Array<>();

    }

    public void update(){
        /*
        System.out.println("=========================");
        System.out.println(Gdx.graphics.getFramesPerSecond() + "   " + entities.size);
        System.out.println(bullets.size + "   " + breakableEntities.size);
        */

        for(IEntity entity: entities) {
            entity.update();
            if (Math.abs(entity.getX()) + Math.abs( entity.getY()) > 15000) {
                entitiesDelete.add(entity);
                entity.delete();
            }
        }

        entities.removeAll(entitiesDelete,true);
        notPassableEntities.removeAll(entitiesDelete,true);
        bullets.removeAll(entitiesDelete,true);
        entitiesDelete.clear();

        for(IEntity bullet: bullets){
            for(IBreakableEntity breakable: breakableEntities){
                if(breakable.getRectangle().overlaps(bullet.getRectangle())){
                    breakable.breakDown();
                    if(breakable.isBroken()){
                        notPassableEntities.removeValue(breakable, true);
                        breakableEntities.removeValue(breakable, true);
                    }
                }
            }
            for(IEntity entity: notPassableEntities)
                if(entity.getRectangle().overlaps(bullet.getRectangle())){
                    entitiesDelete.add(bullet);
                    bullet.delete();
                }
        }

        for(IBreakableEntity enemy : enemies) {
            for(IBreakableEntity player : players) {
                int dx = player.getX() - enemy.getX();
                int dy = player.getY() - enemy.getY();
                if (Math.hypot(dx, dy) < 4 * 50)
                    player.breakDown();
            }
        }

        entities.removeAll(entitiesDelete,true);
        bullets.removeAll(entitiesDelete,true);
        entitiesDelete.clear();
    }

    public void addEntity(IEntity entity){
        if(!entity.isPassable())
            notPassableEntities.add(entity);
        entities.add(entity);
    }

    public void addBullet(IEntity entity){
        entities.add(entity);
        bullets.add(entity);
    }

    public void addBreakableEntity(IBreakableEntity entity){
        addEntity(entity);
        if(entity.getClass() == HumanEnemy.class || entity.getClass() == Enemy.class)
            enemies.add(entity);
        if(entity.getClass() == Player.class)
            players.add(entity);
        breakableEntities.add(entity);
    }

    public boolean isAllowed(Rectangle place){
        for(IEntity entity: notPassableEntities)
            if(place.overlaps(entity.getRectangle()) && entity.getRectangle() != place)
                return false;

        return true;
    }

    public Array<IEntity>          getEntities()            { return entities; }
    public Array<IEntity>          getNotPassableEntities() { return notPassableEntities; }
    public Array<IEntity>          getBullets()             { return bullets; }
    public Array<IBreakableEntity> getBreakableEntities()   { return breakableEntities; }
}
