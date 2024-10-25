package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCourse {
    private Course stat;

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
    }

    @Test
    void testConstructor() {
        assertEquals("STAT", stat.getName());
        assertEquals(14, stat.getTimeHoursBegin());
        assertEquals(0, stat.getTimeMinutesBegin());
        assertEquals(15, stat.getTimeHoursOver());
        assertEquals(0, stat.getTimeMinutesOver());
        assertEquals("lecture", stat.getType());
        assertEquals("A", stat.getProfessor());
        assertEquals("Statistic 200 lecture", stat.getDescription());
        assertEquals("Winter1", stat.getTerm());
        assertEquals(3, stat.getCredit());
        assertEquals("Mon", stat.getDate());
        assertEquals("ESB1024", stat.getPlace());
    }
}
