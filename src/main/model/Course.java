package model;

import java.util.ArrayList;

import org.json.JSONObject;

//Represents a course user enrolled
public class Course extends CaseToDo {
    private String type;
    private String professor;
    private String term;
    private int credit;

    //EFFECTS: create a course
    public Course(String name, 
                int timeHoursBegin, 
                int timeMinutesBegin, 
                int timeHoursOver, 
                int timeMinutesOver, 
                String description,
                String date,
                String type,
                String professor,
                String term,
                int credit,
                String place) {
        super(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, timeMinutesOver, description, date, place);
        this.type = type;
        this.professor = professor;
        this.term = term;
        this.credit = credit;
    }

    //REQUIRES: type must be one of: "lecture", "lab","tutorial" String
    //MODIFIES: this
    //EFFECTS: set the course type
    public void setType(String type) {
        this.type = type;
    }

    //MODIFIES: this
    //EFFECTS: set the professor's name
    public void setProfessor(String pro) {
        professor = pro;
    }

    /*  REQUIRES: term must not be null, date must be String
                from "Winter1", "Winter2", "Summer" */
    private void setTerm(String term) {
        this.term = term;
    }

    //REQUIRES credit >= 0
    //MODIFIES: this
    //EFFECTS: define the course's credit
    private void setCredit(int credit) {
        this.credit = credit;
    }

    //EFFECTS: return the course type
    public String getType() {
        return type;
    }

    //EFFECTS: return the professor's name
    public String getProfessor() {
        return professor;
    }

    //EFFECTS: return the term
    public String getTerm() {
        return term;
    }

    //EFFECTS: return the credit
    public int getCredit() {
        return credit;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("start hour", timeHoursBegin);
        json.put("start minute", timeMinutesBegin);
        json.put("end hour", timeHoursOver);
        json.put("end minute", timeMinutesOver);
        json.put("description", description);
        json.put("date", date);
        json.put("place", place);
        json.put("type", type);
        json.put("professor", professor);
        json.put("term", term);
        json.put("credt", credit);
        return json;
    }
}



