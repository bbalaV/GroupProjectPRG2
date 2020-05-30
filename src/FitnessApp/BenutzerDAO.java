package FitnessApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BenutzerDAO {
   static List<Benutzer> all = new LinkedList<Benutzer>();

    public BenutzerDAO() {// diese Benutzer sollen existieren
        all.add(new Benutzer(1001, "Gerber", "Jolanda", "GEJO", "1234", 97, 177, new ArrayList<Integer>() {
            {
                add(1001);
            }
        }));
        all.add(new Benutzer(1002, "Gerber", "Michael", "GEMI", "abcd", 45, 125, new ArrayList<Integer>() {
            {
                add(1002);
            }
        }));
        all.add(new Benutzer(1003, "Winkler", "Kilian", "WIKI", "wikipedia", 120, 45, new ArrayList<Integer>() {
            {
                add(1003);
            }
        }));
    }

    // alle auf einmal
    public List<Benutzer> getAll() {
        LinkedList<Benutzer> rc = new LinkedList<Benutzer>(all);
        Collections.sort(rc);
        return rc;
    }

    // ein ganz bestimmer Benutzer, falls dieser existert (null, falls nicht)
    public Benutzer getBenutzer(int benutzerNr) {
        Benutzer rb = null;
        for (Benutzer b : all)
            if (b.getBenutzerNr() == benutzerNr)
                return b;
        return rb;
    }

    public void setBenutzer(Benutzer b) {
        all.add(b);
    }

}
