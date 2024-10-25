package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestListOfCourses {
    private ListOfCourses courseList1;
    private ListOfCourses courseList2;
    private Course stat;
    private Course math;

    @BeforeEach
    void runBefore() {
        stat = new Course("STAT", 
                        14, 
                        0, 
                        15, 
                        0, 
                        "Statistic 200 lecture",
                        "Mon",
                        "lecture",
                        "A",
                        "Winter1",
                        3,
                        "ESB1024");
        math = new Course("MATH", 
                        10, 
                        0, 
                        12, 
                        30, 
                        "Math 200 lecture",
                        "Tue",
                        "lecture",
                        "B",
                        "Winter1",
                        3,
                        "WESB101");
        courseList1 = new ListOfCourses();
        courseList2 = new ListOfCourses();
        courseList2.addCase(stat);
        courseList2.addCase(math);
    }

    @Test 
    void testConstructor() {
        ArrayList<CaseToDo> listOfCourse1 = courseList1.getList();
        assertEquals(0,listOfCourse1.size());
        ArrayList<CaseToDo> listOfCourse2 = courseList2.getList();
        assertEquals(2,listOfCourse2.size());
    }

    @Test
    void testCalculateCredit() {
        assertEquals(0,courseList1.calculateCredit());
        assertEquals(6,courseList2.calculateCredit());
    }
}
