package orbit_coaching;

import com.sun.deploy.util.ArrayUtil;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.DataBuffer;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Add_student_payment {
    private JPanel panel1;
    private JComboBox year_combobox;
    private JComboBox class_combobox;
    private JComboBox roll_combobox;
    private JComboBox month_combobox;
    private JTextField amount_textbox;
    private JTable table1;
    private JTextField name_textbox;
    private JList skip_month_list;
    private JButton SAVEButton;
    private JComboBox purpose_combobox;
    private JTextField date_textbox;
    String month_array[] = {"January","February","March","April","May","June","July","August","September",
            "October","November","December"};
    Map<String,Integer> check_month_map = new HashMap<String, Integer>();
    String month=null;
    boolean test_multiple_month()
    {
        month= month_combobox.getSelectedItem().toString();
        int [] selected_indices = skip_month_list.getSelectedIndices();
        ArrayList<String> arrayList = new ArrayList();
        ArrayList<String>previous_month = new ArrayList<>();
        for(int i = 0;i<(int)selected_indices.length;i++) {
            if (skip_month_list.getModel().getElementAt(selected_indices[i]).toString().equals("NONE")) {
                    continue;
            }
            else {
                arrayList.add(skip_month_list.getModel().getElementAt(selected_indices[i]).toString());
            }
        }
            for(int i = 0;i<(int)arrayList.size();i++)
            {
                if(arrayList.get(i).equals(month))
                    return true;
            }
            try
            {
                String r = roll_combobox.getSelectedItem().toString();
                String y = year_combobox.getSelectedItem().toString();
                ResultSet resultSet = Database_query.get_paid_month(r,y);
                resultSet.beforeFirst();

                while (resultSet.next())
                {
                    previous_month.add(resultSet.getString(1));
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            for(int i = 0;i<(int)previous_month.size();i++)
            {
                if(month.equals(previous_month.get(i)))
                    return true;
            }
            for(int i = 0;i<(int)previous_month.size();i++)
            {
                for(int j = 0;j<(int)arrayList.size();j++)
                {
                    if(arrayList.get(j).equals(previous_month.get(i)))
                        return true;
                }
            }
            return false;
    }
    public void insert_to_database()
    {

        String year,cls,roll,month,amount,purpose,date;
        int skipped=0;
        try
        {
            try
            {
                amount = amount_textbox.getText();
            }
            catch (Exception ex)
            {
                amount="";
                skipped=1;
            }

            year = year_combobox.getSelectedItem().toString();
            cls = class_combobox.getSelectedItem().toString();
            roll= roll_combobox.getSelectedItem().toString();
            month = month_combobox.getSelectedItem().toString();
            purpose = purpose_combobox.getSelectedItem().toString();
            date = date_textbox.getText();

            int [] selected_indices = skip_month_list.getSelectedIndices();
            boolean flag = true;
            if(test_multiple_month())
            {
                flag = false;
                String title_bar = "ERROR";
                String error_message = "You have multiple entries for a single month for a year";
                JOptionPane.showMessageDialog(null, error_message,
                        title_bar, JOptionPane.ERROR_MESSAGE);

            }


            for(int i = 0;i<(int)selected_indices.length;i++)
            {
                if(skip_month_list.getModel().getElementAt(selected_indices[i]).toString().equals("NONE"))
                    continue;
                System.out.println(skip_month_list.getModel().getElementAt(selected_indices[i]));
                if (flag)
                Database_query.input_student_payment(roll,cls,skip_month_list.getModel().getElementAt(selected_indices[i]).toString(),
                        year,date,1,"0",purpose);
            }
            if(flag)
            Database_query.input_student_payment(roll,cls,month,year,date,0,amount,purpose);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void fill_data()
    {
        String year = year_combobox.getSelectedItem().toString();
        String cls= class_combobox.getSelectedItem().toString();
        roll_combobox.removeAllItems();

        try
        {
            ResultSet resultSet = Database_query.get_roll_for_payment(cls,year);
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                roll_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void fill_table() {
        DefaultTableModel defaultTableModel = null;

        defaultTableModel = new DefaultTableModel(0, 0);
        String header[] = {"Month", "Status","Amount"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        try
        {
            ResultSet resultSet = Database_query.get_paid_month(roll_combobox.getSelectedItem().toString(),
                    year_combobox.getSelectedItem().toString());

            resultSet.beforeFirst();
            String paid_month[] = new String[200];
            int count = 0;
            Map<String,String> amount = new HashMap<String,String>();
            Map<String,String> skipped_month = new HashMap<String, String>();
            for(int i = 0;i<(int)month_array.length;i++)
            {

                amount.put(month_array[i],"0");
                //check_month_map.put(month_array[i],1);
            }
            check_month_map.clear();
            while (resultSet.next())
            {
                skipped_month.put(resultSet.getString(1),resultSet.getString(3));
                paid_month[count++] = resultSet.getString(1);
                amount.put(resultSet.getString(1),resultSet.getString(2));
                check_month_map.put(resultSet.getString(1),1);
            }
            resultSet.beforeFirst();
            for(int i=0;i<(int)month_array.length;i++)
            {
                String status = "NOT PAID";

                try {
                    if (is_month_found(paid_month, month_array[i])) {
                        status = "PAID";
                    }
                    if(skipped_month.get(month_array[i]).equals("1"))
                    {
                        status = "SKIPPED";
                    }
                }
                catch (Exception ex)
                {
                    status = "NOT PAID";
                }

                defaultTableModel.addRow(new String[] {month_array[i],status,amount.get(month_array[i])});
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }




    }

    boolean is_month_found(String month_list[],String month)
    {

        for(int i = 0;i<(int)month_list.length;i++)
        {
            if(month_list[i].equals(month))
                return true;
        }
        return false;
    }


    public  Add_student_payment()
    {
        try
        {
            ResultSet resultSet = Database_query.get_year();
            resultSet.beforeFirst();

            while(resultSet.next())
            {
                year_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

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

        try
        {
            for(int i = 0;i<(int)month_array.length;i++)
            {
                month_combobox.addItem(month_array[i]);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try
        {
            ResultSet resultSet = Database_query.get_purpose();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                purpose_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //table1.setEnabled(false);
        table1.setDefaultEditor(Object.class, null);
        String [] select_month = new String[15];
        for(int i = 0;i<(int)month_array.length;i++)
        {
            select_month[i] = month_array[i];
        }
        select_month[12]="NONE";

        SAVEButton.setFocusable(false);

        skip_month_list.setListData(select_month);
        purpose_combobox.setEditable(true);
        year_combobox.setEditable(true);
        JFrame jFrame = new JFrame("Orbit (Add Student's Payment)");
        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);

        class_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_data();


            }
        });
        year_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill_data();
            }
        });
        roll_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fill_table();

                try{
                    ResultSet resultSet = Database_query.get_name(roll_combobox.getSelectedItem().toString());
                    resultSet.beforeFirst();

                    if(resultSet.next())
                    {
                        name_textbox.setEditable(false);
                        name_textbox.setText(resultSet.getString(1));
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All The Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Data_validation.check_date(date_textbox.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up Date Field Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {

                    insert_to_database();
                }

            }
        });

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
    boolean check()
    {
        if(name_textbox.getText().equals("") || amount_textbox.getText().equals("")|| date_textbox.getText().equals("") ||
                year_combobox.getSelectedItem().toString().equals("")|| purpose_combobox.getSelectedItem().toString().equals("")
                )
        {
            return true;
        }
        return false;
    }
}
