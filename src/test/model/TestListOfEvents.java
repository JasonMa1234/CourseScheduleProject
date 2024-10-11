package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestListOfEvents {
    private ListOfEvents eventList1;
    private ListOfEvents eventList2;
    private static Event officeHour = new Event(14, 
    0, 
    15, 
    0,
    "office hour");
    private static Event clubActivity = new Event(10, 
    0, 
    11, 
    0,
    "join the club activity and meet new people");

    @BeforeEach
    void runBefore() {
        eventList1 = new ListOfEvents();
        eventList2 = new ListOfEvents();
        eventList2.addEvent(officeHour);
        eventList2.addEvent(clubActivity);
    }

    @Test
    void testCalculate() {
        assertEquals(0,eventList1.calculate());
        assertEquals(6,eventList2.calculate());
    }
}
