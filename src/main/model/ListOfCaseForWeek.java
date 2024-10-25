package model;

import java.util.ArrayList;
import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;



public class ListOfCaseForWeek implements Writable {
    private ArrayList<CaseToDo> caseListMon;
    private ArrayList<CaseToDo> caseListTue;
    private ArrayList<CaseToDo> caseListWed;
    private ArrayList<CaseToDo> caseListThu;
    private ArrayList<CaseToDo> caseListFri;
    private ArrayList<CaseToDo> caseListSat;
    private ArrayList<CaseToDo> caseListSun;
    
    //EFFECTS: create seven list of case to do for each day
    public ListOfCaseForWeek() {
        caseListMon = new ArrayList<CaseToDo>();
        caseListTue = new ArrayList<CaseToDo>();
        caseListWed = new ArrayList<CaseToDo>();
        caseListThu = new ArrayList<CaseToDo>();
        caseListFri = new ArrayList<CaseToDo>();
        caseListSat = new ArrayList<CaseToDo>();
        caseListSun = new ArrayList<CaseToDo>();
    }

    //REQUIRES: caseMon must not be null
    //MODIFIES: this
    //EFFECTS: Add a case for Monday
    public void planMon(CaseToDo caseMon) {
        if (!caseListMon.contains(caseMon)) {
            caseListMon.add(caseMon);
        }
    }

    //REQUIRES: caseTue must not be null
    //MODIFIES: this
    //EFFECTS: Add a case for TuesDay
    public void planTue(CaseToDo caseTue) {
        if (!caseListTue.contains(caseTue)) {
            caseListTue.add(caseTue);
        }
    }

    //REQUIRES: caseWed must not be null
    //MODIFIES: this
    //EFFECTS: Add a case for WednesDay
    public void planWed(CaseToDo caseWed) {
        if (!caseListWed.contains(caseWed)) {
            caseListWed.add(caseWed);
        }
    }
    
    //REQUIRES: caseThu must not be null
    //MODIFIES: this
    //EFFECTS: Add a case for ThusDay
    public void planThu(CaseToDo caseThu) {
        if (!caseListThu.contains(caseThu)) {
            caseListThu.add(caseThu);
        }
    }
    
    //REQUIRES: caseFri must not be null
    //MODIFIES: this
    //EFFECTS: Add a case for FriDay
    public void planFri(CaseToDo caseFri) {
        if (!caseListFri.contains(caseFri)) {
            caseListFri.add(caseFri);
        }
    }

    //REQUIRES: caseSat must not be null
    //MODIFIES: this
    //EFFECTS: Add a case for SaturDay
    public void planSat(CaseToDo caseSat) {
        if (!caseListSat.contains(caseSat)) {
            caseListSat.add(caseSat);
        }
    }
    
    //REQUIRES: caseSun must not be null
    //MODIFIES: this
    //EFFECTS: Add a case for TuesDay
    public void planSun(CaseToDo caseSun) {
        if (!caseListSun.contains(caseSun)) {
            caseListSun.add(caseSun);
        }
    }

    //EFFECTS: Return the arrangement for Monday
    public ArrayList<CaseToDo> getMon() {
        return caseListMon;
    }

    //EFFECTS: return the arrangement for Tuesday
    public ArrayList<CaseToDo> getTue() {
        return caseListTue;
    }

    //EFFECTS: return the arrangement for WednesDay
    public ArrayList<CaseToDo> getWed() {
        return caseListWed;
    }

    //EFFECTS: return the arrangement for Thusday
    public ArrayList<CaseToDo> getThu() {
        return caseListThu;
    }

    //EFFECTS: return the arrangement for Friday
    public ArrayList<CaseToDo> getFri() {
        return caseListFri;
    }

    //EFFECTS: return the arrangement for Saturday
    public ArrayList<CaseToDo> getSat() {
        return caseListSat;
    }

    //EFFECTS: return the arrangement for Sunday
    public ArrayList<CaseToDo> getSun() {
        return caseListSun;
    }
    
    //REQUIRES: caseList must not be null
    //MODIFIES: this
    //EFFECTS: fill the cases in the certain day list
    public void fillWeek(ListOfCase cases) {
        ArrayList<CaseToDo> caseList = cases.getList();
        for (CaseToDo x: caseList) {
            String s = x.getDate();
            if (s.equals("Mon")) {
                planMon(x);
            } else if (s.equals("Tue")) {
                planTue(x);
            } else if (s.equals("Wed")) {
                planWed(x);
            } else if (s.equals("Thu")) {
                planThu(x);
            } else if (s.equals("Fri")) {
                planFri(x);
            } else if (s.equals("Sat")) {
                planSat(x);
            } else {
                planSun(x);
            }
        }
    }

    //REQUIRES: date must be one of "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
    //EFFECTS: return the certain arrangement of a day with given input
    public ArrayList<CaseToDo> getDateList(String date) {
        if (date.equals("Mon")) {
            return caseListMon;
        } else if (date.equals("Tue")) {
            return caseListTue;
        } else if (date.equals("Wed")) {
            return caseListWed;
        } else if (date.equals("Thu")) {
            return caseListThu;
        } else if (date.equals("Fri")) {
            return caseListFri;
        } else if (date.equals("Sat")) {
            return caseListSat;
        } else {
            return caseListSun;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        ArrayList<ArrayList<CaseToDo>> listWeek = new ArrayList<ArrayList<CaseToDo>>();
        listWeek.add(caseListMon);
        listWeek.add(caseListTue);
        listWeek.add(caseListWed);
        listWeek.add(caseListThu);
        listWeek.add(caseListFri);
        listWeek.add(caseListSat);
        listWeek.add(caseListSun);
        json.put("week schedules", caseListToJson(listWeek));
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray caseListToJson(ArrayList<ArrayList<CaseToDo>> listWeek) {
        JSONArray jsonArray = new JSONArray();

        for (ArrayList<CaseToDo> loc : listWeek) {
            JSONObject daySchedule = new JSONObject();
            int index = listWeek.indexOf(loc);
            if (index == 0) {
                daySchedule.put("Mon Schedule", caseToJson(loc));
            } else if (index == 1) {
                daySchedule.put("Tue Schedule", caseToJson(loc));
            } else if (index == 2) {
                daySchedule.put("Wed Schedule", caseToJson(loc));
            } else if (index == 3) {
                daySchedule.put("Thu Schedule", caseToJson(loc));
            } else if (index == 4) {
                daySchedule.put("Fri Schedule", caseToJson(loc));
            } else if (index == 5) {
                daySchedule.put("Sat Schedule", caseToJson(loc));
            } else if (index == 6) {
                daySchedule.put("Sun Schedule", caseToJson(loc));
            }
            jsonArray.put(daySchedule);
        }

        return jsonArray;
    }

    private JSONArray caseToJson(ArrayList<CaseToDo> loc) {
        JSONArray jsonArraySecond = new JSONArray();
        for (CaseToDo c: loc) {
            jsonArraySecond.put(c.toJson());
        }
        return jsonArraySecond;
    }
}
