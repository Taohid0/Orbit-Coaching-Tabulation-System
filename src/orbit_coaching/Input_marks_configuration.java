package orbit_coaching;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.sql.ResultSet;

public class Input_marks_configuration {
    private JPanel panel1;
    private JComboBox class_combobox;
    private JComboBox examtype_combobox;
    private JTextField textField1;
    private JComboBox comboBox1;

    public Input_marks_configuration()
    {
        ResultSet resultSet = null;
        try
        {
            resultSet = Database_query.get_active_students("1","1");
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                System.out.println("done");

                System.out.println(resultSet.getString(1)+resultSet.getString(2));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        JFrame jFrame= new JFrame("Orbit Coaching");
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
