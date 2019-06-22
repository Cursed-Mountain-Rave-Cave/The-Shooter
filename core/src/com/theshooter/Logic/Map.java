package com.theshooter.Logic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.*;

public class Map {
    private Array<IEntity> entities;
    private Array<IEntity> notPassableEntities;
    private Array<Projectile> bullets;
    private Array<Projectile> bulletsDelete;
    private Array<IBreakableEntity> breakableEntities;
    private Array<IEntity> entitiesDelete;
    private Array<IBreakableEntity> enemies;
    private Array<IBreakableEntity> players;

    public Map(){
        entities = new Array<>();
        notPassableEntities = new Array<>();
        bullets = new Array<>();
        bulletsDelete = new Array<>();
        breakableEntities = new Array<>();
        entitiesDelete = new Array<>();
        enemies = new Array<>();
        players = new Array<>();

    }

    public void update(){
        for(IEntity entity: entities) {
            entity.update();
            if (Math.abs(entity.getX()) + Math.abs( entity.getY()) > 20000) {
                if(entity instanceof Projectile)
                    bulletsDelete.add((Projectile) entity);
                entitiesDelete.add(entity);
                entity.delete();
            }
        }

        entities.removeAll(entitiesDelete,true);
        notPassableEntities.removeAll(entitiesDelete,true);
        bullets.removeAll(bulletsDelete,true);
        entitiesDelete.clear();
        bulletsDelete.clear();

        /*
        enemy demage
        for(IBreakableEntity enemy : enemies) {
            for(IBreakableEntity player : players) {
                if (enemy.isBroken()) {
                    enemies.removeValue(enemy, true);
                }
                int dx = player.getX() - enemy.getX();
                int dy = player.getY() - enemy.getY();
                if (Math.hypot(dx, dy) < 2 * 50) {
                    player.breakDown();
                }
            }
        }
         */
        for(Projectile bullet: bullets){
            for(IBreakableEntity breakable: breakableEntities){
                if(breakable == bullet.getDamage().getOwner())
                    continue;;
                if(breakable.getRectangle().overlaps(bullet.getRectangle())){
                    breakable.breakDown(bullet.getDamage());
                    if(breakable.isBroken()){
                        notPassableEntities.removeValue(breakable, true);
                        breakableEntities.removeValue(breakable, true);
                    }
                }
            }
            for(IEntity entity: notPassableEntities)
                if(entity.getRectangle().overlaps(bullet.getRectangle())){
                    bulletsDelete.add(bullet);
                    bullet.delete();
                }
        }

        entities.removeAll(entitiesDelete,true);
        bullets.removeAll(bulletsDelete,true);
        entitiesDelete.clear();
        bulletsDelete.clear();
    }

    public void addEntity(IEntity entity){
        if(!entity.isPassable())
            notPassableEntities.add(entity);
        entities.add(entity);
    }

    public void addBullet(Projectile entity){
        entities.add(entity);
        bullets.add(entity);
    }

    public void addBreakableEntity(IBreakableEntity entity){
        addEntity(entity);
        if(entity.getClass() == HumanEnemy.class || entity instanceof Enemy)
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

    public void clear(){
        entities.clear();
        notPassableEntities.clear();
        bullets.clear();
        breakableEntities.clear();
        entitiesDelete.clear();
        enemies.clear();
        players.clear();
    }
}
