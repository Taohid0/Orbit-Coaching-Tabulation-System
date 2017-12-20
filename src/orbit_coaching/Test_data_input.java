package orbit_coaching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Test_data_input {
    static Statement stmt ;
    static Connection conn ;
    public static void insert_student() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
           int counter=1;
          for(int i= 1;i<=100;i++)
           for(int j= 1;j<=100;j++){
                String query = "INSERT INTO Student (roll,name,fname,mname,class,group_d,school,address," +
                        "date_of_birth,admission_date,blood_group,cnumber1,cnumber2,for_year,temp_roll) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, Integer.toString(counter));
                preparedStatement.setString(2, "name"+counter);
                preparedStatement.setString(3, "father"+counter);
                preparedStatement.setString(4, "mother"+counter);
                preparedStatement.setString(5, Integer.toString(i));
                preparedStatement.setString(6, "Science"+i);
                preparedStatement.setString(7, "school"+i);
                preparedStatement.setString(8, "address"+counter);
                preparedStatement.setString(9, counter+"-"+j+""+i);
                preparedStatement.setString(10, counter+"-"+j+""+(i+15));
                preparedStatement.setString(11, "O+");
                preparedStatement.setString(12, "01710"+counter+i+j);
                preparedStatement.setString(13, "01711"+counter+i+j);
                preparedStatement.setString(14, Integer.toString(2017));
                preparedStatement.setString(15,i+j+counter+"");
               counter++;
                preparedStatement.execute();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void insert_marks() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();

            for (int i = 1;i<=10;i++) {
                String query = "INSERT INTO Marks (roll,exam_type,date,out_of,obtained_markd,cls," +
                        "subject) VALUES(?,?,?,?,?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, Integer.toString(i));
                preparedStatement.setString(2, Integer.toString(i));
                preparedStatement.setString(3, "2-10-2017");
                preparedStatement.setString(4, Integer.toString(i));
                preparedStatement.setString(5, Integer.toString(1));
                preparedStatement.setString(6, Integer.toString(i));
                preparedStatement.setString(7,Integer.toString(i));

                preparedStatement.execute();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static void insert_billing_other() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();

            for (int i = 1;i<=100;i++) {
                String query = "INSERT INTO billing_other (to_whom,date,purpose,amount) VALUES(?,?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, Integer.toString(i));
                preparedStatement.setString(2, Integer.toString(i));
                preparedStatement.setString(3, "2-10-2017");
                preparedStatement.setString(4, Integer.toString(i));
                preparedStatement.execute();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static void insert_billing_other_income() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();

            for (int i = 1;i<=100;i++) {
                String query = "INSERT INTO billing_income_other (from_whom,purpose,date,amount) VALUES(?,?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, Integer.toString(i));
                preparedStatement.setString(2, Integer.toString(i));
                preparedStatement.setString(3, "2-10-2017");
                preparedStatement.setString(4, Integer.toString(i));
                preparedStatement.execute();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
