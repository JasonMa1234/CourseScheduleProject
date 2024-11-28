package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class TestListOfCaseForWeek {
    private ListOfCaseForWeek weekSchedule;
    private CaseToDo event1;
    private CaseToDo event2;
    private CaseToDo courseStat1;
    private CaseToDo courseStat2;
    private CaseToDo course2;
    private ListOfCase caseList;


    @BeforeEach
    void runBefore() {
        weekSchedule = new ListOfCaseForWeek();
        event1 = new EventHappen("officehour1",
                            13,
                            0,
                            15,
                            0,
                            "OfficeHoure",
                            "Mon",
                            "ESB1024");
        event2 = new EventHappen("officehour2",
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
                            "Mon",
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
        caseList.addCase(event1);//Mon
        caseList.addCase(event2);//Thu
        caseList.addCase(courseStat1);//Mon
        caseList.addCase(courseStat2);//Wed
        caseList.addCase(course2);//Tue
        weekSchedule.fillWeek(caseList);
        assertEquals(2,weekSchedule.getMon().size());
        assertEquals(event1, weekSchedule.getMon().get(0));
        assertEquals(courseStat1, weekSchedule.getMon().get(1));
        assertEquals(1,weekSchedule.getTue().size());
        assertEquals(course2, weekSchedule.getTue().get(0));
        assertEquals(1,weekSchedule.getWed().size());
        assertEquals(courseStat2, weekSchedule.getWed().get(0));
        assertEquals(1,weekSchedule.getThu().size());
        assertEquals(event2, weekSchedule.getThu().get(0));
    }

    @Test
    void testGetDateList() {
        caseList.addCase(event1);//Mon
        caseList.addCase(event2);//Thu
        caseList.addCase(courseStat1);//Mon
        caseList.addCase(courseStat2);//Wed
        caseList.addCase(course2);//Tue
        weekSchedule.fillWeek(caseList);
        ArrayList<CaseToDo> dayMon = weekSchedule.getDateList("Mon");
        ArrayList<CaseToDo> dayTue = weekSchedule.getDateList("Tue");
        ArrayList<CaseToDo> dayWed = weekSchedule.getDateList("Wed");
        ArrayList<CaseToDo> dayThu = weekSchedule.getDateList("Thu");
        assertEquals(2,dayMon.size());
        assertEquals(event1, dayMon.get(0));
        assertEquals(courseStat1, dayMon.get(1));
        assertEquals(1,dayTue.size());
        assertEquals(course2, dayTue.get(0));
        assertEquals(1,dayWed.size());
        assertEquals(courseStat2, dayWed.get(0));
        assertEquals(1,dayThu.size());
        assertEquals(event2, dayThu.get(0));
    }
}
