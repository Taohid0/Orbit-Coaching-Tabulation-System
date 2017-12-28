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
        JFrame jFrame = new JFrame("Orbit");

        JMenuBar jMenuBar = new JMenuBar();

        JMenu teacher_menu = new JMenu("Teacher");
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

        //file menu items
        JMenuItem add_institution_info = new JMenuItem("Add Institution Information");
        add_institution_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Insert_institution_data insert_institution_data = new Insert_institution_data();
            }
        });


        JMenuItem edit_institution_info = new JMenuItem("Edit Institution Information");
        edit_institution_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Insert_institution_data_edit insert_institution_data_edit = new Insert_institution_data_edit();
            }
        });

        JMenuItem change_password = new JMenuItem("Change Password");
        change_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Change_password change_password1=new Change_password();
            }
        });

        JMenuItem change_institution_details = new JMenuItem("Change Institution Information");
        change_institution_details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                //Change in
            }
        });



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

        JMenuItem page_menu= new JMenuItem("Print Page Settings");
        page_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Print_settings print_settings = new Print_settings();
            }
        });

        JMenuItem instition_info = new JMenuItem("About Institution");
        instition_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                About_institution about_institution = new About_institution();
            }
        });
        JMenuItem developer_info = new JMenuItem("About Developer");
        developer_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                About_developer about_developer = new About_developer();
            }
        });

        file_menu.add(add_institution_info);
        file_menu.add(edit_institution_info);
        file_menu.add(change_password);
        file_menu.add(page_menu);
        file_menu.add(instition_info);
        file_menu.add(developer_info);
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
        edit_student = new JMenuItem("Edit Student Information");
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
        //result_menu.add(result_submenu);

        //for student menu
        JMenuItem show_student_class_wise =new JMenuItem("Show Students (Class Wise)");
        show_student_class_wise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                show_student_list show=new show_student_list();
            }
        });
        JMenuItem show_student_school_wise = new JMenuItem("Show Students (Institution Wise)");
        show_student_school_wise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Show_student_by_school_and_year show_student_by_school_and_year =new Show_student_by_school_and_year();
            }
        });

        JMenuItem student_payment_student = new JMenuItem("Add Student Payment");
        student_payment_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Add_student_payment add_student_payment = new Add_student_payment();
            }
        });
        JMenuItem student_payment_show_student = new JMenuItem("Student's Payment Show (Student Wise)");
        student_payment_show_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Student_payment_show student_payment_show =new Student_payment_show();
            }
        });

        JMenuItem total_student_payment_show_student = new JMenuItem("Total Student's Payment (Monthly)");
        total_student_payment_show_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Total_monthly_income_from_student total_monthly_income_from_student = new Total_monthly_income_from_student();
            }
        });


        student_menu.add(show_student_class_wise);
        student_menu.add(show_student_school_wise);
        student_menu.add(student_payment_show_student);
        student_menu.add(student_payment_show_student);
        student_menu.add(total_student_payment_show_student);
        jMenuBar.add(student_menu);
        jMenuBar.add(result_menu);

        //teacher menu items
        JMenuItem add_teacher = new JMenuItem("Add Teacher");
        add_teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Add_teacher add_teacher1 = new Add_teacher();
            }
        });
        JMenuItem edit_teacher = new JMenuItem("Edit Teacher");
        edit_teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Edit_teacher edit_teacher =  new Edit_teacher();
            }
        });
        JMenuItem teacher_attendance = new JMenuItem("Teacher Attendance");
        teacher_attendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Teacher_attendance teacher_attendance1 = new Teacher_attendance();
            }
        });
        JMenuItem teacher_attendance_show_teacher =new JMenuItem("Teacher Attendance Show");
        teacher_attendance_show_teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Teacher_attendance_show teacher_attendance_show1 = new Teacher_attendance_show();
            }
        });

        show = new JMenu("Show");
        JMenuItem course_wise_result = new JMenuItem("Course wise Result");
        course_wise_result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Course_wise_result_show course_wise_result_show =  new Course_wise_result_show();
            }
        });
        JMenuItem course_wise_result_name_wise=new JMenuItem("Course wise Result (Highest Marks Order)");
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
                //Show_other_income show_other_income = new Show_other_income();
                Show_total_income_other show_total_income_other = new Show_total_income_other();
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
                //Expense expense = new Expense();
                //Total_expense_year total_expense_year = new Total_expense_year();
                Total_expense total_expense = new Total_expense();
            }
        });

        teacher_menu.add(add_teacher);
        teacher_menu.add(edit_teacher);
        teacher_menu.add(teacher_attendance);
        teacher_menu.add(teacher_attendance_show_teacher);
        jMenuBar.add(teacher_menu);


        //transaction menubar
        JMenu transaction_menu = new JMenu("Transaction");
        JMenuItem add_students_payment = new JMenuItem("Add Student's Payment");
        add_students_payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Add_student_payment add_student_payment = new Add_student_payment();
            }
        });
        JMenuItem other_income_transaction = new JMenuItem("Add Other Income");
        other_income_transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Other_income other_income =new Other_income();}
        });
        JMenuItem add_expense_transaction = new JMenuItem("Add Expense");
        add_expense_transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Expense expense = new Expense();
            }
        });
        JMenuItem expense_for_teacher = new JMenuItem("Show Expense For Teachers");
        expense_for_teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Total_teacher_expense total_teacher_expense = new Total_teacher_expense();
            }
        });
        JMenuItem expense_for_student = new JMenuItem("Show Expense For Students");
        expense_for_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Total_student_expense total_student_expense = new Total_student_expense();
            }
        });
        JMenuItem expense_for_other = new JMenuItem("Show Expense For Others");
        expense_for_other.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Total_other_expense total_other_expense = new Total_other_expense();
            }
        });
        JMenuItem total_expense = new JMenuItem("Show Total Expense");
        total_expense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Total_expense total_expense1 = new Total_expense();
            }
        });
        JMenuItem payment_of_single_student_transaction = new JMenuItem("Show Payment Of Specific Student");
        payment_of_single_student_transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Student_payment_show student_payment_show = new Student_payment_show();

            }
        });
        JMenuItem show_all_monthly_payment = new JMenuItem("Show Total Monthly Payment");
        show_all_monthly_payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Total_monthly_income_from_student total_monthly_income_from_student = new Total_monthly_income_from_student();
            }
        });
        JMenuItem show_all_other_income = new JMenuItem("Show Other Income");
        show_all_other_income.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Show_total_income_other show_total_income_other = new Show_total_income_other();
            }
        });

        transaction_menu.add(add_students_payment);
        transaction_menu.add(payment_of_single_student_transaction);
        transaction_menu.add(other_income_transaction);
        transaction_menu.add(show_all_monthly_payment);
        transaction_menu.add(show_all_other_income);
        transaction_menu.add(add_expense_transaction);
        transaction_menu.add(expense_for_teacher);
        transaction_menu.add(expense_for_student);
        transaction_menu.add(expense_for_other);
        transaction_menu.add(total_expense);
        jMenuBar.add(transaction_menu);


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


        //jMenuBar.add(Print);
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dimension=  new Dimension(450,450);
        img.setMinimumSize(dimension);

        jFrame.setVisible(true);

    }

    private void createUIComponents() {
        img = new JLabel(new ImageIcon("Orbit_Logo.png"));
    }
}

