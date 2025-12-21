package GUI;

import javax.swing.*;
import java.awt.*;
import Has_A_Classes.*;
import Is_A_Classes.*;

public class AccountManagementGUI extends JFrame {
    private JTextField userIdField, initialBalanceField, extraParamField;
    private JTextField interestUserIdField, interestAccountNumberField;
    private JComboBox<String> accountTypeCombo;
    private JTextArea infoArea;
    private JButton addAccountBtn, viewAccountsBtn, clearBtn, backBtn, statisticsBtn, applyInterestBtn;
    private MainMenuGUI mainMenu;
    
    public AccountManagementGUI(MainMenuGUI mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Account Management");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        // ADD ACCOUNT SECTION
        JLabel addAccountLabel = new JLabel("=== ADD ACCOUNT ===");
        addAccountLabel.setBounds(20, 10, 200, 20);
        contentPane.add(addAccountLabel);
        
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(20, 35, 100, 20);
        contentPane.add(userIdLabel);
        
        userIdField = new JTextField();
        userIdField.setBounds(150, 35, 200, 20);
        contentPane.add(userIdField);
        
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(20, 65, 100, 20);
        contentPane.add(accountTypeLabel);
        
        accountTypeCombo = new JComboBox<>(new String[]{"Checking", "Savings"});
        accountTypeCombo.setBounds(150, 65, 200, 20);
        contentPane.add(accountTypeCombo);
        
        JLabel initialBalanceLabel = new JLabel("Initial Balance:");
        initialBalanceLabel.setBounds(20, 95, 100, 20);
        contentPane.add(initialBalanceLabel);
        
        initialBalanceField = new JTextField();
        initialBalanceField.setBounds(150, 95, 200, 20);
        contentPane.add(initialBalanceField);
        
        JLabel extraParamLabel = new JLabel("Overdraft/Interest:");
        extraParamLabel.setBounds(20, 125, 120, 20);
        contentPane.add(extraParamLabel);
        
        extraParamField = new JTextField();
        extraParamField.setBounds(150, 125, 200, 20);
        contentPane.add(extraParamField);
        
        addAccountBtn = new JButton("ADD ACCOUNT");
        addAccountBtn.setBounds(20, 160, 150, 30);
        addAccountBtn.addActionListener(e -> addAccount());
        contentPane.add(addAccountBtn);
        
        viewAccountsBtn = new JButton("VIEW ACCOUNTS");
        viewAccountsBtn.setBounds(180, 160, 150, 30);
        viewAccountsBtn.addActionListener(e -> viewAccounts());
        contentPane.add(viewAccountsBtn);
        
        // APPLY INTEREST SECTION
        JLabel applyInterestLabel = new JLabel("=== APPLY INTEREST ===");
        applyInterestLabel.setBounds(20, 210, 200, 20);
        contentPane.add(applyInterestLabel);
        
        JLabel interestUserIdLabel = new JLabel("User ID:");
        interestUserIdLabel.setBounds(20, 235, 100, 20);
        contentPane.add(interestUserIdLabel);
        
        interestUserIdField = new JTextField();
        interestUserIdField.setBounds(150, 235, 200, 20);
        contentPane.add(interestUserIdField);
        
        JLabel interestAccountNumberLabel = new JLabel("Account Number:");
        interestAccountNumberLabel.setBounds(20, 265, 120, 20);
        contentPane.add(interestAccountNumberLabel);
        
        interestAccountNumberField = new JTextField();
        interestAccountNumberField.setBounds(150, 265, 200, 20);
        contentPane.add(interestAccountNumberField);
        
        applyInterestBtn = new JButton("APPLY INTEREST");
        applyInterestBtn.setBounds(20, 300, 150, 30);
        applyInterestBtn.addActionListener(e -> applyInterest());
        contentPane.add(applyInterestBtn);
        
        // OTHER BUTTONS
        clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(20, 350, 100, 30);
        clearBtn.addActionListener(e -> clearFields());
        contentPane.add(clearBtn);
        
        statisticsBtn = new JButton("INFORMATION");
        statisticsBtn.setBounds(130, 350, 120, 30);
        statisticsBtn.addActionListener(e -> showStatistics());
        contentPane.add(statisticsBtn);
        
        backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(20, 390, 150, 30);
        backBtn.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });
        contentPane.add(backBtn);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(370, 20, 350, 480);
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
            
            // Clear only add account fields
            userIdField.setText("");
            initialBalanceField.setText("");
            extraParamField.setText("");
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
    
    private void applyInterest() {
        try {
            int userId = Integer.parseInt(interestUserIdField.getText().trim());
            int accountNumber = Integer.parseInt(interestAccountNumberField.getText().trim());
            
            User user = BankSystemManager.findUser(userId);
            if (user == null) {
                infoArea.setText("User not found!");
                return;
            }
            
            BankAccount account = user.getAccount(accountNumber);
            if (account == null) {
                infoArea.setText("Account not found!");
                return;
            }
            
            if (account instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                double oldBalance = savingsAccount.getBalance();
                double interest = savingsAccount.calculateInterest();
                savingsAccount.applyInterest();
                
                infoArea.setText("Interest Applied Successfully!\n\n");
                infoArea.append("Account Type: Savings Account\n");
                infoArea.append("Account Number: " + accountNumber + "\n");
                infoArea.append("Interest Rate: " + savingsAccount.getInterestRate() + "%\n");
                infoArea.append("Old Balance: $" + String.format("%.2f", oldBalance) + "\n");
                infoArea.append("Interest Added: $" + String.format("%.2f", interest) + "\n");
                infoArea.append("New Balance: $" + String.format("%.2f", savingsAccount.getBalance()) + "\n");
                
                // Clear interest fields
                interestUserIdField.setText("");
                interestAccountNumberField.setText("");
            } else {
                infoArea.setText("ERROR: This account is not a Savings Account!\n\n");
                infoArea.append("Interest can only be applied to Savings Accounts.\n");
                infoArea.append("This is a " + account.getType() + " Account.");
            }
            
        } catch (NumberFormatException e) {
            infoArea.setText("Invalid input! Check User ID and Account Number");
        }
    }
    
    private void showStatistics() {
        String report = BankSystemManager.displayAll();
        infoArea.setText(report);
    }
    
    private void clearFields() {
        userIdField.setText("");
        initialBalanceField.setText("");
        extraParamField.setText("");
        interestUserIdField.setText("");
        interestAccountNumberField.setText("");
        infoArea.setText("");
    }
}