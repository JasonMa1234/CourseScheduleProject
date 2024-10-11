package model;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCourse {
    private Course stat;
    private static ListOfDate monWedFri;

    @BeforeEach
    void runBefore(){
        monWedFri = new ListOfDate();
        monWedFri.addDate("Mon");
        monWedFri.addDate("Wed");
        monWedFri.addDate("Fri");
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
    }
    @Test
    void testConstructor(){
        assertEquals("STAT", stat.getCourseName());
        assertEquals(14, stat.getTimeBeginHours());
        assertEquals(0, stat.getTimeBeginMinutes());
        assertEquals(15, stat.getTimeOverHours());
        assertEquals(0, stat.getTimeOverMinutes());
        assertEquals("lecture", stat.getType());
        assertEquals("A", stat.getProfessor());
        assertEquals("statistic course", stat.getDescription());
        assertEquals("Winter1", stat.getTerm());
        assertEquals(3, stat.getCredit());
    }
}
