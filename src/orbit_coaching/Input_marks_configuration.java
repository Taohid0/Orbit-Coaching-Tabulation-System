package orbit_coaching;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Input_marks_configuration {
    private JPanel panel1;
    private JComboBox class_combobox;
    private JComboBox examtype_combobox;
    private JTextField textField1;
    private JComboBox year_combobox;
    private JButton DONE;

    public Input_marks_configuration() {
        class_combobox.setEditable(true);
        examtype_combobox.setEditable(true);
        year_combobox.setEditable(true);

        class_combobox.removeAllItems();
        examtype_combobox.removeAllItems();
        year_combobox.removeAllItems();

        ResultSet resultSet = null;
        ArrayList arrayList=new ArrayList();
        try
        {
            resultSet = Database_query.get_class();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                class_combobox.addItem(resultSet.getString(1));
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
             resultSet = Database_query.get_exam_type();
             resultSet.beforeFirst();

             while (resultSet.next())
             {
                 examtype_combobox.addItem(resultSet.getString(1));
             }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            resultSet = Database_query.get_year();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                year_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            resultSet = Database_query.get_active_students("1","1");
            resultSet.beforeFirst();

            while (resultSet.next())
            {
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        JFrame jFrame= new JFrame("Orbit Coaching");
        Dimension dimension=  new Dimension(550,250);
        jFrame.setPreferredSize(dimension);
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("done");
            }
        });

        DONE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String year = year_combobox.getSelectedItem().toString();
                    String cls = class_combobox.getSelectedItem().toString();
                    String exam_type = examtype_combobox.getSelectedItem().toString();
                    String date = textField1.getText();
                    jFrame.dispose();
                    Insert_marks insert_marks=  new Insert_marks(cls,year,exam_type,date);
                    System.out.println(year);
                }
                catch (Exception ex)
                {
                    String error_message = "Please fill up all the fields correctly";
                    String title_bar = "ERROR";
                    JOptionPane.showMessageDialog(null, error_message,
                            title_bar, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
