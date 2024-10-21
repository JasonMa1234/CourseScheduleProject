package model;

import java.util.List;
import java.util.ArrayList;

// Represents a list of courses
public class ListOfCourses extends ListOfCase{
    private ArrayList<Course> courseList;

    //EFFECTS: set the initial course list to be null
    public ListOfCourses() {
       this.courseList = new ArrayList<>(); //stub
    }

    // //REQUIRES: course cannot be null
    // //MODIFIES: this
    // //EFFECTS: add a course into the course list
    // @Override
    // protected void addCase(CaseToDo newCase){
    //     courseList.add(course);
    // }

    // //EFFECTS: return the course list
    // public ArrayList getList(){
    //     return courseList;
    // }

    //EFFECTS: return the total credit in the course list
    public int calculateCredit(){
        int totalCredit = 0;
        for(Course c : courseList){
            totalCredit += c.getCredit();
        }
        return totalCredit;
    }
}
