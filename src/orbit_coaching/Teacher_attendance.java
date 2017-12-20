package orbit_coaching;

import org.omg.CORBA.DATA_CONVERSION;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import static java.sql.DriverManager.getConnection;
import static orbit_coaching.Database_query.conn;

public class Teacher_attendance {
    private JPanel jPanel;
    private JComboBox year_combobox;
    private JTable table1;
    private JComboBox month_combobox;
    private JComboBox day_combobox;
    private JComboBox shift_combobox;
    private JButton SAVEButton;


    public Teacher_attendance()
    {

        JFrame jFrame = new JFrame("Orbit Coaching");
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"ID","Name","Arrival Time"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);
        shift_combobox.setEditable(true);

        try{
            ResultSet resultSet = Database_query.get_year();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                year_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        for(int i = 1;i<=12;i++)
        {
            month_combobox.addItem(i);
        }

        for(int i = 1;i<=31;i++)
        {
            day_combobox.addItem(i);
        }
        year_combobox.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR));
        month_combobox.setSelectedItem(Calendar.getInstance().get(Calendar.MONTH)+1);
        day_combobox.setSelectedItem(Calendar.getInstance().get(Calendar.DATE));
        System.out.println(Calendar.getInstance().get(Calendar.YEAR)+" "+Calendar.getInstance().get(Calendar.MONTH)
               +" "+ Calendar.getInstance().get(Calendar.DATE));

        shift_combobox.addItem("Morning");
        shift_combobox.addItem("Day");

        year_combobox.setEditable(true);
        shift_combobox.setEditable(true);

        SAVEButton.setFocusable(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.getColumn("ID").setCellRenderer( centerRenderer );
        table1.getColumn("Name").setCellRenderer(centerRenderer);
        table1.getColumn("Arrival Time").setCellRenderer(centerRenderer);

        try
        {
            ResultSet resultSet= Database_query.get_teachear_details_for_attendace();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                defaultTableModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2)});
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }



        jFrame.setContentPane(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);
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
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }

    public void save()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/orbit_coaching_tabulation_system",
                    "root", "");
            Statement stmt = conn.createStatement();
            for(int i = 0;i<(int)table1.getRowCount();i++)
            {
              String date = day_combobox.getSelectedItem().toString()+"-"+month_combobox.getSelectedItem().toString()+"-"+
                      year_combobox.getSelectedItem().toString();
              if(table1.getValueAt(i,2).toString().replaceAll("\\s+","")!="" )
              {
                  String query = "INSERT INTO Teacher_attendance(teacher_id,attendance_date,shift_time) VAlUES(?,?,?);";
                  PreparedStatement preparedStatement = conn.prepareStatement(query);

                  preparedStatement.setString(1, table1.getValueAt(i,0).toString());
                  preparedStatement.setString(2, date);
                  preparedStatement.setString(3,table1.getValueAt(i,2)+" ("+
                          shift_combobox.getSelectedItem().toString()+")");
                  preparedStatement.execute();
              }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
