package main.java.gui;

import java.util.List;
import javax.swing.*;
import main.dao.PasswordEntryDao;
import main.java.model.PasswordEntry;
import java.awt.*;
import java.awt.event.*;

public class RetrievePasswordUI extends JFrame {

    private JTextField serviceNameField;
    private JTextField emailIdField;
    private JTextField userNameField;
    private JTextField passwordField;
    private final String serviceNamePlaceHolder = "Enter Service Name";
    private JButton generateDataButton;
    private JButton nextEntryButton;
    private JButton homePage;
    private List<PasswordEntry> entries;
    private int currentIndex = 0;

    public RetrievePasswordUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Retrieve Password");
        setSize(700, 350); // Adjusted for consistency
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        serviceNameField = new JTextField(serviceNamePlaceHolder, 20);
        emailIdField = new JTextField(20);
        userNameField = new JTextField(20);
        passwordField = new JTextField(20);
        passwordField.setEditable(false); // Make the password field non-editable
        serviceNameField.setForeground(Color.GRAY);

        generateDataButton = new JButton("Get Password");
        nextEntryButton = new JButton("Next Entry");
        homePage = new JButton("Back to Home");
        nextEntryButton.setEnabled(false); // Initially disabled

        mainPanel.add(new JLabel("Service Name:"), gbc);
        mainPanel.add(serviceNameField, gbc);
        mainPanel.add(new JLabel("Email:"), gbc);
        mainPanel.add(emailIdField, gbc);
        mainPanel.add(new JLabel("UserName:"), gbc);
        mainPanel.add(userNameField, gbc);
        mainPanel.add(new JLabel("Password:"), gbc);
        mainPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateDataButton);
        buttonPanel.add(nextEntryButton);
        buttonPanel.add(homePage);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        setupListeners();
    }

    private void setupListeners() {
        serviceNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (serviceNameField.getText().equals(serviceNamePlaceHolder)) {
                    serviceNameField.setText("");
                    serviceNameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (serviceNameField.getText().isEmpty()) {
                    serviceNameField.setText(serviceNamePlaceHolder);
                    serviceNameField.setForeground(Color.GRAY);
                }
            }
        });

        generateDataButton.addActionListener(e -> {
            PasswordEntryDao dao = new PasswordEntryDao();
            try {
                entries = dao.getEntry(serviceNameField.getText().equals(serviceNamePlaceHolder) ? "" : serviceNameField.getText());
            } catch (Exception exe) {
                exe.printStackTrace();
            }
            if (entries.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No entries found.");
                nextEntryButton.setEnabled(false);
            } else {
                displayEntry();
                nextEntryButton.setEnabled(entries.size() > 1);
            }
        });

        nextEntryButton.addActionListener(e -> {
            currentIndex = (currentIndex + 1) % entries.size();
            displayEntry();
        });

        homePage.addActionListener(e -> {
            dispose();
            new MainWindow().setVisible(true);
        });
    }

    private void displayEntry() {
        if (entries.isEmpty() || currentIndex < 0 || currentIndex >= entries.size()) return;
        PasswordEntry entry = entries.get(currentIndex);
        emailIdField.setText(entry.getEmailId());
        userNameField.setText(entry.getUserName());
        passwordField.setText(entry.getPassword()); // Assuming the getPassword method returns the decrypted password
    }

}
