import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

public class Teacher extends User {

    String name;
    String lastName;

    Hashtable<Course, ArrayList<Exam>> CourseExam = new Hashtable<>();

    // GUI attributes
    transient JPanel allPage;
    transient JPanel containerPanel;
    transient JPanel selectPanel;
    transient JPanel contentsPanel;
    transient JLabel selectText;



    @Override
    public void initializePage() {

        allPage = new JPanel(new BorderLayout());
        containerPanel = new JPanel(new GridBagLayout());
        selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentsPanel = new JPanel();
        selectText = new JLabel();

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        JLabel title = new JLabel("Teacher");
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
        //        selectText.setText("Select one to create");
        //        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.add(selectText);
        selectPanel.setVisible(false);


        // Student button
        JButton courseButton = new JButton("New Course");
        ImageIcon courseIcon = new ImageIcon("course.png");
        courseButton.setIcon(courseIcon);
        courseButton.setIconTextGap(10);
        courseButton.setFont(new Font("Arial", Font.BOLD, 20));
        courseButton.setVerticalTextPosition(SwingConstants.NORTH);
        courseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        courseButton.setFocusable(false);

        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewCourse();
            }
        });

        // Teacher button
        JButton examButton = new JButton("New Exam");
        ImageIcon examIcon = new ImageIcon("exam.png");
        examButton.setIcon(examIcon);
        examButton.setIconTextGap(10);
        examButton.setFont(new Font("Arial", Font.BOLD, 20));
        examButton.setVerticalTextPosition(SwingConstants.NORTH);
        examButton.setHorizontalTextPosition(SwingConstants.CENTER);
        examButton.setFocusable(false);

        examButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeExam();
            }
        });


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 10, 50, 10);
        gbc.ipadx = 200;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        contentsPanel.add(courseButton);
        contentsPanel.add(examButton);

        containerPanel.add(selectPanel, gbc);
        containerPanel.add(contentsPanel, gbc);

        allPage.add(containerPanel);

        MainFrame.frame.add(allPage, BorderLayout.CENTER);

        MainFrame.show();

    }


    Teacher(String name, String lastName, String username) {
        this.type = Type.Teacher;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.id = Main.database.generateNewId(this);
    }

    public void makeExam() {

        // ----------------Make Exam Page-------------------------

        contentsPanel.removeAll();
        contentsPanel.setLayout(new GridLayout(6, 1, 0, 0));
        contentsPanel.setLayout(new BoxLayout(contentsPanel, BoxLayout.Y_AXIS));
        contentsPanel.setBorder(new EmptyBorder(0, 20, 0, 20));

        selectText.setText("Create a new exam");
        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.setVisible(true);

        // Pre-configure panels' variable
        Font labelFonts = new Font("", Font.BOLD, 18);
        Font otherFonts = new Font("", Font.PLAIN, 14);
        JLabel secondPanelTextLabel = new JLabel();
        ArrayList<Problem> problems = new ArrayList<>();


        // First panel configuration

        JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel firstPanelLabel = new JLabel("Select course: ");
        firstPanelLabel.setFont(labelFonts);
        String[] firstPanelComboChoices = new String[CourseExam.keySet().size()];

        int i = 0;
        for (Course choice : CourseExam.keySet())
            firstPanelComboChoices[i++] = choice.title;

        JComboBox<String> firstPanelComboBox = new JComboBox<>(firstPanelComboChoices);
        firstPanelComboBox.setPrototypeDisplayValue("Click to open comboBox           ");
        firstPanelComboBox.setFont(otherFonts);
        JButton firstPanelOkButton = new JButton("OK");
        firstPanelOkButton.setFont(new Font("", Font.BOLD, 12));
        firstPanelOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondPanelTextLabel.setText(firstPanelComboBox.getItemAt(firstPanelComboBox.getSelectedIndex()));
            }
        });

        firstPanel.add(firstPanelLabel);
        firstPanel.add(firstPanelComboBox);
        firstPanel.add(firstPanelOkButton);

        // Second panel configuration

        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel secondPanelLabel = new JLabel("Course: ");
        secondPanelLabel.setFont(labelFonts);
        secondPanelTextLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        secondPanelTextLabel.setFont(otherFonts);
        secondPanel.add(secondPanelLabel);
        secondPanel.add(secondPanelTextLabel);

        // Third panel configuration

        JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel thirdPanelLabel = new JLabel("Select new question type: ");
        thirdPanelLabel.setFont(labelFonts);
        JButton[] thirdPanelButts = new JButton[4];
        thirdPanelButts[0] = new JButton("Test");
        thirdPanelButts[1] = new JButton("True|False");
        thirdPanelButts[2] = new JButton("EmptySpace");
        thirdPanelButts[3]  = new JButton("Descriptive");

