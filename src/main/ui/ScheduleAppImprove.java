package ui;

import model.*;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exceptions.illegalInputException;

//Schedule application
public class ScheduleAppImprove {
    private static final String JSON_WEEKSCHEDULE = "./data/weekSchedule.json";
    private ListOfEvents eventList;
    private ListOfCourses courseList;
    private ListOfCaseForWeek weekSchedule;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final ArrayList<String> week = new ArrayList<>(
            Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
    private static final ArrayList<String> term = new ArrayList<>(
            Arrays.asList("Term1", "Term2", "Summer"));
    
    //EFFECTS: run the schedule application
    public ScheduleAppImprove() {
        this.jsonWriter = new JsonWriter(JSON_WEEKSCHEDULE);
        this.jsonReader = new JsonReader(JSON_WEEKSCHEDULE);
        this.courseList = new ListOfCourses();
        this.eventList = new ListOfEvents();
        this.weekSchedule = new ListOfCaseForWeek();
        this.input = new Scanner(System.in);
        displayLoop();
    }

        //MODIFIES: this
    //EFFECTS: display the main page of the program, and quit when user want to exit
    private void displayLoop() {
        boolean wantKeep = true;
        while (wantKeep) {
            displayManual();
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("e")) {
                break;
            } else {
                displayFunctions(respon);
                new CheckTime(weekSchedule);
            }
        }
    }

    //EFFECTS: diaplay functionality that is available
    private void displayManual() {
        // System.out.println("e -> exit");
        // System.out.println("input case -> add course or event");
        // System.out.println("daily arrangement -> arrangement for the a day");
        // System.out.println("delete item -> manage the daily arrangement");
        // System.out.println("detail ->  case in detail");
        // System.out.println("check credit -> check course's credit");
        // System.out.println("check number of imortant event -> check number of important event");
        // System.out.println("print course list -> print the course list");
        // System.out.println("save files -> save changes to files");
        // System.out.println("load files -> load files");
        
        System.out.println("========== MENU ==========");
        System.out.println("e       -> Exit the program");
        System.out.println("a       -> Add a course or event");
        System.out.println("d       -> View arrangements for a specific day");
        System.out.println("m       -> Delete a case from daily arrangement");
        System.out.println("v       -> View case details");
        System.out.println("c       -> Check course credits");
        System.out.println("i       -> Count important events");
        System.out.println("p       -> Print the course list");
        System.out.println("s       -> Save changes to files");
        System.out.println("l       -> Load saved files");
        System.out.println("GUI     -> show user interface");
        System.out.println("===========================");
    }



    //MODIFIES: this
    /*EFFECTS: add the case from list week days into the eventlist and courselist according 
     * to the actual type
    */
    private void fillCaseList(CaseToDo caseToDo) {
        if (caseToDo instanceof Event) {
            eventList.addCase(caseToDo);
        } else {
            courseList.addCase(caseToDo);
        }
    }



    //MODIFIES: this
    //EFFECTS: choose the functionality to run with the given respons
    private void displayFunctions(String respon) {
        if (respon.equals("a")) {
            addCase();
        } else if (respon.equals("d")) {
            dailyArrange();
        } else if (respon.equals("m")) {
            manipulateCase();
        } else if (respon.equals("v")) {
            lookDetail();
        } else if (respon.equals("c")) {
            int totalCredit  = courseList.calculateCredit();
            System.out.println("The total credit is: " + totalCredit);
        } else if (respon.equals("i")) {
            int numImportantEvent = eventList.calculateImportant();
            System.out.println("there are " + numImportantEvent + " important upcoming events");
        } else if (respon.equals("s")) {
            saveFiles();
        } else if (respon.equals("l")) {
            loadFiles();
        } else if (respon.equals("p")) {
            printCourseList();
        } else if (respon.equals("gui")) {
            new GraphicalUserInterface();
        }
    }

