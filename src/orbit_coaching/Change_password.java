package orbit_coaching;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Change_password {
    private JPasswordField old_password_field;
    private JPasswordField new_password_field;
    private JPasswordField new_password_field_again;
    private JButton SAVEButton;
    private JPanel panel1;

    Change_password(){

        JFrame jFrame = new JFrame("Orbit (Change Password");
        Dimension dimension=  new Dimension(450,450);
        jFrame.setPreferredSize(dimension);
        SAVEButton.setFocusable(false);

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                change();
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
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }

    void change()
    {
        String old_password_database = "";
        try
        {
            ResultSet resultSet = Database_query.get_username_password();
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                old_password_database = resultSet.getString(2);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


        if(!new String(old_password_field.getPassword()).equals(old_password_database))
        {
            JOptionPane.showMessageDialog(null, "Old Password Mismatched",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(!new String(new_password_field.getPassword()).equals(new String(new_password_field_again.getPassword())))
        {
            JOptionPane.showMessageDialog(null, "New Password Mismatched",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
       else
        {
            Database_query.update_password("Orbit Coaching",new String(new_password_field.getPassword()));
        }

    }
}