//        int finalIn = in;
        thirdPanelButts[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewQuestion(((JButton)e.getSource()).getText(), problems);
            }
        });

        thirdPanelButts[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewQuestion(((JButton)e.getSource()).getText(), problems);
            }
        });

        thirdPanelButts[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewQuestion(((JButton)e.getSource()).getText(), problems);
            }
        });

        thirdPanelButts[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewQuestion(((JButton)e.getSource()).getText(), problems);

            }
        });

        thirdPanel.add(thirdPanelLabel);
        for (JButton button : thirdPanelButts)
            thirdPanel.add(button);

        // Fourth panel configuration

        JPanel fourthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel fourthPanelLabel = new JLabel("Exam name: ");
        fourthPanelLabel.setFont(labelFonts);
        JTextField examName = new JTextField(20);

        fourthPanel.add(fourthPanelLabel);
        fourthPanel.add(examName);

        // Sixth panel configuration
        JPanel sixthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton cancel = new JButton("Go Back");
        JButton submit = new JButton("Submit");

        sixthPanel.add(cancel);
        sixthPanel.add(submit);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFirstPage();
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int courseId = firstPanelComboBox.getSelectedIndex();

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Submit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (choice == 0) {
                    for (myData data : Main.database.allData) {
                        if (!(data instanceof Course))
                            continue;
                        if (data == CourseExam.keySet().toArray()[courseId]) {
                            ((Course) data).exams.add(new Exam(examName.getText(), (Course) data, problems));

                            CourseExam.get((Course) CourseExam.keySet().toArray()[courseId]).add(new Exam(examName.getText()
                                    , (Course) CourseExam.keySet().toArray()[courseId], problems));

//                            problems.clear();

                            break;
                        }
                    }
                }

            }
        });

        contentsPanel.add(firstPanel);

        contentsPanel.add(secondPanel);

        contentsPanel.add(fourthPanel);

        contentsPanel.add(thirdPanel);

        contentsPanel.add(sixthPanel);

        MainFrame.show();

    }

    public void makeNewQuestion(String QT, ArrayList<Problem> problems) {

            if (QT.equals("Test")){

            JTextArea firstArea = new JTextArea();
            JTextArea secondArea = new JTextArea();
            JTextArea thirdArea = new JTextArea();
            JTextArea fourthArea = new JTextArea();
            JTextArea fifthArea = new JTextArea();


            Object[] message = {
                    "Question title:", firstArea,
                    "First choice:", secondArea,
                    "Second choice:", thirdArea,
                    "Third choice:", fourthArea,
                    "Fourth choice:", fifthArea
            };

            int choice = JOptionPane.showConfirmDialog(null, message, QT, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (choice == 0) {

                SpinnerModel newModel = new SpinnerNumberModel(1, 1, 4, 1);
                JSpinner answerSpinner = new JSpinner(newModel);

                Object[] solutionMessage = {
                        "Select correct answer", answerSpinner
                };

                int choiceSolution = JOptionPane.showConfirmDialog(null, solutionMessage, QT + "'s answer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (choiceSolution == 0) {

                    String[] choices = new String[4];
                    choices[0] = secondArea.getText();
                    choices[1] = thirdArea.getText();
                    choices[2] = fourthArea.getText();
                    choices[3] = fifthArea.getText();

                    Problem newProblem = new ProblemTest(firstArea.getText(), new SolutionTest((int) answerSpinner.getValue()) , choices);
                    problems.add(newProblem);
                }
            }

        } else if (QT.equals("True|False")) {

            // True|False Question

            JTextArea firstArea = new JTextArea();

            Object[] message = {
                    "Question title:", firstArea,
            };

            int choice = JOptionPane.showConfirmDialog(null, message, QT, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (choice == 0) {

                final boolean[] answer = new boolean[1];

                JToggleButton trueButt = new JToggleButton("True");
                JToggleButton falseButt = new JToggleButton("False");

                ArrayList<JToggleButton> butts = new ArrayList<>();
                butts.add(trueButt);
                butts.add(falseButt);

                trueButt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        answer[0] = true;
                        butts.get(1).setSelected(false);
                    }
                });

                falseButt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        answer[0] = false;
                        butts.get(0).setSelected(false);
                    }
                });

                Object[] solutionMessage = {
                        "Select answer", trueButt, falseButt
                };

                int choiceSolution = JOptionPane.showConfirmDialog(null, solutionMessage, QT + "'s answer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (choiceSolution == 0) {

                    Problem newProblem = new ProblemTrueFalse(firstArea.getText(), new SolutionTrueFalse(answer[0]));
                    problems.add(newProblem);

                }
            }

        } else if (QT.equals("EmptySpace")) {

            JTextArea firstArea = new JTextArea();
            JTextArea secondArea = new JTextArea();

            Object[] message = {
                    "Question title:", firstArea,
                    "Type word that you want to be out:", secondArea,
            };

            int choice = JOptionPane.showConfirmDialog(null, message, QT, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (choice == 0) {
                Problem newProblem = new ProblemBlank(firstArea.getText(), new SolutionBlank(secondArea.getText()), secondArea.getText());
                problems.add(newProblem);
            }

        } else if (QT.equals("Descriptive")) {

            JTextArea firstArea = new JTextArea();

            Object[] message = {
                    "Question title:", firstArea,
            };

            int choice = JOptionPane.showConfirmDialog(null, message, QT, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (choice == 0) {

                JTextArea answer = new JTextArea(5, 40);
                Object[] solutionMessage = {
                        "Write answer: ", answer
                };

                int choiceSolution = JOptionPane.showConfirmDialog(null, solutionMessage, QT + "'s answer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (choiceSolution == 0) {

                    Problem newProblem = new ProblemDescriptive(firstArea.getText(), new SolutionDescriptive(answer.getText()));
                    problems.add(newProblem);
                }

            }

        }


    }


    public void makeNewCourse() {

        // ----------------Make Course Page-------------------------

        contentsPanel.removeAll();
        contentsPanel.setLayout(new GridLayout(7, 2, 10, 20));

        selectText.setText("Create a new course");
        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.setVisible(true);

        // Date picker configure

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        UtilDateModel midtermModel = new UtilDateModel();
        UtilDateModel finalModel = new UtilDateModel();

        JDatePanelImpl midtermDatePanel = new JDatePanelImpl(midtermModel, p);
        JDatePanelImpl finalDatePanel = new JDatePanelImpl(finalModel, p);


        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField();

        ArrayList<String> tempLessons = new ArrayList<>();
        JLabel lessonsLabel = new JLabel("Lesson: ");
        JTextField lessonField = new JTextField();
        lessonField.setToolTipText("Press Enter to add lesson");

        lessonField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempLessons.add(lessonField.getText());
                JOptionPane.showMessageDialog(null,
                        "Lesson (" + lessonField.getText() + ") Has been added.",
                        "New lesson", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ArrayList<String> tempSources = new ArrayList<>();
        JLabel sourcesLabel = new JLabel("Sources: ");
        JTextField sourcesField = new JTextField();
        sourcesField.setToolTipText("Press Enter to add source");

        sourcesField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempSources.add(sourcesField.getText());
                JOptionPane.showMessageDialog(null,
                        "Source (" + sourcesField.getText() + ") Has been added.",
                        "New Source", JOptionPane.INFORMATION_MESSAGE);

            }
        });


        JLabel midtermDateLabel = new JLabel("Midterm Date: ");
        JDatePickerImpl midtermDatePicker = new JDatePickerImpl(midtermDatePanel, new DateLabelFormatter());

        JLabel finalDateLabel = new JLabel("Final Date: ");
        JDatePickerImpl finalDatePicker = new JDatePickerImpl(finalDatePanel, new DateLabelFormatter());


        JButton cancel = new JButton("Go Back");
        JButton submit = new JButton("Submit");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFirstPage();
            }
        });

        Teacher thisTeacher = this;
        final Course[] newCourse = new Course[1];



        JLabel semesterLabel = new JLabel("Select semester: ");
        ArrayList<Semester> semesterComboChoices = new ArrayList<>();

        for (myData data : Main.database.allData) {
            if (data instanceof Semester)
                semesterComboChoices.add((Semester) data);
        }

        String[] semesterTitles = new String[semesterComboChoices.size()];

        for (int i = 0; i < semesterComboChoices.size(); i++)
            semesterTitles[i] = semesterComboChoices.get(i).title;


        JComboBox<String> semesterComboBox = new JComboBox<>(semesterTitles);
        semesterComboBox.setPrototypeDisplayValue("Click to open comboBox           ");
        semesterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Semester (" + semesterComboBox.getItemAt(semesterComboBox.getSelectedIndex()) + ") selected.");
                Semester semester = semesterComboChoices.get(semesterComboBox.getSelectedIndex());
                semester.courses.add(newCourse[0]);

            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = titleField.getText();
                Date midterm = (Date) midtermDatePicker.getModel().getValue();
                Date finalDate = (Date) finalDatePicker.getModel().getValue();

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Submit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (choice == 0) {
                    Semester newSemester = semesterComboChoices.get(semesterComboBox.getSelectedIndex());
                            newCourse[0] = new Course(title, midterm, finalDate, tempLessons, tempSources, thisTeacher, newSemester);
                    CourseExam.put(newCourse[0], new ArrayList<>());
                }

            }
        });


        contentsPanel.add(titleLabel);
        contentsPanel.add(titleField);

        contentsPanel.add(midtermDateLabel);
        contentsPanel.add(midtermDatePicker);

        contentsPanel.add(finalDateLabel);
        contentsPanel.add(finalDatePicker);


        contentsPanel.add(lessonsLabel);
        contentsPanel.add(lessonField);

        contentsPanel.add(sourcesLabel);
        contentsPanel.add(sourcesField);

        contentsPanel.add(semesterLabel);
        contentsPanel.add(semesterComboBox);

        contentsPanel.add(cancel);
        contentsPanel.add(submit);

        MainFrame.show();

    }

}


