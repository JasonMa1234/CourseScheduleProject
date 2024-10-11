package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestListOfCourses {
    private ListOfCourses courseList1;
    private ListOfCourses courseList2;
    private static ListOfDate monWedFri;
    private static ListOfDate tueThu;


    private static Course stat;
    private static Course math;

    @BeforeEach
    void runBefore() {
        monWedFri = new ListOfDate();
        tueThu = new ListOfDate();
        monWedFri.addDate("Mon");
        monWedFri.addDate("Wed");
        monWedFri.addDate("Fri");
        tueThu.addDate("Tue");
        tueThu.addDate("Thu");
        stat = new Course("STAT", 
        14, 
        0, 
        15, 
        0, 
        "lecture", 
        "A", 
        "statistic course", 
        monWedFri, 
        "Winter1", 
        3);
        math = new Course("MATH", 
        11, 
        00, 
        12, 
        30, 
        "lecture", 
        "B", 
        "math course", 
        tueThu, 
        "Winter1", 
        3);
        courseList1 = new ListOfCourses();
        courseList2 = new ListOfCourses();
        courseList2.addCourse(stat);
        courseList2.addCourse(math);
    }

    @Test
    void testCalculate() {
        assertEquals(0,courseList1.calculate());
        assertEquals(6,courseList2.calculate());
    }
}
