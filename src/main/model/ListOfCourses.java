package model;

import java.util.ArrayList;

// Represents a list of courses
public class ListOfCourses implements Cases{
    private ArrayList<Course> courseList;

    //EFFECTS: set the initial course list to be null
    public ListOfCourses() {
       this.courseList = new ArrayList<>(); //stub
    }

    //REQUIRES: course cannot be null
    //MODIFIES: this
    //EFFECTS: add a course into the course list
    public void addCourse (Course course){
        courseList.add(course);
    }

    //EFFECTS: return the course list
    @Override
    public ArrayList getList(){
        return courseList;
    }
    //EFFECTS: return the total credit in the course list
    @Override
    public int calculate(){
        int totalCredit = 0;
        for(Course c : courseList){
            totalCredit += c.getCredit();
        }
        return totalCredit;
    }
}
