package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.ListOfCourses;
import model.Course;
import model.ListOfCaseForWeek;
import model.CaseToDo;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    private JPanel infoPanel;
    private JPanel inputPanel;
    private Map<String, JPanel> dayPanels;

        private static final ArrayList<String> week = new ArrayList<>(
            Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));

    public GraphicalUserInterface() {
        super("Course Schedule");

        this.courseList = new ListOfCourses();
        this.weekSchedule = new ListOfCaseForWeek();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 450));
        setLayout(new BorderLayout());
        dayPanels = new HashMap<>();
        // Panel for days of the week (left side)
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

        JPanel titlePanel = new JPanel(new GridLayout(1, 7, 5, 5));
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JPanel coursePanel = createCoursePanel();

        for (String day : week) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            titlePanel.add(dayLabel);
        }
        infoPanel.add(titlePanel, BorderLayout.NORTH);
        infoPanel.add(coursePanel,BorderLayout.CENTER);
        return infoPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Button at the top of the input panel
        JButton inputButton = new JButton("Input");
        inputButton.addActionListener(this);
        inputButton.setPreferredSize(new Dimension(100, 30));
        inputButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button

        inputPanel.add(Box.createVerticalStrut(10)); // Spacer between button and text field

        addPanels(inputPanel);
        
        inputPanel.add(inputButton);
        return inputPanel;
    }

    public JPanel createCoursePanel() {
        JPanel coursePanel = new JPanel(new GridLayout(1, 7, 5, 5));
        
        // Initialize day panels for each day in coursePanel
        for (String day : week) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            coursePanel.add(dayPanel);
            dayPanels.put(day, dayPanel);
        }
        return coursePanel;
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
        weekSchedule.fillWeek(courseList);
    }

    private void fillPanel() {
        ArrayList<CaseToDo> courses = courseList.getList();
        for (CaseToDo c : courses) {
            Course course = (Course) c;
            String date = course.getDate();
            String courseInfo = printCourse(course);
            // if (dayPanels.containsKey(date)) {
            //     JLabel courseLabel = new JLabel(courseInfo);
            //     courseLabel.setHorizontalAlignment(SwingConstants.CENTER);
            //     dayPanels.get(date).add(courseLabel);
            //     infoPanel.revalidate();
            //     infoPanel.repaint();
            // }
            JLabel courseLabel = new JLabel(courseInfo);
            courseLabel.setHorizontalAlignment(SwingConstants.LEFT);
            courseLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
            infoPanel.add(courseLabel);
            infoPanel.revalidate();
            infoPanel.repaint();
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
}
