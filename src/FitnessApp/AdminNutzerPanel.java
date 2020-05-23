package FitnessApp;

import javax.swing.*;
import java.awt.*;

class AdminNutzerPanel extends JPanel {
    AdminUserTableModel atm = new AdminUserTableModel();

    public AdminNutzerPanel() {
        JTable adminTable = new JTable(atm);

        add(new JLabel("Alle Nutzer", JLabel.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(adminTable), BorderLayout.CENTER);

    }

}
