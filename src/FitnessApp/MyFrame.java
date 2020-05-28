package FitnessApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements ActionListener {

    static MyFrame frame;
    // Create global panel variables
    LoginPanel lp;
    RegisterPanel rp;
    DashboardPanel dbp;
    JMenuBar mb;
    AdminNutzerPanel admp;
    UserTrainingsplanPanel utp;
    AuswertungPanel aup;
    NutzerdatenPanel ndp;
    Benutzer LoggedUser;
    //Create DAO instance
    BenutzerDAO BenutzerDAOInstance = new BenutzerDAO();
    TrainingseinheitenDAO TrainingseinheitenDAOInstance = new TrainingseinheitenDAO();

    // constructor for gui
    public MyFrame() {

        dbp = new DashboardPanel();

        lp = new LoginPanel();
        lp.login.addActionListener(this);
        lp.registration.addActionListener(this);
        add(lp);

        rp = new RegisterPanel();
        rp.submit.addActionListener(this);

        admp = new AdminNutzerPanel();


        mb = new JMenuBar();

        JMenu db = new JMenu("Dashboard");
        db.setMnemonic('D');
        JMenuItem auswertung = new JMenuItem("Auswertung");
        JMenuItem nutzerdaten = new JMenuItem("Nutzerdaten");
        JMenuItem deleteUser = new JMenuItem("Konto löschen");
        JMenuItem loggout = new JMenuItem("Ausloggen");
        db.add(auswertung);
        db.add(nutzerdaten);
        db.add(deleteUser);
        db.add(loggout);
        auswertung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                aup = new AuswertungPanel(LoggedUser, TrainingseinheitenDAOInstance);
                changePanel(aup);
            }
        });
        nutzerdaten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                ndp = new NutzerdatenPanel(LoggedUser);
                changePanel(ndp);
            }
        });
        deleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                BenutzerDAO.all.remove(LoggedUser);
                LoggedUser = null;
                changePanel(lp);
                System.out.println(BenutzerDAO.all.get(0).getVorname());
                mb.setVisible(false);
            }
        });

        loggout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                LoggedUser = null;
                changePanel(lp);
                mb.setVisible(false);
            }
        });

        mb.add(db);

        JMenu de = new JMenu("Datenerfassung");
        de.setMnemonic('D');
        JMenuItem trainingsplan = new JMenuItem("Trainingspläne anzeigen");
        de.add(trainingsplan);

        trainingsplan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                utp = new UserTrainingsplanPanel(LoggedUser, TrainingseinheitenDAOInstance);
                changePanel(utp);

            }
        });

        mb.add(de);

        JMenu adm = new JMenu("Administration");
        adm.setMnemonic('A');
        JMenuItem userAnzeigen = new JMenuItem("User anzeigen");


        adm.add(userAnzeigen);

        mb.add(adm);

        userAnzeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                changePanel(admp);
            }
        });



        setJMenuBar(mb);

    }

    public static void main(String[] args) {
        frame = new MyFrame();

        frame.mb.setVisible(false);

        frame.setTitle("Fitnessapp");

        // set the size of the frame
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocation(200, 200);
        frame.setVisible(true);
        // default exit action
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    // listening to button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.lp.login) {
            if (checkLogin()) {
                this.mb.setVisible(true);
                changePanel(dbp);
            } else {

            }
        }
        if (e.getSource() == this.lp.registration) {

            changePanel(rp);
        }

        if (e.getSource() == this.rp.submit) {
            Boolean formatCorrect = true;
            String nachname       = this.rp.registerName.getText().trim();
            String vorname        = this.rp.registerVorname.getText().trim();
            String password       = this.rp.registerPassword.getText().trim();
            String gewichtString  = this.rp.registerGewicht.getText().trim();
            String groesseString  = this.rp.registerGroesse.getText().trim();


          if(!isDoubleValid(gewichtString) || !isIntValid(groesseString)){
                formatCorrect = false;
            }



if(formatCorrect){
    Double    gewicht = Double.parseDouble(gewichtString);
    int       groesse = Integer.parseInt(groesseString);
    Benutzer b = new Benutzer(nachname, vorname, password, gewicht, groesse, new ArrayList<Integer>() {
                {
                    add(0000);
                }
            });

            this.LoggedUser = b;
            this.mb.setVisible(true);
            changePanel(dbp);}
else{
    String gewichtFormatwrong  = !isDoubleValid(gewichtString) ? "<p>Gewicht muss eine Zahl mit oder ohne Dezimalstellen sein<br> " : "";
    String groesseFormatwrong  = !isIntValid(groesseString) ? "Groesse muss in cm eine ganze Zahl sein": "";

    String html = "<html><body width='%1s'><h1>Inkorrekte Eingabe</h1>"
            + gewichtFormatwrong
            + groesseFormatwrong;


    JOptionPane.showMessageDialog(MyFrame.frame, String.format(html, 350, 400));
}
        }

    }

    // check if login is successful and return bool value
    boolean checkLogin() {
        // BenutzerDAO test = new BenutzerDAO();
        System.out.println(BenutzerDAO.all);
        List<Benutzer> allUsers = BenutzerDAO.all;
        for (Benutzer b : allUsers) {
            System.out.println("Passwort: " + b.getPasswort());
            System.out.println("Name: " + b.getBenutzername());
            if (b.getBenutzername().equals(this.lp.loginUsername.getText().trim())
                    && b.getPasswort().equals(this.lp.loginPassword.getText().trim())) {
                this.LoggedUser = b;
                return true;
            }
        }
        return false;
    }

    // rerender frame with specific panel
    void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().validate();
        getContentPane().repaint();
    }

    public static boolean isDoubleValid(String doubleValid) {
        try {
            Double.parseDouble(doubleValid);
        } catch (NumberFormatException  e) {
            return false;
        }
        return true;
    }

    public static boolean isIntValid(String intStr) {
        try {
            Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


}


