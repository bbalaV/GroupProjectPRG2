package FitnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NutzerdatenPanel extends JPanel {
    JLabel lblname;
    JLabel lblvorname;
    JLabel lblgewicht;
    JLabel lblgroesse;
    JLabel lblpasswort;

    JTextField name;
    JTextField vorname;
    JTextField gewicht;
    JTextField groesse;
    JTextField passwort;

    JPanel p1, p2;

    JButton btnedit, btnsave, btncancel;

    public NutzerdatenPanel(Benutzer LoggedUser) {

        lblname = new JLabel("Name: ");
        lblvorname = new JLabel("Vorname: ");
        lblgewicht = new JLabel("Gewicht: ");
        lblgroesse = new JLabel("Grösse: ");
        lblpasswort = new JLabel("Passwort: ");

        name = new JTextField(16);
        vorname = new JTextField(16);
        gewicht = new JTextField(16);
        groesse = new JTextField(16);
        passwort = new JTextField(16);


        name.setText(LoggedUser.getName());
        vorname.setText(LoggedUser.getVorname());
        gewicht.setText(String.valueOf(LoggedUser.getGewicht()));
        groesse.setText(String.valueOf(LoggedUser.getGroesse()));
        passwort.setText(LoggedUser.getPasswort());

        setEditableFields(false);


        p1 = new JPanel(new GridLayout(0, 2));

        p1.add(lblname);
        p1.add(name);

        p1.add(lblvorname);
        p1.add(vorname);
        p1.add(lblgewicht);
        p1.add(gewicht);
        p1.add(lblgroesse);
        p1.add(groesse);
        p1.add(lblpasswort);
        p1.add(passwort);


        btnedit = new JButton("Edit");

        btnedit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setEditableFields(true);
                btnedit.setVisible(false);
                btnsave.setVisible(true);
                btncancel.setVisible(true);
            }
        });

        btnsave = new JButton("Save");
        btnsave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                LoggedUser.setName(name.getText());
                LoggedUser.setVorname(vorname.getText());
                LoggedUser.setGewicht(Double.parseDouble(gewicht.getText()));
                LoggedUser.setGroesse(Integer.parseInt(groesse.getText()));
                LoggedUser.setPasswort(passwort.getText());
                setEditableFields(false);
                btnsave.setVisible(false);
                btnedit.setVisible(true);
            }
        });
        btnsave.setVisible(false);

        btncancel = new JButton("Abbrechen");
        btncancel.setVisible(false);

        p2 = new JPanel(new BorderLayout());

        p2.add(p1, BorderLayout.NORTH);
        p2.add(btnedit, BorderLayout.SOUTH);
        p2.add(btnsave);
        //p2.add(btncancel);
        add(p2);


    }

    public void setEditableFields(Boolean val) {
        name.setEditable(val);
        vorname.setEditable(val);
        gewicht.setEditable(val);
        groesse.setEditable(val);
        passwort.setEditable(val);
    }
}
