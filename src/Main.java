
import com.formdev.flatlaf.IntelliJTheme;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static Database database = Database.getDatabase();

    public static void main(String[] args) {

        com.formdev.flatlaf.FlatIntelliJLaf.install();
        com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme.setup();


        JTextField usernameField = new JTextField();
        Object[] message = {
                "Please enter username: ", usernameField
        };
        int choice = 1;
        while (choice != 0) {
            choice = JOptionPane.showConfirmDialog(null, message, "Log In", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (choice == 0) {
                choice = checkForUser(usernameField.getText());
            } else if (choice == 2) {
                int choiceExit = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (choiceExit == 0)
                    System.exit(0);
            }
        }

    }

    static public int checkForUser(String username) {
        if (username.equals("admin")) {
            MainFrame.initialize();
            new Expert().initializePage();
            return 0;
        }
        boolean isFound = false;
        for (myData checkingData : Main.database.allData) {
            if (checkingData instanceof User && ((User) checkingData).username.equals(username)) {
                MainFrame.initialize();
                ((User) checkingData).initializePage();
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            JOptionPane.showMessageDialog(null, "No user found with this username!", "No user found", JOptionPane.WARNING_MESSAGE);
            return 1;
        }
        return 0;
    }


}

class WindowEventHandler extends WindowAdapter {
    public void windowClosing(WindowEvent evt) {
        Main.database.saveToFile();
        evt.getWindow().dispose();
        Main.main(new String[2]);
    }
}

