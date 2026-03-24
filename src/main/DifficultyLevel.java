package main;

/**
 * Schwierigkeitsgrade für das Quiz
 */
public enum DifficultyLevel {
    EASY("Einfach", 20, 60),      // 20 Länder, 60 Sekunden pro Frage
    MEDIUM("Mittel", 50, 45),     // 50 Länder, 45 Sekunden pro Frage
    HARD("Schwer", 195, 30);      // Alle ~195 Länder, 30 Sekunden pro Frage
    
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
