package orbit_coaching;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class Course_wise_result_show {
    private JComboBox class_comboBox1;
    private JComboBox exam_type_comboBox2;
    private JComboBox date_comboBox3;
    private JTextField total_marks_textField1;
    private JTextField highest_marks_textField2;
    private JTextField lowest_marks_textField3;
    private JTable marks_table;
    private JComboBox year_comboBox1;
    private JPanel panel1;


    void fill_year()
    {
        try
        {
            ResultSet resultSet = Database_query.get_year();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                year_comboBox1.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void fill_class()
    {
        try
        {
            ResultSet resultSet = Database_query.get_class();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                class_comboBox1.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void fill_exam_type()
    {

        try
        {
            exam_type_comboBox2.removeAllItems();
            ResultSet resultSet =Database_query.get_exam_type_by_class(class_comboBox1.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                exam_type_comboBox2.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void fill_date()
    {
        try
        {
            date_comboBox3.removeAllItems();
            ResultSet resultSet = Database_query.get_exam_dates(class_comboBox1.getSelectedItem().toString(),
                    year_comboBox1.getSelectedItem().toString());

            resultSet.beforeFirst();

            while (resultSet.next())
            {
                date_comboBox3.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void fill_table_data()
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Registration Number","Roll Number","Name","Obtained Marks"};
        defaultTableModel.setColumnIdentifiers(header);
        marks_table.setModel(defaultTableModel);

        try
        {
            ResultSet resultSet = Database_query.get_course_wise_marks_for_specific_date(class_comboBox1.getSelectedItem().toString(),
                    exam_type_comboBox2.getSelectedItem().toString(),date_comboBox3.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                String name="";
                try {
                    ResultSet name_result = Database_query.get_name(resultSet.getString(1));
                    name_result.beforeFirst();
                    if(name_result.next())
                    {
                        name=name_result.getString(1);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                defaultTableModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2),
                name,resultSet.getString(3)});
                System.out.println(name);

            }
            resultSet.beforeFirst();
            if(resultSet.next()) {
                total_marks_textField1.setText(resultSet.getString(4));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    Course_wise_result_show()
    {
        fill_year();
        fill_class();
        total_marks_textField1.setEditable(false);
        highest_marks_textField2.setEditable(false);
        lowest_marks_textField3.setEditable(false);

        JFrame jFrame = new JFrame("Orbit coaching");
        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        class_comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_exam_type();
                fill_date();
                fill_table_data();
            }
        });

        year_comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_exam_type();
                fill_date();
                fill_table_data();
            }
        });

    }
}
