package model;

//Represents a Event user added
public class Event extends CaseToDo {
    private boolean isImportant;

    //EFFECTS: creates an event whose importance is initially not important
    public Event(
            String name, 
            int timeHoursBegin, 
            int timeMinutesBegin, 
            int timeHoursOver, 
            int timeMinutesOver, 
            String description,
            ListOfDate date,
            String place) {
        super(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, timeMinutesOver, description, date, place);
        this.isImportant = false;
    }

    //REQUIRES: importance must not be null
    //MODIFIES: this
    //EFFECTS: set the event to be importat or not
    public void setImportance(boolean importance){
        isImportant = importance;
    }

    //EFFECTS: return the importance of the event
    public boolean getImportance(){
        return isImportant;
    }
}
