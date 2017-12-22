package orbit_coaching;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Insert_institution_data {
    private JPanel panel1;
    private JTextField name_textfield;
    private JTextField address_textfield;
    private JTextField contact_textfield;
    private JButton SAVEButton;

    Insert_institution_data()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");
        Dimension dimension=  new Dimension(450,350);
        jFrame.setPreferredSize(dimension);
        jFrame.add(panel1);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        SAVEButton.setFocusable(false);

        SAVEButton.addActionListener(new ActionListener() {
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
        String contact = contact_textfield.getText();

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
        if(contact_textfield.getText().equals("") || name_textfield.getText().equals("") || address_textfield.getText().equals(""))
        {
            return true;
        }
        else
            return false;
    }
}
