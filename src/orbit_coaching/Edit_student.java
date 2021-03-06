package orbit_coaching;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Edit_student {

    private JPanel panel1;
    private JTextField name_textbox;
    private JTextField fname_textbox;
    private JTextField mname_textbox;
    private JTextField address_textbox;
    private JTextField admission_date_textbox;
    private JTextField birthdate_textbox;
    private JTextField c1_textbox;
    private JTextField c2_textbox;
    private JButton SAVEEDITEDDATAButton;
    private JComboBox blood_group_combobox;
    private JComboBox class_combobox;
    private JComboBox school_combobox;
    private JComboBox group_combobox;
    private JComboBox for_year_combobox;
    private JComboBox registration_combobox;
    private JTextField roll_textfield;
    String group[] = {"Science","Arts","Commerce",""};
    private String blood_group[] = {"A+","A-","AB+","AB-","B+","B-","O+","O-","Bombay"};
    public void add_school()
    {
        try
        {
            ResultSet resultSet= null;
            resultSet = Database_query.get_school_name();

            resultSet.beforeFirst();

            while (resultSet.next())
            {
                school_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void set_value()
    {

        add_school();
        group_combobox.removeAllItems();
        blood_group_combobox.removeAllItems();

        for(int i =0;i<(int)group.length;i++)
        {
            group_combobox.addItem(group[i]);
        }

        for(int i = 0;i<(int)blood_group.length;i++)
        {
            blood_group_combobox.addItem(blood_group[i]);
        }
        try
        {

            //System.out.println(roll_combobox.getSelectedItem().toString()+for_year_combobox.getSelectedItem().toString());
            ResultSet resultSet =null;
            resultSet = Database_query.get_student_info(registration_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();
            if(resultSet.next())
            {
                add_school();
                try
                {
                    group_combobox.removeAllItems();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                try
                {
                    blood_group_combobox.removeAllItems();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                for(int i =0;i<(int)group.length;i++)
                {
                    group_combobox.addItem(group[i]);
                }

                for(int i = 0;i<(int)blood_group.length;i++)
                {
                    blood_group_combobox.addItem(blood_group[i]);
                }
                roll_textfield.setText(resultSet.getString(2));
                name_textbox.setText(resultSet.getString(3));
                fname_textbox.setText(resultSet.getString(4));
                mname_textbox.setText(resultSet.getString(5));
                class_combobox.setSelectedItem(resultSet.getString(6));
                group_combobox.setSelectedItem(resultSet.getString(7));
                school_combobox.setSelectedItem(resultSet.getString(8));
                address_textbox.setText(resultSet.getString(9));
                birthdate_textbox.setText(resultSet.getString(10));
                admission_date_textbox.setText(resultSet.getString(11));
                blood_group_combobox.setSelectedItem(resultSet.getString(12));
                c1_textbox.setText(resultSet.getString(13));
                c2_textbox.setText(resultSet.getString(14));
                for_year_combobox.setSelectedItem(resultSet.getString(15));

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public Edit_student()
    {
        try
        {
            ResultSet resultSet = null;
            resultSet = Database_query.get_roll_number();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                registration_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        for_year_combobox.setEditable(true);
        school_combobox.setEditable(true);
        class_combobox.setEditable(true);
        group_combobox.setEditable(true);
        blood_group_combobox.setEditable(true);

        //this will be false
        roll_textfield.setEditable(true);

        JFrame jFrame = new JFrame("Orbit Coaching (Edit Student's Information)");
        jFrame.setContentPane(panel1);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
        jFrame.pack();
        jFrame.setVisible(true);

        registration_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     set_value();

            }
        });
        SAVEEDITEDDATAButton.setFocusable(false);

        SAVEEDITEDDATAButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All The Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Data_validation.check_date(admission_date_textbox.getText()) || Data_validation.check_date(birthdate_textbox.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up Date Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String name = name_textbox.getText();
                    String fname = fname_textbox.getText();
                    String mname = mname_textbox.getText();
                    String address = address_textbox.getText();
                    String c1 = c1_textbox.getText();
                    String c2 = c2_textbox.getText();
                    String admission_date = admission_date_textbox.getText();
                    String birthdate = birthdate_textbox.getText();
                    String year = for_year_combobox.getSelectedItem().toString();
                    String roll = registration_combobox.getSelectedItem().toString();
                    String cls = class_combobox.getSelectedItem().toString();
                    String group = group_combobox.getSelectedItem().toString();
                    String bgroup = blood_group_combobox.getSelectedItem().toString();
                    String school = school_combobox.getSelectedItem().toString();
                    String for_year = for_year_combobox.getSelectedItem().toString();
                    String temp_roll = roll_textfield.getText();

                    Database_query.update_student(name, fname, mname, address, c1, c2, admission_date, birthdate,
                            roll, cls, group, school, bgroup, for_year, temp_roll);

                    jFrame.dispose();
                    Home home = new Home();
                }
            }
        });
    }
    boolean check()
    {
        if(name_textbox.getText().equals("") || fname_textbox.getText().equals("")||mname_textbox.getText().equals("")||
                roll_textfield.getText().equals("") || address_textbox.getText().equals("")||
                admission_date_textbox.getText().equals("")||birthdate_textbox.getText().equals(""))
        {
            return true;
        }

        return false;
    }

}
