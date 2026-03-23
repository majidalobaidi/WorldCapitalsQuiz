package main;

import java.time.LocalDateTime;

/**
 * Repräsentiert einen Spieler mit seinem Score
 */
public class Player implements Comparable<Player> {
    private String name;
    private int score;
    private DifficultyLevel difficulty;
    private LocalDateTime date;
    
    /**
     * Konstruktor
     * @param name Spielername
     * @param score Erreichte Punkte
     * @param difficulty Schwierigkeitsgrad
     */
    public Player(String name, int score, DifficultyLevel difficulty) {
        this.name = name;
        this.score = score;
        this.difficulty = difficulty;
        this.date = LocalDateTime.now();
    }
    
    /**
     * Für Sortierung nach Score (höchster zuerst)
     */
    @Override
    public int compareTo(Player other) {
        // TODO: Implementieren - höherer Score zuerst
        return Integer.compare(other.score, this.score);
    }
    
    // TODO: Getter implementieren
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public DifficultyLevel getDifficulty() {
        return difficulty;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    @Override
    public String toString() {
        // TODO: Formatierte Ausgabe
        return name + " - " + score + " Punkte (" + difficulty.getDisplayName() + ")";
    }
}
