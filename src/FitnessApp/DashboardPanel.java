package FitnessApp;

import javax.swing.*;

//class for login panel, get's called first in main
class DashboardPanel extends JPanel {
    JLabel jl;

    public DashboardPanel() {
        jl = new JLabel("Herzlich Wilkommen in der Fitnessapp!");
        // Menüleiste an Frame hinzufügen
        add(jl);
    }

}
