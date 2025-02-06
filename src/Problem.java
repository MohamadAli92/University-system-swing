import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

abstract public class Problem extends myData {
    String title;
    Solution solution;

    Problem(String title, Solution solution) {
        super.type = Type.Problem;
        this.title = title;
        this.solution = solution;
        super.id = Main.database.generateNewId(this);
    }

    abstract JPanel printProblem();

}

class ProblemDescriptive extends Problem {

    ProblemDescriptive(String title, Solution solution) {
        super(title, solution);
    }

    @Override
    JPanel printProblem() {

        JPanel newPanel = new JPanel(new GridLayout(2, 1));

        newPanel.add(new JLabel(this.title));
        newPanel.add(new JTextArea(3, 50));

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;
    }
}

class ProblemTest extends Problem {

    String[] choices;

    ProblemTest(String title, Solution solution, String[] choices) {
        super(title, solution);
        this.choices = choices;
    }

    @Override
    JPanel printProblem() {

        JPanel newPanel = new JPanel(new GridLayout(5, 1));

        newPanel.add(new JLabel(this.title));
        for (int i = 0; i < 4; i++)
            newPanel.add(new JCheckBox(choices[i]));

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;

    }

}

class ProblemBlank extends Problem {

    String blankWord;

    ProblemBlank(String title, Solution solution, String blankWord) {
        super(title, solution);
        this.blankWord = blankWord;
    }

    @Override
    JPanel printProblem() {

        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        String[] parts = this.title.split(this.blankWord);

        newPanel.add(new JLabel(parts[0]));
        newPanel.add(new JTextArea(1, this.blankWord.length()));
        newPanel.add(new JLabel(parts[1]));

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;

    }

}

class ProblemTrueFalse extends Problem {


    ProblemTrueFalse(String title, Solution solution) {
        super(title, solution);
    }

    @Override
    JPanel printProblem() {

        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        newPanel.add(new JLabel(this.title));
        newPanel.add(new JButton("True"));
        newPanel.add(new JButton("false"));

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;
    }

}