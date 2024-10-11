package model;

import java.util.ArrayList;

// Represents a list of courses
public class ListOfCourses implements Cases{
    private ArrayList<Course> CourseList;

    //EFFECTS: set the initial course list to be null
    public ListOfCourses() {
       this.CourseList = new ArrayList<>(); //stub
    }

    //REQUIRES: course cannot be null
    //MODIFIES: this
    //EFFECTS: add a course into the course list
    public void addCourse (Course course){
        CourseList.add(course);
    }

    //EFFECTS: return the total credit in the course list
    @Override
    public int calculate(){
        int totalCredit = 0;
        for(Course c : CourseList){
            totalCredit += c.getCredit();
        }
        return totalCredit;
    }
}
