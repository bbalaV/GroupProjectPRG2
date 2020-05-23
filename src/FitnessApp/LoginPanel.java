package FitnessApp;

import javax.swing.*;
import java.awt.*;

// class for login panel, get's called first in main
class LoginPanel extends JPanel {
    JButton login, registration;
    JTextField loginUsername, loginPassword;
    JLabel lblKrz, lblPas;
    JPanel p1Login, p2Login;

    public LoginPanel() {
        p1Login = new JPanel(new GridLayout(0, 2));
        p2Login = new JPanel(new BorderLayout());

        // create a label to display text
        lblKrz = new JLabel("Benutzername");
        lblPas = new JLabel("Passwort");

        // create new buttons
        login = new JButton("submit");
        registration = new JButton("register");

        // create a object of JTextField with 16 columns
        loginUsername = new JTextField(16);
        loginPassword = new JTextField(16);

        p1Login.add(lblKrz);
        p1Login.add(loginUsername);
        p1Login.add(lblPas);
        p1Login.add(loginPassword);
        p2Login.add(p1Login, BorderLayout.NORTH);
        p2Login.add(login, BorderLayout.CENTER);
        p2Login.add(registration, BorderLayout.SOUTH);
        add(p2Login);

    }
}
