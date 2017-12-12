package orbit_coaching;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Add_teacher {
    private JPanel jPanel;
    private JTextField name_textfield;
    private JTextField joining_date_textfield;
    private JButton save_button;
    private JComboBox institution_combobox;


    public Add_teacher()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");
        Dimension dimension=  new Dimension(450,450);
        jFrame.setPreferredSize(dimension);
        jFrame.setContentPane(jPanel);
        save_button.setFocusable(false);

        institution_combobox.setEditable(true);


        try
        {
            ResultSet resultSet = Database_query.get_teachear_institution();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                institution_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_textfield.getText();
                String joininig_date = joining_date_textfield.getText();
                String institution = institution_combobox.getSelectedItem().toString();

                try
                {
                    Database_query.insert_teacher(name,joininig_date,institution);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
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


}
