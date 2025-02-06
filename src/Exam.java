import java.util.ArrayList;

public class Exam extends myData{

    String title;
    Course course;
    ArrayList<Problem> problems;

    Exam(String title, Course course, ArrayList<Problem> problems) {
        super.type = Type.Exam;
        this.title = title;
        this.course = course;
        this.problems = problems;
        super.id = Main.database.generateNewId(this);
    }

}
