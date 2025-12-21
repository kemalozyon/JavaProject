package GUI;

import javax.swing.*;
import java.awt.*;
import Has_A_Classes.*;
import Is_A_Classes.*;

public class TransactionGUI extends JFrame {
    private JTextField userIdField, accountNumField, amountField;
    private JTextArea displayArea;
    private JButton depositBtn, withdrawBtn, viewBalanceBtn, clearBtn, backBtn, statisticsBtn;
    private MainMenuGUI mainMenu;
    
    public TransactionGUI(MainMenuGUI mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Transaction Management");
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
        userIdField.setBounds(130, 20, 200, 20);
        contentPane.add(userIdField);
        
        JLabel accountNumLabel = new JLabel("Account Number:");
        accountNumLabel.setBounds(20, 50, 100, 20);
        contentPane.add(accountNumLabel);
        
        accountNumField = new JTextField();
        accountNumField.setBounds(130, 50, 200, 20);
        contentPane.add(accountNumField);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 80, 100, 20);
        contentPane.add(amountLabel);
        
        amountField = new JTextField();
        amountField.setBounds(130, 80, 200, 20);
        contentPane.add(amountField);
        
        depositBtn = new JButton("DEPOSIT");
        depositBtn.setBounds(20, 120, 100, 30);
        depositBtn.addActionListener(e -> deposit());
        contentPane.add(depositBtn);
        
        withdrawBtn = new JButton("WITHDRAW");
        withdrawBtn.setBounds(130, 120, 100, 30);
        withdrawBtn.addActionListener(e -> withdraw());
        contentPane.add(withdrawBtn);
        
        viewBalanceBtn = new JButton("VIEW BALANCE");
        viewBalanceBtn.setBounds(240, 120, 120, 30);
        viewBalanceBtn.addActionListener(e -> viewBalance());
        contentPane.add(viewBalanceBtn);
        
        clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(20, 160, 100, 30);
        clearBtn.addActionListener(e -> clearFields());
        contentPane.add(clearBtn);
        
        statisticsBtn = new JButton("INFORMATION");
        statisticsBtn.setBounds(130, 160, 120, 30);
        statisticsBtn.addActionListener(e -> showStatistics());
        contentPane.add(statisticsBtn);
        
        backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(20, 200, 150, 30);
        backBtn.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });
        contentPane.add(backBtn);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(370, 20, 300, 380);
        contentPane.add(scrollPane);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        scrollPane.setViewportView(displayArea);
        
        setVisible(true);
    }
    
    private void deposit() {
        try {
            int userId = Integer.parseInt(userIdField.getText().trim());
            int accountNum = Integer.parseInt(accountNumField.getText().trim());
            double amount = Double.parseDouble(amountField.getText().trim());
            
            BankSystemManager.deposit(userId, accountNum, amount);
            displayArea.setText("Deposit successful!");
            viewBalance();
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid input! Check all fields");
        }
    }
    
    private void withdraw() {
        try {
            int userId = Integer.parseInt(userIdField.getText().trim());
            int accountNum = Integer.parseInt(accountNumField.getText().trim());
            double amount = Double.parseDouble(amountField.getText().trim());
            
            BankSystemManager.withdraw(userId, accountNum, amount);
            displayArea.setText("Withdrawal processed!");
            viewBalance();
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid input! Check all fields");
        }
    }
    
    private void viewBalance() {
        try {
            int userId = Integer.parseInt(userIdField.getText().trim());
            String result = BankSystemManager.display(userId);
            displayArea.setText(result);
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid User ID");
        }
    }
    
    private void showStatistics() {
        String report = BankSystemManager.displayAll();
        displayArea.setText(report);
        
    }
    
    private void clearFields() {
        userIdField.setText("");
        accountNumField.setText("");
        amountField.setText("");
        displayArea.setText("");
    }
}