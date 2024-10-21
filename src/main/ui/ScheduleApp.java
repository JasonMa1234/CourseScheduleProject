package ui;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

//Schedule application
public class ScheduleApp {
    private ListOfEvents eventList;
    private ListOfCourses courseList;
    private ListOfCaseForWeek weekSchedule;
    private ListOfCase caseList;
    private Scanner input;
    private static ListOfDate monWedFri = new ListOfDate();
    private static ListOfDate tueThu = new ListOfDate();;

    

    private static Course stat = new Course("STAT", 
            14, 
            0, 
            15, 
            0, 
            "lecture", 
                    monWedFri, 
            "statistic course", 
            "A", 
            "Winter1", 
            3,
            "ESB 1000");
    
    //EFFECTS: run the schedule application
    public ScheduleApp() {
        weekSchedule = new ListOfCaseForWeek();
        input = new Scanner(System.in);
        boolean wantAdd = false;
        System.out.println("Add course or event in your schedule\n");
        while (wantAdd) {
            checkType();
            System.out.println("Do you want to add more?");
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("yes")) {
                wantAdd = true;
            } else if(respon.equals("no")) {
                wantAdd = false;
            }
        }
        
        weekSchedule.fillWeek(courseList);
        weekSchedule.fillWeek(eventList);
        // monWedFri.addDate("Mon");
        // monWedFri.addDate("Wed");
        // monWedFri.addDate("Fri");
        // tueThu.addDate("Tue");
        // tueThu.addDate("Thu");

        // runSchedule();
    }

    private void checkType() {
        System.out.println("Which type of case would you prefer to take?\n");
        System.out.println("course\n");
        System.out.println("event\n");
        String caseType = input.nextLine();
        runType(caseType);
    }

    private void runType(String caseType) {
        if(caseType.equals("course")) {
            runCourse();
        } else if(caseType.equals("event")) {
            runEvent();
        }
    }

    private void runCourse() {
        boolean wantAdd = true;
        while(wantAdd) {
            System.out.println("input information for the course: \n");
            String name = getName(); 
            int timeHoursBegin = getBeginHour();
            int timeMinutesBegin = getBeginMinute();
            int timeHoursOver = getOverHour();
            int timeMinutesOver = getOverMinute();
            ListOfDate date = getDate();
            String type = getType();
            String professor = getProfessor();
            String term = getTerm();
            int credit = getCredit();
            String place = getPlace();
            String description = getDescription();
            CaseToDo course = new Course(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, 
                                    timeMinutesOver, description, date, type, professor, term,
                                    credit, place);
            courseList.addCase(course);
            System.out.println("Do you want to add more Course?");
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("yes")) {
                wantAdd = true;
            } else if(respon.equals("no")) {
                wantAdd = false;
            }
        }
    }

    private void runEvent() {
        boolean wantAdd = true;
        while(wantAdd) {
            System.out.println("input information for the event: \n");
            String name = getName(); 
            int timeHoursBegin = getBeginHour();
            int timeMinutesBegin = getBeginMinute();
            int timeHoursOver = getOverHour();
            int timeMinutesOver = getOverMinute();
            ListOfDate date = getDate();
            String place = getPlace();
            String description = getDescription();
            CaseToDo event = new Event(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, 
                                    timeMinutesOver, description, date,place);
            eventList.addCase(event);
            System.out.println("Do you want to add more event?");
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("yes")) {
                wantAdd = true;
            } else if(respon.equals("no")) {
                wantAdd = false;
            }
        }
    }

    private String getName() {
        System.out.println("What's the course's name?\n");
        return input.nextLine();
    }

    private int getBeginHour() {
        System.out.println("What's the begin hour?\n");
        return Integer.parseInt(input.nextLine());
    }

    private int getBeginMinute() {
        System.out.println("What's the begin minute?\n");
        return Integer.parseInt(input.nextLine());
    }

    private int getOverHour() {
        System.out.println("What's the over hour?\n");
        return Integer.parseInt(input.nextLine());
    }

    private int getOverMinute() {
        System.out.println("What's the over minute?\n");
        return Integer.parseInt(input.nextLine());
    }

    private String getDescription() {
        System.out.println("Further description?\n");
        return input.nextLine();
    }

    private ListOfDate getDate() {
        ListOfDate dateList = new ListOfDate();
        boolean wantAdd = true;
        int numDate = 0;
        while(wantAdd && numDate <= 7) {
            System.out.println("Add a date: \n");
            String date = input.nextLine();
            dateList.addDate(date);
            System.out.println("Do you want add new date?\n");
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("yes")) {
                wantAdd = true;
            } else if(respon.equals("no")) {
                wantAdd = false;
            }
        }
        return dateList;
    }

    private String getType() {
        System.out.println("What type is it?\n");
        return input.nextLine();
    }

    private String getProfessor() {
        System.out.println("What's the professor?\n");
        return input.nextLine();
    }

    private String getTerm() {
        System.out.println("What's the term?\n");
        return input.nextLine();
    }

    private int getCredit() {
        System.out.println("What's the credit?\n");
        return Integer.parseInt(input.nextLine());
    }

    private String getPlace() {
        System.out.println("What's the place?\n");
        return input.nextLine();
    }
}
//     //MODIFIES: this
//     //EFFECTS: create eventlist and courselist, and run the mode selection method
//     private void runSchedule(){
//         eventList = new ListOfEvents();
//         courseList = new ListOfCourses();
//         init();
//         System.out.println("successfully created the course list!");
//     }

