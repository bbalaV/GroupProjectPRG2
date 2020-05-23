package FitnessApp;

import javax.swing.*;
import java.awt.*;

//class for login panel, get's called second in main
class RegisterPanel extends JPanel {
    JLabel lblVor, lblNach, lblGr, lblGe, lblPw;
    JButton submit;
    JTextField registerVorname, registerName, registerPassword, registerGewicht, registerGroesse;
    JPanel p1, p2;
    Benutzer b;

    public RegisterPanel() {

        // create a label to display text
        lblVor = new JLabel("Vorname");
        lblNach = new JLabel("Nachname", JLabel.LEFT);
        lblGr = new JLabel("Groesse", JLabel.LEFT);
        lblGe = new JLabel("Gewicht", JLabel.LEFT);
        lblPw = new JLabel("Passwort", JLabel.LEFT);

        // create new button
        submit = new JButton("submit");

        // create a object of JTextField with 16 columns
        registerVorname = new JTextField(16);
        registerName = new JTextField(16);
        registerPassword = new JTextField(16);
        registerGewicht = new JTextField(16);
        registerGroesse = new JTextField(16);

        p1 = new JPanel(new GridLayout(0, 2));

        p1.add(lblNach);
        p1.add(registerName);
        p1.add(lblVor);
        p1.add(registerVorname);
        p1.add(lblPw);
        p1.add(registerPassword);
        p1.add(lblGe);
        p1.add(registerGewicht);
        p1.add(lblGr);
        p1.add(registerGroesse);

        p2 = new JPanel(new BorderLayout());

        p2.add(p1, BorderLayout.NORTH);
        p2.add(submit, BorderLayout.SOUTH);
        add(p2);

    }
}
