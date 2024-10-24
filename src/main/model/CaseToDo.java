package model;

import org.json.JSONObject;

import persistence.Writable;

//Represents a case to add in some timetables
public class CaseToDo implements Writable {
    protected String name;
    protected int timeHoursBegin;
    protected int timeMinutesBegin;
    protected int timeHoursOver;
    protected int timeMinutesOver;
    protected String description;
    protected String date;
    protected String place;

    /*REQUIRES: name cannot be null, timeHoursBegin, timeHoursOver must be integer between 0-24,
                timeMinutesBegin, timeMinutesOver must be integer between 0-60 */
    //EFFECTS: create a case with the given name, time start, time over and an optional description
    public CaseToDo(String name, 
                    int timeHoursBegin, 
                    int timeMinutesBegin, 
                    int timeHoursOver, 
                    int timeMinutesOver, 
                    String description,
                    String date,
                    String place) {
        this.name = name;
        this.timeHoursBegin = timeHoursBegin;
        this.timeMinutesBegin = timeMinutesBegin;
        this.timeHoursOver = timeHoursOver;
        this.timeMinutesOver = timeMinutesOver;
        this.description = description;
        this.date = date;
        this.place = place;
    }

    //REQUIRES: newName must not be null
    //MODIFIES: this
    //EFFECTS: change the name to the new name
    public void setName(String newName) {
        name = newName;
    }

    //REQUIRES: newBeginHour must be an integer from 0-24
    //MODIFIES: this
    //EFFECTS: change the hour of the start time to the new hour
    public void setTimeHoursBegin(int newBeginHour) {
        timeHoursBegin = newBeginHour;
    }

    //REQUIRES: newBeginMinute must be an integer from 0-60
    //MODIFIES: this
    //EFFECTS: change the minute of the start time to the new minute
    public void setTimeMinutesBegin(int newBeginMinute) {
        timeMinutesBegin = newBeginMinute;
    }

    //REQUIRES: newOverHour must be an integer from 0-60
    //MODIFIES: this
    //EFFECTS: change the hour of the end time to the new hour
    public void setTimeHoursOver(int newOverHour) {
        timeHoursOver = newOverHour;
    }

    //REQUIRES: newOverMinute must be an integer from 0-60
    //MODIFIES: this
    //EFFECTS: change the minute of the end time to the new minute
    public void setTimeMinutesOver(int newOverMinute) {
        timeMinutesOver = newOverMinute;
    }

    //MODIFIES: this
    //EFFECTS: change the description to new description
    public void setDescription(String newDesc) {
        description = newDesc;
    }

    //REQUIRES newDate must not be null
    //MODIFIES: this
    //EFFECTS: change the date to new date
    public void setDate(String newDate) {
        date = newDate;
    }

    //REQUIRES: newPlace must not be null
    //MODIFIES: this
    //EFFECTS: change the place
    public void setPlace(String newPlace) {
        place = newPlace;
    }

    //EFFECTS: return the name of the case
    public String getName() {
        return name;
    }

    //EFFECTS: return the hour of the start time
    public int getTimeHoursBegin() {
        return timeHoursBegin;
    }

    //EFFECTS: return the minute of the start time
    public int getTimeMinutesBegin() {
        return timeMinutesBegin;
    }

    //EFFECTS: return the hour of the end time
    public int getTimeHoursOver() {
        return timeHoursOver;
    }

    //EFFECTS: return the minute of the end time
    public int getTimeMinutesOver() {
        return timeMinutesOver;
    }

    //EFFECTS: return the description
    public String getDescription() {
        return description;
    }

    //EFFECTS: return the date
    public String getDate() {
        return date;
    }

    //EFFETCS: return the place case take place
    public String getPlace() {
        return place;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("begin hour", timeHoursBegin);
        json.put("begin minute", timeMinutesBegin);
        json.put("over hour", timeHoursOver);
        json.put("over minute", timeMinutesOver);
        json.put("over hour", timeHoursOver);
        json.put("description", description);
        json.put("date", date);
        json.put("place", place);
        return json;
    }
}
