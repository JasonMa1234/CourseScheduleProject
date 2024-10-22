package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEvent {
    private Event officeHour;
    private static ListOfDate mon;

    @BeforeEach
    void runBefore() {
        mon = new ListOfDate();
        mon.addDate("Mon");
        officeHour = new Event("officehour",
                        14,
                        0,
                        15,
                        0,
                        "OfficeHoure",
                        mon,
                        "ESB1024");
    }

    @Test
    void testConstructor() {
        assertEquals("officehour", officeHour.getName());
        assertEquals(14, officeHour.getTimeHoursBegin());
        assertEquals(0, officeHour.getTimeMinutesBegin());
        assertEquals(15, officeHour.getTimeHoursOver());
        assertEquals(0, officeHour.getTimeMinutesOver());
        assertEquals("OfficeHoure", officeHour.getDescription());
        assertEquals(mon, officeHour.getDate());
        assertFalse(officeHour.getImportance());
        assertEquals("ESB1024", officeHour.getPlace());
    }

    @Test
    void testSetImportance() {
        officeHour.setImportance(true);
        assertTrue(officeHour.getImportance());
    }
}
