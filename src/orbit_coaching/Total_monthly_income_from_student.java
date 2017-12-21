package orbit_coaching;

import org.omg.CORBA.DATA_CONVERSION;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Total_monthly_income_from_student {
    private JComboBox year_combobox;
    private JTable table1;
    private JTextField total_amount_text_field;
    private JButton CREATEPDFButton;
    private JPanel panel1;
    DefaultTableModel defaultTableModel = null;

    Total_monthly_income_from_student()
    {
        JFrame jFrame = new JFrame("Orbit (Total Monthly Payment)");

        try
        {
            ResultSet resultSet  = Database_query.get_year();
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


        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Sl No.","Registration Number","Class","Date","Amount"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Sl No.").setCellRenderer(centerRenderer);
        table1.getColumn("Registration Number").setCellRenderer( centerRenderer );
        table1.getColumn("Class").setCellRenderer(centerRenderer);
        table1.getColumn("Date").setCellRenderer(centerRenderer);
        table1.getColumn("Amount").setCellRenderer(centerRenderer);


        year_combobox.addActionListener(new ActionListener() {
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
        jFrame.setContentPane(panel1);
        jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void  fill_table()
    {
        total_amount_text_field.setText("");
        long total = 0;
        int counter=1;
        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Sl No.","Registration Number","Class","Date","Amount"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Sl No.").setCellRenderer(centerRenderer);
        table1.getColumn("Registration Number").setCellRenderer( centerRenderer );
        table1.getColumn("Class").setCellRenderer(centerRenderer);
        table1.getColumn("Date").setCellRenderer(centerRenderer);
        table1.getColumn("Amount").setCellRenderer(centerRenderer);


        try
        {
            ResultSet resultSet = Database_query.get_students_income_details(year_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();
            defaultTableModel.addRow(new String[]{Integer.toString(counter++),resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(5)});
            total+=Integer.parseInt(resultSet.getString(5));

            defaultTableModel.addRow(new String[]{});
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        total_amount_text_field.setText(Long.toString(total));

    }
}
