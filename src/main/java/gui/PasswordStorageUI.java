package main.java.gui;

import javax.swing.*;
import main.dao.PasswordEntryDao;
import main.java.model.PasswordEntry;
import main.java.util.PasswordGenerator;
import java.awt.*;

public class PasswordStorageUI extends JFrame {

    private JTextField serviceNameField;
    private JTextField emailIdField;
    private JTextField userNameField;
    private JTextField passwordField;
    private JButton generatePwdButton;
    private JButton saveButton;
    private JButton homePage;

    public PasswordStorageUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Save New Password");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel with GridBagLayout for form fields and buttons
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Each component in its own row
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Margin for components

        // Initialize components
        serviceNameField = new JTextField(20);
        emailIdField = new JTextField(20);
        userNameField = new JTextField(20);
        passwordField = new JTextField(20);
        generatePwdButton = new JButton("Generate Password");
        saveButton = new JButton("Save Password");
        homePage = new JButton("Back to Home");

        // Add components to mainPanel with GridBagConstraints
        mainPanel.add(new JLabel("Service Name:"), gbc);
        mainPanel.add(serviceNameField, gbc);
        mainPanel.add(new JLabel("Email:"), gbc);
        mainPanel.add(emailIdField, gbc);
        mainPanel.add(new JLabel("UserName:"), gbc);
        mainPanel.add(userNameField, gbc);
        mainPanel.add(new JLabel("Password"), gbc);
        mainPanel.add(passwordField, gbc);
        mainPanel.add(generatePwdButton, gbc);
        mainPanel.add(saveButton, gbc);

        // Setting up the homePage button separately to align it at the bottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(homePage);

        // Configure the frame's layout and add the panels
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);

        // Listeners remain the same
        setupListeners();
    }

    private void setupListeners() {
        generatePwdButton.addActionListener(e -> passwordField.setText(generatePwd()));

        saveButton.addActionListener(e -> {
            PasswordEntry entry = new PasswordEntry(userNameField.getText(), emailIdField.getText(), serviceNameField.getText(), passwordField.getText());
            PasswordEntryDao objPwd = new PasswordEntryDao();
            try {
                objPwd.savePasswordEntry(entry);
                JOptionPane.showMessageDialog(PasswordStorageUI.this, "Password saved successfully!");
            } catch (Exception exe) {
                exe.printStackTrace();
                JOptionPane.showMessageDialog(PasswordStorageUI.this, "Failed to save the password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            serviceNameField.setText("");
            emailIdField.setText("");
            userNameField.setText("");
            passwordField.setText("");
        });

        homePage.addActionListener(e -> {
            dispose();
            new MainWindow().setVisible(true);
        });
    }

    private String generatePwd() {
        PasswordGenerator pwd = new PasswordGenerator(true, true, true, true);
        return pwd.generate(12);
    }
}