    //MODIFIES: this
    //EFFECTS: add courses or events in the courseList and eventList according to their type
    private void addCase() {
        System.out.println("Add course or event in your schedule\n");
        while (true) {
            // checkType();
            boolean invalidInput = false;
            do {
                try {
                    switch (checkTypeCase()) {
                        case "course": runCourse(); 
                        break;
                        case "event": runEvent(); 
                        break;
                        case "e": break;
                    }
                } catch (illegalInputException e) {
                    invalidInput = true;
                } 
            } while (invalidInput);
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("e")) {
                break;
            }
        }
        weekSchedule.fillWeek(courseList);
        weekSchedule.fillWeek(eventList);
    }

    //MODIFIES: this
    //EFFECTS: let users choose which type of case they are adding, course or event
    private String checkTypeCase() throws illegalInputException {
        System.out.println("Which type of case would you prefer to take?\ncourse\nevent\n");
        String caseType = input.nextLine().toLowerCase();
        if (caseType.equals("course") || caseType.equals("event") || caseType.equals("e")) {
            return caseType;
        } else {
            throw new illegalInputException();
        }
    }

        //MODIFIES: this
    //EFFECTS: keep adding course until the user does not want to add anymore
    private void runCourse() {
        while (true) {
            CaseToDo course = getCourse();
            courseList.addCase(course);
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("e")) {
                System.out.println("Finish adding courses, exis by typing 'e', keep adding by click enter");
                break;
            }
        }
    }

    //EFFECTS: create a course object with the information user input
    private CaseToDo getCourse() {
        System.out.println("input information for the course: \n");
        String name = getName(); 
        int timeHoursBegin = getNum("What's the begin hour");
        int timeMinutesBegin = getNum("What's the begin minute");
        int timeHoursOver = getNum("What's the over hour");
        int timeMinutesOver = getNum("What's the over minute");
        String date = getOneFromList("What is the date? (Mon, Tue, Wed, Thu, Fri, Sat, Sun)", week);
        String type = getInfo("What type is it?");
        String professor = getInfo("Who is the professor?");
        String courseTerm = getOneFromList("What is the term? (Term1, Term2, Summer)", term);
        int credit = getNum("What's the credit");
        String place = getInfo("What's the place?");
        String description = getInfo("Further description?");
        CaseToDo course = new Course(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, 
                                timeMinutesOver, description, date, type, professor, courseTerm,
                                credit, place);
        System.out.println("Finish creating course " + name + " exit by typing 'e' keep adding by click enter");
        return course;
    }

    //MODIFIES: this
    //EFFECTS: keep adding event until the user does not want to add anymore
    private void runEvent() {
        while (true) {
            CaseToDo event = getEvent();
            
            eventList.addCase(event);
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("e")) {
                System.out.println("Finish adding events, exis by typing 'e'");
                break;
            } 
        }
    }

    //EFFECTS: create an event object with the information user input
    private CaseToDo getEvent() {
        System.out.println("input information for the event: \n");
        String name = getName(); 
        int timeHoursBegin = getNum("What's the begin hour");
        int timeMinutesBegin = getNum("What's the begin minute");
        int timeHoursOver = getNum("What's the over hour");
        int timeMinutesOver = getNum("What's the over minute");
        String date = getOneFromList("What is the date? (Mon, Tue, Wed, Thu, Fri, Sat, Sun)", week);
        String place = getInfo("What's the place");
        String description = getInfo("Further description?");
        CaseToDo event = new Event(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, 
                                timeMinutesOver, description, date,place);
        System.out.println("Finish creating event " + name + " exit by typing 'e' keep adding by click enter");
        return event;
    }

    //EFFECTS: return the name of case
    public String getName() {
        System.out.println("What's the name?\n");
        return input.nextLine();
    }

    //EFFECTS: return the hour part of the start time
    public int getNum(String que) {
        int beginHour = 0;
        boolean wrongNumber = false;
        do {
            System.out.println(que + " ?\n");
            try {
                beginHour = Integer.parseInt(input.nextLine());
                wrongNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer: " + e.getMessage());
                wrongNumber = true;
            }
        } while (wrongNumber);

        return beginHour;
    }

    public String getOneFromList(String que, ArrayList<String> list) {
        System.out.println(que + " \n");
        String respon = "";
        boolean illegalInput = false;
        do {
            illegalInput = false;
            String inputAnswer = input.nextLine();
            if (list.contains(inputAnswer)) {
                respon = inputAnswer; 
            } else {
                illegalInput = true;
                System.out.println("Wrong input, try again");
            }
        } while (illegalInput);
        return respon;
    }

    public String getInfo(String info) {
        System.out.println(info + "\n");
        return input.nextLine();
    }

    //EFFECTS: print our course and event list for a certain day
    private void dailyArrange() {
        System.out.println("Which day's arrangement would you want to check?\n");
        String date = input.nextLine();
        ArrayList<CaseToDo> dateList = weekSchedule.getDateList(date);
        for (CaseToDo c: dateList) {
            String caseName = c.getName();
            int timeHoursBegin = c.getTimeHoursBegin();
            int timeMinutesBegin = c.getTimeMinutesBegin();
            int timeHoursOver = c.getTimeHoursOver();
            int timeMinutesOver = c.getTimeMinutesOver();
            String startMinute = String.valueOf(timeMinutesBegin);
            String endMinute = String.valueOf(timeMinutesOver); 
            if (timeMinutesBegin < 10) {
                startMinute = "0" + startMinute;
            }
            if (timeMinutesOver < 10) {
                endMinute = "0" + endMinute;
            }
            System.out.println(caseName + " " + timeHoursBegin + ":"
                            + startMinute + " - " + timeHoursOver + endMinute);
        }
    }

    //MODIFIES: this
    //EFFECTS: add or remove an event or course in the schedule
    private void manipulateCase() {
        boolean keepMoodify = true;
        while (keepMoodify) {
            System.out.println("which day would you modify?\nMon, Tue, Wed, Thu, Fri, Sat, Sun");
            String date = input.nextLine();
            ArrayList<CaseToDo> dateListToModify = weekSchedule.getDateList(date);
            System.out.println("Which type of case would you prefer to modify?\ncourse\nevent\n");
            String caseType = input.nextLine();
            if (caseType.equals("course")) {
                System.out.println("what's the course's name?\n");
                String courseNameToChange = input.nextLine();
                modifyCourse(courseNameToChange, dateListToModify);
            } else if (caseType.equals("event")) {
                System.out.println("What's the event's name?\n");
                String eventNameToChange = input.nextLine();
                modifyEvent(eventNameToChange, dateListToModify);
            }
            System.out.println("Do you want to keep modify?");
            keepMoodify = Boolean.parseBoolean(input.nextLine());
        }
    }

    //MODIFIES: this
    //EFFECTS: remove a course
    private void modifyCourse(String courseToChange, ArrayList<CaseToDo> dateListToModify) {
        CaseToDo caseToRemove = null;
        for (CaseToDo c: dateListToModify) {
            if (c.getName().equals(courseToChange)) {
                caseToRemove = c;
            }
        }
        dateListToModify.remove(caseToRemove);
        courseList.getList().remove(caseToRemove);
    }


    //MODIFIES: this
    //EFFECTS: remove an event
    private void modifyEvent(String eventToChange, ArrayList<CaseToDo> dateListToModify) {
        CaseToDo caseToRemove = null;
        for (CaseToDo c: dateListToModify) {
            if (c.getName().equals(eventToChange)) {
                caseToRemove = c;
            }
        }
        dateListToModify.remove(caseToRemove);    
    }

    //EFFECTS: print name, time and place and description for the case
    private void lookDetail() {
        System.out.println("Which type of case would you prefer to see in detail?\ncourse\nevent\n");
        String caseType = input.nextLine();
        if (caseType.equals("course")) {
            lookCourseDetail();
        } else if (caseType.equals("event")) {
            lookEventDetail();
        }
    }

    //EFFECTS: print name, time and place and description for the course
    private void lookCourseDetail() {
        System.out.println("What is the name of the course?");
        String courseName = input.nextLine();
        System.out.println("What is this course's type?");
        String courseType = input.nextLine();
        for (CaseToDo c : courseList.getList()) {
            Course element = (Course) c;
            if (element.getName().equals(courseName)
                    && element.getType().equals(courseType)) {
                System.out.println(printCourse(element)); 
            }
        }
    }
    
    //EFFECTS: print name, time and place and description for the event
    private void lookEventDetail() {
        System.out.println("What is the event?");
        String eventName = input.nextLine();
        for (CaseToDo c : eventList.getList()) {
            Event element = (Event) c;
            if (element.getName().equals(eventName)) {
                printEvent(element);
            }
        }
    }

    //EFFECTS: print name, time and place and description for the course
    private String printCourse(Course course) {
        String name = course.getName();
        int startHour = course.getTimeHoursBegin();
        int startMinute = course.getTimeMinutesBegin();
        int endHour = course.getTimeHoursOver();
        int endMinute = course.getTimeMinutesOver();
        String professor = course.getProfessor();
        String type = course.getType();
        String place = course.getPlace();
        String description = course.getDescription();
        String beginMinute = String.valueOf(startMinute);
        String overMinute = String.valueOf(endMinute); 
        if (startMinute < 10) {
            beginMinute = "0" + beginMinute;
        }
        if (endMinute < 10) {
            overMinute = "0" + overMinute;
        }
        return name + "\n" + " " + startHour + ":"
            + beginMinute + " - " + endHour + overMinute + "\n"
            + professor + " " + place + " " + type + " " + description;
    }

    //EFFECTS: print name, time and place and description for the event
    private void printEvent(Event event) {
        String name = event.getName();
        int startHour = event.getTimeHoursBegin();
        int startMinute = event.getTimeMinutesBegin();
        int endHour = event.getTimeHoursOver();
        int endMinute = event.getTimeMinutesOver();
        String place = event.getPlace();
        String description = event.getDescription();
        String beginMinute = String.valueOf(startMinute);
        String overMinute = String.valueOf(endMinute); 
        if (startMinute < 10) {
            beginMinute = "0" + beginMinute;
        }
        if (endMinute < 10) {
            overMinute = "0" + overMinute;
        }
        System.out.println(name + "\n" + " " + startHour + ":"
                        + beginMinute + " - " + endHour + overMinute + "\n" 
                        + place + " " + description);
    }

    //EFFECTS: print the list of course user has
    public void printCourseList() {
        System.out.println("in which term's course list would you like to print? (Term1, Term2, Summer)");
        String termWant = input.nextLine();
        ArrayList<CaseToDo> listOfCourse = courseList.getList();
        for (CaseToDo c : listOfCourse) {
            Course course = (Course) c;
            String term = course.getTerm();
            if (term.equals(termWant)) {
                System.out.println(printCourse(course));
            }
        }
    }

    //EFFECTS:save the week schedule
    public void saveFiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(weekSchedule);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }

    }

    //EFFETCTS: load the week schedule
    public void loadFiles() {
        try {
            weekSchedule = jsonReader.read();
            fillLists();
            System.out.println("Loaded event list from " + JSON_WEEKSCHEDULE);
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    //MODIFIES: this
    /*EFFECTS: add cases from the week schedule in the course and event list
     *          according to the actual type of case
    */
    private void fillLists() {
        ArrayList<ArrayList<CaseToDo>> listWeek = new ArrayList<ArrayList<CaseToDo>>();
        listWeek.add(weekSchedule.getMon() != null ? weekSchedule.getMon() : new ArrayList<>());
        listWeek.add(weekSchedule.getTue() != null ? weekSchedule.getTue() : new ArrayList<>());
        listWeek.add(weekSchedule.getWed() != null ? weekSchedule.getWed() : new ArrayList<>());
        listWeek.add(weekSchedule.getThu() != null ? weekSchedule.getThu() : new ArrayList<>());
        listWeek.add(weekSchedule.getFri() != null ? weekSchedule.getFri() : new ArrayList<>());
        listWeek.add(weekSchedule.getSat() != null ? weekSchedule.getSat() : new ArrayList<>());
        listWeek.add(weekSchedule.getSun() != null ? weekSchedule.getSun() : new ArrayList<>());
        for (ArrayList<CaseToDo> loc : listWeek) {
            for (CaseToDo c: loc) {
                fillCaseList(c);
            }
        }
    }

        // //MODIFIES: this
    // //EFFECTS: let users choose which type of case they are adding, course or event
    // private void checkType() {
    //     System.out.println("Which type of case would you prefer to take?\ncourse\nevent\n");
    //     String caseType = input.nextLine();
    //     runType(caseType);
    // }

    // //REQUIRES: case Type must be one of: course, event
    // //MODIFIES: this
    // //EFFECTS: run methods to add course or event according to the type given
    // private void runType(String caseType) {
    //     if (caseType.equals("course")) {
    //         runCourse();
    //     } else if (caseType.equals("event")) {
    //         runEvent();
    //     }
    // }

    // //EFFECTS: return the description of the case
    // private String getDescription() {
    //     System.out.println("Further description?\n");
    //     return input.nextLine();
    // }

    // //EFFECTS: return the date of the case
    // private String getDate() {
    //     System.out.println("Add a date: \n");
    //     return input.nextLine();
    // }



    // //EFFECTS: get the type of the course, ex: lecture, lab...
    // private String getType() {
    //     System.out.println("What type is it?\n");
    //     return input.nextLine();
    // }

    // //EFFECTS: get the name of the professor
    // private String getProfessor() {
    //     System.out.println("Who is the professor?\n");
    //     return input.nextLine();
    // }

    // //EFFECTS: get the term it starts
    // private String getTerm() {
    //     System.out.println("What's the term?\n");
    //     return input.nextLine();
    // }

    // //EFFECTS: get the credit of the course
    // private int getCredit() {
    //     System.out.println("What's the credit?\n");
    //     return Integer.parseInt(input.nextLine());
    // }

    // //EFFECTS: get the plae where the case takes place in
    // private String getPlace() {
    //     System.out.println("What's the place?\n");
    //     return input.nextLine();
    // }
}
