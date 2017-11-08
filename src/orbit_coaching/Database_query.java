package orbit_coaching;

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


    static Statement stmt = null;
    static void do_start_up_query() {
        try

        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
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

        try
        {
            String un=  "Orbit coaching";
            String ps= "goahead";

            //stmt.execute("INSERT INTO Admin (user_name,password) VALUES("","");");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
