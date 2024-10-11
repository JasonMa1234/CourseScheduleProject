package model;
import java.util.ArrayList;
import java.util.Arrays;


//Create a list of date that case take in place
public class ListOfDate {
    private static ArrayList<String> week = new ArrayList<String>
    (Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));

    private ArrayList<String> dateList;

    //EFFECTS: create an arraylist object name dateList
    public ListOfDate(){
        dateList = new ArrayList<String>();
    }

    //REQUIRES: date must be the one from week list
    //MODIFIES: this
    //EFFECTS: add one day in the dateList
    public void addDate(String date){
        dateList.add(date);
    }

    //EFFECTS: return the size of the dateList
    public int sizeDate(){
        return dateList.size();
    }
}
