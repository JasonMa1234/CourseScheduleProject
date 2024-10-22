package model;

import java.util.ArrayList;

// Represents a list of events
public class ListOfEvents extends ListOfCase{
    // private ArrayList<Event> eventList;

    //EFFECTS: create a null event list
    public ListOfEvents(){
        super();
        // this.eventList = new ArrayList<Event>(); //stub
    }

    // @Override
    // //REQUIRES: case cannot be null
    // //MODIFIES: this
    // //EFFECTS: add a case into the case list
    // public void addCase(CaseToDo newCase) {
    //     if (newCase instanceof Event) {
    //         eventList.add((Event) newCase); //from chatGPT
    //     }
    // }

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
        if (caseList.size() == 0){
            return count;
        }
        for (CaseToDo e : caseList){
            if (e instanceof Event) {
                Event event = (Event) e;  // From chatGPT
                if(event.getImportance()){
                    count++;
                }    
            }
        }
        return count;
    }
}
