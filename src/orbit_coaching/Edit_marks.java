package orbit_coaching;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Edit_marks {
    private JPanel jPanel1;
    private JComboBox examtype_comobox;
    private JComboBox class_combobox;
    private JTable table1;
    private JComboBox for_year_combobox;
    private JButton button1;
    private JTextField total_marks_textbox;
    private JComboBox date_combobox;
    DefaultTableModel defaultTableModel = null;
    private void fill_fields()
    {

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

        try {
            date_combobox.setEditable(true);
            {
//                System.out.println(class_combobox.getSelectedItem().toString()+for_year_combobox.getSelectedItem().toString());
                ResultSet resultSet = Database_query.get_exam_dates(class_combobox.getSelectedItem().toString(),
                        for_year_combobox.getSelectedItem().toString());
                resultSet.beforeFirst();
                while (resultSet.next())
                {
                    date_combobox.addItem(resultSet.getString(1));
                    System.out.println(resultSet.getString(1));
                }
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
                for_year_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void fill_student_fields()
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Roll Number","Obtained Marks"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        String date = date_combobox.getSelectedItem().toString();
        String cls=class_combobox.getSelectedItem().toString();


        try
        {
            ResultSet resultSet = Database_query.get_exam_type();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                examtype_comobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try
        {
            ResultSet resultSet = Database_query.get_marks_info(cls,date);
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                defaultTableModel.addRow(new String[]{resultSet.getString(1),
              resultSet.getString(5)});



            }
            resultSet.beforeFirst();
            if(resultSet.next())
            {
                examtype_comobox.setSelectedItem(resultSet.getString(2));
                total_marks_textbox.setText(resultSet.getString(4));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public Edit_marks()
    {
        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Roll Number","Obtained Marks"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        class_combobox.setEditable(true);
        examtype_comobox.setEditable(true);
        for_year_combobox.setEditable(true);


        fill_fields();

        JFrame jFrame = new JFrame("Orbit Coaching");
        jFrame.setContentPane(jPanel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.pack();
        jFrame.setVisible(true);

        for_year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_fields();
                fill_student_fields();
            }
        });
        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_fields();
                fill_student_fields();
            }
        });
    }

}
