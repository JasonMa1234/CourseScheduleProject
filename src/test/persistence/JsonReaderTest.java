package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    public ListOfCaseForWeek weekList;
    public ArrayList<CaseToDo> caseList;
    private CaseToDo event1;
    private CaseToDo event2;
    private CaseToDo courseStat1;
    private CaseToDo courseStat2;
    private CaseToDo course2;
    private CaseToDo event3;
    private CaseToDo event4;
    private JsonReader reader;

    @BeforeEach
    void setUp () {
        reader = new JsonReader("./data/weekSchedule.json");
        weekList = new ListOfCaseForWeek();
        caseList = new ArrayList<CaseToDo>();
        event1 = new Event("officehour1",
                            13,
                            0,
                            15,
                            0,
                            "OfficeHoure",
                            "Mon",
                            "ESB1024");
        event2 = new Event("officehour2",
                            10,
                            0,
                            11,
                            0,
                            "OfficeHoure",
                            "Thu",
                            "ESB1024");
        courseStat1 = new Course("STAT", 
                            14, 
                            0, 
                            15, 
                            0, 
                            "Statistic 200 lecture",
                            "Fri",
                            "lecture",
                            "A",
                            "Winter1",
                            3,
                            "ESB1024");

        courseStat2 = new Course("STAT", 
                            14, 
                            0, 
                            15, 
                            0, 
                            "Statistic 200 lecture",
                            "Wed",
                            "lecture",
                            "A",
                            "Winter1",
                            3,
                            "ESB1024");
        
        course2 = new Course("MATH", 
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
        event3 = new Event("party1",
                            13,
                            0,
                            15,
                            0,
                            "party",
                            "Sat",
                            "home");
        event4 = new Event("party2",
                            13,
                            0,
                            15,
                            0,
                            "party",
                            "Sun",
                            "home");
        caseList.add(event1);
        caseList.add(event2);
        caseList.add(courseStat1);
        caseList.add(courseStat2);
        caseList.add(course2);
        caseList.add(event3);
        caseList.add(event4);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");// from progeam provided
        try {
            ListOfCaseForWeek locw = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWeekSchedule() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWeekSchedule.json");
        try {
            ListOfCaseForWeek locw = reader.read();
            assertEquals(0, locw.getMon().size());
            assertEquals(0, locw.getTue().size());
            assertEquals(0, locw.getWed().size());
            assertEquals(0, locw.getThu().size());
            assertEquals(0, locw.getFri().size());
            assertEquals(0, locw.getSat().size());
            assertEquals(0, locw.getSun().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWeekSchedule() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWeekSchedule.json");
        try {
            ListOfCaseForWeek locw = reader.read();
            ArrayList<CaseToDo> scheduleMon = locw.getMon();
            ArrayList<CaseToDo> scheduleTue = locw.getTue();
            ArrayList<CaseToDo> scheduleWed = locw.getWed();
            ArrayList<CaseToDo> scheduleThu = locw.getThu();
            ArrayList<CaseToDo> scheduleFri = locw.getFri();
            ArrayList<CaseToDo> scheduleSat = locw.getSat();
            ArrayList<CaseToDo> scheduleSun = locw.getSun();
            CaseToDo courseFormCase = scheduleMon.get(0);
            Course course = (Course) courseFormCase;
            CaseToDo eventFormCase1 = scheduleTue.get(0);
            Event event1 = (Event) eventFormCase1;
            CaseToDo eventFormCase2 = scheduleTue.get(1);
            Event event2 = (Event) eventFormCase2;
            assertEquals(1,scheduleMon.size());
            assertEquals(2, scheduleTue.size());
            assertEquals(0, scheduleWed.size());
            assertEquals(0, scheduleThu.size());
            assertEquals(0, scheduleFri.size());
            assertEquals(0, scheduleSat.size());
            assertEquals(0, scheduleSun.size());
            assertEquals("Lecture", course.getType());
            assertEquals("B",course.getProfessor());
            assertEquals("Winter1", course.getTerm());
            assertEquals(3, course.getCredit());
            assertEquals("A", course.getName());
            assertEquals(10, course.getTimeHoursBegin());
            assertEquals(0, course.getTimeMinutesBegin());
            assertEquals(11, course.getTimeHoursOver());
            assertEquals(0, course.getTimeMinutesOver());
            assertEquals("A lecture", course.getDescription());
            assertEquals("Mon", course.getDate());
            assertEquals("C", course.getPlace());
            assertFalse(event1.getImportance());
            assertTrue(event2.getImportance());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
