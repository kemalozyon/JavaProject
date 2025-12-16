package Has_A_Classes;

import java.util.ArrayList; // For List implementation
import java.util.HashMap;   // To resolve HashMap
import java.util.List;      // To resolve List
import java.util.Map;       // To resolve Map
import java.util.stream.Collectors; // For stream operations
import java.util.stream.Stream;

import Interface.Withdrawable;
import Is_A_Classes.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BankSystemManager {
    private static ArrayList<User> users = new ArrayList<>();
    private static Set<String> registeredEmails = new HashSet<>();
    
    // Add method
    public static void addUser(int id, String firstName, String lastName, 
                       String email, int age, String password) {
    	if (findUser(id) == null) {
            if (!registeredEmails.contains(email)) {
                User user = new User(id, firstName, lastName, email, age, password);
                users.add(user);
                registeredEmails.add(email);
                System.out.println("User added successfully: " + firstName + " " + lastName);
            } else {
                System.out.println("Email already registered!");
            }
    	}
    }
    
    // Add account to user
    public static void addAccountToUser(int userId, String type, double initialBalance, 
                                double extraParam) {
        User user = findUser(userId);
        if (user != null) {
            int accNum = generateAccountNumber();
            BankAccount account;
            
            if (type.equalsIgnoreCase("checking")) {
                account = new CheckingAccount(accNum, initialBalance, extraParam);
            } else if (type.equalsIgnoreCase("savings")) {
                account = new SavingsAccount(accNum, initialBalance, extraParam);
            } else {
                System.out.println("Invalid account type!");
                return;
            }
            
            user.addAccount(account);
            System.out.println(type + " account added successfully!");
        } else {
            System.out.println("User not found!");
        }
    }
    
    // Search method
    public static User findUser(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
    
    // Delete method
    public static boolean deleteUser(int userId) {
        User user = findUser(userId);
        if (user != null) {
            users.remove(user);
            System.out.println("User deleted successfully!");
            return true;
        }
        System.out.println("User not found!");
        return false;
    }
    
    // Deposit method
    public static void deposit(int userId, int accountNum, double amount) {
        User user = findUser(userId);
        if (user != null) {
            BankAccount account = user.getAccount(accountNum);
            if (account != null) {
                account.deposit(amount);
                System.out.println("Deposit successful! New balance: $" + 
                                 String.format("%.2f", account.getBalance()));
            } else {
                System.out.println("Account not found!");
            }
        } else {
            System.out.println("User not found!");
        }
    }
    
    // Withdraw method
    public static void withdraw(int userId, int accountNum, double amount) {
        User user = findUser(userId);
        if (user != null) {
            BankAccount account = user.getAccount(accountNum);
            if (account != null && account instanceof Withdrawable) {
                boolean success = ((Withdrawable) account).withdraw(amount);
                if (success) {
                    System.out.println("Withdrawal successful! New balance: $" + 
                                     String.format("%.2f", account.getBalance()));
                } else {
                    System.out.println("Insufficient funds!");
                }
            } else {
                System.out.println("Account not found or doesn't support withdrawal!");
            }
        } else {
            System.out.println("User not found!");
        }
    }
    
    // Transfer method
    public static boolean transfer(int fromUserId, int fromAccNum, 
                          String toIBAN, double amount) {
        User fromUser = findUser(fromUserId);
        if (fromUser == null) {
            System.out.println("Source user not found!");
            return false;
        }
        
        BankAccount fromAccount = fromUser.getAccount(fromAccNum);
        if (fromAccount == null) {
            System.out.println("Source account not found!");
            return false;
        }
        
        BankAccount toAccount = findAccountByIBAN(toIBAN);
        if (toAccount == null) {
            System.out.println("Destination account not found!");
            return false;
        }
        
        if (fromAccount instanceof Withdrawable) {
            boolean withdrawn = ((Withdrawable) fromAccount).withdraw(amount);
            if (withdrawn) {
                toAccount.deposit(amount);
                System.out.println("Transfer successful!");
                return true;
            } else {
                System.out.println("Insufficient funds for transfer!");
            }
        }
        return false;
    }
    
    // Calculation method
    public static double calculateTotalSystemBalance() {
        double total = 0;
        for (User user : users) {
            total += user.getTotalBalance();
        }
        return total;
    }
    
    // Display all users
    public static String displayAll() {
        if (users.isEmpty()) {
            return "No users in the system.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("========== BANK SYSTEM REPORT ==========\n");
        sb.append("Total Users: ").append(users.size()).append("\n");
        sb.append("Total System Balance: $")
          .append(String.format("%.2f", calculateTotalSystemBalance())).append("\n");
        sb.append("========================================\n\n");
        
        for (User user : users) {
            sb.append(user.toString()).append("\n");
        }
        
        return sb.toString();
    }
    
    // Display specific user
    public static String display(int userId) {
        User user = findUser(userId);
        if (user != null) {
            return user.toString();
        }
        return "User not found!";
    }
    
    // Helper method
    private static BankAccount findAccountByIBAN(String iban) {
        for (User user : users) {
            for (BankAccount account : user.getAccounts()) {
                if (account.getIban().equals(iban)) {
                    return account;
                }
            }
        }
        return null;
    }
    
    // Helper method
    private static int generateAccountNumber() {
        return (int) (Math.random() * 900000) + 100000;
    }
}