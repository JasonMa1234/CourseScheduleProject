package model;
import java.util.ArrayList;

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
    private ListOfDate date;
    private String term;
    private int credit;
    private static ListOfDate DATE = new ListOfDate();

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
    ListOfDate date, 
    String term, 
    int credit){
        this.courseName = courseName; //stub
        this.courseDescription = courseDescription;
        this.timeHoursBegin = timeHoursBegin;
        this.timeMinutesBegin = timeMinutesBegin;
        this.timeHoursOver = timeHoursOver;
        this.timeMinutesOver = timeMinutesOver;
        this.credit = credit;
        this.date = DATE;
        this.professor = professor;
        this.term = term;
        this.type = type;
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
    private void setDate(ListOfDate date){
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
    public String getCourseName(){
        return courseName;
    }

    //EFFECTS: return the course's begin time in hour
    public int getTimeBeginHours(){
        return timeHoursBegin;
    }

    //EFFECTS: return the course's begin time in minute
    public int getTimeBeginMinutes(){
        return timeMinutesBegin;
    }

    //EFFECTS: return the course's over time in hour
    public int getTimeOverHours(){
        return timeHoursOver;
    }

    //EFFECTS: return the course's over time in minutes
    public int getTimeOverMinutes(){
        return timeMinutesOver;
    }

    //EFFECTS: return the course type
    public String getType(){
        return type;
    }


    //EFFECTS: return the professor's name
    public String getProfessor(){
        return professor;
    }


    //EFFECTS: return additional description to the course
    public String getDescription(){
        return courseDescription;
    }

    //EFFECTS: return the course date
    public ListOfDate getDate(){
        return date;
    }

    //EFFECTS: return the term
    public String getTerm(){
        return term;
    }

    //EFFECTS: return the credit
    public int getCredit(){
        return credit;
    }
}
