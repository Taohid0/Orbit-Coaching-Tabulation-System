package orbit_coaching;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javafx.scene.control.Alert.AlertType;
public class Login_form {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton GOButton;
    private JFrame jFrame ;

    public Login_form()
    {
        jFrame  = new JFrame("Orbit Coaching");
        Dimension dimension=  new Dimension(450,450);
        jFrame.setPreferredSize(dimension);
        //textField1=  new JTextField();
        //passwordField1 = new JPasswordField();
        //GOButton = new JButton();
        GOButton.setFocusable(false);
        GOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String un ="Orbit Coaching";
                String pw = "goahead";
                String error_message = "Username or password or both mismatched";
                String title_bar = "ERROR";


                ResultSet resultSet = Database_query.get_username_password();

                try{
                    resultSet.beforeFirst();
                    if(resultSet.next())
                    {
                        if(textField1.getText().equals(resultSet.getString(1)) &&
                               new String(passwordField1.getPassword()).equals(resultSet.getString(2)))
                        {
                            System.out.println("login Successfull");
                            jFrame.dispose();
                            //jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            Home home = new Home();
                        }
                        else
                        {

                            JOptionPane.showMessageDialog(null, error_message,
                                    title_bar, JOptionPane.ERROR_MESSAGE);
                        }

                    }
                    else if(textField1.getText().equals(un) && new String(passwordField1.getPassword()).equals(pw))
                    {
                        System.out.println("Login successfull");
                        Home home = new Home();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, error_message,
                                title_bar, JOptionPane.ERROR_MESSAGE);

                    }

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }


            }
        });


        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }

}
