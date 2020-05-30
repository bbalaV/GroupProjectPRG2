package FitnessApp;

import javax.swing.*;
import java.util.List;
// Ansicht für Auswertung
class AuswertungPanel extends JPanel {
    JLabel durchHerz;
    JLabel gesZeit;

    public AuswertungPanel(Benutzer LoggedUser, TrainingseinheitenDAO TEA) {
        int durchHerzCounter = 0;
        int durchHerzSum = 0;
        int traininghours = 0;
        int trainingminutes = 0;
        String trainingszeit;
        List<Integer> trainingList = LoggedUser.getTrainingsList();
        // Durch alle Trainingseinheiten vom User loopen und Werte fuer Bewertung berechnen
        for (Trainingseinheit training : TEA.getAll()) {
            if (trainingList.contains(training.getTid())) {
                durchHerzCounter++;
                durchHerzSum += training.getHerzfrequenz();
                traininghours += training.getTrainingsdauer().getHour();
                trainingminutes += training.getTrainingsdauer().getMinute();

            }

        }
        // Einzeigen der Auswertung
        if (durchHerzCounter == 0) {
            add(new JLabel("Kein Training absolviert"));
        } else {
            durchHerz = new JLabel("Durchschnittliche Herzrate: " + durchHerzSum / durchHerzCounter);
            JSeparator seperator = new JSeparator(SwingConstants.HORIZONTAL);
            add(durchHerz);
            add(seperator);
            traininghours += trainingminutes / 60;
            trainingminutes = trainingminutes % 60;
            add(new JLabel("Gesamte Trainingszeit: " + traininghours + " Stunden" + " und " + trainingminutes + " Minuten"));
        }

    }

}
