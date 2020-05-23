package FitnessApp;

import javax.swing.table.AbstractTableModel;
import java.util.Iterator;
import java.util.List;

class UserTrainingsplanModel extends AbstractTableModel {

    String[] PlanColumn = {"TID", "Datum", "Zeit", "Herzfrequenz"};
    Benutzer LoggedBenutzer;
    List<Trainingseinheit> referenceList = TrainingseinheitenDAO.all;


    public String getColumnName(int col) {
        return PlanColumn[col];
    }

    public void setLoggedBenutzer(Benutzer b) {
        LoggedBenutzer = b;
        for (Iterator<Trainingseinheit> iterator = referenceList.iterator(); iterator.hasNext(); ) {

            Trainingseinheit training = iterator.next();
            if (!LoggedBenutzer.existsUserTrainingsList(training.getTid())) {
                iterator.remove();
            }
        }

        fireTableDataChanged();
    }

    public int getRowCount() {
        return TrainingseinheitenDAO.all.size();
    }

    public int getColumnCount() {
        return PlanColumn.length;
    }

    public Object getValueAt(int row, int col) {

        switch (col) {
            case 0:
                return referenceList.get(row).getTid();
            case 1:
                return referenceList.get(row).getDatum();
            case 2:
                return referenceList.get(row).getTrainingsdauer();
            case 3:
                return referenceList.get(row).getHerzfrequenz();
            default:
                throw new RuntimeException("Fehler im Laden der Tabelle.");
        }


    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
