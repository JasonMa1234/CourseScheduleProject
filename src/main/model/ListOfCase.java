package model;

import java.util.ArrayList;

public class ListOfCase {
    protected ArrayList<CaseToDo> caseList;

    //create an empty ListOfCase
    public ListOfCase() {
        caseList = new ArrayList<CaseToDo>();
    }

    //REQUIRES: case cannot be null
    //MODIFIES: this
    //EFFECTS: add a case into the case list
    public void addCase(CaseToDo newCase) {
        String name = newCase.getName();
        caseList.add(newCase);
        EventLog.getInstance().logEvent(new Event("Add new case " + name + " into the caseList"));
    }

    //EFFECTS: return the case list
    public ArrayList<CaseToDo> getList() {
        return caseList;
    }

    //EFFECTS: remove a case from the caseList 
    public void removeCase(String caseToChange, String date) {
        ArrayList<CaseToDo> caseList = getList();
        CaseToDo caseToRemove = null;
        for (CaseToDo c: caseList) {
            if (c.getName().equals(caseToChange) && c.getDate().equals(date)) {
                caseToRemove = c;
            }
        }
        String name = caseToRemove.getName();
        caseList.remove(caseToRemove);    
        EventLog.getInstance().logEvent(new Event("remove one case " + name + " from the caseList"));
    }
}
