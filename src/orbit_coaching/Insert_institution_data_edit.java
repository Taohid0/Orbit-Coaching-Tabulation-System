package orbit_coaching;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Insert_institution_data_edit {
    private JTextField name_textfield;
    private JTextField address_textfield;
    private JTextField contact_number_textfield;
    private JButton UPDATEINFORMATIONButton;
    private JPanel panel1;

    Insert_institution_data_edit()
    {

        JFrame jFrame = new JFrame("Orbit Coaching");
        jFrame.add(panel1);
        Dimension dimension=  new Dimension(450,350);
        jFrame.setPreferredSize(dimension);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        try
        {
            ResultSet resultSet = Database_query.get_institution_info();
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                name_textfield.setText(resultSet.getString(1));
                address_textfield.setText(resultSet.getString(2));
                contact_number_textfield.setText(resultSet.getString(3));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        UPDATEINFORMATIONButton.setFocusable(false);

        UPDATEINFORMATIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {

                    add_date();
                    jFrame.dispose();
                    Home home = new Home();
                }
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

    public void add_date()
    {
        String name = name_textfield.getText();
        String address = address_textfield.getText();
        String contact = contact_number_textfield.getText();

        try
        {
            Database_query.delete_institution();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            Database_query.insert_institution_data(name,address,contact);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    boolean check()
    {
        if(contact_number_textfield.getText().equals("") || name_textfield.getText().equals("") || address_textfield.getText().equals(""))
        {
            return true;
        }
        else
            return false;
    }


}
