package orbit_coaching;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Other_income {
    private JPanel panel1;
    private JComboBox from_combobox;
    private JComboBox purpose_combobox;
    private JTextField date_textfield;
    private JTextField amount_textfield;
    private JButton SAVEButton;
    private JComboBox type_combobox;

    public Other_income()
    {
        JFrame jFrame = new JFrame("Orbit (Add Other Income)");
        type_combobox.addItem("Student");
        type_combobox.addItem("Teacher");
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


        Dimension dimension=  new Dimension(450,350);
        jFrame.setPreferredSize(dimension);
        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All The Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Data_validation.check_date(date_textfield.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up Date Field Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {

                    other_income_save();
                    jFrame.dispose();
                    Home home = new Home();
                }}
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

            Database_query.insert_other_income(from,purpose,date,amount,type_combobox.getSelectedItem().toString());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    boolean check()
    {
        if(date_textfield.getText().equals("") || amount_textfield.getText().equals("") ||
                from_combobox.getSelectedItem().toString().equals(""))
        {
            return true;
        }
        return false;
    }
}
