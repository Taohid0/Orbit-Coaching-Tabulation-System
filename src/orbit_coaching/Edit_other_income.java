package orbit_coaching;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import sun.nio.ch.DatagramSocketAdaptor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Edit_other_income {
    private JComboBox year_combobox;
    private JTable table1;
    private JPanel pan1;
    private JButton UPDATEButton;

    DefaultTableModel defaultTableModel = null;

    public Edit_other_income()
    {
        JFrame jFrame = new JFrame("Orbit (Edit Other Incomes)");

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

        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"FROM", "TYPE","PURPOSE", "DATE", "AMOUNT"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("FROM").setCellRenderer(centerRenderer);
        table1.getColumn("TYPE").setCellRenderer(centerRenderer);
        table1.getColumn("PURPOSE").setCellRenderer(centerRenderer);
        table1.getColumn("DATE").setCellRenderer(centerRenderer);
        table1.getColumn("AMOUNT").setCellRenderer(centerRenderer);

        jFrame.setContentPane(pan1);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_table();
            }
        });
        jFrame.addWindowListener(
                new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e)
                    {
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
                }
        );
    }

    public void fill_table()
    {

        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"FROM","TYPE", "PURPOSE", "DATE", "AMOUNT"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("FROM").setCellRenderer(centerRenderer);
        table1.getColumn("TYPE").setCellRenderer(centerRenderer);
        table1.getColumn("PURPOSE").setCellRenderer(centerRenderer);
        table1.getColumn("DATE").setCellRenderer(centerRenderer);
        table1.getColumn("AMOUNT").setCellRenderer(centerRenderer);
        int total = 0;
        try
        {
            ResultSet resultSet = Database_query.get_other_income_details(year_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                String name = "";
                try
                {
                    ResultSet nameResultset = Database_query.get_name_of_student(resultSet.getString(1));
                    nameResultset.beforeFirst();

                    if(nameResultset.next())
                    {
                        name=nameResultset.getString(1);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                defaultTableModel.addRow(new String[]{resultSet.getString(1),name,resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4)});
                total+=Integer.parseInt(resultSet.getString(4));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
