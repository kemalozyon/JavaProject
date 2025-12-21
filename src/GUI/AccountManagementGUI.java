package GUI;

import javax.swing.*;
import java.awt.*;
import Has_A_Classes.*;
import Is_A_Classes.*;

public class AccountManagementGUI extends JFrame {
    private JTextField userIdField, initialBalanceField, extraParamField;
    private JComboBox<String> accountTypeCombo;
    private JTextArea infoArea;
    private JButton addAccountBtn, viewAccountsBtn, clearBtn, backBtn, statisticsBtn;
    private MainMenuGUI mainMenu;
    
    public AccountManagementGUI(MainMenuGUI mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Account Management");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(20, 20, 100, 20);
        contentPane.add(userIdLabel);
        
        userIdField = new JTextField();
        userIdField.setBounds(150, 20, 200, 20);
        contentPane.add(userIdField);
        
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(20, 50, 100, 20);
        contentPane.add(accountTypeLabel);
        
        accountTypeCombo = new JComboBox<>(new String[]{"Checking", "Savings"});
        accountTypeCombo.setBounds(150, 50, 200, 20);
        contentPane.add(accountTypeCombo);
        
        JLabel initialBalanceLabel = new JLabel("Initial Balance:");
        initialBalanceLabel.setBounds(20, 80, 100, 20);
        contentPane.add(initialBalanceLabel);
        
        initialBalanceField = new JTextField();
        initialBalanceField.setBounds(150, 80, 200, 20);
        contentPane.add(initialBalanceField);
        
        JLabel extraParamLabel = new JLabel("Overdraft/Interest:");
        extraParamLabel.setBounds(20, 110, 120, 20);
        contentPane.add(extraParamLabel);
        
        extraParamField = new JTextField();
        extraParamField.setBounds(150, 110, 200, 20);
        contentPane.add(extraParamField);
        
        addAccountBtn = new JButton("ADD ACCOUNT");
        addAccountBtn.setBounds(20, 150, 150, 30);
        addAccountBtn.addActionListener(e -> addAccount());
        contentPane.add(addAccountBtn);
        
        viewAccountsBtn = new JButton("VIEW ACCOUNTS");
        viewAccountsBtn.setBounds(180, 150, 150, 30);
        viewAccountsBtn.addActionListener(e -> viewAccounts());
        contentPane.add(viewAccountsBtn);
        
        clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(20, 190, 100, 30);
        clearBtn.addActionListener(e -> clearFields());
        contentPane.add(clearBtn);
        
        statisticsBtn = new JButton("STATISTICS");
        statisticsBtn.setBounds(130, 190, 120, 30);
        statisticsBtn.addActionListener(e -> showStatistics());
        contentPane.add(statisticsBtn);
        
        backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(20, 230, 150, 30);
        backBtn.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });
        contentPane.add(backBtn);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(370, 20, 300, 380);
        contentPane.add(scrollPane);
        
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        scrollPane.setViewportView(infoArea);
        
        setVisible(true);
    }
    
    private void addAccount() {
        try {
            int userId = Integer.parseInt(userIdField.getText().trim());
            String type = (String) accountTypeCombo.getSelectedItem();
            double initialBalance = Double.parseDouble(initialBalanceField.getText().trim());
            double extraParam = Double.parseDouble(extraParamField.getText().trim());
            
            BankSystemManager.addAccountToUser(userId, type, initialBalance, extraParam);
            infoArea.setText("Account added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            infoArea.setText("Invalid input! Check all fields");
        }
    }
    
    private void viewAccounts() {
        try {
            int userId = Integer.parseInt(userIdField.getText().trim());
            String result = BankSystemManager.display(userId);
            infoArea.setText(result);
        } catch (NumberFormatException e) {
            infoArea.setText("Invalid User ID");
        }
    }
    
    private void showStatistics() {
        int totalAccounts = BankAccount.getTotalAccountsCreated();
        int totalUsers = User.getTotalUsers();
        double totalBalance = BankSystemManager.calculateTotalSystemBalance();
        
        StringBuilder stats = new StringBuilder();
        stats.append("========== ACCOUNT STATISTICS ==========\n\n");
        stats.append("Total Bank Accounts: ").append(totalAccounts).append("\n");
        stats.append("Total Users: ").append(totalUsers).append("\n");
        stats.append("Total System Balance: $").append(String.format("%.2f", totalBalance)).append("\n\n");
        
        if (totalUsers > 0) {
            double avgAccountsPerUser = (double) totalAccounts / totalUsers;
            stats.append("Average Accounts per User: ").append(String.format("%.2f", avgAccountsPerUser)).append("\n");
        }
        
        stats.append("\n========================================");
        
        infoArea.setText(stats.toString());
    }
    
    private void clearFields() {
        userIdField.setText("");
        initialBalanceField.setText("");
        extraParamField.setText("");
        infoArea.setText("");
    }
}