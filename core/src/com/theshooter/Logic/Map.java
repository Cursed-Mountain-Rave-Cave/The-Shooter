package com.theshooter.Logic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.theshooter.Logic.Entity.*;
import com.theshooter.Logic.Entity.Abstract.IBreakable;
import com.theshooter.Logic.Entity.Abstract.IEntity;

public class Map {
    private Array<IEntity> entities;
    private Array<IEntity> notPassableEntities;
    private Array<Projectile> projectoles;
    private Array<Projectile> projectolesDelete;
    private Array<IBreakable> breakableEntities;
    private Array<IEntity> entitiesDelete;
    private Array<IBreakable> enemies;
    private Array<IBreakable> players;

    public Map(){
        entities = new Array<>();
        notPassableEntities = new Array<>();
        projectoles = new Array<>();
        projectolesDelete = new Array<>();
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
                    projectolesDelete.add((Projectile) entity);
                entitiesDelete.add(entity);
                entity.delete();
            }
        }

        entities.removeAll(entitiesDelete,true);
        notPassableEntities.removeAll(entitiesDelete,true);
        projectoles.removeAll(projectolesDelete,true);
        entitiesDelete.clear();
        projectolesDelete.clear();

        for(Projectile projectole: projectoles){
            for(IBreakable breakable: breakableEntities){
                if(breakable == projectole.getDamage().getOwner())
                    continue;
                if(breakable.getRectangle().overlaps(projectole.getRectangle())){
                    breakable.breakDown(projectole.getDamage());
                    if(breakable.isBroken()){
                        notPassableEntities.removeValue(breakable, true);
                        breakableEntities.removeValue(breakable, true);
                    }
                    projectole.delete();
                    projectolesDelete.add(projectole);
                    break;
                }
            }

            if(projectole.isDeleted())
                continue;

            for(IEntity entity: notPassableEntities){
                if(entity == projectole.getDamage().getOwner())
                    continue;
                if(entity.getRectangle().overlaps(projectole.getRectangle())){
                    projectolesDelete.add(projectole);
                    projectole.delete();
                }
            }

        }

        entities.removeAll(projectolesDelete,true);
        projectoles.removeAll(projectolesDelete,true);
        entitiesDelete.clear();
        projectolesDelete.clear();
    }

    public void addEntity(IEntity entity){
        if(!entity.isPassable())
            notPassableEntities.add(entity);
        entities.add(entity);
    }

    public void addBullet(Projectile entity){
        entities.add(entity);
        projectoles.add(entity);
    }

    public void addBreakableEntity(IBreakable entity){
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
        projectoles.clear();
        breakableEntities.clear();
        entitiesDelete.clear();
        enemies.clear();
        players.clear();
    }
}
