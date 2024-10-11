package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestListOfDate {
    private ListOfDate dateList;
    
    @BeforeEach
    void runBefore(){
        dateList = new ListOfDate();
        dateList.addDate("Mon");
        dateList.addDate("Tue");
        dateList.addDate("Fri");
    }

    @Test
    void testConstructor(){
        assertEquals(3, dateList.sizeDate());
    }
}
