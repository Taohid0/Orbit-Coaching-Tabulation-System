package orbit_coaching;

import javax.swing.*;
import java.awt.*;

public class test {
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

    test()
    {
        JFrame jFrame = new JFrame("test");
        jFrame.add(panel1);
        jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
