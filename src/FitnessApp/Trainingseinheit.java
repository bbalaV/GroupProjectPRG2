package FitnessApp;

import java.time.LocalDate;
import java.time.LocalTime;
// Trainingseinheitsobjekt
public class Trainingseinheit implements Comparable<Trainingseinheit> {

    private int tid;
    private LocalDate datum;
    private LocalTime trainingsdauer;
    private int herzfrequenz;

    public Trainingseinheit(int tid, LocalDate datum, LocalTime trainingsdauer, int herzfrequenz) {

        this.tid = tid;
        this.datum = datum;
        this.trainingsdauer = trainingsdauer;
        this.herzfrequenz = herzfrequenz;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tid;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trainingseinheit other = (Trainingseinheit) obj;
        return tid == other.tid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getTrainingsdauer() {
        return trainingsdauer;
    }

    public void setTrainingsdauer(LocalTime trainingsdauer) {
        this.trainingsdauer = trainingsdauer;
    }

    public int getHerzfrequenz() {
        return herzfrequenz;
    }

    public void setHerzfrequenz(int herzfrequenz) {
        this.herzfrequenz = herzfrequenz;
    }

    public int compareTo(Trainingseinheit o) {
        return tid - o.tid;
    }

}
