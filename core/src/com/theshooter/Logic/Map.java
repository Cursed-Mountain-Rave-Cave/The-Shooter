package com.theshooter.Logic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.Entity;
import com.theshooter.Logic.Entity.Player;

public class Map {

    private Array<Entity> entities;
    private Array<Entity> notPassableEntities;

    public Map(){
        entities = new Array<>();
        notPassableEntities = new Array<>();
    }

    public void addEntity(Entity entity){
        if(!entity.isPassable())
            notPassableEntities.add(entity);
        entities.add(entity);
    }

    public boolean isAllowed(Rectangle place){
        for(Entity entity: notPassableEntities)
            if(place.overlaps(entity.getRectangle()))
                return false;

        return true;
    }

}
