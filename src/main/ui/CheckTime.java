package ui;


import java.util.ArrayList;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.CaseToDo;
import model.ListOfCaseForWeek;


public class CheckTime {

    private LocalTime currentTime;
    private int minute;
    private int hour;
    private LocalDate today;
    private int dayOfWeekNum;
    private ListOfCaseForWeek weekSchedule;

    public CheckTime(ListOfCaseForWeek weekSchedule) {
        this.currentTime = LocalTime.now();//from chatGPT
        this.minute = currentTime.getMinute();
        this.hour = currentTime.getHour();
        this.today = LocalDate.now();  
        this.dayOfWeekNum = today.getDayOfWeek().getValue();  
        this.weekSchedule = weekSchedule;
        getWeekDay();
    }

    private void getWeekDay() {
        if (dayOfWeekNum == 1) {
            checkWeekSchedule(weekSchedule.getMon());
        } else if (dayOfWeekNum == 2) {
            checkWeekSchedule(weekSchedule.getTue());
        } else if (dayOfWeekNum == 3) {
            checkWeekSchedule(weekSchedule.getWed());
        } else if (dayOfWeekNum == 4) {
            checkWeekSchedule(weekSchedule.getThu());
        } else if (dayOfWeekNum == 5) {
            checkWeekSchedule(weekSchedule.getFri());
        } else if (dayOfWeekNum == 6) {
            checkWeekSchedule(weekSchedule.getSat());
        } else if (dayOfWeekNum == 7) {
            checkWeekSchedule(weekSchedule.getSun());
        }
    }

    private void checkWeekSchedule(ArrayList<CaseToDo> caseList) {
        for (CaseToDo c : caseList) {
            String name = c.getName();
            int beginHour = c.getTimeHoursBegin();
            int beginMinute = c.getTimeMinutesBegin();
            if (beginHour == hour) {
                if ((minute + 30) > beginMinute) {
                    int timeLeft = beginMinute - minute;
                    System.out.println(name + " is beginning in " + timeLeft + " minutes");
                } else if (minute > beginMinute) {
                    int timeOver = minute - beginMinute;
                    System.out.println("you are " + timeOver + " late for " + name);
                } else {
                    System.out.println(name + " is starting!");
                }
            } else if (hour > beginHour) {
                System.out.println("You miss " + name);
            }
        }
    }
}
