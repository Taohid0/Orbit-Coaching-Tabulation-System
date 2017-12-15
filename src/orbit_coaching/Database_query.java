package orbit_coaching;

import com.mysql.jdbc.util.ResultSetUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.Calendar;

import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.println;

public class Database_query {

    static String create_database = "CREATE DATABASE orbit_coaching_tabulation_system;";

     static String create_admin_table = "CREATE TABLE Admin\n" +
        "(\n" +
        "    user_name VARCHAR(100),\n" +
        "    password VARCHAR(100),\n" +
        "    ID INT PRIMARY KEY AUTO_INCREMENT\n" +
        ");";

    static String create_institution_table = "CREATE TABLE Institution\n" +
            "(\n" +
            "    name VARCHAR(200),\n" +
            "    address VARCHAR(500),\n" +
            "    contact_number VARCHAR(100),\n"+
            "    ID INT PRIMARY KEY AUTO_INCREMENT\n" +
            ");";

    static String create_teacher_attendance_table = "CREATE TABLE Teacher_attendance\n" +
            "(\n" +
            "    teacher_id VARCHAR(25),\n" +
            "    attendance_date VARCHAR(25),\n" +
            "    shift_time VARCHAR(50),\n"+
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
             "    name VARCHAR(200),\n" +
             "    institution VARCHAR(250),\n" +
             "    joining_date VARCHAR(20),\n" +
             "    isDeleted BOOL DEFAULT 0,\n"+
             "    year VARCHAR(15),\n"+
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
             "    subject VARCHAR(100),\n"+
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
            String url = "jdbc:mysql://localhost";
            String username = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement(create_database);
            stmt.execute();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

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

        try
        {
            stmt.execute(create_institution_table);
        }
        catch (Exception ex)
        {
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
        try
        {
            stmt.execute(create_teacher_attendance_table);
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

    public static ResultSet get_other_income_details(String yr)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT from_whom,purpose,date,amount from billing_income_other WHERE DATE LIKE "+
                    "\"%"+yr+"\"";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }
    public static ResultSet get_marks_for_specific_result(String yr,String cls,String exm,String roll)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT subject,obtained_markd FROM Marks WHERE cls="+cls+" AND exam_type="+exm+
                    " AND  roll="+roll+" AND date LIKE "+"\"%"+yr.toString()+"\""+" ORDER BY subject " ;
            System.out.println(query);
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


    public static ResultSet get_student_of_year_class(String yr,String cls)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT roll,temp_roll,name,school from Student WHERE for_year="+yr+" AND class="+cls+";";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_student_of_year_school(String yr,String school)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT roll,temp_roll,name,class from Student WHERE for_year="+yr+" AND school="+school+"" +
                    " ORDER BY class,name;";
            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_expense_year(String yr)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT to_whom,purpose,date,amount from billing_other WHERE "
                    +" date LIKE "+"\"%"+yr.toString()+"\""+" ORDER BY to_whom " ;
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

    public static ResultSet get_roll_from_registration_number(String reg)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT temp_roll from Student WHERE roll="+reg;
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
    public static ResultSet get_course_wise_marks_for_specific_date(String cls,String exam_type,String date,String subject)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();

            String query ="SELECT roll,temp_roll,obtained_markd,out_of FROM Marks WHERE cls="+cls+ " AND exam_type="+exam_type+ " " +
                    " AND subject="+subject+" AND date=\""+date+"\""+ " ORDER BY  cast(roll As INT) ";

            resultSet= stmt.executeQuery(query);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }


   //this will be marks wise,not namewise; name change korte r ecche kortese na!
    public static ResultSet get_course_wise_marks_for_specific_date_name_wise(String cls,String exam_type,String date,String subject)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();

            String query ="SELECT roll,temp_roll,obtained_markd,out_of FROM Marks WHERE cls="+cls+ " AND exam_type="+exam_type+ " " +
                    " AND subject="+subject+" AND date=\""+date+"\""+ " ORDER BY  obtained_markd ";

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
            return resultSet;
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
            String query = "SELECT exam_type,subject,date,obtained_markd FROM Marks WHERE cls="+cls+" AND roll="+roll
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

    public static ResultSet get_highest_marks_specific(String cls,String yr,String exam_type,String subject)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT MAX(obtained_markd) FROM Marks WHERE cls="+cls+" AND exam_type="+exam_type
                    +" AND subject="+subject
                    +" AND date LIKE "+"\"%"+yr.toString()+"\"";

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

    public static ResultSet get_exam_date(String cls,String year,String xm,String subject)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT date from Marks WHERE cls="+cls+
                    " AND exam_type="+xm+
                    " AND subject="+subject+
                    " AND date LIKE "+"\"%"+year.toString()+"\" " +
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

    public static ResultSet get_name_schoolname_fname_mname(String roll)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT name,school,fname,mname from Student WHERE roll="+roll;
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
            String query = "SELECT DISTINCT purpose from billing_student ORDER BY purpose";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_purpose_other_income()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT purpose from billing_income_other ORDER BY purpose";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_whom_other_income()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT from_whom from billing_income_other ORDER BY purpose";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }


    public static ResultSet get_teachear_institution()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT institution from Teacher ORDER BY (institution)";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_teacher_attendance(String ID,String yr)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT attendance_date,shift_time from Teacher_attendance " +
                    "WHERE teacher_id="+ID +" AND attendance_date LIKE "+"\"%"+yr.toString()+"\" ORDER BY attendance_date ASC " +
                    ",shift_time DESC ";
            resultSet= stmt.executeQuery(query);


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_teacher_specific_year(String yr)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT ID,name,institution,joining_date,isdeleted from Teacher Where " +//is active will be added later
                    "year LIKE "+"\"%"+yr.toString()+"\" ORDER BY name";
            resultSet= stmt.executeQuery(query);


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_teacher_all_year()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT ID,name,institution,joining_date,isdeleted from Teacher" +//is active will be added later
                    " ORDER BY name";

            resultSet= stmt.executeQuery(query);


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_teachear_ids()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT ID from Teacher ORDER BY ID;";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }


    public static ResultSet get_institution_info()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT name,address,contact_number from Institution;";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_teachear_details()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT ID,name from Teacher ORDER BY name,ID";
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_teachear_name_institution(String ID)
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT name,institution from Teacher WHERE ID="+ID;
            resultSet= stmt.executeQuery(query);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet get_subject_list()
    {
        ResultSet resultSet=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT subject from Marks ORDER BY subject";
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
    public static void insert_teacher(String name,String joining_date,String institution,String yr) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "INSERT INTO Teacher (name,joining_date,institution,year) VALUES(?,?,?,?);";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,joining_date);
            preparedStatement.setString(3,institution);
            preparedStatement.setString(4,yr);
            preparedStatement.execute();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void insert_other_income(String from,String purpose,String date,String amount) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
            String query = "INSERT INTO billing_income_other (from_whom,purpose,date,amount) VALUES(?,?,?,?);";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,from);
            preparedStatement.setString(2,purpose);
            preparedStatement.setString(3,date);
            preparedStatement.setString(4,amount);
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
