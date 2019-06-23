package com.theshooter.Logic.Event;


import com.badlogic.gdx.utils.Array;

import java.util.Map;
import java.util.TreeMap;

public class Event {

    Map<String , Boolean> flags;
    Array<Array<Object>> commands;

    public Event(){
        flags = new TreeMap<>();
        commands = new Array<>();
    }

    public void addFlag(String flag, boolean value){
        flags.put(flag, value);
    }

    public void addCommand(Array<Object> command){
        commands.add(command);
    }

    public Map<String, Boolean> getFlags() {
        return flags;
    }

    public Array<Array<Object>> getCommands() {
        return commands;
    }
}
