package orbit_coaching;

import javax.swing.*;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Add_student {
    private JPanel panel1;
    private JTextField student_name_textbox;
    private JTextField father_name_textbox;
    private JTextField mother_name_textbox;
    private JTextField roll_textbox;
    private JTextField address_textbox;
    private JTextField admission_date_textbox;
    private JTextField date_of_birth_textbox;
    private JTextField contact1_textbox;
    private JTextField contact2_textbox;
    private JButton ADDTHISSTUDENTButton;
    private JComboBox blood_group_combobox;
    private JComboBox group_combobox;
    private JComboBox school_combobox;
    private JComboBox class_combobox;
    private JTextField foryear_textbox;
    private JTextField registration_number_textbox;
    JFrame jFrame;

    private String group_values []= {"Science","Arts","Commerce"};
    private String blood_group[] = {"A+","A-","AB+","AB-","B+","B-","O+","O-","Bombay"};
    private String mname,fname,name,cls,roll,school,bgroup,group,admission_date,birth_date,address,c1,c2,for_year;
    private String temp_roll;

    public Add_student()
    {

        ADDTHISSTUDENTButton.setFocusable(false);
        group_combobox.removeAllItems();
        blood_group_combobox.removeAllItems();
        school_combobox.removeAllItems();
        class_combobox.removeAllItems();
        group_combobox.setEditable(true);
        school_combobox.setEditable(true);
        class_combobox.setEditable(true);


        try
        {
            ResultSet resultSet = Database_query.get_school_name();
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
        school_combobox.addItem("");

        try
        {
            ResultSet resultSet = Database_query.get_class();
            resultSet.beforeFirst();
            while (resultSet.next())
            {
                class_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        class_combobox.addItem("");




        for (int i = 0;i<(int)group_values.length;i++)
        {
            group_combobox.addItem(group_values[i]);
        }
        group_combobox.addItem("");

        for(int i = 0;i<(int)blood_group.length;i++)
        {
            blood_group_combobox.addItem(blood_group[i]);

        }

        ADDTHISSTUDENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get_info();
                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All The Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Data_validation.check_date(admission_date) || Data_validation.check_date(birth_date))
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up Date Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Database_query.insert_student(name, fname, mname, address, c1, c2, admission_date, birth_date, roll,
                            cls, group, school, bgroup, for_year, temp_roll);
                    jFrame.dispose();
                    Home home = new Home();
                    //System.out.println(bgroup);
                }


            }
        });
        registration_number_textbox.setEditable(false
        );
        jFrame = new JFrame("Orbit (Add New Student)");
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.pack();
        jFrame.setVisible(true);

        try
        {
            ResultSet resultSet = Database_query.get_max_roll();
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                String r = Integer.toString(Integer.parseInt(resultSet.getString(1))+1);
                registration_number_textbox.setText(r);
            }
        }
        catch (NumberFormatException e)
        {
            registration_number_textbox.setText("1");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

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

    private void get_info()
    {
        name = student_name_textbox.getText();
        fname = father_name_textbox.getText();
        mname = mother_name_textbox.getText();
        address = address_textbox.getText();
        c1 = contact1_textbox.getText();
        c2 = contact2_textbox.getText();
        admission_date = date_of_birth_textbox.getText();
        birth_date= date_of_birth_textbox.getText();
        roll = registration_number_textbox.getText();
        cls =class_combobox.getSelectedItem().toString();
        group = group_combobox.getSelectedItem().toString();
        school = school_combobox.getSelectedItem().toString();
        bgroup=  blood_group_combobox.getSelectedItem().toString();
        for_year = foryear_textbox.getText();
        temp_roll =roll_textbox.getText();

    }
    boolean check()
    {
        if(student_name_textbox.getText().equals("") || father_name_textbox.getText().equals("")||mother_name_textbox.getText().equals("")||
                roll_textbox.getText().equals("") || address_textbox.getText().equals("")||foryear_textbox.getText().equals("")||
                admission_date_textbox.getText().equals("")||date_of_birth_textbox.getText().equals("")||
                class_combobox.getItemCount()==0 || school_combobox.getItemCount()==0 || group_combobox.getItemCount()==0 ||
                blood_group_combobox.getItemCount()==0)
        {
            return true;
        }

        return false;
    }


}
