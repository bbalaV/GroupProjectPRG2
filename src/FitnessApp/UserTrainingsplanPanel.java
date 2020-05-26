package FitnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

class UserTrainingsplanPanel extends JPanel {
    UserTrainingsplanModel utm;
    Benutzer LoggedUser;
    JTable adminTable;
    public UserTrainingsplanPanel(Benutzer LoggedUser, TrainingseinheitenDAO TEA) {
        utm = new UserTrainingsplanModel(TEA, LoggedUser);
        adminTable = new JTable(utm);
        adminTable.setColumnSelectionAllowed(false);
        JButton jb = new JButton("Neu");
        this.LoggedUser = LoggedUser;
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JTextField datum = new JTextField();
                JTextField trainingsdauer = new JTextField();
                JTextField herzfrequenz = new JTextField();

                JLabel lbldatum = new JLabel("Datum");
                JLabel lbltrainingd = new JLabel("Dauer");
                JLabel lblherzf = new JLabel("Herzfrequenz");

                JPanel p1 = new JPanel(new GridLayout(0, 2));

                JDialog TrainingsplanDialog = new JDialog(MyFrame.frame, "Erfassen");
                TrainingsplanDialog.setTitle("Neuen Trainingsplan erfassen");


                p1.add(lbldatum);
                p1.add(datum);

                p1.add(lbltrainingd);
                p1.add(trainingsdauer);

                p1.add(lblherzf);
                p1.add(herzfrequenz);


                JButton dialogErf = new JButton("Erfassen");
                dialogErf.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        String datumTr = datum.getText();
                        String trainingsdauerTr = trainingsdauer.getText();
                        String herzfrequenzTr = herzfrequenz.getText();


                        String dateformatwrong  = "<p>Datum Format muss so sein: jjjj-mm-tt <br> ";
                        String timeformatwrong  = "Zeit Format muss so sein: hh:mm:ss; <br> ";
                        String heartformatwrong = "Herzfrequenz muss eine ganze Zahl sein";
                        String html = "<html><body width='%1s'><h1>Inkorrekte Eingabe</h1>"
                                + dateformatwrong
                                + timeformatwrong
                                + heartformatwrong;


                        JOptionPane.showMessageDialog(MyFrame.frame, String.format(html, 350, 400));
//WICHTIG!!!! KORRIGIEREN DAO IST EIN OBJECT, WESWEGEN NEUE INSTANCEN SCHIEFLAUFEN
                        Trainingseinheit b = new Trainingseinheit(1001 + TEA.all.size(), LocalDate.parse(datumTr), LocalTime.parse(trainingsdauerTr), Integer.parseInt(herzfrequenzTr));

                        TEA.setTrainingseinheit(b);
                        LoggedUser.setTrainingsList(b.getTid());

                        datum.setText(null);
                        trainingsdauer.setText(null);
                        herzfrequenz.setText(null);
                        utm = new UserTrainingsplanModel(TEA, LoggedUser);

                        adminTable.setModel(utm);

                        TrainingsplanDialog.setVisible(false);


                    }
                });
                JButton dialogAbr = new JButton("Abbrechen");

                TrainingsplanDialog.setLayout(new BorderLayout());
                TrainingsplanDialog.add(p1, BorderLayout.NORTH);
                JPanel bottom = new JPanel(new GridLayout(0, 2));
                bottom.add(dialogErf);
                bottom.add(dialogAbr);
                TrainingsplanDialog.add(bottom, BorderLayout.SOUTH);


                TrainingsplanDialog.setSize(400, 400);

                // set dialogbox to modal
                TrainingsplanDialog.setModal(true);
                TrainingsplanDialog.setResizable(false);
                TrainingsplanDialog.setVisible(true);

                TrainingsplanDialog.getContentPane().validate();
                TrainingsplanDialog.getContentPane().repaint();


            }
        });

        add(jb);
        add(new JSeparator());
        add(new JLabel("Alle Trainingspläne", JLabel.CENTER));
        add(new JSeparator());
        add(new JScrollPane(adminTable));

    }



}
