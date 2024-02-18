package main.java; // Adjust the package name as per your project structure

import javax.swing.SwingUtilities;

import main.java.gui.MainWindow;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}
