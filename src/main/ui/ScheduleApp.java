package ui;

import model.*;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Schedule application
public class ScheduleApp {
    private static final String JSON_WEEKSCHEDULE = "./data/weekSchedule.json";
    private ListOfEvents eventList;
    private ListOfCourses courseList;
    private ListOfCaseForWeek weekSchedule;
    private ListOfCase caseList;
    private Scanner input;
    private static ListOfDate monWedFri = new ListOfDate();
    private static ListOfDate tueThu = new ListOfDate();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static Course stat = new Course("STAT", 
            14, 
            0, 
            15, 
            0, 
            "lecture", 
                    "Mon", 
            "statistic course", 
            "A", 
            "Winter1", 
            3,
            "ESB 1000");
    
    //EFFECTS: run the schedule application
    public ScheduleApp() {
        // fillLists();
        this.jsonWriter = new JsonWriter(JSON_WEEKSCHEDULE);
        this.jsonReader = new JsonReader(JSON_WEEKSCHEDULE);
        this.courseList = new ListOfCourses();
        this.eventList = new ListOfEvents();
        this.weekSchedule = new ListOfCaseForWeek();
        input = new Scanner(System.in);
        displayLoop();
        
        
        // monWedFri.addDate("Mon");
        // monWedFri.addDate("Wed");
        // monWedFri.addDate("Fri");
        // tueThu.addDate("Tue");
        // tueThu.addDate("Thu");

        // runSchedule();
    }

    private void fillLists() {
        ArrayList<ArrayList<CaseToDo>> listWeek = new ArrayList<ArrayList<CaseToDo>>();
        listWeek.add(weekSchedule.getMon());
        listWeek.add(weekSchedule.getTue());
        listWeek.add(weekSchedule.getWed());
        listWeek.add(weekSchedule.getThu());
        listWeek.add(weekSchedule.getFri());
        listWeek.add(weekSchedule.getSat());
        listWeek.add(weekSchedule.getSun());
        for (ArrayList<CaseToDo> loc : listWeek) {
            for (CaseToDo c: loc) {
                fillCaseList(c);
            }
        }
    }

    private void fillCaseList(CaseToDo caseToDo) {
        if (caseToDo instanceof Event) {
            eventList.addCase(caseToDo);
        } else {
            courseList.addCase(caseToDo);
        }
    }

    private void displayLoop() {
        boolean wantKeep = true;
        while (wantKeep) {
            displayManual();
            String respon = input.nextLine().toLowerCase();
            displayFunctions(respon);
            new CheckTime(weekSchedule);
            System.out.println("Do you want to exist?(yes/no)");
            String wantToExist = input.nextLine().toLowerCase();

            if (wantToExist.equals("yes")) {
                wantKeep = false;
            } else if (wantToExist.equals("no")) {
                wantKeep = true;
            }
        }
    }

    private void displayManual() {
        System.out.println("input case -> add course or event");
        System.out.println("daily arrangement -> arrangement for the a day");
        System.out.println("add or delete item -> manage the daily arrangement");
        System.out.println("detail ->  case in detail");
        System.out.println("check credit -> check course's credit");
        System.out.println("check number of imortant event -> check number of important event");
        System.out.println("save files -> save changes to files");
        System.out.println("load files -> load files");
    }

    private void displayFunctions(String respon) {
        if (respon.equals("input case")) {
            addCase();
        } else if (respon.equals("daily arrangement")) {
            dailyArrange();
        } else if (respon.equals("add or delete item")) {
            manipulateCase();
        } else if (respon.equals("detail")) {
            lookDetail();
        } else if (respon.equals("check credit")) {
            int totalCredit  = courseList.calculateCredit();
            System.out.println("The total credit is: " + totalCredit);
        } else if (respon.equals("check number of imortant event")) {
            int numImportantEvent = eventList.calculateImportant();
            System.out.println("there are " + numImportantEvent + " important upcoming events");
        } else if (respon.equals("save files")) {
            saveFiles();
        } else if (respon.equals("load files")) {
            loadFiles();
        }
    }

