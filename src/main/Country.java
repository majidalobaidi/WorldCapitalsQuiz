package main;

/**
 * Repräsentiert ein Land mit seiner Hauptstadt
 */
public class Country {
    private String name;
    private String capital;
    private String continent;
    private boolean answered;
    
    /**
     * Konstruktor
     * @param name Name des Landes
     * @param capital Hauptstadt des Landes
     * @param continent Kontinent
     */
    public Country(String name, String capital, String continent) {
        this.name = name;
        this.capital = capital;
        this.continent = continent;
        this.answered = false;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getCapital() {
        return this.capital;
    }
    
    public String getContinent() {
        return this.continent;
    }
    
    public boolean isAnswered() {
        
        return this.answered;
    }
    
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
    
    /**
     * Überprüft ob die eingegebene Antwort korrekt ist
     * @param answer Benutzer-Antwort
     * @return true wenn korrekt, sonst false
     */
    public boolean checkAnswer(String answer) {
         return this.capital
        .replaceAll("\\s", "")       // Leerzeichen entfernen
        .equalsIgnoreCase(            // Groß-/Kleinschreibung ignorieren
            answer.replaceAll("\\s", "")
        );
    }
}
