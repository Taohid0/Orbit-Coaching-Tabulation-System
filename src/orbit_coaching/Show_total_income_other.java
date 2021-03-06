package orbit_coaching;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Show_total_income_other {
    private JComboBox year_combobox;
    private JTable table1;
    private JTextField total_textfield;
    private JButton CREATEPDFButton;
    private JPanel panel1;
    DefaultTableModel defaultTableModel = null;

    Show_total_income_other() {
        JFrame jFrame = new JFrame("Orbit (Total Income)");

        try {
            ResultSet resultSet = Database_query.get_year();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                year_combobox.addItem(resultSet.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        year_combobox.setEditable(true);


        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"Sl No.", "ID", "Name", "Date", "Purpose", "Type", "Amount"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Sl No.").setCellRenderer(centerRenderer);
        table1.getColumn("ID").setCellRenderer(centerRenderer);
        table1.getColumn("Name").setCellRenderer(centerRenderer);
        table1.getColumn("Date").setCellRenderer(centerRenderer);
        table1.getColumn("Purpose").setCellRenderer(centerRenderer);
        table1.getColumn("Type").setCellRenderer(centerRenderer);
        table1.getColumn("Amount").setCellRenderer(centerRenderer);

        year_combobox.setEditable(true);

        jFrame.setContentPane(panel1);
        jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        jFrame.pack();
        jFrame.setVisible(true);

        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                jFrame.dispose();
                Home home =new Home();
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
        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_table();
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
                        Total_income_other_pdf_creation_coaching_pad total_income_other_pdf_creation_coaching_pad =
                         new Total_income_other_pdf_creation_coaching_pad(year_combobox.getSelectedItem().toString());
                    }
                    else
                    {
                        Total_income_other_pdf_creation total_incomeother_pdf_creation =
                                new Total_income_other_pdf_creation(year_combobox.getSelectedItem().toString());
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                Home home =new Home();
            }
        });
    }

    void fill_table() {
        total_textfield.setText("");
        int counter = 1;
        int total = 0;
        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"Sl No.", "ID", "Name", "Date", "Purpose", "Type", "Amount"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Sl No.").setCellRenderer(centerRenderer);
        table1.getColumn("ID").setCellRenderer(centerRenderer);
        table1.getColumn("Name").setCellRenderer(centerRenderer);
        table1.getColumn("Date").setCellRenderer(centerRenderer);
        table1.getColumn("Purpose").setCellRenderer(centerRenderer);
        table1.getColumn("Type").setCellRenderer(centerRenderer);
        table1.getColumn("Amount").setCellRenderer(centerRenderer);

        try {
            ResultSet resultSet = Database_query.get_income_year(year_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next()) {
                String name = "";
                String cnt = Integer.toString(counter++);
                String type = resultSet.getString(5);
                if (type.equals("Student"))
                    try {
                        ResultSet resultSet1 = Database_query.get_name(resultSet.getString(1));
                        resultSet1.beforeFirst();

                        if (resultSet1.next()) {
                            name = resultSet1.getString(1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                else if (type.equals("Teacher"))
                    try {
                        ResultSet resultSet1 = Database_query.get_teacher_name(resultSet.getString(1));
                        resultSet1.beforeFirst();

                        if (resultSet1.next()) {
                            name = resultSet1.getString(1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                else
                    name = "";

                defaultTableModel.addRow(new String[]{cnt, resultSet.getString(1), name, resultSet.getString(3), resultSet.getString(2),
                        resultSet.getString(5), resultSet.getString(4)});

                total+=Integer.parseInt(resultSet.getString(4));


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
       total_textfield.setText(Integer.toString(total));
    }
}
