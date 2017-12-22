package orbit_coaching;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class Add_teacher {
    private JPanel jPanel;
    private JTextField name_textfield;
    private JTextField joining_date_textfield;
    private JButton save_button;
    private JComboBox institution_combobox;
    private JComboBox year_combobox;
    private JTextField id_textfield;


    public Add_teacher()
    {
        JFrame jFrame = new JFrame("Orbit (Add Teacher)");
        Dimension dimension=  new Dimension(450,350);
        jFrame.setPreferredSize(dimension);
        jFrame.setContentPane(jPanel);
        save_button.setFocusable(false);

        institution_combobox.setEditable(true);
        jFrame.setResizable(false);
        id_textfield.setEditable(false);

        try
        {
            ResultSet resultSet = Database_query.get_max_teacher_id();
            resultSet.beforeFirst();

            if(resultSet.next())
            {
                String r = Integer.toString(Integer.parseInt(resultSet.getString(1))+1);
                id_textfield.setText(r);
            }
        }
        catch (NumberFormatException e)
        {
            id_textfield.setText("1");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


        try
        {
            ResultSet resultSet = Database_query.get_teachear_institution();
            resultSet.beforeFirst();

            while (resultSet.next())
            {
                institution_combobox.addItem(resultSet.getString(1));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

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
        year_combobox.setEditable(true);

        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = name_textfield.getText();
                String joininig_date = joining_date_textfield.getText();
                String institution = institution_combobox.getSelectedItem().toString();
                String yr = year_combobox.getSelectedItem().toString();
                if(check())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up All The Fields Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Data_validation.check_date(joining_date_textfield.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Fill Up Date Field Correctly",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    try {
                        Database_query.insert_teacher(name, joininig_date, institution, yr, id_textfield.getText());
                        jFrame.dispose();
                        Home home = new Home();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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
        if(year_combobox.getSelectedItem().toString().replaceAll("\\s+","")=="" ||
                institution_combobox.getSelectedItem().toString().replaceAll("\\s+","")==""||
                name_textfield.getText().equals("") || joining_date_textfield.getText().equals(""))
        {
            return true;
        }
        return false;
    }


}
