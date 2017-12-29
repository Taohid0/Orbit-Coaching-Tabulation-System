package orbit_coaching;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Edit_marks {
    private JPanel jPanel1;
    private JComboBox examtype_comobox;
    private JComboBox class_combobox;
    private JTable table1;
    private JComboBox for_year_combobox;
    private JButton button1;
    private JTextField total_marks_textbox;
    private JComboBox subject_combobox;
    private JTextField date_textfield;

    DefaultTableModel defaultTableModel = null;


    public void edit_marks()
    {
        ResultSet resultSet=null;
        Connection conn = null;
        Statement stmt = null;
        String date=null,cls=null,exam_type=null;
        try {
            date = date_textfield.getText();
            cls = class_combobox.getSelectedItem().toString();
            exam_type = examtype_comobox.getSelectedItem().toString();
        }
        catch (Exception ex)
        {
            //show error
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            stmt = conn.createStatement();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        for(int i =0;i<(int)table1.getRowCount();i++) {
        try
        {
                if(table1.getValueAt(i,0).toString().replaceAll("\\s","")==""
                        && table1.getValueAt(i,1).toString().replaceAll("\\s","")=="")
                {
                    //add warning message;
                }
                else

                {
                    String query = "DELETE FROM Marks WHERE roll=" + "\""+ table1.getValueAt(i, 0) + "\""+ " AND "+
                            " exam_type="+"\""+examtype_comobox.getSelectedItem().toString()+
                            "\""+" AND for_year="+ "\""+for_year_combobox.getSelectedItem().toString()+ "\""+
                            " AND subject="+ "\""+
                            subject_combobox.getSelectedItem().toString()+ "\""+
                            " AND cls="+"\""+cls+"\"" + " AND  date ="+"\""+date+"\"";
                    System.out.println(query);stmt.execute(query);


                    query= "INSERT INTO Marks (roll,exam_type,date,out_of,obtained_markd,cls,subject,for_year) " +
                            "VALUES (?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setString(1,table1.getValueAt(i,0).toString());
                    preparedStatement.setString(2,exam_type);
                    preparedStatement.setString(3,date);
                    preparedStatement.setString(4,total_marks_textbox.getText());
                    preparedStatement.setString(5,table1.getValueAt(i,1).toString());
                    preparedStatement.setString(6,cls);
                    preparedStatement.setString(7,subject_combobox.getSelectedItem().toString());
                    preparedStatement.setString(8,for_year_combobox.getSelectedItem().toString());
                    preparedStatement.execute();
                }
            }
            catch ( Exception ex)
            {
            ex.printStackTrace();
             }
            }

    }
    private void fill_fields()
    {

            try
            {
                class_combobox.removeAllItems();
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

            try
            {

                examtype_comobox.removeAllItems();
                ResultSet resultSet = Database_query.get_exam_type();
                resultSet.beforeFirst();
                while (resultSet.next())
                {
                    examtype_comobox.addItem(resultSet.getString(1));
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            try
            {
                subject_combobox.removeAllItems();
                ResultSet resultSet = Database_query.get_subject_list();
                resultSet.beforeFirst();
                while (resultSet.next())
                {
                    subject_combobox.addItem(resultSet.getString(1));
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }




    }

    public void fill_student_fields()
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Registration Number","Obtained Marks"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Registration Number").setCellRenderer( centerRenderer );
        table1.getColumn("Obtained Marks").setCellRenderer(centerRenderer);


        String date = "";
        String cls="";
        try
        {
            date = date_textfield.getText();
            cls = class_combobox.getSelectedItem().toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


        try
        {
            ResultSet resultSet = Database_query.get_marks_info(cls,date,examtype_comobox.getSelectedItem().toString(),
                    subject_combobox.getSelectedItem().toString());
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                defaultTableModel.addRow(new String[]{resultSet.getString(1),
              resultSet.getString(7)});

            }
            resultSet.beforeFirst();
//            if(resultSet.next())
//            {
//                examtype_comobox.setSelectedItem(resultSet.getString(2));
//            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public Edit_marks()
    {
        defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Registration Number","Obtained Marks"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Registration Number").setCellRenderer( centerRenderer );
        table1.getColumn("Obtained Marks").setCellRenderer(centerRenderer);



        for_year_combobox.setEditable(true);

        fill_fields();
        try
        {

            for_year_combobox.removeAllItems();
            ResultSet resultSet = Database_query.get_year();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                for_year_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        JFrame jFrame = new JFrame("Orbit Coaching (Edit Marks)");
        jFrame.setContentPane(jPanel1);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.pack();

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


        jFrame.setVisible(true);

        for_year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //fill_fields();
                //fill_student_fields();
                //fill_date();
            }
        });
        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //fill_fields();
                //fill_student_fields();
               // fill_date();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All The Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Data_validation.check_date(date_textfield.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up Date Field Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    edit_marks();
                    jFrame.dispose();
                    Home home = new Home();
                }
            }
        });

        date_textfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_student_fields();
            }
        });
        subject_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_student_fields();
            }
        });
    }
    void fill_date()
    {


        try
        {
            ResultSet resultSet =Database_query.get_total_marks(for_year_combobox.getSelectedItem().toString(),
                   class_combobox.getSelectedItem().toString(),examtype_comobox.getSelectedItem().toString(),
                    subject_combobox.getSelectedItem().toString(),date_textfield.getText());
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                total_marks_textbox.setText(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    boolean check()
    {
        try {
            if (total_marks_textbox.getText().equals("") || date_textfield.getText().equals("") ||
                    class_combobox.getSelectedItem().toString().equals("") || examtype_comobox.getSelectedItem().toString().equals("") ||
                    subject_combobox.getSelectedItem().toString().equals("")|| for_year_combobox.getSelectedItem().toString().equals(""))
                return true;
            return false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return true;
        }
    }
}
