package orbit_coaching;



import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.Vector;

public class Course_wise_result_show_name_wise {
    private JComboBox class_comboBox1;
    private JComboBox exam_type_comboBox2;
    private JTextField total_marks_textField1;
    private JTextField highest_marks_textField2;
    private JTextField lowest_marks_textField3;
    private JTable marks_table;
    private JComboBox year_comboBox1;
    private JPanel panel1;
    private JButton print_button;
    private JComboBox subject_combobox;
    private JTextField date_textfield;
    int highest_marks = 0,lowest_marks = 0,average=0,counter=0;


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
            try {
                exam_type_comboBox2.removeAllItems();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
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
            ResultSet resultSet = Database_query.get_exam_date(class_comboBox1.getSelectedItem().toString(),
                    year_comboBox1.getSelectedItem().toString(),subject_combobox.getSelectedItem().toString(),
                    exam_type_comboBox2.getSelectedItem().toString());
            if(resultSet.next())
            {
                date_textfield.setText(resultSet.getString(1));
            }


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void fill_table_data()
    {
        highest_marks = 0;
        lowest_marks=  10000;
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"REGISTRATION NUMBER","NAME","OBTAINED MARKS"};
        defaultTableModel.setColumnIdentifiers(header);
        marks_table.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        marks_table.getColumn("REGISTRATION NUMBER").setCellRenderer(centerRenderer);
       // marks_table.getColumn("ROLL NUMBER").setCellRenderer(centerRenderer);
        marks_table.getColumn("NAME").setCellRenderer(centerRenderer);
        marks_table.getColumn("OBTAINED MARKS").setCellRenderer(centerRenderer);

        marks_table.setModel(defaultTableModel);

        try
        {
            ResultSet resultSet = Database_query.get_course_wise_marks_for_specific_date_name_wise(class_comboBox1.getSelectedItem().toString(),
                    exam_type_comboBox2.getSelectedItem().toString(),date_textfield.getText(),
                    subject_combobox.getSelectedItem().toString());
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
                String obtained_marks="Absent";
                if(!resultSet.getString(3).equals("-1"))
                {
                    obtained_marks = resultSet.getString(3);
                }

                defaultTableModel.addRow(new String[]{resultSet.getString(1),
                        name,obtained_marks});
                if(obtained_marks.charAt(0)!='A' && Integer.parseInt(obtained_marks)>highest_marks)
                {
                    highest_marks = Integer.parseInt(obtained_marks);
                }
                if(obtained_marks.charAt(0)!='A' && Integer.parseInt(obtained_marks)<lowest_marks)
                {
                    lowest_marks = Integer.parseInt(obtained_marks);
                }
                average+=Integer.parseInt(obtained_marks);
                counter++;

            }
            resultSet.beforeFirst();
            if(resultSet.next()) {
                total_marks_textField1.setText(resultSet.getString(4));
            }
            highest_marks_textField2.setText(Integer.toString(highest_marks));
            lowest_marks_textField3.setText(Integer.toString(lowest_marks));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    Course_wise_result_show_name_wise()
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"REGISTRATION NUMBER","NAME","OBTAINED MARKS"};
        defaultTableModel.setColumnIdentifiers(header);
        marks_table.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        marks_table.getColumn("REGISTRATION NUMBER").setCellRenderer(centerRenderer);
        //marks_table.getColumn("ROLL NUMBER").setCellRenderer(centerRenderer);
        marks_table.getColumn("NAME").setCellRenderer(centerRenderer);
        marks_table.getColumn("OBTAINED MARKS").setCellRenderer(centerRenderer);



        fill_year();
        fill_class();
        year_comboBox1.setEditable(true);
        class_comboBox1.setEditable(true);
        subject_combobox.setEditable(true);
        exam_type_comboBox2.setEditable(true);

        total_marks_textField1.setEditable(false);
        highest_marks_textField2.setEditable(false);
        lowest_marks_textField3.setEditable(false);

        JFrame jFrame = new JFrame("Orbit (Course Wise Result (Highest Marks Order)");
        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        try
        {
            ResultSet resultSet = Database_query.get_subject_list();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                subject_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        print_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  jFrame.dispose();
                try
                {
                    ResultSet resultSet = Database_query.get_page_setup();
                    resultSet.beforeFirst();

                    average/=counter;
                    String av = Integer.toString(average);
                    if(resultSet.next())
                    {
                        Couse_wise_hm_pdf_coaching_pad couse_wise_hm_pdf_coaching_pad = new Couse_wise_hm_pdf_coaching_pad(
                                class_comboBox1.getSelectedItem().toString(),
                                exam_type_comboBox2.getSelectedItem().toString(),
                                date_textfield.getText(),subject_combobox.getSelectedItem().toString(),
                                total_marks_textField1.getText(),Integer.toString(highest_marks),
                                Integer.toString(lowest_marks), av);
                    }
                    else
                    {
                        Couse_wise_hm_pdf couse_wise_hm_pdf_coaching_pad = new
                                Couse_wise_hm_pdf(class_comboBox1.getSelectedItem().toString(),
                                exam_type_comboBox2.getSelectedItem().toString(),
                                date_textfield.getText(),subject_combobox.getSelectedItem().toString(),
                                total_marks_textField1.getText(),Integer.toString(highest_marks),
                                Integer.toString(lowest_marks), av);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                Home home =new Home();

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
        class_comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_exam_type();
                fill_date();
                fill_table_data();
            }
        });
        exam_type_comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_exam_type();
                fill_date();
                fill_table_data();
            }
        });
        subject_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_exam_type();
                fill_date();
                fill_table_data();
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

        date_textfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_table_data();
            }
        });
    }
}
