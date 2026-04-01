package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import javafx.scene.layout.Border;

import java.awt.*;
import java.util.List;
import java.awt.event.*;

/**
 * Grafische Benutzeroberfläche für das Quiz
 */
public class QuizUI extends JFrame {
    private Quiz quiz;
    private HighscoreManager highscoreManager;
    
    // GUI-Komponenten
    private JTable countryTable;
    private DefaultTableModel tableModel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel scoreLabel;
    private JLabel timerLabel;
    private JProgressBar progressBar;
    private Timer countdownTimer;
    
    /**
     * Konstruktor - initialisiert die GUI
     */
    public QuizUI() {
        quiz = new Quiz();
        highscoreManager = new HighscoreManager();
        
        initializeGUI();
        showDifficultySelection();
    }
    
    /**
     * Initialisiert alle GUI-Komponenten
     */
    private void initializeGUI() {
        setTitle("World Capitals Quiz");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout erstellen
            // Hauptpanel mit BorderLayout
            setLayout(new BorderLayout());

            // Nord: Score und Timer
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            scoreLabel = new JLabel("Score: 0");
            timerLabel = new JLabel("Time: --:--");

            topPanel.add(scoreLabel);
            topPanel.add(timerLabel);

            // Mitte: Tabelle mit Ländern
            createCountryTable();
            add(new JScrollPane(countryTable), BorderLayout.CENTER);

            // Süd: Eingabefeld und Button
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout());

            answerField = new JTextField(15);
            answerField.addActionListener(e -> handleAnswer());

            submitButton = new JButton("Antworten");
            submitButton.addActionListener(e -> handleAnswer());

            bottomPanel.add(answerField);
            bottomPanel.add(submitButton);

            // NEU: End Game Button
            JButton endGameButton = new JButton("Spiel beenden");
            endGameButton.addActionListener(e -> {
                int choice = JOptionPane.showConfirmDialog(this,
                    "Möchtest du das Spiel wirklich beenden?",
                    "Spiel beenden",
                    JOptionPane.YES_NO_OPTION);
                
                if (choice == JOptionPane.YES_OPTION) {
                    countdownTimer.stop(); // Timer stoppen
                    showResults();
                }
            });

