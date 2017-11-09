package orbit_coaching;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JFrame jFrame;

    private String group_values []= {"Science","Arts","Commerce"};
    private String blood_group[] = {"A+","A-","AB+","AB-","B+","B-","O+","O-","Bombay"};
    private String mname,fname,name,cls,roll,school,bgroup,group,admission_date,birth_date,address,c1,c2;


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
                Database_query.insert_student(name,fname,mname,address,c1,c2,admission_date,birth_date,roll,
                        cls,group,school,bgroup);
                jFrame.dispose();
                Home home = new Home();
                //System.out.println(bgroup);


            }
        });
        jFrame = new JFrame("Add New Student");
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);


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
        roll = roll_textbox.getText();
        cls =class_combobox.getSelectedItem().toString();
        group = group_combobox.getSelectedItem().toString();
        school = school_combobox.getSelectedItem().toString();
        bgroup=  blood_group_combobox.getSelectedItem().toString();




    }


}
