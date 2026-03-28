package main;

import javax.swing.SwingUtilities;

/**
 * Hauptklasse - Einstiegspunkt für das World Capitals Quiz
 * 
 * @author Euer Team
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        // GUI starten

        SwingUtilities.invokeLater(() -> {
            QuizUI ui = new QuizUI();
            ui.setVisible(true);
        });

    }
}
