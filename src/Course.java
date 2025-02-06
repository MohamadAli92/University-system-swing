import java.util.ArrayList;
import java.util.Date;

public class Course extends myData{

    String title;
    ArrayList<String> lessons = new ArrayList<>();
    ArrayList<String> sources = new ArrayList<>();
    ArrayList<Exam> exams = new ArrayList<>();
//    ArrayList<Semester> semesters = new ArrayList<>();
    Semester semester;
    Teacher teacher;
    Date midtermDate;
    Date finalDate;

    Course(String title, Date midtermDate, Date finalDate, ArrayList<String> lessons, ArrayList<String> sources, Teacher teacher, Semester semester) {
        super.type = Type.Course;
        this.title = title;
        this.lessons = lessons;
        this.sources = sources;
        this.semester = semester;
//        this.semesters = new ArrayList<>();
        this.midtermDate = midtermDate;
        this.finalDate = finalDate;
        this.teacher = teacher;
        super.id = Main.database.generateNewId(this);
    }

}
