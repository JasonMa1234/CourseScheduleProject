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
    private String date;
    private String term;
    private int credit;

    //REQUIRES: courseName, courseDescription cannot be null
    //EFFECTS: create a Course with the name of courseName and details of courseDescription
    public Course(String courseName, 
    int timeHoursBegin, 
    int timeMinutesBegin,
    int timeHoursOver, 
    int timeMinutesOver, 
    String type, 
    String professor, 
    String courseDescription, 
    String date, 
    String term, 
    int credit){
        this.courseName = ""; //stub
        this.courseDescription = "";
        this.timeHoursBegin = 0;
        this.timeMinutesBegin = 0;
        this.timeHoursOver = 0;
        this.timeMinutesOver = 0;
        this.credit = 0;
        this.date = "";
        this.professor = "";
        this.term = "";
        this.type = "";
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

    //REQUIRES: date input cannot be null, date must be
    //          String from "Mon, Tue, Wed, Thu, Fri"
    //MODIFIES: this
    //EFFECTS: define the course's date
    private void setDate(String date){
        this.date = date;
    }
    
    // REQUIRES: term must not be null, date must be String
    //          from "Winter1", "Winter2", "Summer"
    private void setTerm(String term){
        this.term = term;
    }

    //REQUIRES credit >= 0
    //MODIFIES: this
    //EFFECTS: define the course's credit
    private void setCredit(int credit){
        this.credit = credit;
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

    //EFFECTS: return the course date
    private String getDate(){
        return date;
    }

    //EFFECTS: return the term
    private String getTerm(){
        return term;
    }

    //EFFECTS: return the credit
    public int getCredit(){
        return credit;
    }
}
