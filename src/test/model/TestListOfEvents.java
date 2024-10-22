package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestListOfEvents {
    private ListOfEvents eventList1;
    private ListOfEvents eventList2;
    private Event officeHour;
    private Event clubActivity;
    private static ListOfDate mon;
    private static ListOfDate tue;


    @BeforeEach
    void runBefore() {
        mon = new ListOfDate();
        mon.addDate("Mon");
        tue = new ListOfDate();
        tue.addDate("Tue");
        officeHour = new Event("officehour1",
                            13,
                            0,
                            15,
                            0,
                            "OfficeHoure",
                            mon,
                            "ESB1024");
        clubActivity =  new Event("officehour2",
                        10,
                        0,
                        11,
                        0,
                        "OfficeHoure",
                        tue,
                        "ESB1024");
        clubActivity.setImportance(true);
        eventList1 = new ListOfEvents();
        eventList2 = new ListOfEvents();
        eventList2.addCase(officeHour);
        eventList2.addCase(clubActivity);
    }

    @Test
    void testConstructor(){
        ArrayList<CaseToDo> ListOfEvent1 = eventList1.getList();
        assertEquals(0,ListOfEvent1.size());
        ArrayList<CaseToDo> ListOfEvent2 = eventList2.getList();
        assertEquals(2, ListOfEvent2.size());
    }

    @Test
    void testCalculate() {
        assertEquals(0,eventList1.calculateImportant());
        assertEquals(1,eventList2.calculateImportant());
    }
}
