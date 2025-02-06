import javax.swing.*;

public class MainFrame {

    static public JFrame frame;

    static void initialize() {
        frame = new JFrame();
        frame.setTitle("SESS");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addWindowListener(new WindowEventHandler());

    }

    static public void show() {
        frame.revalidate();
        frame.repaint();
    }

}
