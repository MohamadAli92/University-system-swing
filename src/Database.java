import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Database {

    Hashtable<Semester, Course[]> SemesterCourse = new Hashtable<>();
    Hashtable<Course, Exam[]> CourseExam = new Hashtable<>();
    ArrayList<myData> allData = new ArrayList<>();

    // Singleton pattern
    static private Database database;

    private Database() {
        boolean result = this.readFromFile();
        if (!result) {
            SemesterCourse = new Hashtable<>();
            CourseExam = new Hashtable<>();
        }
    }

    static public Database getDatabase() {
        if (database == null)
            database = new Database();
        return database;
    }

    public String generateNewId(myData newData) {

        String newId;
        Random random = new Random();
        newId = String.valueOf(random.nextInt(10000));
        while (true) {

            ArrayList<String> ids = new ArrayList<>();
            for (myData data : allData)
                ids.add(data.id);
            if (!ids.contains(newId))
                break;

        }
        newData.id = newId;
        allData.add(newData);
        return newId;

    }

    private boolean readFromFile() {

        try {

            FileInputStream fileInput = new FileInputStream("DatabaseSC.ser");
            ObjectInputStream input = new ObjectInputStream(fileInput);
            SemesterCourse = (Hashtable<Semester, Course[]>) input.readObject();
            input.close();
            fileInput.close();

            fileInput = new FileInputStream("DatabaseCE.ser");
            input = new ObjectInputStream(fileInput);
            CourseExam = (Hashtable<Course, Exam[]>) input.readObject();
            input.close();
            fileInput.close();

            fileInput = new FileInputStream("DatabaseDATA.ser");
            input = new ObjectInputStream(fileInput);
            allData = (ArrayList<myData>) input.readObject();
            input.close();
            fileInput.close();

            return true;

        } catch (IOException | ClassNotFoundException e) {
            File SC = new File("DatabaseSC.ser");
            File CE = new File("DatabaseCE.ser");
            File DATA = new File("DatabaseDATA.ser");
            try {
                SC.createNewFile();
                CE.createNewFile();
                DATA.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return false;
        }

    }

    public void saveToFile() {

        try {
            FileOutputStream fileOutput = new FileOutputStream("DatabaseSC.ser");
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            output.writeObject(SemesterCourse);
            output.close();
            fileOutput.close();

            fileOutput = new FileOutputStream("DatabaseCE.ser");
            output = new ObjectOutputStream(fileOutput);
            output.writeObject(CourseExam);
            output.close();
            fileOutput.close();

            fileOutput = new FileOutputStream("DatabaseDATA.ser");
            output = new ObjectOutputStream(fileOutput);
            output.writeObject(allData);
            output.close();
            fileOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
