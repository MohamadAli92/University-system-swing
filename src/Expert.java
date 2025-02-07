import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

public class Expert extends User {

    transient JPanel allPage;
    transient JPanel containerPanel;
    transient JPanel selectPanel;
    transient JPanel contentsPanel;
    transient JLabel selectText;

    // GUI functions
    @Override
    public void initializePage() {

        allPage = new JPanel(new BorderLayout());
        containerPanel = new JPanel(new GridBagLayout());
        selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentsPanel = new JPanel();
        selectText = new JLabel();

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        JLabel title = new JLabel("Expert");
        title.setFont(new Font("", Font.BOLD, 35));
        titlePanel.add(title);
        titlePanel.setBackground(Color.PINK);

        allPage.add(titlePanel, BorderLayout.NORTH);

        makeFirstPage();
    }

    public void makeFirstPage() {


        // ----------------First Page-------------------------

        contentsPanel.removeAll();
        contentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ((FlowLayout)contentsPanel.getLayout()).setHgap(50);

        // Select one of these label
        selectText.setText("Select one to create");
        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.add(selectText);

        // Student button
        JButton studentButton = new JButton("Student");
        ImageIcon studentIcon = new ImageIcon(getClass().getResource("student.png"));
        studentButton.setIcon(studentIcon);
        studentButton.setIconTextGap(10);
        studentButton.setFont(new Font("Arial", Font.BOLD, 20));
        studentButton.setVerticalTextPosition(SwingConstants.NORTH);
        studentButton.setHorizontalTextPosition(SwingConstants.CENTER);
        studentButton.setFocusable(false);

        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTeacherOrStudent(Type.Student);
            }
        });

        // Teacher button
        JButton teacherButton = new JButton("Teacher");
        ImageIcon teacherIcon = new ImageIcon(getClass().getResource("teacher.png"));
        teacherButton.setIcon(teacherIcon);
        teacherButton.setIconTextGap(10);
        teacherButton.setFont(new Font("Arial", Font.BOLD, 20));
        teacherButton.setVerticalTextPosition(SwingConstants.NORTH);
        teacherButton.setHorizontalTextPosition(SwingConstants.CENTER);
        teacherButton.setFocusable(false);

        teacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTeacherOrStudent(Type.Teacher);
            }
        });

        // Semester button
        JButton semesterButton = new JButton("Semester");
        ImageIcon semesterIcon = new ImageIcon(getClass().getResource("semester.png"));
        semesterButton.setIcon(semesterIcon);
        semesterButton.setIconTextGap(10);
        semesterButton.setFont(new Font("Arial", Font.BOLD, 20));
        semesterButton.setVerticalTextPosition(SwingConstants.NORTH);
        semesterButton.setHorizontalTextPosition(SwingConstants.CENTER);
        semesterButton.setFocusable(false);

        semesterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeSemester();
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 10, 50, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        contentsPanel.add(teacherButton);
        contentsPanel.add(studentButton);
        contentsPanel.add(semesterButton);

        containerPanel.add(selectPanel, gbc);
        containerPanel.add(contentsPanel, gbc);

        allPage.add(containerPanel);

        MainFrame.frame.add(allPage, BorderLayout.CENTER);

        MainFrame.show();
    }


    public void makeSemester() {

        // ----------------Make Semester Page-------------------------

        contentsPanel.removeAll();
        contentsPanel.setLayout(new GridLayout(5, 2, 10, 20));

        selectText.setText("Create a new semester");

        // Date picker configure

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        UtilDateModel startModel = new UtilDateModel();
        UtilDateModel endModel = new UtilDateModel();
        UtilDateModel scoreModel = new UtilDateModel();

        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);
        JDatePanelImpl scoreDatePanel = new JDatePanelImpl(scoreModel, p);


        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField();


        JLabel startDateLabel = new JLabel("Start Date: ");
        JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());

        JLabel endDateLabel = new JLabel("End Date: ");
        JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

        JLabel scoreDateLabel = new JLabel("Score submission Date: ");
        JDatePickerImpl scoreDatePicker = new JDatePickerImpl(scoreDatePanel, new DateLabelFormatter());

        JButton cancel = new JButton("Go Back");
        JButton submit = new JButton("Submit");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFirstPage();
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = titleField.getText();
                Date start = (Date) startDatePicker.getModel().getValue();
                Date end = (Date) endDatePicker.getModel().getValue();
                Date score = (Date) scoreDatePicker.getModel().getValue();

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Submit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (choice == 0) {
                    new Semester(title, score, start, end);
                }

            }
        });

        contentsPanel.add(titleLabel);
        contentsPanel.add(titleField);

        contentsPanel.add(startDateLabel);
        contentsPanel.add(startDatePicker);

        contentsPanel.add(endDateLabel);
        contentsPanel.add(endDatePicker);

        contentsPanel.add(scoreDateLabel);
        contentsPanel.add(scoreDatePicker);

        contentsPanel.add(cancel);
        contentsPanel.add(submit);

        MainFrame.show();
    }

    public void makeTeacherOrStudent(Type userType) {

        // ------------------Make teacher Page---------------
        contentsPanel.removeAll();
        contentsPanel.setLayout(new GridLayout(4, 2, 10, 20));

        if (userType == Type.Teacher)
            selectText.setText("Create a new teacher");
        else if (userType == Type.Student)
            selectText.setText("Create a new student");


        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameField = new JTextField();

        JLabel userNameLabel = new JLabel("Username: ");
        JTextField userNameField = new JTextField();

        JButton cancel = new JButton("Go Back");
        JButton submit = new JButton("Submit");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFirstPage();
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String lastName = lastNameField.getText();
                String username = userNameField.getText();

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Submit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (choice == 0) {
                    if (userType == Type.Teacher)
                        new Teacher(name, lastName, username);
                    else
                        new Student(name, lastName, username);
                }

            }
        });

        contentsPanel.add(nameLabel);
        contentsPanel.add(nameField);

        contentsPanel.add(lastNameLabel);
        contentsPanel.add(lastNameField);

        contentsPanel.add(userNameLabel);
        contentsPanel.add(userNameField);

        contentsPanel.add(cancel);
        contentsPanel.add(submit);

        MainFrame.show();

    }

// Only student maker

//    public void makeStudent() {
//
//        // ------------------Make Student Page---------------
//        contentsPanel.removeAll();
//        contentsPanel.setLayout(new GridLayout(4, 2, 10, 20));
//
//        selectText.setText("Create a new student");
//
//        JLabel nameLabel = new JLabel("Name: ");
//        JTextField nameField = new JTextField();
//
//        JLabel lastNameLabel = new JLabel("Last Name: ");
//        JTextField lastNameField = new JTextField();
//
//        JLabel userNameLabel = new JLabel("Username: ");
//        JTextField userNameField = new JTextField();
//
//        JButton cancel = new JButton("Go Back");
//        JButton submit = new JButton("Submit");
//
//        cancel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                makeFirstPage();
//            }
//        });
//
//        submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                String name = nameField.getText();
//                String lastName = lastNameField.getText();
//                String username = userNameField.getText();
//
//                int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Submit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
//                if (choice == 0) {
//                    new Teacher(name, lastName, username);
//                }
//
//            }
//        });
//
//        contentsPanel.add(nameLabel);
//        contentsPanel.add(nameField);
//
//        contentsPanel.add(lastNameLabel);
//        contentsPanel.add(lastNameField);
//
//        contentsPanel.add(userNameLabel);
//        contentsPanel.add(userNameField);
//
//        contentsPanel.add(cancel);
//        contentsPanel.add(submit);
//
//        MainFrame.show();
//    }

}

