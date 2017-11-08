package orbit_coaching;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login_form {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton GOButton;
    private JFrame jFrame ;

    public Login_form()
    {
        jFrame  = new JFrame("Login");
        //textField1=  new JTextField();
        //passwordField1 = new JPasswordField();
        //GOButton = new JButton();

        GOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String un ="Orbit Coaching";
                String pw = "goahead";


                ResultSet resultSet = Database_query.get_username_password();

                try{
                    resultSet.beforeFirst();
                    if(resultSet.next())
                    {
                        if(textField1.getText().equals(resultSet.getString(1)) &&
                               new String(passwordField1.getPassword()).equals(resultSet.getString(2)))
                        {
                            System.out.println("login Successfull");
                            Home home = new Home();
                        }
                        else
                        {
                            System.out.println("login unsuccessfull");
                        }

                    }
                    else if(textField1.getText().equals(un) && new String(passwordField1.getPassword()).equals(pw))
                    {
                        System.out.println("Login successfull");
                        Home home = new Home();
                    }
                    else
                    {
                        System.out.println("Login unsuccessfull");
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
        jFrame.setVisible(true);

    }

}
