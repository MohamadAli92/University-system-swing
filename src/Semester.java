import java.util.ArrayList;
import java.util.Date;

public class Semester extends myData {

    String title;
    ArrayList<Course> courses;
    Date scoreSubmission;
    Date start;
    Date end;

    Semester(String title, Date scoreSubmission, Date start, Date end) {
        super.type = Type.Semester;
        this.title = title;
        this.courses = new ArrayList<>();
        this.scoreSubmission = scoreSubmission;
        this.start = start;
        this.end = end;
        super.id = Main.database.generateNewId(this);
    }

}
