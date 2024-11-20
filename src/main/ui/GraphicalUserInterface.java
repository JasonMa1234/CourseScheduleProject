package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.ListOfCourses;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Course;
import model.ListOfCaseForWeek;
import model.CaseToDo;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GraphicalUserInterface extends JFrame implements ActionListener {
    private ListOfCourses courseList;
    private ListOfCaseForWeek weekSchedule;
    private JTextField txtCourseName = new JTextField(10);
    private JTextField txtDate = new JTextField(10);
    private JTextField txtType = new JTextField(10);
    private JTextField txtBeginHour = new JTextField(10);
    private JTextField txtBeginMinute = new JTextField(10);
    private JTextField txtEndHour = new JTextField(10);
    private JTextField txtEndMinute = new JTextField(10);
    private JTextField txtProfessor = new JTextField(10);
    private JTextField txtTerm = new JTextField(10);
    private JTextField txtCredit = new JTextField(10);
    private JTextField txtDescription = new JTextField(10);
    private JTextField txtPlace = new JTextField(10);
    private JTable courseTable = new JTable();
    private JPanel infoPanel;
    private JPanel inputPanel;
    private Map<String, JPanel> dayPanels;
    private static final String JSON_WEEKSCHEDULE = "./data/weekSchedule.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    private static final ArrayList<String> week = new ArrayList<>(
            Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));

    public GraphicalUserInterface() {
        super("Course Schedule");

        this.courseList = new ListOfCourses();
        this.weekSchedule = new ListOfCaseForWeek();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1300, 500));
        setLayout(new BorderLayout());
        dayPanels = new HashMap<>();
        // Panel for days of the week (left side)
        this.jsonWriter = new JsonWriter(JSON_WEEKSCHEDULE);
        this.jsonReader = new JsonReader(JSON_WEEKSCHEDULE);

        infoPanel = createInfoPanel();

        // Panel for input section (right side)
        inputPanel = createInputPanel();

        // Add schedule and input panels to the main layout
        add(infoPanel, BorderLayout.WEST);  // Days of the week on the left
        add(inputPanel, BorderLayout.EAST);       // Button and input fields on the right

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        infoPanel.setPreferredSize(new Dimension(1000, 500));
        String[] columnNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        Object[][] data = new Object[10][7];;
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setRowHeight(50);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new MultiLineTableCellRenderer());
        }
        courseTable = table;
        JScrollPane scrollPane = new JScrollPane(table);
        infoPanel.add(scrollPane, BorderLayout.CENTER);
        return infoPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setPreferredSize(new Dimension(300, 500));
        JButton inputButton = createButton("Input");
        JButton saveButton = createButton("SaveCourse");
        JButton loadButton = createButton("LoadCourse");

        inputPanel.add(Box.createVerticalStrut(10)); 

        addPanels(inputPanel);
        
        inputPanel.add(inputButton);
        inputPanel.add(saveButton);
        inputPanel.add(loadButton);


        return inputPanel;
    }

    private JButton createButton(String type) {
        JButton button = new JButton(type);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(100, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); 
        button.setActionCommand(type);
        return button;
    }

    private void addPanels(JPanel inputPanel) {
        inputPanel.add(createPanel("Course Name: ",txtCourseName));
        inputPanel.add(createPanel("Date: ",txtDate));
        inputPanel.add(createPanel("Type: ", txtType));
        inputPanel.add(createPanel("Time Begin (In Hour): ", txtBeginHour));
        inputPanel.add(createPanel("Time Begin (In Minute): ", txtBeginMinute));
        inputPanel.add(createPanel("Time Over (In Hour): ", txtEndHour));
        inputPanel.add(createPanel("Time Over (In Minute): ", txtEndMinute));
        inputPanel.add(createPanel("Professor: ", txtProfessor));
        inputPanel.add(createPanel("Term: ", txtTerm));
        inputPanel.add(createPanel("Credit: ", txtCredit));
        inputPanel.add(createPanel("Place: ", txtPlace));
        inputPanel.add(createPanel("Description: ", txtDescription));
    }

    //MODIFIES: txtField
    //EFFECTS: add the field in the GUI
    private JPanel createPanel(String name, JTextField txtField) {
        JPanel courseNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));  // Left-align components in this row
        JLabel courseLabel = new JLabel(name);
        courseNamePanel.add(courseLabel);
        courseNamePanel.add(txtField);
        
        return courseNamePanel;
    }

    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Input")) {
            addCourse();
            weekSchedule.fillWeek(courseList);
            fillPanel();
        } else if (e.getActionCommand().equals("SaveCourse")) {
            saveCourse();
        } else if (e.getActionCommand().equals("LoadCourse")) {
            loadCourse();
            fillPanel();
        }
    }

    private void addCourse() {
        String name = getInfo(0); 
        int timeHoursBegin = getNum(0);
        int timeMinutesBegin = getNum(1);
        int timeHoursOver = getNum(2);
        int timeMinutesOver = getNum(3);
        String date = getInfo(1);
        String type = getInfo(2);
        String professor = getInfo(3);
        String courseTerm = getInfo(4);
        int credit = getNum(4);
        String place = getInfo(5);
        String description = getInfo(6);
        CaseToDo course = new Course(name, timeHoursBegin, timeMinutesBegin, timeHoursOver, 
                                timeMinutesOver, description, date, type, professor, courseTerm,
                                credit, place);  
        courseList.addCase(course);
    }

    private void fillPanel() {
        ArrayList<CaseToDo>[] weekCourses = new ArrayList[]{
            weekSchedule.getMon(),
            weekSchedule.getTue(),
            weekSchedule.getWed(),
            weekSchedule.getThu(),
            weekSchedule.getFri(),
            weekSchedule.getSat(),
            weekSchedule.getSun()
        };
        for (int i = 0; i < weekCourses.length; i++) {
            ArrayList<CaseToDo> courses = weekCourses[i];
            courses.sort(Comparator.comparingInt(CaseToDo::getTimeHoursBegin)
                    .thenComparingInt(CaseToDo::getTimeMinutesBegin));
            fillInCourse(i, courses);
        }
    }

    private void fillInCourse(int numCol, ArrayList<CaseToDo> listCourses) {
        int rows = 0;

        for (CaseToDo c : listCourses) {
            Course course = (Course) c;
            String info = printCourse(course);
            courseTable.setValueAt(info, rows, numCol);
            rows++;
        }
    }

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

    public String getInfo(int input) {
        if (input == 0) {
            return txtCourseName.getText();
        } else if (input == 1) {
            return txtDate.getText();
        } else if (input == 2) {
            return txtType.getText();
        } else if (input == 3) {
            return txtProfessor.getText();
        } else if (input == 4) {
            return txtTerm.getText();
        } else if (input == 5) {
            return txtPlace.getText();
        } else {
            return txtDescription.getText();
        }
    }

    public int getNum(int num) {
        if (num == 0) {
            return Integer.parseInt(txtBeginHour.getText());
        } else if (num == 1) {
            return Integer.parseInt(txtBeginMinute.getText());
        } else if (num == 2) {
            return Integer.parseInt(txtEndHour.getText());
        } else if (num == 3) {
            return Integer.parseInt(txtEndMinute.getText());
        } else {
            return Integer.parseInt(txtCredit.getText());
        }
    }

    private void saveCourse() {
        try {
            jsonWriter.open();
            jsonWriter.write(weekSchedule);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }

    }

    private void loadCourse() {
        try {
            weekSchedule = jsonReader.read();
            fillLists();
            System.out.println("Loaded event list from " + JSON_WEEKSCHEDULE);
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

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
                courseList.addCase(c);
            }
        }
    }
}
