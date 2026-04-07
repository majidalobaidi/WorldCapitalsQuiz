package main;

/**
 * Schwierigkeitsgrade für das Quiz
 */
public enum DifficultyLevel {
    EASY("Einfach", 20, 15),      // 20 Länder, 15 Sekunden pro Frage
    MEDIUM("Mittel", 50, 10),     // 50 Länder, 10 Sekunden pro Frage
    HARD("Schwer", 195, 7);      // Alle ~195 Länder, 7 Sekunden pro Frage
    
    private final String displayName;
    private final int numberOfCountries;
    private final int timePerQuestion;
    
    DifficultyLevel(String displayName, int numberOfCountries, int timePerQuestion) {
        this.displayName = displayName;
        this.numberOfCountries = numberOfCountries;
        this.timePerQuestion = timePerQuestion;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public int getNumberOfCountries() {
        return numberOfCountries;
    }
    
    public int getTimePerQuestion() {
        return timePerQuestion;
    }
}
