public class myData implements java.io.Serializable {
    String id;
    Type type;
}

abstract class User extends myData {
    String username;

    abstract public void initializePage();
}
