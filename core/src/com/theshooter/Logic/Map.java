package com.theshooter.Logic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.*;
import com.theshooter.Logic.Entity.Abstract.IBreakable;
import com.theshooter.Logic.Entity.Abstract.IEntity;
import com.theshooter.Logic.Entity.Abstract.IMovable;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Creatures.Player;

public class Map {
    private Array<IEntity> entities;
    private Array<IEntity> notPassableEntities;
    private Array<Projectile> projectiles;
    private Array<IBreakable> breakableEntities;
    private Array<IMovable> movableEntities;

    private Array<Projectile> projectilesDelete;

    private Array<IEntity> entitiesDelete;
    private Array<IBreakable> enemies;
    private Array<IBreakable> players;

    public Map(){
        entities = new Array<>();
        notPassableEntities = new Array<>();
        projectiles = new Array<>();
        projectilesDelete = new Array<>();
        breakableEntities = new Array<>();
        entitiesDelete = new Array<>();
        enemies = new Array<>();
        players = new Array<>();
        movableEntities = new Array<>();
    }

    public void update(){
        for(IEntity entity: entities) {
            entity.update();
            if (Math.abs(entity.getX()) + Math.abs( entity.getY()) > 20000) {
                if(entity instanceof Projectile)
                    projectilesDelete.add((Projectile) entity);
                entitiesDelete.add(entity);
                entity.delete();
            }
        }

        entities.removeAll(entitiesDelete,true);
        notPassableEntities.removeAll(entitiesDelete,true);
        projectiles.removeAll(projectilesDelete,true);
        entitiesDelete.clear();
        projectilesDelete.clear();

        for (IMovable movable: movableEntities)
            movable.move();

        for(Projectile projectile: projectiles){
            for(IBreakable breakable: breakableEntities){
                if(breakable == projectile.getDamage().getOwner())
                    continue;
                if(breakable.getRectangle().overlaps(projectile.getRectangle())){
                    breakable.breakDown(projectile.getDamage());
                    if(breakable.isBroken()){
                        notPassableEntities.removeValue(breakable, true);
                        breakableEntities.removeValue(breakable, true);
                    }
                    projectile.delete();
                    projectilesDelete.add(projectile);
                    break;
                }
            }

            if(projectile.isDeleted())
                continue;

            for(IEntity entity: notPassableEntities){
                if(entity == projectile.getDamage().getOwner())
                    continue;
                if(entity.getRectangle().overlaps(projectile.getRectangle())){
                    projectilesDelete.add(projectile);
                    projectile.delete();
                }
            }

        }

        entities.removeAll(projectilesDelete,true);
        projectiles.removeAll(projectilesDelete,true);
        entitiesDelete.clear();
        projectilesDelete.clear();
    }

    public void addEntity(IEntity entity){
        entities.add(entity);

        if(!entity.isPassable())
            notPassableEntities.add(entity);

        if (entity instanceof Player)
            players.add((Player)entity);
        else if(entity instanceof CreatureEntity)
            enemies.add((CreatureEntity) entity);

        if(entity instanceof Projectile)
            projectiles.add((Projectile) entity);

        if (entity instanceof IBreakable)
            breakableEntities.add((BreakableEntity) entity);

        if (entity instanceof IMovable)
            movableEntities.add((IMovable) entity);
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
        projectiles.clear();
        breakableEntities.clear();
        entitiesDelete.clear();
        enemies.clear();
        players.clear();
    }
}
