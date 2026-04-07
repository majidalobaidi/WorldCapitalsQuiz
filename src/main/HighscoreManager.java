package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Verwaltet die Highscores (Speichern/Laden)
 */
public class HighscoreManager {
    private static final String HIGHSCORE_FILE = "highscores.dat";
    private List<Player> highscores;

    /**
     * Konstruktor
     */
    public HighscoreManager() {
        this.highscores = new ArrayList<>();
        loadHighscores();
    }

    /**
     * Fügt einen neuen Highscore hinzu
     * 
     * @param player Spieler mit Score
     */
    public void addHighscore(Player player) {
        highscores.add(player); // Spieler hinzufügen
        Collections.sort(highscores); // Liste sortieren (nutzt compareTo!)
        if (highscores.size() > 10) {
            highscores = highscores.subList(0, 10); // Nur Top 10 behalten
            highscores = new ArrayList<>(highscores);
        }
        saveHighscores(); // Speichern
    }

    /**
     * Lädt Highscores aus Datei
     */
    private void loadHighscores() {
        // TODO: Highscores aus Datei laden
        // Tipp: ObjectInputStream verwenden
        try {
            File file = new File(HIGHSCORE_FILE);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                highscores = (List<Player>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            System.out.println("Keine Highscores gefunden. Neue Liste wird erstellt.");
        }
    }

    /**
     * Speichert Highscores in Datei
     */
    private void saveHighscores() {
        // TODO: Highscores in Datei speichern
        // Tipp: ObjectOutputStream verwenden
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            oos.writeObject(highscores);
            oos.close();
        } catch (Exception e) {
            System.err.println("Fehler beim Speichern der Highscores: " + e.getMessage());
        }
    }

    /**
     * Gibt die Top 10 Highscores zurück
     * 
     * @return Liste der Top 10 Spieler
     */
    public List<Player> getTop10() {
        return new ArrayList<>(highscores); // Kopie zurückgeben
    }

    /**
     * Überprüft ob ein Score in die Top 10 kommt
     * 
     * @param score Der zu prüfende Score
     * @return true wenn Top 10
     */
    public boolean isTopScore(int score) {
        if (highscores.size() < 10)
            return true; // Noch keine 10 Einträge
        return score > highscores.get(9).getScore(); // Besser als Platz 10?
    }
}
