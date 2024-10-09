package model;

//Represents a course user enrolled
public class Course implements Case{
    private String courseName;
    private int timeHoursBegin;
    private int timeMinutesBegin;
    private int timeHoursOver;
    private int timeMinutesOver;
    private String type;
    private String professor;
    private String courseDescription;

    //REQUIRES: courseName, courseDescription cannot be null
    //EFFECTS: create a Course with the name of courseName and details of courseDescription
    private void Course(String courseName,String courseDescription){
        this.courseName = ""; //stub
        this.courseDescription = "";
    }

    //REQUIRES: name must not be null
    //MODIFIES: this
    //EFFECTS: set the course name to the given value
    private void setCourseName(String name){
        courseName = name;
    }

    //REQUIRES: hours must between 0-24, minutes must between 0-60
    //MODIFIES: this
    //EFFECTS: set the course's begin time
    private void setTimeBegin(int hours,int minutes){
        timeHoursBegin = hours;
        timeMinutesBegin = minutes;
    }

    //REQUIRES: hours must between 0-24, minutes must between 0-60
    //MODIFIES: this
    //EFFECTS: set the course's over time
    private void setTimeOver(int hours,int minutes){
        timeHoursOver = hours;
        timeMinutesOver = minutes;
    }

    //REQUIRES: type must be one of: "lecture", "lab","tutorial" String
    //MODIFIES: this
    //EFFECTS: set the course type
    private void setType(String type){
        this.type = type;
    }

    //MODIFIES: this
    //EFFECTS: set the professor's name
    private void setProfessor(String pro){
        professor = pro;
    }

    //MODIFIES: this
    //EFFECTS: add additional description to the course
    private void setDescription(String desc){
        courseDescription = desc;
    }
    
    //EFFECTS: return the course name
    private String getCourseName(){
        return courseName;
    }

    //EFFECTS: return the course's begin time in hour
    private int getTimeBeginHours(){
        return timeHoursBegin;
    }

    //EFFECTS: return the course's begin time in minute
    private int getTimeBeginMinutes(){
        return timeMinutesBegin;
    }

    //EFFECTS: return the course's over time in hour
    private int getTimeOverHours(){
        return timeHoursOver;
    }

    //EFFECTS: return the course's over time in minutes
    private int getTimeOverMinutes(){
        return timeMinutesOver;
    }

    //EFFECTS: return the course type
    private String getType(){
        return type;
    }


    //EFFECTS: return the professor's name
    private String getProfessor(String pro){
        return professor;
    }


    //EFFECTS: return additional description to the course
    private String getDescription(String desc){
        return courseDescription;
    }
}
