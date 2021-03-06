package FitnessApp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class TrainingseinheitenDAO {
    public List<Trainingseinheit> all = new LinkedList<Trainingseinheit>();

    public TrainingseinheitenDAO() {// diese Trainingseinheiten sollen existieren
        //Diese Daten existieren weiter
        all.add(new Trainingseinheit(1001, LocalDate.parse("2019-02-15"), LocalTime.parse("12:12:55"), 125));
        all.add(new Trainingseinheit(1002, LocalDate.parse("2019-03-16"), LocalTime.parse("01:12:55"), 180));
        all.add(new Trainingseinheit(1003, LocalDate.parse("2019-04-17"), LocalTime.parse("00:12:55"), 175));

    }

    // Schnelltest
    public static void main(String[] args) {
        TrainingseinheitenDAO data = new TrainingseinheitenDAO();
        System.out.println(data.getAll());
        System.out.println(data.getTid(1002));
    }

    // alle auf einmal
    public List<Trainingseinheit> getAll() {
        LinkedList<Trainingseinheit> rc = new LinkedList<Trainingseinheit>(all);
        Collections.sort(rc);
        return rc;
    }

    // eine ganz bestimmte  Trainingseinheit, falls diese existert (null, falls nicht)
    public Trainingseinheit getTid(int tid) {
        Trainingseinheit rb = null;
        for (Trainingseinheit b : all)
            if (b.getTid() == tid)
                return b;
        return rb;
    }


    public void setTrainingseinheit(Trainingseinheit t) {
        all.add(t);
    }

    public void deleteTrainingseinheit(Trainingseinheit t) {
        all.remove(t);
    }
}