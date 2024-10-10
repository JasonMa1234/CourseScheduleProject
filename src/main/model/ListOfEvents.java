package model;

import java.util.ArrayList;

// Represents a list of events
public class ListOfEvents implements Cases{
    private ArrayList<Event> EventList;

    //EFFECTS: create a null event list
    public void ListOfEvents(){
        this.EventList = new ArrayList<>(); //stub
    }

    //REQUIRES: event input cannot be null
    //MODIFIES: this
    //EFFECTS: add a event into the Event list
    private void addEvent(Event event){
        EventList.add(event);
    }

    @Override
    public int Calculate(){
        int count = 0;
        for (Event e : EventList){
            count++;
        }
        return count;
    }
}
