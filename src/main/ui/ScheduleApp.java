package ui;
import model.*;
import java.util.Scanner;

public class ScheduleApp {
    private ListOfEvents eventList;
    private ListOfCourses courseList;
    private Scanner input;
    private static ListOfDate monWedFri = new ListOfDate();
    private static ListOfDate tueThu = new ListOfDate();;

    

    private static Course stat = new Course("STAT", 
    14, 
    0, 
    15, 
    0, 
    "lecture", 
    "A", 
    "statistic course", 
    monWedFri, 
    "Winter1", 
    3);

    public ScheduleApp(){
        monWedFri.addDate("Mon");
        monWedFri.addDate("Wed");
        monWedFri.addDate("Fri");
        tueThu.addDate("Tue");
        tueThu.addDate("Thu");

        runSchedule();
    }

    private void runSchedule(){
        eventList = new ListOfEvents();
        courseList = new ListOfCourses();
        init();
        System.out.println("successfully created the course list!");
    }

    private void init() {
        String command = null;
        displayChooseMod();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
        command = input.next();
        if (command.equals("Course")){
            Boolean continueInput = true;
            while (continueInput){
                Course course = createCourse();
                courseList.addCourse (course);
                continueInput = ifCountinue(continueInput);
            }
            System.out.println("All courses are added");
        } else if(command == "Event"){
            System.out.println("invalid input");
        }else{
            System.out.println("invalid input");
        }
    }

    private void displayChooseMod(){
        System.out.println("Which list would you create? Course or Event?\n");
    }

    private Course createCourse(){
        String courseName;
        int timeHoursBegin; 
        int timeMinutesBegin;
        int timeHoursOver;
        int timeMinutesOver; 
        String type;
        String professor; 
        String courseDescription; 
        ListOfDate date = monWedFri;
        String term;
        int credit;
        System.out.println("Course's name: \n");
        courseName = input.next();
        System.out.println("Begin time in hours: \n");
        timeHoursBegin = Integer.parseInt(input.next());
        System.out.println("Begin time in minutes: \n");
        timeMinutesBegin = Integer.parseInt(input.next());
        System.out.println("End time in hours \n");
        timeHoursOver = Integer.parseInt(input.next());
        System.out.println("End time in Minutes: \n");
        timeMinutesOver = Integer.parseInt(input.next());
        System.out.println("Type is: \n");
        type = input.next();
        System.out.println("Professor's name: \n");
        professor = input.next();
        System.out.println("Further description: \n");
        courseDescription = input.next();
        System.out.println("Term: \n");
        term = input.next();
        System.out.println("Credit: \n");
        credit = Integer.parseInt(input.next());
        Course course = new Course( courseName, 
        timeHoursBegin, 
        timeMinutesBegin,
        timeHoursOver, 
        timeMinutesOver, 
        type, 
        professor, 
        courseDescription, 
        date, 
        term, 
        credit);
        return course;
    }
    private Boolean ifCountinue(Boolean continueInput){
        System.out.println("continue?\n");
        continueInput = Boolean.parseBoolean(input.next());
        return continueInput;
    }
}
