package GUI;

import javax.swing.*;
import java.awt.*;
import Has_A_Classes.*;

public class UserManagementGUI extends JFrame {
    private JTextField userIdField, firstNameField, lastNameField, emailField, ageField, passwordField;
    private JTextArea displayArea;
    private JButton addBtn, deleteBtn, searchBtn, displayAllBtn, clearBtn, backBtn, statisticsBtn;
    private MainMenuGUI mainMenu;
    
    public UserManagementGUI(MainMenuGUI mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("User Management");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(20, 20, 100, 20);
        contentPane.add(userIdLabel);
        
        userIdField = new JTextField();
        userIdField.setBounds(130, 20, 200, 20);
        contentPane.add(userIdField);
        
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 50, 100, 20);
        contentPane.add(firstNameLabel);
        
        firstNameField = new JTextField();
        firstNameField.setBounds(130, 50, 200, 20);
        contentPane.add(firstNameField);
        
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 80, 100, 20);
        contentPane.add(lastNameLabel);
        
        lastNameField = new JTextField();
        lastNameField.setBounds(130, 80, 200, 20);
        contentPane.add(lastNameField);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 110, 100, 20);
        contentPane.add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(130, 110, 200, 20);
        contentPane.add(emailField);
        
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 140, 100, 20);
        contentPane.add(ageLabel);
        
        ageField = new JTextField();
        ageField.setBounds(130, 140, 200, 20);
        contentPane.add(ageField);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 170, 100, 20);
        contentPane.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(130, 170, 200, 20);
        contentPane.add(passwordField);
        
        addBtn = new JButton("ADD");
        addBtn.setBounds(20, 220, 100, 30);
        addBtn.addActionListener(e -> addUser());
        contentPane.add(addBtn);
        
        deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds(130, 220, 100, 30);
        deleteBtn.addActionListener(e -> deleteUser());
        contentPane.add(deleteBtn);
        
        searchBtn = new JButton("SEARCH");
        searchBtn.setBounds(240, 220, 100, 30);
        searchBtn.addActionListener(e -> searchUser());
        contentPane.add(searchBtn);
        
        displayAllBtn = new JButton("DISPLAY ALL");
        displayAllBtn.setBounds(20, 260, 150, 30);
        displayAllBtn.addActionListener(e -> displayAllUsers());
        contentPane.add(displayAllBtn);
        
        clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(180, 260, 100, 30);
        clearBtn.addActionListener(e -> clearFields());
        contentPane.add(clearBtn);
        
        statisticsBtn = new JButton("STATISTICS");
        statisticsBtn.setBounds(20, 300, 150, 30);
        statisticsBtn.addActionListener(e -> showStatistics());
        contentPane.add(statisticsBtn);
        
        backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(180, 300, 150, 30);
        backBtn.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });
        contentPane.add(backBtn);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(360, 20, 410, 430);
        contentPane.add(scrollPane);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        scrollPane.setViewportView(displayArea);
        
        setVisible(true);
    }
    
    private void addUser() {
        try {
            int id = Integer.parseInt(userIdField.getText().trim());
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String password = passwordField.getText().trim();
            
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                displayArea.setText("Fill all necessary fields");
                return;
            }
            
            BankSystemManager.addUser(id, firstName, lastName, email, age, password);
            displayArea.setText("User added successfully");
            clearFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid input! Check ID and Age fields");
        }
    }
    
    private void deleteUser() {
        try {
            int id = Integer.parseInt(userIdField.getText().trim());
            boolean deleted = BankSystemManager.deleteUser(id);
            if (deleted) {
                displayArea.setText("User deleted successfully");
                clearFields();
            } else {
                displayArea.setText("User not found");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid User ID");
        }
    }
    
    private void searchUser() {
        try {
            int id = Integer.parseInt(userIdField.getText().trim());
            String result = BankSystemManager.display(id);
            displayArea.setText(result);
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid User ID");
        }
    }
    
    private void displayAllUsers() {
        String result = BankSystemManager.displayAll();
        displayArea.setText(result);
    }
    
    private void showStatistics() {
        int totalUsers = User.getTotalUsers();
        double totalBalance = BankSystemManager.calculateTotalSystemBalance();
        
        StringBuilder stats = new StringBuilder();
        stats.append("========== USER STATISTICS ==========\n\n");
        stats.append("Total Registered Users: ").append(totalUsers).append("\n");
        stats.append("Total System Balance: $").append(String.format("%.2f", totalBalance)).append("\n\n");
        
        if (totalUsers > 0) {
            double avgBalance = totalBalance / totalUsers;
            stats.append("Average Balance per User: $").append(String.format("%.2f", avgBalance)).append("\n");
        }
        
        stats.append("\n=====================================");
        
        displayArea.setText(stats.toString());
    }
    
    private void clearFields() {
        userIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        ageField.setText("");
        passwordField.setText("");
        displayArea.setText("");
    }
}