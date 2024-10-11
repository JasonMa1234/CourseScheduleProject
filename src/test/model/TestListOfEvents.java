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

    @BeforeEach
    void runBefore() {
        officeHour = new Event("officehour",
        3,
        0,
        5,
        0,
        "Tue",
        "OfficeHoure");
        clubActivity = new Event("clubactivity",
        10, 
        0, 
        11, 
        0,
        "Thu",
        "join the club activity and meet new people");
        clubActivity.setIsImportant(true);
        eventList1 = new ListOfEvents();
        eventList2 = new ListOfEvents();
        eventList2.addEvent(officeHour);
        eventList2.addEvent(clubActivity);
    }

    @Test
    void testConstructor(){
        ArrayList<Event> ListOfEvent1 = eventList1.getList();
        assertEquals(0,ListOfEvent1.size());
        ArrayList<Event> ListOfEvent2 = eventList2.getList();
        assertEquals(2, ListOfEvent2.size());
    }

    @Test
    void testCalculate() {
        assertEquals(0,eventList1.calculate());
        assertEquals(1,eventList2.calculate());
    }
}
