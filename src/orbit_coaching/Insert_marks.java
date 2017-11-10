package orbit_coaching;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.sql.ResultSet;

public class Insert_marks {
    private JComboBox examtype_comobox;
    private JComboBox class_combobox;
    private JTextField date_textfield;
    private JTable table1;
    private JPanel jPanel1;
    private JTextField textField1;

    public Insert_marks(String cls,String year,String exam_type,String date)
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);
        String header[] = {"Roll Number","Name","Obtained Marks"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);
        //table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ResultSet resultSet = null;
        try
        {
            resultSet = Database_query.get_active_students(cls,year);
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                defaultTableModel.addRow(new String[] {resultSet.getString(1),resultSet.getString(2)});
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        table1.getTableHeader().setReorderingAllowed(false);


        JFrame jFrame = new JFrame("Input Marks");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(jPanel1);
        jFrame.pack();
        jFrame.setVisible(true);


    }
}
