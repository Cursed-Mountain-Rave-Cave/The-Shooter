package com.theshooter.Logic;

import com.badlogic.gdx.utils.Array;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Creatures.Player;
import com.theshooter.Logic.Event.Event;
import com.theshooter.Logic.Event.Place;

import java.util.Map;
import java.util.TreeMap;

public class EventController {

    Map<String, Boolean> flags;
    Array<Event> events;
    Array<Place> places;

    Array<Event> eventsToDelete;
    Array<Place> placesToDelete;



    public EventController(){
        flags = new TreeMap<>();
        events = new Array<>();
        places = new Array<>();

        eventsToDelete = new Array<>();
        placesToDelete = new Array<>();
    }

    public void clear(){
        flags.clear();
        events.clear();
        places.clear();
    }

    public void update(){
        addFlag("all_killed", Game.getInstance().getEntityController().getMap().getEnemiesCount() == 0);
        Player player = Game.getInstance().getEntityController().getPlayer();
        for (Place place: places){
            if (Math.hypot(player.getX() - place.getX(), player.getY() - place.getY()) < place.getR()){
                flags.put(place.getFlag(), place.getValue());
                placesToDelete.add(place);
            }
        }

        for (Event event: events){
            if(checkEvent(event)){
                eventsToDelete.add(event);
                for (Array<Object> command: event.getCommands())
                    executeCommand(command);
            }
        }

        places.removeAll(placesToDelete, true);
        events.removeAll(eventsToDelete, true);
        placesToDelete.clear();
        eventsToDelete.clear();
    }

    private boolean checkEvent(Event event){
        for (String flag: event.getFlags().keySet()){
            if(event.getFlags().get(flag) != flags.get(flag)){
                return false;
            }
        }
        return true;
    }

    private void executeCommand(Array<Object> command){
         if (command.get(0).equals("sout")){
             System.out.println(command.get(1));
         }
         if (command.get(0).equals("tp")){
             Game.getInstance().getEntityController().getPlayer().setX((int) command.get(1));
             Game.getInstance().getEntityController().getPlayer().setY((int) command.get(2));
         }
         if (command.get(0).equals("set")){
             addFlag((String) command.get(1), (Boolean) command.get(2));
         }
    }

    public void addFlag(String name, boolean value){
        flags.put(name, value);
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void addPlace(Place place){
        places.add(place);
    }
}