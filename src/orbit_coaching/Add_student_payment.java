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
import java.sql.ResultSet;
import java.util.ArrayList;

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
    String month[] = {"January","February","March","April","May","June","July","August","September",
            "October","November","December"};

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

            Database_query.input_student_payment(roll,cls,month,year,date,skipped,amount,purpose);

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
        String header[] = {"Month", "Status"};
        defaultTableModel.setColumnIdentifiers(header);
        table1.setModel(defaultTableModel);

        try
        {
            ResultSet resultSet = Database_query.get_paid_month(roll_combobox.getSelectedItem().toString(),
                    year_combobox.getSelectedItem().toString());

            resultSet.beforeFirst();
            String paid_month[] = new String[15];
            int count = 0;
            while (resultSet.next())
            {
                paid_month[count++] = resultSet.getString(1);
            }
            resultSet.beforeFirst();
            for(int i=0;i<(int)month.length;i++)
            {
                String status = "NOT PAID";

                try {
                    if (is_month_found(paid_month, month[i])) {
                        status = "PAID";
                    }
                }
                catch (Exception ex)
                {
                    status = "NOT PAID";
                }

                defaultTableModel.addRow(new String[] {month[i],status});
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
            for(int i = 0;i<(int)month.length;i++)
            {
                month_combobox.addItem(month[i]);
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
        for(int i = 0;i<(int)month.length;i++)
        {
            select_month[i] = month[i];
        }
        select_month[12]="NONE";

        SAVEButton.setFocusable(false);

        skip_month_list.setListData(select_month);
        JFrame jFrame = new JFrame("Orbit Coaching");
        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                insert_to_database();
            }
        });

    }
}
