package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    
    public MainWindow() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Password Manager");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton btnRetrievePwd = new JButton("Retrieve Password");
        JButton btnSavePassword = new JButton("Save new Password");

        btnRetrievePwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new RetrievePasswordUI().setVisible(true); 
            }
        });

        btnSavePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new PasswordStorageUI().setVisible(true); 
            }
        });

        panel.add(btnSavePassword);
        panel.add(btnRetrievePwd);

        this.getContentPane().add(panel);
    }

}