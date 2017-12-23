package orbit_coaching;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class About_developer {

    private JTextArea textArea1;
    private JPanel panel1;

    About_developer()
    {
        JFrame jFrame = new JFrame("Orbit (About Developer)");
        jFrame.add(panel1);
        String data = "\n\n\t\tTaohidul Islam\t\t\n\t\tComputer Science And Engineering Discipline\t\t\n" +
                "\t\tKhulna University\n\t\tEmail : taohidulii@gmail.com\n" +
                "\t\tContact Number : 01710239959\n\n";
        textArea1.setText(data);
        textArea1.setEditable(false);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.pack();
        jFrame.setVisible(true);

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
}
