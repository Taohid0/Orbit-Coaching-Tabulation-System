package orbit_coaching;

import javafx.embed.swing.JFXPanel;
import org.omg.PortableInterceptor.HOLDING;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Edit_teacher {

    private JComboBox id_combobox;
    private JTextField name_textfield;
    private JTextField joining_date_text_field;
    private JTextField year_textfield;
    private JTextField institution_textfield;
    private JComboBox active_combox;
    private JButton update_button;
    private JPanel panel1;

    Edit_teacher()
    {
        JFrame jFrame = new JFrame("Orbit (Edit Teacher's Information)");
        Dimension dimension=  new Dimension(450,350);
        jFrame.setPreferredSize(dimension);
        jFrame.setContentPane(panel1);
        jFrame.setResizable(false);
        update_button.setFocusable(false);

        active_combox.addItem("ACTIVE");
        active_combox.addItem("INACTIVE");
        try
        {
            ResultSet resultSet =Database_query.get_teachear_ids();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                id_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        id_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_data();
            }
        });
        update_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (check()) {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All The Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (Data_validation.check_date(joining_date_text_field.getText())) {
                    JOptionPane.showMessageDialog(null, "Please Fill Up Date Field Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int active = 0;
                    if (active_combox.getSelectedItem().toString().equals("INACTIVE")) {
                        active = 1;
                    }
                    Database_query.update_teacher(Integer.parseInt(id_combobox.getSelectedItem().toString())
                            , name_textfield.getText(), institution_textfield.getText(),
                            joining_date_text_field.getText(), year_textfield.getText(), active);
                }
            }
        });
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e)
            {
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


    public void set_data()
    {
        try
        {
            ResultSet resultSet  = Database_query.get_teacher_info(id_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                name_textfield.setText(resultSet.getString(1));
                institution_textfield.setText(resultSet.getString(2));
                joining_date_text_field.setText(resultSet.getString(3));
                if(resultSet.getString(4).equals("0"))
                    active_combox.setSelectedItem("ACTIVE");
                else
                    active_combox.setSelectedItem("INACTIVE");
                year_textfield.setText(resultSet.getString(5));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    boolean check()
    {
        if(name_textfield.getText().equals("") || joining_date_text_field.getText().equals("") || joining_date_text_field.getText().equals("")
                || institution_textfield.getText().equals(""))
        {
            return true;
        }
        return false;
    }
}
