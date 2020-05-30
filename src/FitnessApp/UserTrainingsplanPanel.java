package FitnessApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

class UserTrainingsplanPanel extends JPanel {
    UserTrainingsplanModel utm;
    Benutzer LoggedUser;
    JTable adminTable;
    Boolean editToggle = false;
    public UserTrainingsplanPanel(Benutzer LoggedUser, TrainingseinheitenDAO TEA) {
        utm = new UserTrainingsplanModel(TEA, LoggedUser);
        adminTable = new JTable(utm);
        adminTable.setColumnSelectionAllowed(false);
        JButton jb = new JButton("Neu");
        JButton jbEdit = new JButton("Editieren");
        JButton jbDelete = new JButton("Löschen");
        jbDelete.setVisible(false);
        adminTable.setEnabled(false);
        this.LoggedUser = LoggedUser;

        jbDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                LoggedUser.deleteTrainingsList(utm.referenceList.get(adminTable.getSelectedRow()).getTid());
                utm = new UserTrainingsplanModel(TEA, LoggedUser);
                adminTable.setModel(utm);


            }});

        jbEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(editToggle){
                    editToggle = false;
                    jbEdit.setText("Editieren");
                    adminTable.setEnabled(editToggle);
                    jbDelete.setVisible(false);
                }else{
                    editToggle = true;
                    jbEdit.setText("Abbrechnen");
                    adminTable.setEnabled(editToggle);
                    jbDelete.setVisible(true);
                }
                adminTable.setEnabled(editToggle);

            }});

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
                        Boolean formatCorrect = true;
                        String datumTr = datum.getText();
                        String trainingsdauerTr = trainingsdauer.getText();
                        String herzfrequenzTr = herzfrequenz.getText();


                       if (!isDateValid(datumTr) || !isTimeValid(trainingsdauerTr) || !herzfrequenzTr.matches("-?\\d+")) {
                           formatCorrect = false;
                        }


                        if (formatCorrect) {

                            Trainingseinheit b = new Trainingseinheit(1001 + TEA.all.size(), LocalDate.parse(datumTr), LocalTime.parse(trainingsdauerTr), Integer.parseInt(herzfrequenzTr));

                            TEA.setTrainingseinheit(b);
                            LoggedUser.setTrainingsList(b.getTid());

                            datum.setText(null);
                            trainingsdauer.setText(null);
                            herzfrequenz.setText(null);
                            utm = new UserTrainingsplanModel(TEA, LoggedUser);

                            adminTable.setModel(utm);

                            TrainingsplanDialog.setVisible(false);
                        } else {

                            String dateformatwrong  = !isDateValid(datumTr) ? "<p>Datum Format muss so sein: jjjj-mm-tt <br> " : "";
                            String timeformatwrong  = !isTimeValid(trainingsdauerTr) ? "Zeit Format muss so sein: hh:mm:ss; <br> ": "";
                            String heartformatwrong = !herzfrequenzTr.matches("-?\\d+") ? "Herzfrequenz muss eine ganze Zahl sein": "";

                            String html = "<html><body width='%1s'><h1>Inkorrekte Eingabe</h1>"
                                    + dateformatwrong
                                    + timeformatwrong
                                    + heartformatwrong;

                            JOptionPane.showMessageDialog(MyFrame.frame, String.format(html, 350, 400));
                        }


                    }
                });
                JButton dialogAbr = new JButton("Abbrechen");
                dialogAbr.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        datum.setText(null);
                        trainingsdauer.setText(null);
                        herzfrequenz.setText(null);
                        utm = new UserTrainingsplanModel(TEA, LoggedUser);

                        adminTable.setModel(utm);

                        TrainingsplanDialog.setVisible(false);
                    }
                });
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
        add(jbDelete);
        add(jbEdit);
        add(new JSeparator());
        add(new JLabel("Alle Trainingspläne", JLabel.CENTER));
        add(new JSeparator());
        add(new JScrollPane(adminTable));

    }

    public boolean isDateValid(String dateStr) {
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isTimeValid(String dateStr) {
        try {
            LocalTime.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


}


