package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;

public class TestListOfCaseForWeek {
    private ListOfCaseForWeek weekSchedule;
    private CaseToDo event1;
    private CaseToDo event2;
    private CaseToDo course1;
    private CaseToDo course2;
    private ListOfCase caseList;
    private static ListOfDate mon;
    private static ListOfDate thu;
    private static ListOfDate monWedFri;
    private static ListOfDate tueThu;

    @BeforeEach
    void runBefore() {
        weekSchedule = new ListOfCaseForWeek();
        mon = new ListOfDate();
        mon.addDate("Mon");
        thu = new ListOfDate();
        thu.addDate("Thu");
        monWedFri = new ListOfDate();
        tueThu = new ListOfDate();
        monWedFri.addDate("Mon");
        monWedFri.addDate("Wed");
        monWedFri.addDate("Fri");
        tueThu.addDate("Tue");
        tueThu.addDate("Thu");
        event1 = new Event("officehour1",
                            13,
                            0,
                            15,
                            0,
                            "OfficeHoure",
                            mon,
                            "ESB1024");
        event2 = new Event("officehour2",
                            10,
                            0,
                            11,
                            0,
                            "OfficeHoure",
                            thu,
                            "ESB1024");
        course1 = new Course("STAT", 
                            14, 
                            0, 
                            15, 
                            0, 
                            "Statistic 200 lecture",
                            monWedFri,
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
                            tueThu,
                            "lecture",
                            "B",
                            "Winter1",
                            3,
                            "WESB101");
        caseList = new ListOfCase();
    }

    @Test
    void testConstructor() {
        assertEquals(0,weekSchedule.getMon().size());
        assertEquals(0,weekSchedule.getTue().size());
        assertEquals(0,weekSchedule.getWed().size());
        assertEquals(0,weekSchedule.getThu().size());
        assertEquals(0,weekSchedule.getFri().size());
        assertEquals(0,weekSchedule.getSat().size());
        assertEquals(0,weekSchedule.getSun().size());
    }

    @Test
    void testFillWeek() {
        caseList.addCase(event1);
        caseList.addCase(event2);
        caseList.addCase(course1);
        caseList.addCase(course2);
        weekSchedule.fillWeek(caseList);
        assertEquals(2,weekSchedule.getMon().size());
        assertEquals(event1, weekSchedule.getMon().get(0));
        assertEquals(course1, weekSchedule.getMon().get(1));
        assertEquals(1,weekSchedule.getTue().size());
        assertEquals(course2, weekSchedule.getTue().get(0));
        assertEquals(1,weekSchedule.getWed().size());
        assertEquals(course1, weekSchedule.getWed().get(0));
        assertEquals(2,weekSchedule.getThu().size());
        assertEquals(event2, weekSchedule.getThu().get(0));
        assertEquals(course2, weekSchedule.getThu().get(1));
    }
}
