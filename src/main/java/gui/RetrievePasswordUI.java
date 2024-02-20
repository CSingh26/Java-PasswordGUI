package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.dao.PasswordEntryDao;
import main.java.model.PasswordEntry;

import java.util.List;

public class RetrievePasswordUI extends JFrame {

    private JTextField serviceNameField;
    private JTextField emailIdField;
    private JTextField userNameField;
    private JTextField passwordField;
    private JButton nextEntry;
    private JButton getData;
    private JButton homePage;
    private List<PasswordEntry> entries;
    private int curr = 0;

    public RetrievePasswordUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Retrieve Password");
        setSize(550, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        serviceNameField = new JTextField(20);
        emailIdField = new JTextField(20);
        userNameField = new JTextField(20);
        passwordField = new JTextField(20);
        passwordField.setEditable(false);

        getData = new JButton("Retrieve Password");
        nextEntry = new JButton("Next Entry");
        homePage = new JButton("Back to Home");
        nextEntry.setEnabled(false);

        
        gbc.gridx = 0; gbc.gridy = 0; 
        mainPanel.add(new JLabel("Service Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; 
        mainPanel.add(serviceNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; 
        mainPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; 
        mainPanel.add(emailIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; 
        mainPanel.add(new JLabel("Username"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; 
        mainPanel.add(userNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; 
        mainPanel.add(new JLabel("Password"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; 
        mainPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(getData);
        buttonPanel.add(nextEntry);
        buttonPanel.add(homePage);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        getData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordEntryDao dao = new PasswordEntryDao();
                if (serviceNameField.getText().isEmpty() || serviceNameField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(RetrievePasswordUI.this, "Please enter service name");
                } else {
                    try {
                        entries = dao.getEntry(serviceNameField.getText());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                if (entries.isEmpty()) {
                    JOptionPane.showMessageDialog(RetrievePasswordUI.this, "No enteries found with this service name.");
                } else {
                    if (entries.size() > 1) {
                        nextEntry.setEnabled(true);
                    } else {
                        nextEntry.setEnabled(false);
                    }
                }

                curr = 0;
                displayNext();
            }
            
        });

        nextEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curr = (curr + 1) % entries.size();
                displayNext();
            }
            
        });

        homePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new MainWindow().setVisible(true); 
            }
        });

    }

    private void displayNext() {
        PasswordEntry entry = entries.get(curr);
        emailIdField.setText(entry.getEmailId());
        userNameField.setText(entry.getUserName());
        passwordField.setText(entry.getPassword());
    }

}
