package orbit_coaching;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        Dimension dimension=  new Dimension(450,250);
        jFrame.setPreferredSize(dimension);
        jFrame.setContentPane(panel1);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(combobox.getSelectedItem().toString().equals("Plain White Page"))
                {
                    Database_query.insert_page_data();
                }
            }
        });
    }

}
