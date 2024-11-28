package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.IOException;

//class that runs GUI
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
    private static final String JSON_WEEKSCHEDULE = "./data/weekSchedule.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ImageIcon term1;
    private ImageIcon term2;
    private ImageIcon summer;
    private JLabel imageAsLabel;
    private JPanel imagePanel;

    //EFFECTS: initialize panels in the user interface
    public GraphicalUserInterface() {
        super("Course Schedule");

        this.courseList = new ListOfCourses();
        this.weekSchedule = new ListOfCaseForWeek();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1300, 700));
        setLayout(new BorderLayout());
        this.jsonWriter = new JsonWriter(JSON_WEEKSCHEDULE);
        this.jsonReader = new JsonReader(JSON_WEEKSCHEDULE);

        infoPanel = createInfoPanel();

        inputPanel = createInputPanel();

        add(infoPanel, BorderLayout.WEST);  
        add(inputPanel, BorderLayout.EAST);     

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        loadImages();
    }

    //EFFECTS: create the panel that contains information displayed
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        infoPanel.setPreferredSize(new Dimension(1000, 700));
        String[] dateTitle = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        Object[][] tableCells = new Object[10][7];;
        DefaultTableModel tableModel = new DefaultTableModel(tableCells, dateTitle);
        JTable table = new JTable(tableModel);
        
        table.setRowHeight(50);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ChangingTableLinesCellRenderer());
        }
        courseTable = table;
        JScrollPane scrollPane = new JScrollPane(table);
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(1000, 50));
        imagePanel.setLayout(new FlowLayout());
        infoPanel.add(imagePanel, BorderLayout.NORTH);
    
        this.imagePanel = imagePanel;

        return infoPanel;
    }

    //EFFECTS: create panel that display the input part of the user interface
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setPreferredSize(new Dimension(300, 700));
        JButton inputButton = createButton("Input");
        JButton saveButton = createButton("SaveCourse");
        JButton loadButton = createButton("LoadCourse");
        JButton showTerm1Button = createButton("LoadCourse(Term1)");
        JButton showTerm2Button = createButton("LoadCourse(Term2)");
        JButton showSummerButton = createButton("LoadCourse(Summer)");


        inputPanel.add(Box.createVerticalStrut(10)); 

        addPanels(inputPanel);
        
        inputPanel.add(inputButton);
        inputPanel.add(saveButton);
        inputPanel.add(loadButton);
        inputPanel.add(showTerm1Button);
        inputPanel.add(showTerm2Button);
        inputPanel.add(showSummerButton);



        return inputPanel;
    }

    //EFFECTS: create Button that aligned center and with width 100 and height 30
    private JButton createButton(String type) {
        JButton button = new JButton(type);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(100, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); 
        button.setActionCommand(type);
        return button;
    }

    //MODIFIES: this
    //EFFECTS: add input blank in to the input panel
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
    //EFFECTS: create courseLabel being contained in the course Name Panel
    private JPanel createPanel(String name, JTextField txtField) {
        JPanel courseNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        JLabel courseLabel = new JLabel(name);
        courseNamePanel.add(courseLabel);
        courseNamePanel.add(txtField);
        
        return courseNamePanel;
    }

    //This is the method that is called when the the JButton btn is clicked
    // From 
    //https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Input")) {
            addCourse();
            weekSchedule.fillWeek(courseList);
            fillPanel("All");
            imagePanel.removeAll();
        } else if (e.getActionCommand().equals("SaveCourse")) {
            saveCourse();
        } else if (e.getActionCommand().equals("LoadCourse")) {
            clearTable();
            loadCourse();
            fillPanel("All");
            imagePanel.removeAll();
        } else if (e.getActionCommand().equals("LoadCourse(Term1)")) {
            performAction("Term1");
        } else if (e.getActionCommand().equals("LoadCourse(Term2)")) {
            performAction("Term2");
        } else if (e.getActionCommand().equals("LoadCourse(Summer)")) {
            performAction("Summer");
        }
    }

    //MODIFIES: this
    //EFFECTS: perform command input
    private void performAction(String term) {
        clearTable();
        // loadCourse();
        if (term.equals("Term1")) {
            fillPanel("Term1");
            setTerm1();
        } else if (term.equals("Term2")) {
            fillPanel("Term2");
            setTerm2();
        } else if (term.equals("Summer")) {
            fillPanel("Summer");
            setSummer();
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    public void setTerm1() {
        imagePanel.removeAll();
        imageAsLabel = new JLabel(term1);
        imagePanel.add(imageAsLabel);
        imagePanel.revalidate(); 
        imagePanel.repaint();
    }

    // from https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    public void setTerm2() {
        imagePanel.removeAll();
        imageAsLabel = new JLabel(term2);
        imagePanel.add(imageAsLabel);
        imagePanel.revalidate(); 
        imagePanel.repaint();
    }

    // from https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    public void setSummer() {
        imagePanel.removeAll();
        imageAsLabel = new JLabel(summer);
        imagePanel.add(imageAsLabel);
        imagePanel.revalidate(); 
        imagePanel.repaint();
    }

    //MODIFIES: this
    //EFFECTS: Extract the course information
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
        boolean canAdd = checkIfAbleToAdd(date, timeHoursBegin, timeMinutesBegin, 
                timeHoursOver, timeMinutesOver, courseTerm);
        if (canAdd) {
            courseList.addCase(course);
        }
    }

    //EFFECTS: check whether courses have time conflicts in one day
    private boolean checkIfAbleToAdd(String date, 
            int hourBegin, int minuteBegin, int hourOver, int minuteOver, String courseTerm) {
        boolean canAdd = true;
        for (CaseToDo c : courseList.getList()) {
            int timeHoursBeginCourseAdded = c.getTimeHoursBegin();
            int timeMinutesBeginCourseAdded = c.getTimeMinutesBegin();
            int timeHoursOverCourseAdded = c.getTimeHoursOver();
            int timeMinutesOverCourseAdded = c.getTimeMinutesOver();
            if (c.getDate().equals(date)) {
                Course courseInList = (Course) c;
                String term = courseInList.getTerm();
                if (term.equals(courseTerm)) {
                    canAdd = timeConflict(timeHoursBeginCourseAdded, timeMinutesBeginCourseAdded,
                    timeHoursOverCourseAdded, timeMinutesOverCourseAdded,
                    hourBegin, minuteBegin,
                    hourOver, minuteOver);
                    if (!canAdd) {
                        return canAdd;
                    }
                }
            }
        }
        return canAdd;
    }

    //EFFECTS: check whether two cases have time conflict with the given start and end time.
    public boolean timeConflict(int hourBegin1, int minuteBegin1, int hourOver1, int minuteOver1,
                                int hourBegin2, int minuteBegin2, int hourOver2, int minuteOver2) {
        double beginTime1 = hourBegin1 + 0.01 * minuteBegin1;
        double endTime1 = hourOver1 + 0.01 * minuteOver1;
        double beginTime2 = hourBegin2 + 0.01 * minuteBegin2;
        double endTime2 = hourOver2 + 0.01 * minuteOver2;
        
        return beginTime1 >= endTime2 || beginTime2 >= endTime1;
    }

    //MODIFIES: this
    //EFFECTS: fill the planel according to given term
    private void fillPanel(String term) {
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
            fillInCourse(i, courses, term);
        }
    }

    //MODIFIES: this
    //EFFECTS: fill the planel according to given term
    private void fillInCourse(int numCol, ArrayList<CaseToDo> listCourses, String term) {
        int rows = 0;

        if (term.equals("All")) {
            for (CaseToDo c : listCourses) {
                Course course = (Course) c;
                String info = printCourse(course);
                courseTable.setValueAt(info, rows, numCol);
                rows++;
            }
        } else {
            for (CaseToDo c : listCourses) {
                Course course = (Course) c;
                String info = printCourse(course);
                String courseTerm = course.getTerm();
                if (courseTerm.equals(term)) {
                    courseTable.setValueAt(info, rows, numCol);
                }
                rows++;
            }
        }
    }

    //EFFECTS: return the course's basic information
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
            + beginMinute + " - " + endHour + ":" + overMinute + "\n"
            + professor + " " + place + " " + type + " " + description;
    }

    //EFFECTS: get text information from text field
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

    //EFFECTS: get numeric information from the text field
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

    //MODIFIES: this
    //EFFECTS: save the course into Json file
    private void saveCourse() {
        try {
            jsonWriter.open();
            jsonWriter.write(weekSchedule);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }

    }

    //MODIFIES: this
    //EFFECTS load the courses from the Json file and fill them into the table
    private void loadCourse() {
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
                courseList.addCase(c);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: clear all texts in the table cells
    private void clearTable() {
        TableModel model = courseTable.getModel();
        int rows = model.getRowCount();
        int cols = model.getColumnCount();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                courseTable.setValueAt("", row, col); 
            }
        }
    }

    // from https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        term1 = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "Term1.png");
        term2 = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "Term2.png");
        summer = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "Summer.png");
    }
}
