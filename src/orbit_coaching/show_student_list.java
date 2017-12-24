package orbit_coaching;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class show_student_list {
    private JPanel panel1;
    private JComboBox year_combobox;
    private JComboBox class_combobox;
    private JTable table1;
    private JButton CREATEPDFButton;

    DefaultTableModel defaultTableModel=null;
    public show_student_list()
    {
        JFrame jFrame = new JFrame("Orbit (Show All Students)");

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
        class_combobox.setEditable(true);

        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"REGISTRATION NUMBER","ROLL NUMBER","NAME","INSTITUTION"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("REGISTRATION NUMBER").setCellRenderer( centerRenderer );
        table1.getColumn("ROLL NUMBER").setCellRenderer(centerRenderer);
        table1.getColumn("NAME").setCellRenderer(centerRenderer);
        table1.getColumn("INSTITUTION").setCellRenderer(centerRenderer);

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

        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_table();
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
                        Show_student_list_pdf_coaching_pad show_student_list_pdf_coaching_pad = new Show_student_list_pdf_coaching_pad(
                                class_combobox.getSelectedItem().toString(),year_combobox.getSelectedItem().toString());

                    }
                    else
                    {

                        Show_student_list_pdf show_student_list_pdf = new Show_student_list_pdf(
                                class_combobox.getSelectedItem().toString(),year_combobox.getSelectedItem().toString()
                                );
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                Home home = new Home();
            }

        });
        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_table();
            }
        });
        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_table();
            }
        });
    }

    public void fill_table()
    {
        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"REGISTRATION NUMBER","ROLL NUMBER","NAME","INSTITUTION"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("REGISTRATION NUMBER").setCellRenderer( centerRenderer );
        table1.getColumn("ROLL NUMBER").setCellRenderer(centerRenderer);
        table1.getColumn("NAME").setCellRenderer(centerRenderer);
        table1.getColumn("INSTITUTION").setCellRenderer(centerRenderer);

        String yr = year_combobox.getSelectedItem().toString();
        String cls = class_combobox.getSelectedItem().toString();

        try
        {
            ResultSet resultSet = Database_query.get_student_of_year_class(yr,cls);
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                defaultTableModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2),
                resultSet.getString(3),resultSet.getString(4)});
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
