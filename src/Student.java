import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Student extends User {

    String name;
    String lastName;

    ArrayList<Course> courses;

    // GUI attributes
    transient JPanel allPage;
    transient JPanel containerPanel;
    transient JPanel selectPanel;
    transient JPanel contentsPanel;
    transient JLabel selectText;


    Student(String name, String lastName, String username) {
        super.type = Type.Student;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        courses = new ArrayList<>();
        super.id = Main.database.generateNewId(this);
    }

    @Override
    public void initializePage() {

        allPage = new JPanel(new BorderLayout());
        containerPanel = new JPanel(new GridBagLayout());
        selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentsPanel = new JPanel();
        selectText = new JLabel();

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        JLabel title = new JLabel("Student");
        title.setFont(new Font("", Font.BOLD, 35));
        titlePanel.add(title);
        titlePanel.setBackground(Color.PINK);

        allPage.add(titlePanel, BorderLayout.NORTH);

        makeFirstPage();

    }

    public void makeFirstPage() {
        contentsPanel.removeAll();
        contentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ((FlowLayout)contentsPanel.getLayout()).setHgap(50);

        selectPanel.add(selectText);
        selectPanel.setVisible(false);

        JButton courseButton = new JButton("Sign up on Course");
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
                signUpOnCourse();
            }
        });

        // Teacher button
        JButton examButton = new JButton("Attempt an exam");
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
                attemptOnExam();
            }
        });


        GridBagConstraints gbc = new GridBagConstraints();
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

    public void signUpOnCourse() {

        // ----------------Sign up on course-------------------------

        ArrayList<Course> availableCourses = new ArrayList<>();
        for (myData newData : Main.database.allData) {
            if (newData.type == Type.Course && !this.courses.contains((Course) newData))
                availableCourses.add((Course) newData);
        }

        contentsPanel.removeAll();

        selectText.setText("Sign up on a course");
        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.setVisible(true);

        String[] cols = new String[]{"No.", "Course Name", "Teacher's Name"};
        String[][] coursesData = new String[availableCourses.size()][3];
        for (int i = 0; i < availableCourses.size(); i++)
            coursesData[i] = new String[]{String.valueOf(i), availableCourses.get(i).title, availableCourses.get(i).teacher.lastName + " " +
                    availableCourses.get(i).teacher.lastName};

        JTable courseTable = new JTable(coursesData, cols);
        JScrollPane scrollableTable = new JScrollPane(courseTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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
                int i = courseTable.getSelectedRow();
                if (i == -1)
                    JOptionPane.showMessageDialog(null, "Please select one row!", "", JOptionPane.WARNING_MESSAGE);
                else {
                    int choice = JOptionPane.showConfirmDialog(null, "Do you really want to add course (" + coursesData[i][1] + ") by (" +
                            coursesData[i][2] + "?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (choice == 0) {
                        for (myData data : Main.database.allData){
                            if (data instanceof Course && data.id.equals(availableCourses.get(i).id)) {
                                courses.add(availableCourses.get(i));
                                break;
                            }
                        }

                    }
                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(cancel);
        buttonsPanel.add(submit);

        contentsPanel.add(scrollableTable);
        contentsPanel.add(buttonsPanel);


        MainFrame.show();
    }

    public void attemptOnExam() {

        contentsPanel.removeAll();

        selectText.setText("Attempt an exam");
        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.setVisible(true);

        // Update exams

        String[] cols = new String[]{"No.", "Exam name", "Course Name", "Teacher's Name"};

        int examN = 0;
        for (Course course : courses)
            examN += course.exams.size();

        String[][] coursesData = new String[examN][5];
        int k = 0;
        for (int i = 0; i < courses.size(); i++) {
            for (int j = 0; j < courses.get(i).exams.size(); j++) {
                coursesData[k++] = new String[]{String.valueOf(i) + "-" + String.valueOf(j),
                        courses.get(i).exams.get(j).title, courses.get(i).title, courses.get(i).teacher.lastName};
            }
        }

        JTable courseTable = new JTable(coursesData, cols);
        JScrollPane scrollableTable = new JScrollPane(courseTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


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

                int row = courseTable.getSelectedRow();
                if (row != -1) {
                    String data = (String) courseTable.getValueAt(courseTable.getSelectedRow(), 0);
                    String[] indices = data.split("-");
                    int i = Integer.parseInt(indices[0]);
                    int j = Integer.parseInt(indices[1]);


                    printExam(courses.get(i).exams.get(j), i, j);

                }
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(cancel);
        buttonsPanel.add(submit);

        contentsPanel.add(scrollableTable);
        contentsPanel.add(buttonsPanel);

//        contentsPanel.add(courseTable);

        MainFrame.show();

    }

    public void printExam(Exam printingExam, int i, int j) {

        contentsPanel.removeAll();

        selectText.setText("(" + printingExam.title + ") From course (" + printingExam.course.title + ")");
        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.setVisible(true);

        JButton submit = new JButton("Submit");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showSolution(printingExam, i, j);

            }
        });

        ArrayList<Integer> answeredOnes = new ArrayList<>();

        String[][] qs = new String[printingExam.problems.size()][2];
        String[] cols = new String[]{"Question title", "Answered"};

        int in = 0;
        for (Problem question : printingExam.problems) {
            qs[in][0] = question.title;
            qs[in][1] = "False";
            in++;
        }

        JTable examTable = new JTable(qs, cols) {
            @Override
            public java.awt.Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                java.awt.Component comp = super.prepareRenderer(renderer, row, col);
                if (answeredOnes.contains(row)) {
                    comp.setBackground(Color.lightGray);
                    qs[row][1] = "True";
                }
                return comp;
            }
        };

        examTable.setDefaultEditor(Object.class, null);

        examTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable myTable = (JTable) e.getSource();
                int row = myTable.getSelectedRow();
                if (row >= 0 && !answeredOnes.contains(row)) {
                    Object[] message = {
                            printingExam.problems.get(row).printProblem()
                    };

                    JOptionPane.showConfirmDialog(null, message, "Question" + row + 1,
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    answeredOnes.add(row);
                } else if (answeredOnes.contains(row)) {
                    JOptionPane.showMessageDialog(null,
                            "You have answered this question before!", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JScrollPane scrollablePanel = new JScrollPane(examTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        contentsPanel.add(scrollablePanel);
        contentsPanel.add(submit);

        MainFrame.show();

    }

    public void showSolution(Exam printingExam, int i, int j) {
        contentsPanel.removeAll();

        selectText.setText("(" + printingExam.title + ") From course (" + printingExam.course.title + ")");
        selectText.setFont(new Font("", Font.BOLD, 26));
        selectPanel.setVisible(true);

        // Update exams

//        JPanel examPanel = new JPanel(new GridBagLayout());
//
////        examPanel.setSize(new Dimension(800, 600));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(20, 0, 0, 0);
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        for (Problem question : printingExam.problems) {
//
//            examPanel.add(question.printProblem(), gbc);
//            examPanel.add(new JSeparator());
//        }
//
//        JButton cancel = new JButton("Go Back");
        JButton submit = new JButton("DONE");

//        cancel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                attemptOnExam();
//            }
//        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                courses.get(i).exams.remove(j);
                attemptOnExam();

            }
        });


        String[][] qs = new String[printingExam.problems.size()][1];
        String[] cols = new String[]{"Question title"};

        int in = 0;
        for (Problem question : printingExam.problems) {
            qs[in++][0] = question.title;
        }

        JTable examTable = new JTable(qs, cols);

        examTable.setDefaultEditor(Object.class, null);

        examTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable myTable = (JTable) e.getSource();
                int row = myTable.getSelectedRow();

                Object[] message = {
                        ("Q: " + printingExam.problems.get(row).title), "A: ", printingExam.problems.get(row).solution.printSolution()
                };

                JOptionPane.showMessageDialog(null, message, "Solution" + row + 1,
                        JOptionPane.PLAIN_MESSAGE);

            }
        });

        JScrollPane scrollablePanel = new JScrollPane(examTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        contentsPanel.add(scrollablePanel);
        contentsPanel.add(submit);

        MainFrame.show();

    }

}
