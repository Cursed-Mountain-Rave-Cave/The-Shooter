package com.theshooter.Logic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.BreakableEntity;
import com.theshooter.Logic.Entity.Entity;

public class Map {

    private Array<Entity> entities;
    private Array<Entity> notPassableEntities;
    private Array<Entity> bullets;
    private Array<BreakableEntity> breakableEntities;

    public Map(){
        entities = new Array<>();
        notPassableEntities = new Array<>();
        bullets = new Array<>();
        breakableEntities = new Array<>();
    }

    public void update(){
        for(Entity entity: entities)
            entity.update();

        for(Entity bullet: bullets){
            for(BreakableEntity breakable: breakableEntities){
                if(breakable.getRectangle().overlaps(bullet.getRectangle())){
                    breakable.breakDown();
                }
            }
        }
    }

    public void addEntity(Entity entity){
        if(!entity.isPassable())
            notPassableEntities.add(entity);
        entities.add(entity);
    }

    public void addBullet(Entity entity){
        entities.add(entity);
        bullets.add(entity);
    }

    public void addBreakableEntity(BreakableEntity entity){
        entities.add(entity);
        breakableEntities.add(entity);
    }

    public boolean isAllowed(Rectangle place){
        for(Entity entity: notPassableEntities)
            if(place.overlaps(entity.getRectangle()))
                return false;

        return true;
    }

}
