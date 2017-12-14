package orbit_coaching;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.sql.ResultSet;

public class Student_wise_result_one_exam {
    private JPanel jpanel;
    private JComboBox class_combobox;
    private JComboBox year_combobox;
    private JComboBox exam_combobox;
    private JComboBox registration_no_combobox;
    private JTable table1;
    private JTextField name_textfield;
    private JTextField school_textfield;
    private JTextField father_name_textfield;
    private JTextField mother_name_combobox;

    DefaultTableModel defaultTableModel = null;

    public Student_wise_result_one_exam()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");

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

        try
        {
            ResultSet resultSet = Database_query.get_year();
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
        year_combobox.setEditable(true);

        try
        {
            ResultSet resultSet = Database_query.get_exam_type();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                exam_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            ResultSet resultSet = Database_query.get_roll_for_payment(class_combobox.getSelectedItem().toString(),
                    year_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                registration_no_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"SUBJECT", "MARKS", "GRADE POINT", "AMOUNT"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("TO").setCellRenderer(centerRenderer);
        table1.getColumn("PURPOSE").setCellRenderer(centerRenderer);
        table1.getColumn("DATE").setCellRenderer(centerRenderer);
        table1.getColumn("AMOUNT").setCellRenderer(centerRenderer);

        name_textfield.setEditable(false);
        school_textfield.setEditable(false);
        father_name_textfield.setEditable(false);
        mother_name_combobox.setEditable(false);



    }


}
