package GUI;

import javax.swing.*;
import java.awt.*;
import Has_A_Classes.*;
import Is_A_Classes.*;

public class TransferGUI extends JFrame {
    private JTextField fromUserIdField, fromAccountNumField, toIBANField, amountField;
    private JTextArea displayArea;
    private JButton transferBtn, clearBtn, backBtn, statisticsBtn;
    private MainMenuGUI mainMenu;
    
    public TransferGUI(MainMenuGUI mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Money Transfer");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel fromUserIdLabel = new JLabel("From User ID:");
        fromUserIdLabel.setBounds(20, 20, 100, 20);
        contentPane.add(fromUserIdLabel);
        
        fromUserIdField = new JTextField();
        fromUserIdField.setBounds(150, 20, 200, 20);
        contentPane.add(fromUserIdField);
        
        JLabel fromAccountNumLabel = new JLabel("From Account:");
        fromAccountNumLabel.setBounds(20, 50, 100, 20);
        contentPane.add(fromAccountNumLabel);
        
        fromAccountNumField = new JTextField();
        fromAccountNumField.setBounds(150, 50, 200, 20);
        contentPane.add(fromAccountNumField);
        
        JLabel toIBANLabel = new JLabel("To IBAN:");
        toIBANLabel.setBounds(20, 80, 100, 20);
        contentPane.add(toIBANLabel);
        
        toIBANField = new JTextField();
        toIBANField.setBounds(150, 80, 200, 20);
        contentPane.add(toIBANField);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 110, 100, 20);
        contentPane.add(amountLabel);
        
        amountField = new JTextField();
        amountField.setBounds(150, 110, 200, 20);
        contentPane.add(amountField);
        
        transferBtn = new JButton("TRANSFER");
        transferBtn.setBounds(20, 150, 120, 30);
        transferBtn.addActionListener(e -> transfer());
        contentPane.add(transferBtn);
        
        clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(150, 150, 100, 30);
        clearBtn.addActionListener(e -> clearFields());
        contentPane.add(clearBtn);
        
        statisticsBtn = new JButton("INFORMATION");
        statisticsBtn.setBounds(20, 190, 120, 30);
        statisticsBtn.addActionListener(e -> showStatistics());
        contentPane.add(statisticsBtn);
        
        backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(150, 190, 150, 30);
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
    
    private void transfer() {
        try {
            int fromUserId = Integer.parseInt(fromUserIdField.getText().trim());
            int fromAccNum = Integer.parseInt(fromAccountNumField.getText().trim());
            String toIBAN = toIBANField.getText().trim();
            double amount = Double.parseDouble(amountField.getText().trim());
            
            if (toIBAN.isEmpty()) {
                displayArea.setText("Please enter destination IBAN");
                return;
            }
            
            boolean success = BankSystemManager.transfer(fromUserId, fromAccNum, toIBAN, amount);
            
            if (success) {
                displayArea.setText("Transfer successful!\n\n");
                String result = BankSystemManager.display(fromUserId);
                displayArea.append(result);
                clearFields();
            } else {
                displayArea.setText("Transfer failed! Check your inputs and balance");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid input! Check all fields");
        }
    }
    
    private void showStatistics() {
    	String report = BankSystemManager.displayAll();
        displayArea.setText(report);
        
 
    }
    
    private void clearFields() {
        fromUserIdField.setText("");
        fromAccountNumField.setText("");
        toIBANField.setText("");
        amountField.setText("");
    }
}
