package orbit_coaching;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Expense {
    private JPanel panel1;
    private JTextField to_textfield;
    private JComboBox purpose_combobox;
    private JTextField date_textfield;
    private JButton SAVEButton;
    private JComboBox type_combobox;
    private JTextField amount_textbox;

    public Expense()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");

        type_combobox.addItem("Teacher");
        type_combobox.addItem("Student");
        type_combobox.addItem("Other");
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

        purpose_combobox.setEditable(true);

        SAVEButton.setFocusable(false);


        Dimension dimension=  new Dimension(450,350);
        jFrame.setPreferredSize(dimension);
        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

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
            String from= to_textfield.getText();
            String purpose = purpose_combobox.getSelectedItem().toString();
            String date = date_textfield.getText();
            String amount = amount_textbox.getText();

            Database_query.insert_expense(from,purpose,date,amount,type_combobox.getSelectedItem().toString());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
