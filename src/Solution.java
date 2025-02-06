import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

abstract public class Solution extends myData {

    abstract public JPanel printSolution();

}

class SolutionDescriptive extends Solution {

    String answer;

    SolutionDescriptive(String answer) {
        this.answer = answer;
    }

    @Override
    public JPanel printSolution() {

        JPanel newPanel = new JPanel(new GridLayout(1, 1));

        JLabel answer = new JLabel(this.answer);
        answer.setForeground(new Color(0, 150, 23));
        newPanel.add(answer);

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;

    }

}

class SolutionTest extends Solution {

    int trueChoice;

    SolutionTest(int trueChoice) {
        this.trueChoice = trueChoice;
    }

    @Override
    public JPanel printSolution() {

        JPanel newPanel = new JPanel(new GridLayout(5, 1));

        JLabel answer = new JLabel(("True choice is number (" + trueChoice + ")."));
        answer.setForeground(new Color(0, 150, 23));
        newPanel.add(answer);

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;

    }

}

class SolutionBlank extends Solution {

    String blankAnswer;

    SolutionBlank(String blankAnswer) {
        this.blankAnswer = blankAnswer;
    }

    @Override
    public JPanel printSolution() {

        JPanel newPanel = new JPanel(new GridLayout(5, 1));

        JLabel answer = new JLabel(("True answer is (" + blankAnswer + ")."));
        answer.setForeground(new Color(0, 150, 23));
        newPanel.add(answer);

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;

    }

}

class SolutionTrueFalse extends Solution {

    boolean answer;

    SolutionTrueFalse(boolean answer) {
        this.answer = answer;
    }

    @Override
    public JPanel printSolution() {

        JPanel newPanel = new JPanel(new GridLayout(5, 1));

        JLabel answerLabel = new JLabel(("True answer was (" + answer + ")."));
        answerLabel.setForeground(new Color(0, 150, 23));
        newPanel.add(answerLabel);

        newPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        return newPanel;

    }

}