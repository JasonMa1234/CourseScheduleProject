package model;

import java.util.List;
import java.util.ArrayList;

// Represents a list of courses
public class ListOfCourses extends ListOfCase{
    // private ArrayList<Course> courseList;

    //EFFECTS: set the initial course list to be null
    public ListOfCourses() {
        super();
    //    this.courseList = new ArrayList<Course>(); //stub
    }

    // @Override
    // //REQUIRES: case cannot be null
    // //MODIFIES: this
    // //EFFECTS: add a case into the case list
    // public void addCase(CaseToDo newCase) {
    //     if (newCase instanceof Course) {
    //         courseList.add((Course) newCase); //from chatGPT
    //     }
    // }

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

        if (caseList.size() == 0){
            return totalCredit;
        }

        for(CaseToDo c : caseList){
            if (c instanceof Course) {
                Course course = (Course) c;  // From chatGPT
                int courseCredit = course.getCredit();
                totalCredit += courseCredit;
            }
        }
        return totalCredit;
    }
}
