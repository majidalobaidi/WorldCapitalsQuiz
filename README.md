# 🌍 World Capitals Quiz

Ein interaktives Quiz-Spiel in Java, um die Hauptstädte der Welt zu lernen!

## 📋 Beschreibung

World Capitals Quiz ist ein Lernspiel, bei dem Spieler ihr Wissen über Welthauptstädte testen können. Das Spiel bietet verschiedene Schwierigkeitsgrade, ein Zeitlimit pro Frage und ein Highscore-System.

## ✨ Features

- **Große Länderdatenbank**: Über 60 Länder aus allen Kontinenten
- **3 Schwierigkeitsgrade**:
  - Einfach: 20 Länder, 60 Sekunden pro Frage
  - Mittel: 50 Länder, 45 Sekunden pro Frage
  - Schwer: Alle Länder, 30 Sekunden pro Frage
- **Interaktive Tabelle**: Zeigt alle Länder, richtige Antworten werden direkt angezeigt
- **Zeitlimit**: Jede Frage muss innerhalb der Zeit beantwortet werden
- **Punktesystem**: Score basiert auf richtigen Antworten und Geschwindigkeit
- **Highscore-Liste**: Top 10 werden gespeichert

## 🚀 Installation

### Voraussetzungen
- Java JDK 8 oder höher
- Beliebige IDE (Eclipse, IntelliJ IDEA, VS Code) oder Terminal

### Projekt klonen
```bash
git clone https://github.com/euer-username/WorldCapitalsQuiz.git
cd WorldCapitalsQuiz
```

### Kompilieren
```bash
javac -d bin src/main/*.java
```

### Ausführen
```bash
java -cp bin main.Main
```

## 🎮 Spielanleitung

1. Wähle einen Schwierigkeitsgrad
2. Eine Tabelle mit Ländern wird angezeigt
3. Gib die Hauptstadt für jedes Land ein
4. Bei richtiger Antwort erscheint die Hauptstadt in der Tabelle
5. Achte auf das Zeitlimit!
6. Am Ende siehst du deinen Score und kannst ihn speichern

## 📁 Projektstruktur

```
WorldCapitalsQuiz/
├── src/
│   └── main/
│       ├── Main.java              # Einstiegspunkt
│       ├── Quiz.java              # Quiz-Logik
│       ├── QuizUI.java            # Grafische Oberfläche
│       ├── Country.java           # Land-Datenmodell
│       ├── Player.java            # Spieler-Datenmodell
│       ├── DifficultyLevel.java   # Schwierigkeitsgrade
│       └── HighscoreManager.java  # Highscore-Verwaltung
├── countries.csv                   # Länder-Datenbank
├── README.md                       # Diese Datei
└── .gitignore                      # Git-Ausschlüsse
```

## 🛠️ Technologien

- **Java**: Hauptprogrammiersprache
- **Swing**: GUI-Framework
- **Java Collections**: Datenverwaltung
- **Java I/O**: Highscore-Speicherung

## 👥 Autoren

- [Dein Name]
- [Name deines Freundes]

## 📝 Lizenz

Dieses Projekt ist für Bildungszwecke erstellt.

## 🔮 Zukünftige Features (Optional)

- [ ] Mehr Länder hinzufügen (bis zu 195)
- [ ] Sounds und Animationen
- [ ] Multiplayer-Modus
- [ ] Verschiedene Quiz-Modi (z.B. Multiple Choice)
- [ ] Statistiken pro Kontinent
- [ ] Flaggen-Quiz

## 🐛 Bug Reports

Bitte öffnet ein Issue auf GitHub, wenn ihr Bugs findet!

## 🤝 Beitragen

Pull Requests sind willkommen! Für größere Änderungen bitte zuerst ein Issue öffnen.
