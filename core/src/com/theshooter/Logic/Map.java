package com.theshooter.Logic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.IBreakableEntity;
import com.theshooter.Logic.Entity.IEntity;

public class Map {

    private Array<IEntity> entities;
    private Array<IEntity> notPassableEntities;
    private Array<IEntity> bullets;
    private Array<IBreakableEntity> breakableEntities;
    private Array<IEntity> entitiesDelete;

    public Map(){
        entities = new Array<>();
        notPassableEntities = new Array<>();
        bullets = new Array<>();
        breakableEntities = new Array<>();
        entitiesDelete = new Array<>();

    }

    public void update(){
        for(IEntity entity: entities) {
            entity.update();
            if (Math.hypot(entity.getX(), entity.getY()) > 1000000) {
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
                    removeFromNotPassable(breakable);
                }
            }
        }
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
    
    public void removeFromNotPassable(IEntity target) {
        for(IEntity entity: notPassableEntities)
            if(entity == target)
                notPassableEntities.removeValue(entity, true);
    }
}
