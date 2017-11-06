package orbit_coaching;

import javax.swing.*;
import java.awt.*;

public class Home {
    private JPanel homePanel;
    private JButton button1;
    private JMenu file_menu,student_menu,result_menu,teacher_menu,bill_menu,help_menu;
    private JMenuItem add_student,edit_student;
    private JMenuItem input_marks,edit_marks,studentwise_result,coursewise_result,print_result,studentwise_print,coursewise_print;

    public Home()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");

        JMenuBar jMenuBar = new JMenuBar();

        //file menu
        file_menu = new JMenu("File");
        JMenuItem exit_menu=  new JMenuItem("Exit");
        file_menu.add(exit_menu);
        jMenuBar.add(file_menu);
        //student menu
        student_menu = new JMenu("Student");
        add_student = new JMenuItem("Add New Student");
        edit_student = new JMenuItem("Edit Stuudent Info");
        student_menu.add(add_student);
        student_menu.add(edit_student);
        //result menu
        result_menu = new JMenu("Result");
        input_marks = new JMenuItem("Input Marks");
        edit_marks = new JMenuItem("Edit Marks");
        studentwise_result = new JMenuItem("Studentwise Result");
        coursewise_result = new JMenuItem("Coursewise Result");
        JMenu result_submenu = new JMenu("Print Result");
        studentwise_print = new JMenuItem("Studentwise");
        coursewise_print = new JMenuItem("Coursewise");
        result_submenu.add(studentwise_print);
        result_submenu.add(coursewise_print);
        result_menu.add(input_marks);
        result_menu.add(edit_marks);
        result_menu.add(result_submenu);

        jMenuBar.add(student_menu);
        jMenuBar.add(result_menu);
        jFrame.setJMenuBar(jMenuBar);


        jFrame.setContentPane(homePanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        jFrame.pack();


        jFrame.setVisible(true);

    }
}

