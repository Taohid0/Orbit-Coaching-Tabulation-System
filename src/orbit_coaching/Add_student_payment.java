package orbit_coaching;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Add_student_payment {
    private JPanel panel1;
    private JComboBox year_combobox;
    private JComboBox class_combobox;
    private JComboBox roll_combobox;
    private JComboBox month_combobox;
    private JTextField textField1;
    private JTable table1;

    public void fill_data()
    {
        String year = year_combobox.getSelectedItem().toString();
        String cls= class_combobox.getSelectedItem().toString();
        roll_combobox.removeAllItems();

        try
        {
            ResultSet resultSet = Database_query.get_roll_for_payment(cls,year);
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                roll_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void fill_table()
    {
        DefaultTableModel defaultTableModel=null;

        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Month","Status"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);




    }

    boolean is_month_found(String month_list[],String month)
    {
        for(int i = 0;i<(int)month_list.length;i++)
        {
            if(month_list[i].equals(month))
                return true;
        }
        return false;
    }


    public  Add_student_payment()
    {
        try
        {
            ResultSet resultSet = Database_query.get_year();
            resultSet.beforeFirst();

            while(resultSet.next())
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
            ResultSet resultSet = Database_query.get_class();
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
        String month[] = {"January","February","March","April","May","June","July","August","September",
                        "October","November","December"};
        try
        {
            for(int i = 0;i<(int)month.length;i++)
            {
                month_combobox.addItem(month[i]);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }



        JFrame jFrame = new JFrame("Orbit Coaching");
        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);

        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_data();
            }
        });
        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_data();
            }
        });
        roll_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_table();
            }
        });
    }
}
