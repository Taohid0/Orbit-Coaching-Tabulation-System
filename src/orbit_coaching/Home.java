package orbit_coaching;

import sun.util.resources.cldr.es.CalendarData_es_PY;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;

import static javafx.scene.input.KeyCode.F;

public class Home {
    private JPanel homePanel;
    private JPanel panel1;
    private JLabel img;
    private JMenu file_menu,student_menu,result_menu,teacher_menu,bill_menu,help_menu;
    private JMenuItem add_student,edit_student;
    private JMenuItem input_marks,edit_marks,studentwise_result,coursewise_result,print_result,studentwise_print,coursewise_print;
    private JMenu Print,Result,Payment,Total_transaction;
    private JMenuItem Student_wise,Course_wise,Income,Expense,Monthly,Yearly;
    private JMenu show;

    public Home()
    {
        JFrame jFrame = new JFrame("Orbit Coaching");

        JMenuBar jMenuBar = new JMenuBar();

        Print = new JMenu("Print");
        Result = new JMenu("Result");
        Student_wise = new JMenuItem("Studentwise");
        Course_wise = new JMenuItem("Coursewise");
        Payment = new JMenu("Payment");
        Income = new JMenuItem("Income");
        Expense = new JMenuItem("Expense");
        Total_transaction = new JMenu("Total transaction");
        Monthly= new JMenuItem("Monthly");
        Yearly = new JMenuItem("Yearly");

        Result.add(Student_wise);
        Result.add(Course_wise);

        Payment.add(Income);
        Payment.add(Expense);

        Total_transaction.add(Monthly);
        Total_transaction.add(Yearly);

        Print.add(Result);
        Print.add(Payment);
        Print.add(Total_transaction);


        //file menu
        file_menu = new JMenu("File");
        JMenuItem exit_menu=  new JMenuItem("Exit");
        exit_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file_menu.add(exit_menu);
        jMenuBar.add(file_menu);
        //student menu
        student_menu = new JMenu("Student");
        add_student = new JMenuItem("Add New Student");
        add_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Add_student add_student = new Add_student();
            }
        });
        edit_student = new JMenuItem("Edit Student Info");
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

        show = new JMenu("Show");
        JMenuItem course_wise_result = new JMenuItem("Course wise Result");
        course_wise_result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Course_wise_result_show course_wise_result_show =  new Course_wise_result_show();
            }
        });
        JMenuItem course_wise_result_name_wise=new JMenuItem("Course wise Result (name wise)");
        course_wise_result_name_wise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Course_wise_result_show_name_wise course_wise_result_show_name_wise=
                        new Course_wise_result_show_name_wise();
            }
        });
        JMenuItem student_wise_result = new JMenuItem("Student wise result");
        student_wise_result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Student_wise_result_show student_wise_result_show = new Student_wise_result_show();
            }
        });

        JMenuItem student_wise_result_single_exam = new JMenuItem("Student wise result (single exam)");
        student_wise_result_single_exam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Student_wise_result_one_exam student_wise_result_one_exam = new Student_wise_result_one_exam();
            }
        });
        JMenuItem student_list = new JMenuItem("Student List By School");
        student_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Show_student_by_school_and_year show_student_by_school_and_year = new Show_student_by_school_and_year();
            }
        });
        JMenuItem student_list_by_class = new JMenuItem("Student List By Class");
        student_list_by_class.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                show_student_list show_by_class = new show_student_list();
            }
        });
        JMenuItem show_student_payment =new JMenuItem("Student's Payment");
        show_student_payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Student_payment_show student_payment_show = new Student_payment_show();
            }
        });

        JMenuItem income_from_student = new JMenuItem("Income From student (monthly payment)");
        income_from_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Student_payment_show student_payment_show = new Student_payment_show();
            }
        });
        JMenuItem income_other = new JMenuItem("Other income");
        income_other.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Show_other_income show_other_income = new Show_other_income();
            }
        });
        JMenuItem teacher_attendance_show = new JMenuItem("Teacher Attendance show");
        teacher_attendance_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Teacher_attendance_show teacher_attendance_show1 = new Teacher_attendance_show();
            }
        });

        JMenuItem show_expense = new JMenuItem("Show Expense");
        show_expense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jFrame.dispose();
                Expense expense = new Expense();
            }
        });

        show.add(course_wise_result);
        show.add(course_wise_result_name_wise);
        show.add(student_wise_result);
        show.add(student_wise_result_single_exam);
        show.add(student_list_by_class);
        show.add(student_list);
        show.add(show_student_payment);
        show.add(income_from_student);
        show.add(income_other);
        show.add(teacher_attendance_show);
        show.add(show_expense);
        jMenuBar.add(show);


        jMenuBar.add(Print);
        jFrame.setJMenuBar(jMenuBar);


        jFrame.setContentPane(homePanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        edit_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Edit_student edit_student = new Edit_student();
            }
        });
        input_marks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Insert_marks insert_marks = new Insert_marks();
            }
        });
        edit_marks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Edit_marks edit_marks = new Edit_marks();
            }
        });


        jFrame.pack();


        jFrame.setVisible(true);

    }

    private void createUIComponents() {
        img = new JLabel(new ImageIcon("Orbit_Logo.png"));
    }
}

