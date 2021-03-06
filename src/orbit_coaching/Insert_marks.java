package orbit_coaching;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Insert_marks {
    private JComboBox examtype_comobox;
    private JComboBox class_combobox;
    private JTextField date_textfield;
    private JTable table1;
    private JPanel jPanel1;
    private JComboBox for_year_combobox;
    private JButton SAVE;
    private JTextField total_marks_textbox;
    private JComboBox subject_combobox;
    int count=0;

    public void get_table_data()
    {
        try
        {
            if(examtype_comobox.getSelectedItem().toString().replaceAll("\\s+","")=="" ||
               class_combobox.getSelectedItem().toString().replaceAll("\\s+","")==""||
                    date_textfield.getText().replaceAll("\\s+","")=="" ||
                    for_year_combobox.getSelectedItem().toString().replaceAll("\\s+","")==""||
                    total_marks_textbox.getText().replaceAll("\\s+","")=="" ||
                    class_combobox.getSelectedItem().toString().replaceAll("\\s","")==""||
            subject_combobox.getSelectedItem().toString().replaceAll("\\s+","")=="")
            {
                String error_message = "Please fill up all the configuration fields correctly";
                String title_bar = "ERROR";
                JOptionPane.showMessageDialog(null, error_message,
                        title_bar, JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
        catch (Exception ex)
        {
            String error_message = "Please fill up all the configuration fields correctly";
            String title_bar = "ERROR";
            JOptionPane.showMessageDialog(null, error_message,
                    title_bar, JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }

        Statement stmt=null;
        Connection conn=null;
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
            for (int i = 0;i<(int)table1.getRowCount();i++)
        {
            try
            {

                String xm = examtype_comobox.getSelectedItem().toString();
                String date = date_textfield.getText();
                String total_marks = total_marks_textbox.getText();
                String obtn_marks=table1.getValueAt(i,2).toString().replaceAll("\\s+","");
                System.out.println(xm);

                if(subject_combobox.getSelectedItem().toString().equals("ICT"))
                {
                    obtn_marks =Integer.toString(Integer.parseInt(obtn_marks)*2);
                }
                System.out.println(subject_combobox.getSelectedItem());


                System.out.println(table1.getValueAt(i,2));

                if(table1.getValueAt(i,0).toString().replaceAll("\\s+","")!="" &&
                        table1.getValueAt(i,1).toString().replaceAll("\\s+","")!="" &&
                    table1.getValueAt(i,2).toString().replaceAll("\\s+","")!="")
                {
                    String query = "INSERT INTO Marks (roll,exam_type,date,out_of,obtained_markd,cls,subject,for_year) VALUES(?,?,?,?,?,?,?,?);";
                if(table1.getValueAt(i,2).toString().startsWith("A") || table1.getValueAt(i,2).toString().startsWith("a"))
                {
                    table1.setValueAt(-1,i,2);
                }

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, table1.getValueAt(i,0).toString());
                preparedStatement.setString(2, xm);
                preparedStatement.setString(3,date);
                preparedStatement.setString(4,total_marks);
                preparedStatement.setString(5,obtn_marks);
                preparedStatement.setString(6,class_combobox.getSelectedItem().toString());
                preparedStatement.setString(7,subject_combobox.getSelectedItem().toString());
                preparedStatement.setString(8,for_year_combobox.getSelectedItem().toString());
                preparedStatement.execute();
                count++;
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
    }

    public Insert_marks()
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Registration Number","Name","Obtained Marks"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("Registration Number").setCellRenderer( centerRenderer );
        table1.getColumn("Name").setCellRenderer(centerRenderer);
        table1.getColumn("Obtained Marks").setCellRenderer(centerRenderer);


        String cls ="";
        class_combobox.removeAllItems();
        examtype_comobox.removeAllItems();

        examtype_comobox.setEditable(true);
        for_year_combobox.setEditable(true);
        class_combobox.setEditable(true);

        try {
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
        subject_combobox.setEditable(true);
        try
        {
            ResultSet resultSet=null;
            resultSet = Database_query.get_exam_type();
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
            ResultSet resultSet =null;
            resultSet = Database_query.get_class();
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

        try {
         cls=class_combobox.getSelectedItem().toString();
        }
        catch (Exception ex)
        {
            cls = "";
        }

         String year ="";
        try {
            year=for_year_combobox.getSelectedItem().toString();
        }
        catch (Exception ex)
        {
            year = "";
        }

        ResultSet resultSet = null;

        try
        {
            resultSet = Database_query.get_year();
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

        table1.getTableHeader().setReorderingAllowed(false);


        JFrame jFrame = new JFrame("Orbit (Input Marks)");
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setContentPane(jPanel1);

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

        class_combobox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {

                    String year ="";
                    String cls = "";
                    try {
                        year=for_year_combobox.getSelectedItem().toString();
                    }
                    catch (Exception ex)
                    {
                        year = "";
                    }

                    try {
                        cls=class_combobox.getSelectedItem().toString();
                    }
                    catch (Exception ex)
                    {
                        cls = "";
                    }
                    ResultSet resultSet1=null;

                    resultSet1 = Database_query.get_active_students(cls,year);
                    resultSet1.beforeFirst();

                    DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
                    String header[] = {"Registration Number","Name","Obtained Marks"};
                    defaultTableModel.setColumnIdentifiers(header);
                    table1.setModel(defaultTableModel);

                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    table1.getColumn("Registration Number").setCellRenderer( centerRenderer );
                    table1.getColumn("Name").setCellRenderer(centerRenderer);
                    table1.getColumn("Obtained Marks").setCellRenderer(centerRenderer);


                    while (resultSet1.next())
                    {
                        defaultTableModel.addRow(new String[] {resultSet1.getString(1),resultSet1.getString(2)});
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        for_year_combobox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {

                    String year ="";
                    String cls = "";
                    try {
                        year=for_year_combobox.getSelectedItem().toString();
                    }
                    catch (Exception ex)
                    {
                        year = "";
                    }

                    try {
                        cls=class_combobox.getSelectedItem().toString();
                    }
                    catch (Exception ex)
                    {
                        cls = "";
                    }
                    ResultSet resultSet1=null;

                    resultSet1 = Database_query.get_active_students(cls,year);
                    resultSet1.beforeFirst();

                    DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
                    String header[] = {"Registration Number","Name","Obtained Marks"};
                    defaultTableModel.setColumnIdentifiers(header);
                    table1.setModel(defaultTableModel);

                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    table1.getColumn("Registration Number").setCellRenderer( centerRenderer );
                    table1.getColumn("Name").setCellRenderer(centerRenderer);
                    table1.getColumn("Obtained Marks").setCellRenderer(centerRenderer);

                    while (resultSet1.next())
                    {
                        defaultTableModel.addRow(new String[] {resultSet1.getString(1),resultSet1.getString(2)});
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        SAVE.setFocusable(false);
        SAVE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (check()) {
                    JOptionPane.showMessageDialog(null, "Please, Fill Up Date Field Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Data_validation.check_date(date_textfield.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please, Fill Up Date Field Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    get_table_data();
                    String message = count + " Marks Entry added";
                    String title = "SUCCESSFUL";
                    JOptionPane.showMessageDialog(null, message,
                            title, JOptionPane.INFORMATION_MESSAGE);
                    jFrame.dispose();
                    Home home =new Home();
                }
            }
        });

    }
    boolean check()
    {
        if(date_textfield.getText().equals("")||total_marks_textbox.getText().equals("")||
                examtype_comobox.getSelectedItem().toString().equals("")||subject_combobox.getSelectedItem().toString().equals("")||
                class_combobox.getSelectedItem().toString().equals("")||for_year_combobox.getSelectedItem().toString().equals(""))
            return true;
        return false;
    }
}
