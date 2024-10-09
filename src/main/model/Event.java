package model;

//Represents a Event user added
public class Event implements Case{
    private int timeHoursBegin;
    private int timeMinutesBegin;
    private int timeHoursOver;
    private int timeMinutesOver;
    private String description;

    //REQUIRES: description cannot be null
    //EFFECTS: add a description descripts the event
    public void Events(String description){
        this.description = ""; // stub
    }

    //REQUIRES: hours must between 0-24, minutes must between 0-60
    //MODIFIES: this
    //EFFECTS: set the event's begin time
    private void setTimeBegin(int hours,int minutes){
        timeHoursBegin = hours;
        timeMinutesBegin = minutes;
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

    //EFFECTS: return the event's begin time in hour
    private int getTimeBeginHours(){
        return timeHoursBegin;
    }

    //EFFECTS: return the event's begin time in minute
    private int getTimeBeginMinutes(){
        return timeMinutesBegin;
    }

    //EFFECTS: return the event's over time in hour
    private int getTimeOverHours(){
        return timeHoursOver;
    }

    //EFFECTS: return the event's over time in minutes
    private int getTimeOverMinutes(){
        return timeMinutesOver;
    }

    //EFFECTS: return additional description to the event
    private String getDescription(String desc){
        return description;
    }
}
