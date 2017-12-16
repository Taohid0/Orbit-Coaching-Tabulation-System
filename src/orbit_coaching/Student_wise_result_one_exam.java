package orbit_coaching;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    private JComboBox fourth_subject_combobox;
    private JButton PRINTButton;
    private JTextField gpa_with_fourth_subject_textfield;
    private JTextField gpa_without_fourth_subject_textfield;

    DefaultTableModel defaultTableModel = null;
    boolean flag = false;
    double total_point=0.0;
    String fourth_sub = "";
    String fourth_sub_gpd="";
    int subject_counter = 0;

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

        try
        {
            fourth_subject_combobox.addItem("NONE");
            ResultSet resultSet = Database_query.get_subject_list();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                fourth_subject_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"SUBJECT", "MARKS", "GRADE", "GRADE POINT","HIGHEST MARKS"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("SUBJECT").setCellRenderer(centerRenderer);
        table1.getColumn("MARKS").setCellRenderer(centerRenderer);
        table1.getColumn("GRADE").setCellRenderer(centerRenderer);
        table1.getColumn("GRADE POINT").setCellRenderer(centerRenderer);
        table1.getColumn("HIGHEST MARKS").setCellRenderer(centerRenderer);

        name_textfield.setEditable(false);
        school_textfield.setEditable(false);
        father_name_textfield.setEditable(false);
        mother_name_combobox.setEditable(false);

        gpa_with_fourth_subject_textfield.setEditable(false);
        gpa_without_fourth_subject_textfield.setEditable(false);



        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setContentPane(jpanel);

        jFrame.pack();
        jFrame.setVisible(true);

        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_textfields();
                fill_table_date();
            }
        });
        exam_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_textfields();
                fill_table_date();
            }
        });
        registration_no_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_textfields();
                fill_table_date();
            }
        });
        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_textfields();
                fill_table_date();
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

    public void set_textfields()
    {
        try
        {
            name_textfield.setText("");
            school_textfield.setText("");
            father_name_textfield.setText("");
            mother_name_combobox.setText("");

            ResultSet resultSet = Database_query.get_name_schoolname_fname_mname(registration_no_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            if (resultSet.next())
            {
                name_textfield.setText(resultSet.getString(1));
                school_textfield.setText(resultSet.getString(2));
                father_name_textfield.setText(resultSet.getString(3));
                mother_name_combobox.setText(resultSet.getString(4));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    public void fill_table_date()
    {
        gpa_with_fourth_subject_textfield.setText("");
        gpa_without_fourth_subject_textfield.setText("");
        subject_counter =0;
        flag = false;
        total_point =0.0;
        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"SUBJECT", "MARKS", "GRADE", "GRADE POINT","HIGHEST MARKS"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("SUBJECT").setCellRenderer(centerRenderer);
        table1.getColumn("MARKS").setCellRenderer(centerRenderer);
        table1.getColumn("GRADE").setCellRenderer(centerRenderer);
        table1.getColumn("GRADE POINT").setCellRenderer(centerRenderer);
        table1.getColumn("HIGHEST MARKS").setCellRenderer(centerRenderer);

        try {

            ResultSet resultSet1 = Database_query.get_marks_for_specific_result(year_combobox.getSelectedItem().toString(),
                    class_combobox.getSelectedItem().toString(),
                    exam_combobox.getSelectedItem().toString(),
                    registration_no_combobox.getSelectedItem().toString());

            resultSet1.beforeFirst();
            while (resultSet1.next()) {

                ResultSet highest_resultset = Database_query.get_highest_marks_specific(class_combobox.getSelectedItem().toString(),
                        year_combobox.getSelectedItem().toString(), exam_combobox.getSelectedItem().toString(),
                        resultSet1.getString(1));
                String highest = "";

                try {
                    if (highest_resultset.next()) {
                        highest = highest_resultset.getString(1);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                String mrk = resultSet1.getString(2);
                String grade_point =Grade_point_calculation.get_grade(mrk);
                String grade = Double.toString(Grade_point_calculation.get_grade_point(mrk));
                if(fourth_subject_combobox.equals(resultSet1.getString(1)))
                {
                    fourth_sub = fourth_subject_combobox.getSelectedItem().toString();
                    fourth_sub_gpd = grade_point;
                }
                else
                    total_point+=Double.parseDouble(grade);
                if(grade.equals("F") && !fourth_subject_combobox.getSelectedItem().toString().equals(resultSet1.getString(1)))
                    flag = true;
                String sub = resultSet1.getString(1);
                if(sub.equals(fourth_subject_combobox.getSelectedItem().toString()))
                    sub+="(4th)";
                defaultTableModel.addRow(new String[]{sub, mrk,
                        grade_point,grade.substring(0, 3),
                        highest});
                subject_counter++;
            }
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if(flag==true){
            gpa_without_fourth_subject_textfield.setText("0.00 (F)");
            gpa_with_fourth_subject_textfield.setText("0.00 (F)");
        }
        else if(fourth_subject_combobox.getSelectedItem().toString().equals("NONE"))
    {
        double with_fourth = total_point/subject_counter;
        double without_fourth = total_point/subject_counter;
        if(with_fourth>5.00)
            with_fourth=5.00;

        gpa_with_fourth_subject_textfield.setText(Double.toString(with_fourth).substring(0,3)+" ("+
        Grade_point_calculation.get_grade_from_point(with_fourth)+")");
        gpa_without_fourth_subject_textfield.setText(Double.toString(with_fourth).substring(0,3)+
        " ("+Grade_point_calculation.get_grade_from_point(without_fourth)+")");
    }
        else
        {
            double without_fourth = total_point/(subject_counter-1);
            if(Double.parseDouble(fourth_sub_gpd)-2.00>=0.0001)
            {
                total_point+=(Double.parseDouble(fourth_sub_gpd)-2.00);
            }
            double with_fourth = total_point/(subject_counter-1);
            if(with_fourth>5.00)
                with_fourth=5.00;

            gpa_with_fourth_subject_textfield.setText(Double.toString(with_fourth).substring(0,3)+" ("+
                    Grade_point_calculation.get_grade_from_point(with_fourth)+")");
            gpa_without_fourth_subject_textfield.setText(Double.toString(with_fourth).substring(0,3)+
                    " ("+Grade_point_calculation.get_grade_from_point(without_fourth)+")");

        }

    }

}