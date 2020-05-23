package FitnessApp;

import javax.swing.table.AbstractTableModel;

class AdminUserTableModel extends AbstractTableModel {

    String[] AdminColumn = {"Name", "Vorname", "Gewicht", "Grösse"};

    public String getColumnName(int col) {
        return AdminColumn[col];
    }

    public int getRowCount() {
        return BenutzerDAO.all.size();
    }

    public int getColumnCount() {
        return AdminColumn.length;
    }

    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return BenutzerDAO.all.get(row).getName();
            case 1:
                return BenutzerDAO.all.get(row).getVorname();
            case 2:
                return BenutzerDAO.all.get(row).getGewicht();
            case 3:
                return BenutzerDAO.all.get(row).getGroesse();
            default:
                throw new RuntimeException("Fehler im Laden der Tabelle.");
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
