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

public class Student_wise_result_show {
    private JComboBox year_combobox;
    private JComboBox class_combobox;
    private JComboBox roll_combobox;
    private JTextField name_text_field;
    private JTable table1;
    private JPanel pan1;
    private JButton CREATEPDFButton;

    void fill_year()
    {
        try
        {
            year_combobox.removeAllItems();
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
    }
    void fill_class()
    {
        try
        {
            try {
                class_combobox.removeAllItems();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
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
    }
    void fill_roll()
    {
        try
        {
            try {
                roll_combobox.removeAllItems();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            try {
                ResultSet resultSet = Database_query.get_roll_for_payment(class_combobox.getSelectedItem().toString(),
                        year_combobox.getSelectedItem().toString());
                resultSet.beforeFirst();



            while (resultSet.next())
            {
                roll_combobox.addItem(resultSet.getString(1));
            }
            try
            {
                resultSet.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            try
            {
                resultSet.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void fill_table()
    {

        table1.removeAll();
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"EXAM","SUBJECT","DATE","OBTAINED MARKS","HIGHEST MARKS","TOTAL"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        try
        {
            ResultSet resultSet = Database_query.get_student_wise_marks(year_combobox.getSelectedItem().toString(),
                    class_combobox.getSelectedItem().toString(),roll_combobox.getSelectedItem().toString());

            resultSet.beforeFirst();

            while (resultSet.next())
            {
                String highest_marks = "";
                String out_of="";

                try
                {

                    String c= class_combobox.getSelectedItem().toString();
                    String e = resultSet.getString(1);
                    String s=resultSet.getString(2);
                    String d =resultSet.getString(3);

                    ResultSet highest_marks_result = Database_query.get_highest_marks2(e,s,c,d);
                    highest_marks_result.beforeFirst();

                    if(highest_marks_result.next())
                    {
                        highest_marks = highest_marks_result.getString(1);
                    }


                    try
                    {
                        ResultSet out_of_result_set = Database_query.get_out_of_marks2(e,s,c,d);
                        out_of_result_set.beforeFirst();
                        if(out_of_result_set.next())
                        {
                            out_of = out_of_result_set.getString(1);
                        }
                        try
                        {
                            out_of_result_set.close();
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    try
                    {
                        highest_marks_result.close();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                String obtaind_marks = "Absent";
                try
                {
                    if(!resultSet.getString(4).equals("-1"))
                    {
                        obtaind_marks = resultSet.getString(4);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                defaultTableModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),
                obtaind_marks,highest_marks,out_of});
            }
            try
            {
                resultSet.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    Student_wise_result_show()
    {
        JFrame jFrame = new JFrame("Orbit (Student Wise Result)");
        jFrame.setContentPane(pan1);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        name_text_field.setEditable(false);
        year_combobox.setEditable(true);
        class_combobox.setEditable(true);
        roll_combobox.setEditable(true);
        fill_year();
        CREATEPDFButton.setFocusable(false);
        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_class();
                fill_roll();
            }
        });
        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_roll();
            }
        });

        roll_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name_text_field.setText("");
                    ResultSet resultSet = Database_query.get_name(roll_combobox.getSelectedItem().toString());
                    resultSet.beforeFirst();

                    if(resultSet.next())
                    {
                        name_text_field.setText(resultSet.getString(1));
                    }
                    try
                    {
                        resultSet.close();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                fill_table();

                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                table1.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
                table1.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
                table1.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                table1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
                table1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
                table1.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

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
        CREATEPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                try
                {
                    ResultSet resultSet = Database_query.get_page_setup();
                    resultSet.beforeFirst();

                    if(resultSet.next())
                    {
                        Student_wise_result_all_exam_coaching_pad_pdf st =new
                                Student_wise_result_all_exam_coaching_pad_pdf(year_combobox.getSelectedItem().toString(),
                                class_combobox.getSelectedItem().toString(),roll_combobox.getSelectedItem().toString(),
                                name_text_field.getText());
                    }
                    else {
                        Student_wise_result_all_exam_pdf st = new Student_wise_result_all_exam_pdf(
                                year_combobox.getSelectedItem().toString(),
                                class_combobox.getSelectedItem().toString(),roll_combobox.getSelectedItem().toString(),
                                name_text_field.getText()
                        );
                    }
                    try
                    {
                        resultSet.close();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    jFrame.dispose();
                    Home home = new Home();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });
    }
    boolean check()
    {
        if(year_combobox.getSelectedItem().toString().equals("") ||roll_combobox.getSelectedItem().toString().equals("")||
                roll_combobox.getSelectedItem().toString().equals(""))
            return true;
        return false;
    }

}
