package orbit_coaching;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import javax.swing.*;
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
        jFrame.add(panel1);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        SAVEButton.setFocusable(false);

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_date();
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
}
