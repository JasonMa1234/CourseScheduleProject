package model;

import org.json.JSONObject;

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
            String date,
            String place) {
        super(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, timeMinutesOver, description, date, place);
        this.isImportant = false;
    }

    //REQUIRES: importance must not be null
    //MODIFIES: this
    //EFFECTS: set the event to be importat or not
    public void setImportance(boolean importance) {
        isImportant = importance;
    }

    //EFFECTS: return the importance of the event
    public boolean getImportance() {
        return isImportant;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("is Event", true);
        json.put("name", name);
        json.put("start hour", timeHoursBegin);
        json.put("start minute", timeMinutesBegin);
        json.put("end hour", timeHoursOver);
        json.put("end minute", timeMinutesOver);
        json.put("description", description);
        json.put("date", date);
        json.put("place", place);
        json.put("importance", isImportant);
        return json;
    }
}