//     //MODIFIES: this
//     //EFFECTS: accept the type user want to insert into list
//     private void init() {
//         String command = null;
//         displayChooseMod();
//         input = new Scanner(System.in);
//         input.useDelimiter("\r?\n|\r");
//         command = input.next();
//         if (command.equals("Course")){
//             Boolean continueInput = true;
//             while (continueInput){
//                 Course course = createCourse();
//                 courseList.addCourse (course);
//                 continueInput = ifCountinue(continueInput);
//             }
//             System.out.println("All courses are added");
//         } else if(command == "Event"){
//             System.out.println("invalid input");
//         }else{
//             System.out.println("invalid input");
//         }
//     }

//     private void displayChooseMod(){
//         System.out.println("Which list would you create? Course or Event?\n");
//     }

//     //EFFECTS: create the course based on user's requirement
//     private Course createCourse(){
//         String courseName;
//         int timeHoursBegin; 
//         int timeMinutesBegin;
//         int timeHoursOver;
//         int timeMinutesOver; 
//         String type;
//         String professor; 
//         String courseDescription; 
//         ListOfDate date = monWedFri;
//         String term;
//         int credit;
//         System.out.println("Course's name: \n");
//         courseName = input.next();
//         System.out.println("Begin time in hours: \n");
//         timeHoursBegin = Integer.parseInt(input.next());
//         System.out.println("Begin time in minutes: \n");
//         timeMinutesBegin = Integer.parseInt(input.next());
//         System.out.println("End time in hours \n");
//         timeHoursOver = Integer.parseInt(input.next());
//         System.out.println("End time in Minutes: \n");
//         timeMinutesOver = Integer.parseInt(input.next());
//         System.out.println("Type is: \n");
//         type = input.next();
//         System.out.println("Professor's name: \n");
//         professor = input.next();
//         System.out.println("Further description: \n");
//         courseDescription = input.next();
//         System.out.println("Term: \n");
//         term = input.next();
//         System.out.println("Credit: \n");
//         credit = Integer.parseInt(input.next());
//         Course course = new Course( courseName, 
//         timeHoursBegin, 
//         timeMinutesBegin,
//         timeHoursOver, 
//         timeMinutesOver, 
//         type, 
//         professor, 
//         courseDescription, 
//         date, 
//         term, 
//         credit);
//         return course;
//     }

//     //REQUIRES: continueInput must be a boolean and not null
//     //MODIFIES: continueInput
//     //EFFECTS: let user decide whther continue inserting course or event
//     private Boolean ifCountinue(Boolean continueInput){
//         System.out.println("continue?\n");
//         continueInput = Boolean.parseBoolean(input.next());
//         return continueInput;
//     }
// }
