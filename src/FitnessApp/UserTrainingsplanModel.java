package FitnessApp;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

class UserTrainingsplanModel extends AbstractTableModel {

    String[] PlanColumn = {"TID", "Datum", "Zeit", "Herzfrequenz"};
    Benutzer LoggedBenutzer;
    List<Trainingseinheit> referenceList;

    public UserTrainingsplanModel(TrainingseinheitenDAO TEA, Benutzer LoggedUser){
        referenceList = TEA.getAll();
        this.LoggedBenutzer = LoggedUser;
        setLoggedBenutzer(LoggedBenutzer);

    }


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
        return referenceList.size();
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
    @Override
    public void setValueAt(Object aValue, int row, int col)
    {
        String dateformatwrong  = "<p>Datum Format muss so sein: jjjj-mm-tt <br> ";
        String timeformatwrong  ="Zeit Format muss so sein: hh:mm:ss; <br> ";
        String heartformatwrong = "Herzfrequenz muss eine ganze Zahl sein";

        String html = "<html><body width='%1s'><h1>Inkorrekte Eingabe</h1>";




        if(1 == col) {
            if(MyFrame.isDateValid((String) aValue)){
            referenceList.get(row).setDatum(LocalDate.parse( (String) aValue)); }
            else{
                JOptionPane.showMessageDialog(MyFrame.frame, String.format(html + dateformatwrong, 350, 400));
            }
        }
        else if(2 == col) {
            if(MyFrame.isTimeValid((String) aValue)){
            referenceList.get(row).setTrainingsdauer( LocalTime.parse( (String) aValue) );
        }
            else{
                JOptionPane.showMessageDialog(MyFrame.frame, String.format(html + timeformatwrong, 350, 400));
            }
        }
        else if(3 == col) {
            if(MyFrame.isIntValid((String) aValue)){
                referenceList.get(row).setHerzfrequenz(Integer.parseInt( (String) aValue));
            }
            else{
                JOptionPane.showMessageDialog(MyFrame.frame, String.format(html + heartformatwrong, 350, 400));
            }

        }
    }



    public void refreshTable(){
        fireTableDataChanged();
    }

    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            default:
                return true;
        }
    }
}
