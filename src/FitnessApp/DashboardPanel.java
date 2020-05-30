package FitnessApp;

import javax.swing.*;

//Ansicht nachdem Einloggen
class DashboardPanel extends JPanel {
    JLabel jl;

    public DashboardPanel() {
        jl = new JLabel("Herzlich Wilkommen in der Fitnessapp!");
        // Menüleiste an Frame hinzufügen
        add(jl);
    }

}
