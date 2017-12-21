package orbit_coaching;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Student_payment_show {
    private JComboBox year_combobox;
    private JComboBox registration_combobox;
    private JTable table1;
    private JTextField name_textfield;
    private JTextField father_textfield;
    private JTextField mother_textfield;
    private JTextField school_textfield;
    private JPanel panel1;
    private JTextField total_textbox;
    private JButton PRINTButton;
    DefaultTableModel defaultTableModel = null;
    Student_payment_show()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");

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
        try
        {
            ResultSet resultSet = Database_query.get_roll_number();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                registration_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        year_combobox.setEditable(true);
        registration_combobox.setEditable(true);

        name_textfield.setEditable(false);
        father_textfield.setEditable(false);
        mother_textfield.setEditable(false);
        school_textfield.setEditable(false);
        total_textbox.setEditable(false);

        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"Sl No.", "DATE", "PURPOSE", "AMOUNT"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Sl No.").setCellRenderer(centerRenderer);
        table1.getColumn("DATE").setCellRenderer(centerRenderer);
        table1.getColumn("PURPOSE").setCellRenderer(centerRenderer);
        table1.getColumn("AMOUNT").setCellRenderer(centerRenderer);

        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

       year_combobox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               fill_table();
           }
       });
       registration_combobox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               fill_table();
               fill_textfield();
           }
       });
       jFrame.addWindowListener(new WindowListener() {
           @Override
           public void windowOpened(WindowEvent e) {

           }

           @Override
           public void windowClosing(WindowEvent e) {
              jFrame.dispose();
              Home home = new Home();
           }

           @Override
           public void windowClosed(WindowEvent e) {

           }

           @Override
           public void windowIconified(WindowEvent e) {

           }

           @Override
           public void windowDeiconified(WindowEvent e) {

           }

           @Override
           public void windowActivated(WindowEvent e) {

           }

           @Override
           public void windowDeactivated(WindowEvent e) {

           }
       });
    }

    void fill_table()
    {
        int counter = 1;
        int total = 0;
        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"Sl No.", "DATE", "PURPOSE", "AMOUNT"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Sl No.").setCellRenderer(centerRenderer);
        table1.getColumn("DATE").setCellRenderer(centerRenderer);
        table1.getColumn("PURPOSE").setCellRenderer(centerRenderer);
        table1.getColumn("AMOUNT").setCellRenderer(centerRenderer);

        try
        {
            ResultSet resultSet= Database_query.get_monthy_payment_data(year_combobox.getSelectedItem().toString(),
                    registration_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                defaultTableModel.addRow(new String[]{resultSet.getString(1),
                resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)});
                total+=Integer.parseInt(resultSet.getString(4));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            ResultSet resultSet = Database_query.get_other_income_details_specific_student(year_combobox.getSelectedItem().toString(),
                    registration_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                defaultTableModel.addRow(new String[]{resultSet.getString(1),
                        resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)});
                total+=Integer.parseInt(resultSet.getString(4));
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        

         total_textbox.setText(Integer.toString(total));
    }
    void fill_textfield()
    {
        String roll= registration_combobox.getSelectedItem().toString();

        try
        {
            ResultSet resultSet = Database_query.get_student_info(roll);
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                name_textfield.setText(resultSet.getString(3));
                father_textfield.setText(resultSet.getString(4));
                mother_textfield.setText(resultSet.getString(5));
                school_textfield.setText(resultSet.getString(8));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
