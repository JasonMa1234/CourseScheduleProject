package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListOfCase {
    protected ArrayList<CaseToDo> caseList;

    //create an empty ListOfCase
    public ListOfCase() {
        caseList = new ArrayList<CaseToDo>();
    }

    //REQUIRES: case cannot be null
    //MODIFIES: this
    //EFFECTS: add a case into the case list
    public void addCase (CaseToDo newCase) {
        caseList.add(newCase);
    }

    //EFFECTS: return the case list
    public ArrayList<CaseToDo> getList(){
        return caseList;
    }
}
