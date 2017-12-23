package orbit_coaching;

import org.omg.CORBA.DATA_CONVERSION;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class About_institution {
    private JTextArea name;
    private JTextArea address;
    private JTextArea contact;
    private JPanel panel1;

    About_institution()
    {
        JFrame jFrame = new JFrame("Orbit (About Institution)");
        jFrame.add(panel1);

        try
        {
            ResultSet resultSet = Database_query.get_institution_info();
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                name.setText("\n\t\t"+resultSet.getString(1)+"\t\t\n");
                address.setText("\n\t\t"+resultSet.getString(2)+"\t\t\n");
                contact.setText("\n\t\t"+resultSet.getString(3)+"\t\t\n");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        name.setEditable(false);
        address.setEditable(false);
        contact.setEditable(false);

        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.pack();
        jFrame.setVisible(true);

        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                jFrame.dispose();
                Home home  = new Home();
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

}
