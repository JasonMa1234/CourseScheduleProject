package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private ListOfCase caseList = new ListOfCase();
    private Course stat;
    private Course math;
    private EventHappen officeHour;
    private EventHappen clubActivity;
    
    @BeforeEach
    void setupCaseList() {
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
        officeHour = new EventHappen("officehour1",
                        13,
                        0,
                        15,
                        0,
                        "OfficeHoure",
                        "Mon",
                        "ESB1024");
        clubActivity =  new EventHappen("officehour2",
                        10,
                        0,
                        11,
                        0,
                        "Meet new people",
                        "Tue",
                        "ESB1024");
        officeHour.setImportance(true);
        caseList.addCase(stat);
        caseList.addCase(math);
        caseList.addCase(officeHour);
        caseList.addCase(clubActivity);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfCaseForWeek wr = new ListOfCaseForWeek();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWeekSchedule() {
        try {
            ListOfCaseForWeek locw = new ListOfCaseForWeek();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWeekSchedule.json");
            writer.open();
            writer.write(locw);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWeekSchedule.json");
            locw = reader.read();
            assertEquals(0, locw.getMon().size());
            assertEquals(0, locw.getTue().size());
            assertEquals(0, locw.getWed().size());
            assertEquals(0, locw.getThu().size());
            assertEquals(0, locw.getFri().size());
            assertEquals(0, locw.getSat().size());
            assertEquals(0, locw.getSun().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWeekSchedule() {
        try {
            ListOfCaseForWeek locw = new ListOfCaseForWeek();
            locw.fillWeek(caseList);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(locw);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            locw = reader.read();
            ArrayList<CaseToDo> scheduleMon = locw.getMon();
            ArrayList<CaseToDo> scheduleTue = locw.getTue();
            ArrayList<CaseToDo> scheduleWed = locw.getWed();
            ArrayList<CaseToDo> scheduleThu = locw.getThu();
            ArrayList<CaseToDo> scheduleFri = locw.getFri();
            ArrayList<CaseToDo> scheduleSat = locw.getSat();
            ArrayList<CaseToDo> scheduleSun = locw.getSun();
            CaseToDo courseFormCase1 = scheduleMon.get(0);
            Course course1 = (Course) courseFormCase1;
            CaseToDo eventFormCase1 = scheduleMon.get(1);
            EventHappen event1 = (EventHappen) eventFormCase1;
            CaseToDo eventFormCase2 = scheduleTue.get(1);
            EventHappen event2 = (EventHappen) eventFormCase2;
            assertEquals(2,scheduleMon.size());
            assertEquals(2, scheduleTue.size());
            assertEquals(0, scheduleWed.size());
            assertEquals(0, scheduleThu.size());
            assertEquals(0, scheduleFri.size());
            assertEquals(0, scheduleSat.size());
            assertEquals(0, scheduleSun.size());
            assertEquals("lecture", course1.getType());
            assertEquals("A",course1.getProfessor());
            assertEquals("Winter1", course1.getTerm());
            assertEquals(3, course1.getCredit());
            assertEquals("STAT", course1.getName());
            assertEquals(14, course1.getTimeHoursBegin());
            assertEquals(0, course1.getTimeMinutesBegin());
            assertEquals(15, course1.getTimeHoursOver());
            assertEquals(0, course1.getTimeMinutesOver());
            assertEquals("Statistic 200 lecture", course1.getDescription());
            assertEquals("Mon", course1.getDate());
            assertEquals("ESB1024", course1.getPlace());
            assertTrue(event1.getImportance());
            assertFalse(event2.getImportance());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
