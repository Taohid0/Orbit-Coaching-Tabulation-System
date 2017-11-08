package orbit_coaching;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_form {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton GOButton;
    JFrame jFrame ;

    public Login_form()
    {
        jFrame  = new JFrame("Login");
        textField1=  new JTextField();
        passwordField1 = new JPasswordField();
        GOButton = new JButton();

        GOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

}
