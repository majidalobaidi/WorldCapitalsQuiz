package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Hauptklasse für die Quiz-Logik
 */
public class Quiz {
    private List<Country> allCountries;
    private List<Country> currentQuizCountries;
    private DifficultyLevel difficulty;
    private int score;
    private long startTime;
    private int timeLimit; // Sekunden pro Frage
    
    /**
     * Konstruktor
     */
    public Quiz() {
        this.allCountries = new ArrayList<>();
        this.currentQuizCountries = new ArrayList<>();
        this.score = 0;
        loadCountries();
    }
    
    /**
     * Lädt alle Länder und Hauptstädte
     */
    private void loadCountries() {
        // Schritt 1: Pfad zur CSV-Datei
        String csvFile = "countries.csv";
        
        // Schritt 2: Try-Catch für File-Handling
        try {
            // Schritt 3: BufferedReader öffnen
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            
            String line;
            
            // Schritt 4: Zeile für Zeile lesen
            while ((line = br.readLine()) != null) {
                
                // Schritt 5: Zeile an Kommas trennen
                String[] data = line.split(",");
                
                // Schritt 6: Country-Objekt erstellen
                // data[0] = Land, data[1] = Hauptstadt, data[2] = Kontinent
                String countryName = data[0];
                String capital = data[1];
                String continent = data[2];
                
                // Schritt 7: Zur Liste hinzufügen
                allCountries.add(new Country(countryName, capital, continent));
            }
            
            // Schritt 8: BufferedReader schließen
            br.close();
            
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Länder: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Startet ein neues Quiz basierend auf Schwierigkeitsgrad
     */

    public void startNewQuiz(DifficultyLevel difficulty) {
        // TODO: Quiz-Länder basierend auf Schwierigkeit auswählen
        // - EASY: 20 Länder
        // - MEDIUM: 50 Länder
        // - HARD: Alle Länder

        currentQuizCountries.clear(); // Liste leeren
        this.difficulty = difficulty;  // Schwierigkeit speichern

        int anzahl = difficulty.getNumberOfCountries();
        List<Country> tempCountries = new ArrayList<>(allCountries); //Kopie erstellen
        Collections.shuffle(tempCountries); // Mischen (zufällige Reihenfolge)

        //Richtige Anzahl auswählen
        //HARD
        if(anzahl>= allCountries.size()){
            currentQuizCountries.addAll(tempCountries);
        }
        else{
            currentQuizCountries.addAll(tempCountries.subList(0, anzahl));
        }

        // TODO: Startzeitpunkt setzen
        startTime = System.currentTimeMillis();
        timeLimit = difficulty.getTimePerQuestion() * anzahl;

    }
    
    /**
     * Überprüft die Antwort für ein bestimmtes Land
     * @param country Das Land
     * @param answer Die Antwort des Benutzers
     * @return true wenn korrekt
     */
    public boolean checkAnswer(Country country, String answer) {
        // TODO: Antwort überprüfen
        if(country.getCapital()
            .replaceAll("\\s", "")              // Leerzeichen entfernen
            .equalsIgnoreCase(
                answer.replaceAll("\\s", ""))){

        // TODO: Punkte aktualisieren wenn korrekt
        score+=10;

        // TODO: Land als beantwortet markieren
        country.setAnswered(true);
        return true;
        }
        return false;
    }
    
    /**
     * Berechnet die verbleibende Zeit für die aktuelle Frage
     */
    public int getRemainingTime() {
        // TODO: Zeitdifferenz berechnen
        return (int) (timeLimit - (System.currentTimeMillis() - startTime)/1000);
    }
    
    /**
     * Überprüft ob die Zeit abgelaufen ist
     * @return true wenn Zeit abgelaufen
     */
    public boolean isTimeUp() {
        return getRemainingTime()==0;
    }
    
    /**
     * Berechnet den finalen Score
     * @return Score (Punkte + Zeitbonus)
     */
    public int calculateFinalScore() {
        // Score berechnen
        // Beispiel: Korrekte Antworten * 10 + Zeitbonus
        int timeBonus = Math.max(0, getRemainingTime() * 2); // 2 Punkte pro übrige Sekunde
        return score + timeBonus;
    }
    
    // TODO: Getter und Setter
    public List<Country> getAllCountries() {
        return allCountries;
    }
    
    public List<Country> getCurrentQuizCountries() {
        return currentQuizCountries;
    }
    
    public int getScore() {
        return score;
    }
    
    public DifficultyLevel getDifficulty() {
        return difficulty;
    }
}