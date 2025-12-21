package GUI;

import javax.swing.*;
import java.awt.*;
import Has_A_Classes.*;
import Is_A_Classes.*;

public class StatisticsGUI extends JFrame {
    private JLabel totalUsersLabel, totalAccountsLabel, totalBalanceLabel;
    private JTextArea reportArea;
    private JButton refreshBtn, viewReportBtn, clearBtn, backBtn;
    private MainMenuGUI mainMenu;
    
    public StatisticsGUI(MainMenuGUI mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("System Statistics");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel statsPanel = new JPanel(new GridLayout(5, 1));
        
        totalUsersLabel = new JLabel("Total Users: 0");
        totalAccountsLabel = new JLabel("Total Accounts: 0");
        totalBalanceLabel = new JLabel("Total Balance: $0.00");
        
        JPanel buttonPanel = new JPanel();
        refreshBtn = new JButton("Refresh");
        viewReportBtn = new JButton("View Report");
        clearBtn = new JButton("Clear");
        
        buttonPanel.add(refreshBtn);
        buttonPanel.add(viewReportBtn);
        buttonPanel.add(clearBtn);
        
        backBtn = new JButton("Back to Main Menu");
        backBtn.addActionListener(e -> {
            dispose();
            mainMenu.setVisible(true);
        });
        
        statsPanel.add(totalUsersLabel);
        statsPanel.add(totalAccountsLabel);
        statsPanel.add(totalBalanceLabel);
        statsPanel.add(buttonPanel);
        statsPanel.add(backBtn);
        
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        
        refreshBtn.addActionListener(e -> refreshStatistics());
        viewReportBtn.addActionListener(e -> viewReport());
        clearBtn.addActionListener(e -> reportArea.setText(""));
        
        add(statsPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        refreshStatistics();
        
        setVisible(true);
    }
    
    private void refreshStatistics() {
        int totalUsers = User.getTotalUsers();
        int totalAccounts = BankAccount.getTotalAccountsCreated();
        double totalBalance = BankSystemManager.calculateTotalSystemBalance();
        
        totalUsersLabel.setText("Total Users: " + totalUsers);
        totalAccountsLabel.setText("Total Accounts: " + totalAccounts);
        totalBalanceLabel.setText("Total Balance: $" + String.format("%.2f", totalBalance));
        
        JOptionPane.showMessageDialog(this, "Statistics refreshed!");
    }
    
    private void viewReport() {
        String report = BankSystemManager.displayAll();
        reportArea.setText(report);
    }
}