package FitnessApp;

import java.util.ArrayList;
import java.util.List;
// Benutzerobjekt
public class Benutzer implements Comparable<Benutzer> {
    private final ArrayList<Integer> trainingsList;
    int benutzerNr;
    String name;
    private String vorname;
    private String benutzername;
    private String passwort;
    private double gewicht;
    private int groesse;

    public Benutzer(int benutzerNr, String name, String vorname, String benutzername, String passwort, double gewicht, int groesse, ArrayList<Integer> trainingsList) {
        // Check damit kein Nutzer mit ID unter 1000 oder ueber 9999 entsteht
        if (benutzerNr < 1000 || benutzerNr > 9999)
            throw new IllegalArgumentException("Benutzernummer ist nicht erlaubt: " + benutzerNr);
        this.benutzerNr = benutzerNr;
        this.name = name;
        this.vorname = vorname;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.gewicht = gewicht;
        this.groesse = groesse;
        this.trainingsList = trainingsList;


    }

    public Benutzer(String name, String vorname, String passwort, double gewicht, int groesse, ArrayList<Integer> trainingsList) {

        int benutzerNrloc = BenutzerDAO.all.size() + 1001; //Insgesamte Benutzeranzahl + 1001
        //Nehme die ersten zwei Buchstaben vom Nachnamen und vom Vornamen und setze es als Benutzername
        String benutzernameloc = (name.substring(0, 2).concat(vorname.substring(0, 2)));
        benutzernameloc = benutzernameloc.toUpperCase();
        this.benutzerNr = benutzerNrloc;
        this.name = name;
        this.vorname = vorname;
        this.benutzername = benutzernameloc;
        this.passwort = passwort;
        this.gewicht = gewicht;
        this.groesse = groesse;
        this.trainingsList = trainingsList;
        BenutzerDAO.all.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public int getGroesse() {
        return groesse;
    }

    public void setGroesse(int groesse) {
        this.groesse = groesse;
    }

    public int getBenutzerNr() {
        return benutzerNr;
    }

    public void setBenutzerNr(int benutzerNr) {
        this.benutzerNr = benutzerNr;
    }

    public boolean existsUserTrainingsList(int tid) {
        return trainingsList.contains(tid);
    }

    public String toString() {
        return name + ", " + vorname + " (" + benutzerNr + ")";
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;
        Benutzer that = (Benutzer) o;
        return benutzerNr == that.benutzerNr;
    }

    public int hashCode() {
        return benutzerNr;
    }

    public int compareTo(Benutzer o) {
        return benutzerNr - o.benutzerNr;
    }

    public List<Integer> getTrainingsList() {
        return trainingsList;
    }

    public void setTrainingsList(int Training) {
        this.trainingsList.add(Training);
    }

    public void deleteTrainingsList(int Training) {
        this.trainingsList.remove((Integer) Training);
    }
}