    private void addCase() {
        boolean wantAdd = true;
        System.out.println("Add course or event in your schedule\n");
        while (wantAdd) {
            checkType();
            System.out.println("Do you want to add more?");
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("yes")) {
                wantAdd = true;
            } else if (respon.equals("no")) {
                wantAdd = false;
            }
        }
        weekSchedule.fillWeek(courseList);
        weekSchedule.fillWeek(eventList);
    }

    private void checkType() {
        System.out.println("Which type of case would you prefer to take?\ncourse\nevent\n");
        String caseType = input.nextLine();
        runType(caseType);
    }

    private void runType(String caseType) {
        if (caseType.equals("course")) {
            runCourse();
        } else if (caseType.equals("event")) {
            runEvent();
        }
    }

    private void runCourse() {
        boolean wantAdd = true;
        while (wantAdd) {
            CaseToDo course = getCourse();
            courseList.addCase(course);
            System.out.println("Do you want to add more Course?");
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("yes")) {
                wantAdd = true;
            } else if (respon.equals("no")) {
                wantAdd = false;
            }
        }
    }

    private CaseToDo getCourse() {
        System.out.println("input information for the course: \n");
        String name = getName(); 
        int timeHoursBegin = getBeginHour();
        int timeMinutesBegin = getBeginMinute();
        int timeHoursOver = getOverHour();
        int timeMinutesOver = getOverMinute();
        String date = getDate();
        String type = getType();
        String professor = getProfessor();
        String term = getTerm();
        int credit = getCredit();
        String place = getPlace();
        String description = getDescription();
        CaseToDo course = new Course(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, 
                                timeMinutesOver, description, date, type, professor, term,
                                credit, place);
        return course;
    }

    private void runEvent() {
        boolean wantAdd = true;
        while (wantAdd) {
            CaseToDo event = getEvent();
            
            eventList.addCase(event);
            System.out.println("Do you want to add more event?");
            String respon = input.nextLine().toLowerCase();
            if (respon.equals("yes")) {
                wantAdd = true;
            } else if (respon.equals("no")) {
                wantAdd = false;
            }
        }
    }

    private CaseToDo getEvent() {
        System.out.println("input information for the event: \n");
        String name = getName(); 
        int timeHoursBegin = getBeginHour();
        int timeMinutesBegin = getBeginMinute();
        int timeHoursOver = getOverHour();
        int timeMinutesOver = getOverMinute();
        String date = getDate();
        String place = getPlace();
        String description = getDescription();
        CaseToDo event = new Event(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, 
                                timeMinutesOver, description, date,place);
        return event;
    }


    private String getName() {
        System.out.println("What's the name?\n");
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

    private String getDate() {
        System.out.println("Add a date: \n");
        return input.nextLine();
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

    private void manipulateCase() {
        boolean keepMoodify = true;
        while (keepMoodify) {
            System.out.println("which day would you modify?\nMon, Tue, Wed, Thu, Fri, Sat, Sun");
            String date = input.nextLine();
            ArrayList<CaseToDo> dateListToModify = weekSchedule.getDateList(date);
            System.out.println("Which type of case would you prefer to modify?\ncourse\nevent\n");
            String caseType = input.nextLine();
            if (caseType.equals("course")) {
                CaseToDo courseToChange = getCourse();
                modifyCourse(courseToChange, dateListToModify);
            } else if (caseType.equals("event")) {
                CaseToDo eventToChange = getEvent();
                modifyEvent(eventToChange, dateListToModify);
            }
            System.out.println("Do you want to keep modify?");
            keepMoodify = Boolean.parseBoolean(input.nextLine());
        }
    }

    private void modifyCourse(CaseToDo courseToChange, ArrayList<CaseToDo> dateListToModify) {
        dateListToModify.remove(courseToChange);
    }

    private void modifyEvent(CaseToDo eventToChange, ArrayList<CaseToDo> dateListToModify) {
        dateListToModify.remove(eventToChange);
    }

    private void lookDetail() {
        System.out.println("Which type of case would you prefer to see in detail?\ncourse\nevent\n");
        String caseType = input.nextLine();
        if (caseType.equals("course")) {
            lookCourseDetail();
        } else if (caseType.equals("event")) {
            lookEventDetail();
        }
    }

    private void lookCourseDetail() {
        System.out.println("What is the name of the course?");
        String courseName = input.nextLine();
        System.out.println("What is this course's type?");
        String courseType = input.nextLine();
        for (CaseToDo c : courseList.getList()) {
            Course element = (Course) c;
            if (element.getName().equals(courseName)
                    && element.getType().equals(courseType)) {
                printCourse(element);
            }
        }
    }
    
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

    private void printCourse(Course course) {
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
        System.out.println(name + "\n" + " " + startHour + ":"
                        + beginMinute + " - " + endHour + overMinute + "\n"
                        + professor + " " + place + " " + type + " " + description);
    }

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

    public void saveFiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(weekSchedule);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Unable to write to file");
        }

    }

    public void loadFiles() {
        try {
            weekSchedule = jsonReader.read();
            System.out.println("Loaded event list from " + JSON_WEEKSCHEDULE);
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }
}
