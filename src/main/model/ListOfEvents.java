package model;

import java.util.ArrayList;

// Represents a list of events
public class ListOfEvents extends ListOfCase{
    private ArrayList<Event> eventList;

    //EFFECTS: create a null event list
    public ListOfEvents(){
        this.eventList = new ArrayList<Event>(); //stub
    }

    // //REQUIRES: event input cannot be null
    // //MODIFIES: this
    // //EFFECTS: add a event into the Event list
    // public void addEvent(Event event){
    //     eventList.add(event);
    // }

    // //EFFECTS: return the event list in the class
    // public ArrayList getList(){
    //     return eventList;
    // }

    public int calculateImportant(){
        int count = 0;
        if (eventList.size() == 0){
            return count;
        }
        for (Event e : eventList){
            if(e.getImportance()){
                count++;
            }
        }
        return count;
    }
}
