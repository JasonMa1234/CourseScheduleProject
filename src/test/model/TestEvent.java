package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEvent {
    private Event officeHour;

    @BeforeEach
    void runBefore(){
        officeHour = new Event("officehour",
        3,
        0,
        5,
        0,
        "Tue",
        "OfficeHoure");
    }
    @Test
    void testConstructor(){
        assertEquals("STAT", officeHour.getEventName());
        assertEquals(14, officeHour.getTimeBeginHours());
        assertEquals(0, officeHour.getTimeBeginMinutes());
        assertEquals(15, officeHour.getTimeOverHours());
        assertEquals(0, officeHour.getTimeOverMinutes());
        assertEquals("statistic course", officeHour.getDescription());
        assertEquals("Tue", officeHour.getEventDate());
        assertFalse(officeHour.getIsImportant());
        officeHour.setIsImportant(true);
        assertTrue(officeHour.getIsImportant());
    }
}
