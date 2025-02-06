//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LoginPage {
//
//    static JPanel allPage = new JPanel(new BorderLayout());
//    static JPanel containerPanel = new JPanel(new GridBagLayout());
//    static JPanel contentsPanel = new JPanel();
//
//    public void initializePage() {
//
//        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
//        JLabel title = new JLabel("Log In");
//        title.setFont(new Font("", Font.BOLD, 35));
//        titlePanel.add(title);
//        titlePanel.setBackground(Color.PINK);
//
//        allPage.add(titlePanel, BorderLayout.NORTH);
//
//        makeFirstPage();
//
//    }
//
//    public void makeFirstPage() {
//
//        // ----------------First Page-------------------------
//
//        contentsPanel.removeAll();
//        contentsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        ((FlowLayout)contentsPanel.getLayout()).setHgap(20);
//
//
//
//        // Get name part
//
//        JLabel nameLabel = new JLabel("Enter your Username: ");
//        nameLabel.setFont(new Font("", Font.BOLD, 16));
//        JTextField nameField = new JTextField(30);
//
//        nameField.setFont(new Font("", Font.PLAIN, 16));
//        JButton login = new JButton("→");
//
//        nameField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                login.doClick();
//            }
//        });
//
//        login.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                checkForUser(nameField.getText());
//            }
//        });
//
//
//        contentsPanel.add(nameLabel);
//        contentsPanel.add(nameField);
//        contentsPanel.add(login);
//
//        containerPanel.add(contentsPanel);
//
//        allPage.add(containerPanel);
//
//        MainFrame.frame.add(allPage, BorderLayout.CENTER);
//
//        MainFrame.show();
//
//    }
//
//}
