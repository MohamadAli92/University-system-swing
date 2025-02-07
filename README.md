# University-system-swing
A simple university system using java OOP and swing as GUI.

In this project I've tried to practice using OOp and a GUI library in java.

I've used swing for GUI and serialize data type in java for saving data in hard disk, I've used this data type because it is platform indepent and it can be used on every platrform.

-   **Bulding Project:**

This project has been built using intellij. You can build this project by including libraries using commands.

-   **Login page:**

    This application has 3 user modes:

        1.  Admin
        2.  Teacher
        3.  Student

    You can login with a username which can be one of above three user types.

    First of all we have to login as Admin which has **admin** as its username by default, and using that we can create other users including Teacher and Student user types.

![](./Screenshots/1.png)

-   **Expert's page:**

    After that we can create users or semesters.

![](./Screenshots/2.png)

    For creating each user we have to indicate user's name and last name and also an unique username.

![](./Screenshots/3.png)
![](./Screenshots/4.png)

    In order to create a semester we have to choose a title for that semester and also semester's related dates.

![](./Screenshots/5.png)

-   **Teacher's page:**

    After that we can login with recently created teacher's username.

    As a teacher we can take two type of actions:

        1.  Creating new course
        2.  Creating new exam

![](./Screenshots/6.png)

    In order to create a course we have to indicate it's title, dates and also lessons and sources and after that we can specify that which semester does this course belongs to.

    For adding lessons or sources we have to press Enter button after typing its name.

![](./Screenshots/7.png)

    Now we can create exams and associate them to courses. For each exam we can define 4 types of question and also their correct answers as well.

![](./Screenshots/8.png)

-   **Student's page:**

    As a student we can take two actions:

        1.  Signing up on a course
        2.  Attempting on an exam

![](./Screenshots/9.png)

    1.  Signing up on a course
    
    At first we should select a course from the list and then sumbit that.

![](./Screenshots/10.png)

    2.  Signing up on a course
    
    At first we should select an exam from the list and then sumbit that.

![](./Screenshots/11.png)

    Now student can attend on an exam and answer questions.

![](./Screenshots/12.png)

    And after submiting the answer, we can see correct answer of each question by clicking on that question.

![](./Screenshots/13.png)


**Note that the program would make three database files;**

**Which would be used for saving data.**
