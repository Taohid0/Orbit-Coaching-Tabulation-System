package orbit_coaching;

import com.mysql.jdbc.util.ResultSetUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Database_query {
     static String create_admin_table = "CREATE TABLE Admin\n" +
        "(\n" +
        "    user_name VARCHAR(100),\n" +
        "    password VARCHAR(100),\n" +
        "    ID INT PRIMARY KEY AUTO_INCREMENT\n" +
        ");";
     static String create_student_table = "CREATE TABLE Student\n" +
             "(\n" +
             "    roll VARCHAR(25) PRIMARY KEY,\n" +
             "    name VARCHAR(100),\n" +
             "    fname VARCHAR(100),\n" +
             "    mname VARCHAR(100),\n" +
             "    class VARCHAR(100),\n" +
             "    group_d VARCHAR(100),\n" +
             "    school VARCHAR(100),\n" +
             "    address VARCHAR(500),\n" +
             "    date_of_birth VARCHAR(20),\n" +
             "    admission_date VARCHAR(20),\n" +
             "    blood_group VARCHAR(10),\n" +
             "    cnumber1 VARCHAR(25),\n" +
             "    cnumber2 VARCHAR(25),\n" +
             "     isDeleted BOOLEAN\n"+
             ");";

     static String create_teacher_table = "CREATE TABLE Teacher\n" +
             "(\n" +
             "    name VARCHAR(100),\n" +
             "    institution VARCHAR(100),\n" +
             "    joining_date VARCHAR(20),\n" +
             "    ID INT PRIMARY KEY AUTO_INCREMENT\n" +
             ");";

     static String create_subect_table = "CREATE TABLE Subject\n" +
             "(\n" +
             "    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
             "    name VARCHAR(100),\n" +
             "    institution VARCHAR(100),\n" +
             "    joining_date VARCHAR(25),\n" +
             "    teacher INT,\n" +
             "    CONSTRAINT Subject_teacher_ID_fk FOREIGN KEY (teacher) REFERENCES teacher (ID)\n" +
             ");";
     static String create_marks_table = "CREATE TABLE Marks\n" +
             "(\n" +
             "    exam_type VARCHAR(100),\n" +
             "    date VARCHAR(25),\n" +
             "    out_of INT,\n" +
             "    obtained_markd INT,\n" +
             "    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT\n" +
             ");";

     static String create_billing_student ="CREATE TABLE Billing_student\n" +
             "(\n" +
             "    student VARCHAR(25),\n" +
             "    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
             "    month VARCHAR(25),\n" +
             "    year VARCHAR(25),\n" +
             "    date VARCHAR(25),\n" +
             "    skipped BOOLEAN,\n" +
             "    amount INT,\n" +
             "    CONSTRAINT Billing_student_student_roll_fk FOREIGN KEY (student) REFERENCES student (roll)\n" +
             ")";
     static String create_other_billing = "CREATE TABLE billing_other\n" +
             "(\n" +
             "    to_whom VARCHAR(100),\n" +
             "    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
             "    date VARCHAR(25),\n" +
             "    purpose VARCHAR(500),\n" +
             "    amount INT\n" +
             ");";


    static Statement stmt ;
    static Connection conn ;
    static void do_start_up_query() {
        try

        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            stmt.execute(create_admin_table);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try{
            stmt.execute(create_student_table);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            stmt.execute(create_teacher_table);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try {
            stmt.execute(create_subect_table);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try {
            stmt.execute(create_marks_table);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            stmt.execute(create_billing_student);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try {
            stmt.execute(create_other_billing);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static ResultSet get_username_password()
    {
        ResultSet resultSet = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String un=  "Orbit Coaching";
            String ps= "goahead";

            resultSet = stmt.executeQuery(" SELECT * FROM Admin LIMIT 1");

            if(resultSet.next()) {
                return resultSet;

            }
            else {
                String query = "INSERT INTO Admin (user_name,password) VALUES(?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, un);
                preparedStatement.setString(2, ps);
                preparedStatement.execute();

            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;

    }
    public static ResultSet get_school_name()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT school from Student;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_class()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT class from Student;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static void insert_student(String name,String fname,String mname,String address,String c1,String c2,String
            admission_date,String birth_date, String roll,String cls,String group,String school,String bgroup) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "INSERT INTO Student (roll,name,fname,mname,class,group_d,school,address," +
                    "date_of_birth,admission_date,blood_group,cnumber1,cnumber2) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,roll);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,fname);
            preparedStatement.setString(4,mname);
            preparedStatement.setString(5,cls);
            preparedStatement.setString(6,group);
            preparedStatement.setString(7,school);
            preparedStatement.setString(8,address);
            preparedStatement.setString(9,birth_date);
            preparedStatement.setString(10,admission_date);
            preparedStatement.setString(11,bgroup);
            preparedStatement.setString(12,c1);
            preparedStatement.setString(13,c2);

            preparedStatement.execute();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
