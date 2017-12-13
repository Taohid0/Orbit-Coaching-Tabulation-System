package orbit_coaching;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.Calendar;

public class Teacher_attendance_show {
    private JPanel panel1;
    private JComboBox year_combobox;
    private JComboBox id_combobox;
    private JTextField institution_textfield;
    private JTable table1;
    private JTextField name_textfield;
    private JButton BACKButton;

    DefaultTableModel defaultTableModel=null;

    public Teacher_attendance_show()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");
         defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Date","Arrival Time"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Date").setCellRenderer( centerRenderer );
        table1.getColumn("Arrival Time").setCellRenderer(centerRenderer);



        try
         {
             ResultSet resultSet = Database_query.get_year();
             resultSet.beforeFirst();

             while (resultSet.next())
             {
                 year_combobox.addItem(resultSet.getString(1));
             }
             year_combobox.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR));
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
         }

         try
         {
             ResultSet resultSet = Database_query.get_teachear_ids();
             resultSet.beforeFirst();

             while (resultSet.next())
             {
                 id_combobox.addItem(resultSet.getString(1));
             }
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
         }




         id_combobox.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 set_name_institution();
                 set_attendance_table();
             }
         });

        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_attendance_table();
            }
        });
        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Home home = new Home();
            }
        });
    }

    public void set_name_institution()
    {
        try
        {
            String id = id_combobox.getSelectedItem().toString();
            ResultSet resultSet = Database_query.get_teachear_name_institution(id);
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                name_textfield.setText(resultSet.getString(1));
                institution_textfield.setText(resultSet.getString(2));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void set_attendance_table()
    {
        try
        {
            defaultTableModel = new DefaultTableModel(0,0);
            String header[] = {"Date","Arrival Time"};
            defaultTableModel.setColumnIdentifiers(header);
            table1.setModel(defaultTableModel);

            ResultSet resultSet = Database_query.get_teacher_attendance(id_combobox.getSelectedItem().toString(),
                    year_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {

                defaultTableModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2)});
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
