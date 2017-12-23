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

public class show_all_teacher_list {
    private JComboBox year_combobox;
    private JTable table1;
    private JPanel panel1;
    private JButton CANCELButton;
    DefaultTableModel defaultTableModel = null;
    public show_all_teacher_list()
    {
        JFrame jFrame = new JFrame("Orbit (Show All Teacher List)");
        year_combobox.addItem("ALL");
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

        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"ID","NAME","INSTITUTION","JOINING DATE","ACTIVE"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("ID").setCellRenderer( centerRenderer );
        table1.getColumn("NAME").setCellRenderer(centerRenderer);
        table1.getColumn("INSTITUTION").setCellRenderer(centerRenderer);
        table1.getColumn("JOINING DATE").setCellRenderer(centerRenderer);
        table1.getColumn("ACTIVE").setCellRenderer(centerRenderer);


        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Home home = new Home();
            }
        });
        CANCELButton.setFocusable(false);
    }
    public void fill_table_date()
    {
        String yr = year_combobox.getSelectedItem().toString();
        if(yr.equals("ALL")) {
            try {
                defaultTableModel = new DefaultTableModel(0,0);
                String header[] = {"ID","NAME","INSTITUTION","JOINING DATE","ACTIVE"};
                defaultTableModel.setColumnIdentifiers(header);
                table1.setModel(defaultTableModel);

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                table1.getColumn("ID").setCellRenderer( centerRenderer );
                table1.getColumn("NAME").setCellRenderer(centerRenderer);
                table1.getColumn("INSTITUTION").setCellRenderer(centerRenderer);
                table1.getColumn("JOINING DATE").setCellRenderer(centerRenderer);
                table1.getColumn("ACTIVE").setCellRenderer(centerRenderer);

                ResultSet resultSet = Database_query.get_teacher_all_year();
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String active = "YES";
                    if (resultSet.getString(5).toString().equals("1")) {
                        active = "NO";
                    }
                    defaultTableModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4), active});
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            else
            {
                try
                {
                    defaultTableModel = new DefaultTableModel(0,0);
                    String header[] = {"ID","NAME","INSTITUTION","JOINING DATE","ACTIVE"};
                    defaultTableModel.setColumnIdentifiers(header);
                    table1.setModel(defaultTableModel);

                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    table1.getColumn("ID").setCellRenderer( centerRenderer );
                    table1.getColumn("NAME").setCellRenderer(centerRenderer);
                    table1.getColumn("INSTITUTION").setCellRenderer(centerRenderer);
                    table1.getColumn("JOINING DATE").setCellRenderer(centerRenderer);
                    table1.getColumn("ACTIVE").setCellRenderer(centerRenderer);

                    ResultSet resultSet = Database_query.get_teacher_specific_year(year_combobox.getSelectedItem().toString());
                    resultSet.beforeFirst();

                    while (resultSet.next())
                    {
                        String active="YES";
                        if(resultSet.getString(5).toString().equals("1"))
                        {
                            active="NO";
                        }
                        defaultTableModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2),
                                resultSet.getString(3),resultSet.getString(4),active});
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
        }

    }

}
