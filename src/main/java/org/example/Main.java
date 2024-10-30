package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Biblioteca app = new Biblioteca();
            app.setVisible(true);
        });
    }
}