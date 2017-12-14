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

public class Total_expense_year {
    private JComboBox year_combobox;
    private JTable table1;
    private JPanel panel1;
    private JButton CANCELButton;
    private JTextField total_textfield;

    DefaultTableModel defaultTableModel =null;
    public Total_expense_year()
    {
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

        JFrame jFrame = new JFrame("Orbit coaching");

        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"TO", "PURPOSE", "DATE", "AMOUNT"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("TO").setCellRenderer(centerRenderer);
        table1.getColumn("PURPOSE").setCellRenderer(centerRenderer);
        table1.getColumn("DATE").setCellRenderer(centerRenderer);
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
        CANCELButton.setFocusable(false);
        total_textfield.setEditable(false);

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
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Home home = new Home();
            }
        });
    }
    public void fill_table() {
        total_textfield.setText("");
        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"TO", "PURPOSE", "DATE", "AMOUNT"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("TO").setCellRenderer(centerRenderer);
        table1.getColumn("PURPOSE").setCellRenderer(centerRenderer);
        table1.getColumn("DATE").setCellRenderer(centerRenderer);
        table1.getColumn("AMOUNT").setCellRenderer(centerRenderer);

        String yr = year_combobox.getSelectedItem().toString();


        long total = 0;
        try {
            ResultSet resultSet = Database_query.get_expense_year(yr);
            resultSet.beforeFirst();

            while (resultSet.next()) {
                defaultTableModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4)});
                total+=Long.parseLong(resultSet.getString(4));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        total_textfield.setText(Long.toString(total));
    }

}
