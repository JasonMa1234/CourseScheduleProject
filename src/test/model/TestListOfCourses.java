package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestListOfCourses {
    private ListOfCourses courseList1;
    private ListOfCourses courseList2;
    private static Course stat = new Course("STAT", 
    14, 
    0, 
    15, 
    0, 
    "lecture", 
    "A", 
    "statistic course", 
    "Wed", 
    "Winter1", 
    3);
    private static Course math = new Course("MATH", 
    11, 
    00, 
    12, 
    30, 
    "lecture", 
    "B", 
    "math course", 
    "The", 
    "Winter1", 
    3);

    @BeforeEach
    void runBefore() {
        courseList1 = new ListOfCourses();
        courseList2 = new ListOfCourses();
        courseList2.addCourse(stat);
        courseList2.addCourse(math);
    }

    @Test
    void totalCredit() {
        assertEquals(0,courseList1.Calculate());
    }
}
