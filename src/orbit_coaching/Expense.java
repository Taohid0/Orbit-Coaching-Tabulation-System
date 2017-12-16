package orbit_coaching;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Expense {
    private JPanel panel1;
    private JComboBox from_combobox;
    private JComboBox purpose_combobox;
    private JTextField date_textfield;
    private JTextField amount_textfield;
    private JButton SAVEButton;

    public Expense()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");

        try
        {
            ResultSet resultSet = Database_query.get_purpose_other_income();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                purpose_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        purpose_combobox.setEditable(true);

        try
        {
            ResultSet resultSet = Database_query.get_whom_other_income();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                from_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        purpose_combobox.setEditable(true);
        from_combobox.setEditable(true);

        SAVEButton.setFocusable(false);


        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                other_income_save();
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
    public void other_income_save()
    {

        try
        {
            String from= from_combobox.getSelectedItem().toString();
            String purpose = purpose_combobox.getSelectedItem().toString();
            String date = date_textfield.getText();
            String amount = amount_textfield.getText();

            Database_query.insert_expense(from,purpose,date,amount);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
