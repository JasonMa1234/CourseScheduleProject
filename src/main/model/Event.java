package model;

//Represents a Event user added
public class Event implements Case{
    private String eventName;
    private int timeHoursBegin;
    private int timeMinutesBegin;
    private int timeHoursOver;
    private int timeMinutesOver;
    private String description;
    private String date;
    private static int credit = 0;
    private boolean isImportant;

    //REQUIRES: description cannot be null
    //EFFECTS: add a description descripts the event
    public Event(String eventName,
    int timeHoursBegin,
    int timeMinutesBegin,
    int timeHoursOver,
    int timeMinutesOver,
    String date,
    String description){
        this.eventName = eventName;
        this.timeHoursBegin = timeHoursBegin;
        this.timeMinutesBegin = timeMinutesBegin;
        this.timeHoursOver = timeHoursOver;
        this.timeMinutesOver = timeMinutesOver;
        this.description = description;
        this.date = date;
        this.isImportant = false; // stub
    }

    //REQUIRES: hours must between 0-24, minutes must between 0-60
    //MODIFIES: this
    //EFFECTS: set the event's begin time
    private void setTimeBegin(int hours,int minutes){
        timeHoursBegin = hours;
        timeMinutesBegin = minutes;
    }

    //REQUIRES: newName must not be null
    //MODIFIES: this
    //EFFECTS: set the event's name
    private void setEventName(String newName){
        eventName = newName;
    }

    //REQUIRES: hours must between 0-24, minutes must between 0-60
    //MODIFIES: this
    //EFFECTS: set the event's over time
    private void setTimeOver(int hours,int minutes){
        timeHoursOver = hours;
        timeMinutesOver = minutes;
    }

    //MODIFIES: this
    //EFFECTS: add additional description to the event
    private void setDescription(String desc){
        description = desc;
    }

    //REQUIRES: isImportant cannot be null
    //MODIFIES: this
    //EFFECTS: set the event to be important case or not
    public void setIsImportant(Boolean isImportant){
        this.isImportant = isImportant;
    }

    //REQUIRES: date must be one of week days
    //MODIFIES: this
    //EFFECTS: set the date of the event happens
    public void setDate(String date){
        this.date = date;
    }

    //EFFECTS: return the event's name
    public String getEventName(){
        return eventName;
    }

    //EFFECTS: return the event's date
    public String getEventDate(){
        return date;
    }

    //EFFECTS: return the event's begin time in hour
    public int getTimeBeginHours(){
        return timeHoursBegin;
    }

    //EFFECTS: return the event's begin time in minute
    public int getTimeBeginMinutes(){
        return timeMinutesBegin;
    }

    //EFFECTS: return the event's over time in hour
    public int getTimeOverHours(){
        return timeHoursOver;
    }

    //EFFECTS: return the event's over time in minutes
    public int getTimeOverMinutes(){
        return timeMinutesOver;
    }

    //EFFECTS: return additional description to the event
    public String getDescription(){
        return description;
    }

    //EFFECTS: return whether te event is important
    public boolean getIsImportant(){
        return isImportant;
    }
}
