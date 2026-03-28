package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javafx.scene.layout.Border;

import java.awt.*;
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
        
        // TODO: Layout erstellen
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

            submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> handleAnswer());

            bottomPanel.add(answerField);
            bottomPanel.add(submitButton);
        
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

    }
    
    /**
     * Zeigt Dialog zur Schwierigkeitsauswahl
     */
    private void showDifficultySelection() {
        // TODO: Dialog mit Buttons für EASY, MEDIUM, HARD erstellen
        // TODO: Nach Auswahl startNewQuiz() aufrufen
    }
    
    /**
     * Startet ein neues Quiz
     * @param difficulty Gewählter Schwierigkeitsgrad
     */
    private void startNewQuiz(DifficultyLevel difficulty) {
        // TODO: Quiz initialisieren
        // TODO: Tabelle füllen
        // TODO: Timer starten
    }
    
    /**
     * Erstellt die Tabelle mit allen Ländern
     */
    private void createCountryTable() {
        // TODO: JTable mit 2 Spalten erstellen: "Land" und "Hauptstadt"
        // Spalten: Land | Hauptstadt
        String[] columnNames = {"Land", "Hauptstadt"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabelle nicht editierbar
            }
        };
        
        // TODO: Länder zur Tabelle hinzufügen
        // für jedes Land: tableModel.addRow(new Object[]{land.getName(), ""});
        
        countryTable = new JTable(tableModel);
        // TODO: Tabellen-Styling
    }
    
    /**
     * Behandelt die Antwort-Eingabe
     */
    private void handleAnswer() {
        String answer = answerField.getText().trim();
        
        // TODO: Aktuelles Land ermitteln (erstes unbeantwortetes)
        // TODO: Antwort überprüfen mit quiz.checkAnswer()
        // TODO: Bei korrekter Antwort:
        //   - Hauptstadt in Tabelle eintragen
        //   - Score aktualisieren
        //   - Grüne Markierung
        // TODO: Bei falscher Antwort:
        //   - Rote Markierung
        //   - Richtige Antwort zeigen (optional)
        
        answerField.setText("");
        answerField.requestFocus();
        
        // TODO: Überprüfen ob Quiz beendet
        checkQuizCompletion();
    }
    
    /**
     * Überprüft ob das Quiz abgeschlossen ist
     */
    private void checkQuizCompletion() {
        // TODO: Prüfen ob alle Fragen beantwortet oder Zeit abgelaufen
        // TODO: Wenn fertig: showResults() aufrufen
    }
    
    /**
     * Zeigt die Endergebnisse an
     */
    private void showResults() {
        // TODO: Timer stoppen
        // TODO: Finalen Score berechnen
        // TODO: Namenseingabe für Highscore
        // TODO: Highscore speichern
        // TODO: Highscore-Liste anzeigen
        // TODO: "Neues Spiel" Option anbieten
    }
    
    /**
     * Startet den Countdown-Timer
     */
    private void startTimer() {
        // TODO: Timer erstellen der jede Sekunde:
        //   - Verbleibende Zeit aktualisiert
        //   - timerLabel aktualisiert
        //   - Bei Zeit abgelaufen: Quiz beenden
        
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implementieren
            }
        });
        // countdownTimer.start();
    }
    
    /**
     * Aktualisiert die Score-Anzeige
     */
    private void updateScoreDisplay() {
        // TODO: scoreLabel aktualisieren
        // scoreLabel.setText("Score: " + quiz.getScore());
    }
    
    /**
     * Zeigt die Highscore-Liste
     */
    private void showHighscores() {
        // TODO: Dialog mit Top 10 Highscores erstellen
    }
}