            bottomPanel.add(answerField);
            bottomPanel.add(submitButton);
            bottomPanel.add(endGameButton); // NEU
        
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

    }
    
    /**
     * Zeigt Dialog zur Schwierigkeitsauswahl
     */
    private void showDifficultySelection() {
        //Dialog mit Buttons für EASY, MEDIUM, HARD erstellen

        Object[] options = {"Einfach", "Mittel", "Schwer"};

        int choice = JOptionPane.showOptionDialog(
            this,
            "Wähle Schwierigkeitsgrad",
            "Schwierigkeit",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        DifficultyLevel selectedDifficultyLevel = null;

        switch (choice) {
            case 0:
                selectedDifficultyLevel = DifficultyLevel.EASY;
                break;
            case 1:
                selectedDifficultyLevel = DifficultyLevel.MEDIUM;
                break;
            case 2:
                selectedDifficultyLevel = DifficultyLevel.HARD;             
                break;
            default:
                break;
        }

        //Nach Auswahl startNewQuiz() aufrufen
        if (selectedDifficultyLevel != null)
            startNewQuiz(selectedDifficultyLevel);

    }
    
    /**
     * Startet ein neues Quiz
     * @param difficulty Gewählter Schwierigkeitsgrad
     */
    private void startNewQuiz(DifficultyLevel difficulty) {
        //Quiz initialisieren
        quiz.startNewQuiz(difficulty);

        //Score anzeigen
            scoreLabel.setText("Score: " + quiz.getScore());

        //Tabelle füllen
        tableModel.setRowCount(0); // Alte Zeilen löschen (falls neues Spiel)

        // Für jedes Land in currentQuizCountries:
        for (Country country : quiz.getCurrentQuizCountries()) {
            // Zeile hinzufügen: Land-Name, leere Hauptstadt
            tableModel.addRow(new Object[]{country.getName(), ""});
        }

        //Timer starten
        startTimer();
    }
    
    /**
     * Erstellt die Tabelle mit allen Ländern
     */
    private void createCountryTable() {
        //JTable mit 2 Spalten erstellen: "Land" und "Hauptstadt"
        // Spalten: Land | Hauptstadt
        String[] columnNames = {"Land", "Hauptstadt"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabelle nicht editierbar
            }
        };
        
        //Länder zur Tabelle hinzufügen
        // für jedes Land: tableModel.addRow(new Object[]{land.getName(), ""});
        
        countryTable = new JTable(tableModel);

        //Tabellen-Styling
        // Beispiel Styling:
        countryTable.setFont(new Font("Arial", Font.PLAIN, 14));
        countryTable.setRowHeight(25);
    }
    
    /**
     * Behandelt die Antwort-Eingabe
     */
    private void handleAnswer() {
        String answer = answerField.getText().trim();
        
        //Aktuelles Land ermitteln (erstes unbeantwortetes)
        // Leere Antwort ignorieren
        if (answer.isEmpty()) {
            return;
        }
        
        // Gehe durch ALLE Länder und prüfe ob die Antwort passt
        boolean found = false;
        
        for (Country country : quiz.getCurrentQuizCountries()) {
            // Überspringe bereits beantwortete Länder
            if (country.isAnswered()) {
                continue;
            }
            
            // Prüfe ob die Antwort zu diesem Land passt
            if (quiz.checkAnswer(country, answer)) {
                found = true;
                
                // Finde die Zeile in der Tabelle und fülle die Hauptstadt aus
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String landInTabelle = (String) tableModel.getValueAt(i, 0);
                    
                    if (landInTabelle.equals(country.getName())) {
                        // Hauptstadt eintragen
                        tableModel.setValueAt(country.getCapital(), i, 1);
                        // TODO: Zeile grün markieren (optional, später)
                        break;
                    }
                }
                
                // Score aktualisieren
                updateScoreDisplay();
                // TODO: Bei falscher Antwort:
                /*if(found==false) {
                    // Optional: Zeige die richtige Antwort
                    JOptionPane.showMessageDialog(this, 
                        "Diese Antwort ist nicht in dieser Tabelle" ,
                        "Falsch", 
                        JOptionPane.ERROR_MESSAGE);
                }*/
                break; // Stoppe nach erstem Match
            }
        }

        
        
        answerField.setText("");
        answerField.requestFocus();
        
        //Überprüfen ob Quiz beendet
        checkQuizCompletion();
    }
    
    /**
     * Überprüft ob das Quiz abgeschlossen ist
     */
    private void checkQuizCompletion() {
        // Prüfe ob Zeit abgelaufen
        if (quiz.isTimeUp()) {
            showResults();
            return;
        }
        
        // Prüfe ob alle Länder beantwortet
        boolean allAnswered = true;
        for (Country country : quiz.getCurrentQuizCountries()) {
            if (!country.isAnswered()) {
                allAnswered = false;
                break;
            }
        }
        
        if (allAnswered) {
            showResults();
        }
    }
    
    /**
     * Zeigt die Endergebnisse an
     */
    private void showResults() {
        //Timer stoppen
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        // Fehlende Hauptstädte ausfüllen und zählen
        int totalQuestions = quiz.getCurrentQuizCountries().size();
        int correctAnswers = 0;
        
        for (Country country : quiz.getCurrentQuizCountries()) {
            if (country.isAnswered()) {
                correctAnswers++;
            } else {
                // Nicht beantwortet - fülle Hauptstadt aus (rot markieren)
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String landInTabelle = (String) tableModel.getValueAt(i, 0);
                    
                    if (landInTabelle.equals(country.getName())) {
                        // Hauptstadt mit Markierung eintragen
                        tableModel.setValueAt("❌ " + country.getCapital(), i, 1);
                        // TODO: Zeile rot markieren (später)
                        break;
                    }
                }
            }
        }

        //Finalen Score berechnen
        int finalScore = quiz.calculateFinalScore();
        // Ergebnis-Dialog
        String message = String.format(
            "Quiz beendet!\n\n" +
            "Korrekte Antworten: %d / %d\n" +
            "Dein Score: %d Punkte",
            correctAnswers, totalQuestions, finalScore
        );

        JOptionPane.showMessageDialog(this, message, "Ergebnis", 
                                    JOptionPane.INFORMATION_MESSAGE);

        // Namenseingabe für Highscore
        // Highscore speichern
        // Highscore speichern
        if (highscoreManager.isTopScore(finalScore)) {
            String name = JOptionPane.showInputDialog(this, 
                "Neuer Highscore! Gib deinen Namen ein:",
                "Highscore",
                JOptionPane.PLAIN_MESSAGE);
            
            if (name != null && !name.trim().isEmpty()) {
                Player player = new Player(name.trim(), finalScore, quiz.getDifficulty());
                highscoreManager.addHighscore(player);
            }
        }
        //Highscore-Liste anzeigen
        showHighscores();

        //"Neues Spiel" Option anbieten
        int choice = JOptionPane.showConfirmDialog(this,
            "Möchtest du nochmal spielen?",
            "Neues Spiel",
            JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Tabelle leeren
            tableModel.setRowCount(0);
            // Neue Schwierigkeitsauswahl
            showDifficultySelection();
        } else {
            System.exit(0);
        }
    }
    
    /**
     * Startet den Countdown-Timer
     */
    private void startTimer() {
        // TODO: Timer erstellen der jede Sekunde:
        //   - Verbleibende Zeit aktualisiert
        //   - timerLabel aktualisiert
        //   - Bei Zeit abgelaufen: Quiz beenden

        // Timer: alle 1000ms (1 Sekunde) wird die Aktion ausgeführt
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int remaining = quiz.getRemainingTime();
                // Sekunden in Minuten und Sekunden umrechnen
                int minutes = remaining / 60;    // z.B. 330 / 60 = 5 Minuten
                int seconds = remaining % 60;    // z.B. 330 % 60 = 30 Sekunden
                
                // Format: "Zeit: 05:30"
                timerLabel.setText(String.format("Zeit: %02d:%02d", minutes, seconds));
                // %02d bedeutet: 2 Stellen, mit führender 0 wenn nötig

                if (remaining <= 0) {
                    // Zeit ist abgelaufen!
                    countdownTimer.stop();  // Timer stoppen
                    showResults();          // Ergebnisse anzeigen
                }
            }
        });
        countdownTimer.start();
    }
    
    /**
     * Aktualisiert die Score-Anzeige
     */
    private void updateScoreDisplay() {
        scoreLabel.setText("Score: " + quiz.getScore());
    }
    
    /**
     * Zeigt die Highscore-Liste
     */
    private void showHighscores() {
        // TODO: Dialog mit Top 10 Highscores erstellen
        List<Player> top10= highscoreManager.getTop10();
        StringBuilder sb = new StringBuilder("=== TOP 10 HIGHSCORES ===\n\n");

        for(int i=0; i< top10.size(); i++){
            Player p = top10.get(i);
            sb.append(String.format("%d. %s - %d Punkte (%s)\n", 
            i+1, p.getName(), p.getScore(), p.getDifficulty().getDisplayName()));
        }
        JOptionPane.showMessageDialog(this, sb.toString(), 
        "Highscores", JOptionPane.INFORMATION_MESSAGE);
    }
}