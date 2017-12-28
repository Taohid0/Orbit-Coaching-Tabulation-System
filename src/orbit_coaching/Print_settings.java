package orbit_coaching;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Print_settings {
    private JComboBox combobox;
    private JButton SAVEButton;
    private JPanel panel1;

    Print_settings()
    {
        JFrame jFrame = new JFrame("Orbit (Print Page Setup)");
        try
        {
            combobox.addItem("Plain White Page");
            combobox.addItem("Coaching Pad");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            ResultSet resultSet = Database_query.get_page_setup();
            resultSet.beforeFirst();
            if(resultSet.next())
            {
                combobox.setSelectedItem("Coaching Pad");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        Dimension dimension=  new Dimension(450,250);
        jFrame.setPreferredSize(dimension);
        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        SAVEButton.setFocusable(false);
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(combobox.getSelectedItem().toString().equals("Plain White Page"))
                {
                    Database_query.delete_page_data();
                }
                else
                {
                    Database_query.insert_page_data();
                }
                jFrame.dispose();
            }
        });
    }

}
