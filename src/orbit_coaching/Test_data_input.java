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

            for (int i = 101;i<=1000;i++) {
                String query = "INSERT INTO Student (roll,name,fname,mname,class,group_d,school,address," +
                        "date_of_birth,admission_date,blood_group,cnumber1,cnumber2,for_year) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, Integer.toString(i));
                preparedStatement.setString(2, Integer.toString(i));
                preparedStatement.setString(3, Integer.toString(i));
                preparedStatement.setString(4, Integer.toString(i));
                preparedStatement.setString(5, Integer.toString(1));
                preparedStatement.setString(6, Integer.toString(i));
                preparedStatement.setString(7, Integer.toString(i));
                preparedStatement.setString(8, Integer.toString(i));
                preparedStatement.setString(9, Integer.toString(i));
                preparedStatement.setString(10, Integer.toString(i));
                preparedStatement.setString(11, Integer.toString(i));
                preparedStatement.setString(12, Integer.toString(i));
                preparedStatement.setString(13, Integer.toString(i));
                preparedStatement.setString(14, Integer.toString(1));

                preparedStatement.execute();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
