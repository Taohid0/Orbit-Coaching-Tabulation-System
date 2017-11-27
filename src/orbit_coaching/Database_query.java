package orbit_coaching;

import com.mysql.jdbc.util.ResultSetUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.Calendar;

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
             "    temp_roll VARCHAR(25),\n"+
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
             "    for_year VARCHAR(20),\n"+
             "     isDeleted BOOL DEFAULT 0\n"+
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
             "    name VARCHAR(100),\n" +
             "    institution VARCHAR(100),\n" +
             "    joining_date VARCHAR(25),\n" +
             "    teacher INT,\n" +
             "    CONSTRAINT Subject_teacher_ID_fk FOREIGN KEY (teacher) REFERENCES teacher (ID)\n" +
             ");";
     static String create_marks_table = "CREATE TABLE Marks\n" +
             "(\n" +
             "    roll VARCHAR(25),\n"+
             "    temp_roll VARCHAR(25),\n"+
             "    exam_type VARCHAR(100),\n" +
             "    date VARCHAR(25),\n" +
             "    out_of INT,\n" +
             "    obtained_markd INT,\n" +
             "     cls VARCHAR(25)"+
             ");";

     static String create_billing_student ="CREATE TABLE Billing_student\n" +
             "(\n" +
             "    roll VARCHAR(25),\n" +
             "    cls VARCHAR(25),\n"+
             "    month VARCHAR(25),\n" +
             "    year VARCHAR(25),\n" +
             "    date VARCHAR(25),\n" +
             "    skipped VARCHAR(5),\n" +
             "    amount VARCHAR(25),\n" +
             "     purpose VARCHAR(100)\n"+
             ")";
     static String create_other_billing = "CREATE TABLE billing_other\n" +
             "(\n" +
             "    to_whom VARCHAR(100),\n" +
             "    date VARCHAR(25),\n" +
             "    purpose VARCHAR(500),\n" +
             "    amount INT\n" +
             ");";
    static String create_other_income_billing = "CREATE TABLE billing_income_other\n" +
            "(\n" +
            "    from_whom VARCHAR(100),\n" +
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
        try
        {
            stmt.execute(create_other_income_billing);
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

    public static ResultSet get_student_info(String roll)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT * from Student WHERE roll="+roll+" ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }


    public static ResultSet get_roll_for_payment(String cls,String year)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT roll from Student WHERE class="+cls+" AND for_year="+year+" ORDER BY cast(roll as INT) DESC ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_roll_number()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT roll from Student ORDER BY cast(roll as INT) DESC ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_marks_info(String cls,String date)
    {
        System.out.println(cls + " "+date);
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT *  from Marks WHERE cls="+cls+ " AND  date =\""+date.toString()+"\"";//
            System.out.println(query);// +
                 //   " ORDER BY cast(roll as INT) ASC ";
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
            String query = "SELECT DISTINCT class from Student ORDER BY CAST(class as INT) ASC ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }
    public static ResultSet get_exam_type()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT exam_type from Marks ORDER BY exam_type ASC ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_exam_type_by_class(String cls)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT exam_type from Marks WHERE cls="+cls+" ORDER BY exam_type ASC ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }
    public static ResultSet get_course_wise_marks_for_specific_date(String cls,String exam_type,String date)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();

            String query ="SELECT roll,temp_roll,obtained_markd,out_of FROM Marks WHERE cls="+cls+ " AND exam_type="+exam_type+ " " +
                    " AND date=\""+date+"\"";

            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }
    public static ResultSet get_name_of_student(String roll)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query ="SELECT name FROM Student WHERE roll="+roll;
                    //// "SELECT DISTINCT exam_type from Marks WHERE cls="+cls+" ORDER BY exam_type ASC ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_student_wise_marks(String year,String cls,String roll)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT exam_type,date,obtained_markd FROM Marks WHERE cls="+cls+" AND roll="+roll
                    +" AND date LIKE "+"\"%"+year.toString()+"\" ";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }


    public static ResultSet get_highest_marks(String cls,String date,String exam_type)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT MAX(obtained_markd) FROM Marks WHERE cls="+cls+" AND exam_type="+exam_type
                    +" AND date="+"\""+date+"\"";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_year()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT for_year from Student ORDER BY CAST(for_year as INT) DESC ;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_exam_dates(String cls,String year)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT date from Marks WHERE cls="+cls+" AND date LIKE "+"\"%"+year.toString()+"\" " +
                    " ORDER BY date DESC " ;
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_paid_month(String roll,String year)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT month,amount,skipped from billing_student WHERE roll="+roll+" AND year="+year; ;
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }




    public static ResultSet get_name(String roll)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT name from Student WHERE roll="+roll;
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet input_student_payment(String roll,String cls,String month,String year,String date,
                                                  int skipped,String amount,String purpose)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "INSERT INTO billing_student (roll,cls,month,year,date,skipped,amount,purpose) " +
                    " VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1,roll);
            preparedStatement.setString(2,cls);
            preparedStatement.setString(3,month);
            preparedStatement.setString(4,year);
            preparedStatement.setString(5,date);
            preparedStatement.setString(6,Integer.toString(skipped));
            preparedStatement.setString(7,amount);
            preparedStatement.setString(8,purpose);


            preparedStatement.execute();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_purpose()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT purpose from billing_student";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static void insert_student(String name,String fname,String mname,String address,String c1,String c2,String
            admission_date,String birth_date, String roll,String cls,String group,String school,String bgroup,String for_year) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "INSERT INTO Student (roll,name,fname,mname,class,group_d,school,address," +
                    "date_of_birth,admission_date,blood_group,cnumber1,cnumber2,for_year) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

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
            preparedStatement.setString(14,for_year);

            preparedStatement.execute();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public static void update_student(String name,String fname,String mname,String address,String c1,String c2,String
            admission_date,String birth_date, String roll,String cls,String group,String school,String bgroup,String for_year) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "UPDATE Student " +
                    "SET name=?,fname=?,mname=?,class=?,group_d=?,school=?,address=?," +
                    "date_of_birth =?,admission_date=?,blood_group=?," +
                    "cnumber1=?,cnumber2=?,for_year=? WHERE roll="+roll+";";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,fname);
            preparedStatement.setString(3,mname);
            preparedStatement.setString(4,cls);
            preparedStatement.setString(5,group);
            preparedStatement.setString(6,school);
            preparedStatement.setString(7,address);
            preparedStatement.setString(8,birth_date);
            preparedStatement.setString(9,admission_date);
            preparedStatement.setString(10,bgroup);
            preparedStatement.setString(11,c1);
            preparedStatement.setString(12,c2);
            preparedStatement.setString(13,for_year);

            preparedStatement.execute();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



    public static ResultSet get_active_students(String cls,String year)
    {
        ResultSet resultSet=null;
        try {
            String y = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT roll,name from Student WHERE isDeleted = 0 AND class="+cls+" AND for_year="+year +
                   " ORDER BY roll ASC ";
            resultSet= stmt.executeQuery(query);

            resultSet.beforeFirst();


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

}
