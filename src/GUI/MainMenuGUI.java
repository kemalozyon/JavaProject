package GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenuGUI extends JFrame {
    
    public MainMenuGUI() {
        setTitle("Bank Management System - Main Menu");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));
        
        JLabel titleLabel = new JLabel("BANK MANAGEMENT SYSTEM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JButton userManagementBtn = new JButton("User Management");
        JButton accountManagementBtn = new JButton("Account Management");
        JButton transactionBtn = new JButton("Transactions");
        JButton transferBtn = new JButton("Money Transfer");
        JButton statisticsBtn = new JButton("System Statistics");
        JButton exitBtn = new JButton("Exit");
        
        userManagementBtn.addActionListener(e -> {
            new UserManagementGUI(this);
            setVisible(false);
        });
        
        accountManagementBtn.addActionListener(e -> {
            new AccountManagementGUI(this);
            setVisible(false);
        });
        
        transactionBtn.addActionListener(e -> {
            new TransactionGUI(this);
            setVisible(false);
        });
        
        transferBtn.addActionListener(e -> {
            new TransferGUI(this);
            setVisible(false);
        });
        
        statisticsBtn.addActionListener(e -> {
            new StatisticsGUI(this);
            setVisible(false);
        });
        
        exitBtn.addActionListener(e -> System.exit(0));
        
        add(titleLabel);
        add(userManagementBtn);
        add(accountManagementBtn);
        add(transactionBtn);
        add(transferBtn);
        add(statisticsBtn);
        add(exitBtn);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuGUI());
    }
}