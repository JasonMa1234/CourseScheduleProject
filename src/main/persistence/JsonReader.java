package persistence;

import model.CaseToDo;
import model.ListOfCaseForWeek;
import java.util.ArrayList;
import model.ListOfCaseForWeek;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
import org.junit.experimental.categories.Category;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfCaseForWeek read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWeekSchedule(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private ListOfCaseForWeek parseWeekSchedule(JSONObject jsonObject) {
        ListOfCaseForWeek listWeek = new ListOfCaseForWeek();
        JSONArray jsonArrayWeek = jsonObject.getJSONArray("week schedules");
    
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};//from chatGPT
    
        for (int i = 0; i < jsonArrayWeek.length(); i++) {
            JSONObject jsonObjDay = jsonArrayWeek.getJSONObject(i);
            
            for (String day : days) {
                String scheduleKey = day + " Schedule"; 
                
                if (jsonObjDay.has(scheduleKey)) {
                    JSONArray jsonArray = jsonObjDay.getJSONArray(scheduleKey);
                    ArrayList<CaseToDo> caseList = new ArrayList<>();
    
                    for (Object json : jsonArray) {
                        JSONObject jsonObj = (JSONObject) json;
                        addCases(caseList, jsonObj);
                    }
    
                    for (CaseToDo c : caseList) {
                        switch (day) {
                            case "Mon": listWeek.planMon(c); break;
                            case "Tue": listWeek.planTue(c); break;
                            case "Wed": listWeek.planWed(c); break;
                            case "Thu": listWeek.planThu(c); break;
                            case "Fri": listWeek.planFri(c); break;
                            case "Sat": listWeek.planSat(c); break;
                            case "Sun": listWeek.planSun(c); break;
                        }
                    }
                }
            }
        }
    
        return listWeek;
    }
    

    private ArrayList<CaseToDo> addCases(ArrayList<CaseToDo> caseListMon, JSONObject jsonObject) {
        String name = String.valueOf(jsonObject.getString("name"));
        int startHour = Integer.valueOf(jsonObject.getInt("start hour"));
        int startMinute = Integer.valueOf(jsonObject.getInt("start minute"));
        int overHour = Integer.valueOf(jsonObject.getInt("end hour"));
        int overMinute = Integer.valueOf(jsonObject.getInt("end minute"));
        String date = String.valueOf(jsonObject.getString("date"));
        String description = String.valueOf(jsonObject.getString("description"));
        String place = String.valueOf(jsonObject.getString("place"));
        CaseToDo caseToDo = new CaseToDo(name, startHour, startMinute, overMinute, overHour, description, date, place);
        caseListMon.add(caseToDo);
        return caseListMon;
    }

}
