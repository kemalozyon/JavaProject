package Is_A_Classes;

import Interface.Withdrawable;
import java.util.Date;

public abstract class BankAccount implements Withdrawable{
    // Static variable to track total accounts created
    private static int totalAccountsCreated = 0;
    
    protected int accountNumber;
    protected double balance;
    protected Date creationDate;
    protected String iban;
    protected String type;
    
    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.creationDate = new Date();
        this.iban = generateIBAN(accountNumber);
        totalAccountsCreated++;
    }
    
    // Static method
    public static int getTotalAccountsCreated() {
        return totalAccountsCreated;
    }
    
    // Static method to generate IBAN
    private static String generateIBAN(int accountNumber) {
        return "TR" + String.format("%020d", accountNumber);
    }
    
    // Concrete methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    public abstract String AccType();
    public double getBalance() {
        return balance;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public String getIban() {
        return iban;
    }
    
    public String getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "Account #" + accountNumber + " | Type: " + type + 
               " | Balance: $" + String.format("%.2f", balance) + 
               " | IBAN: " + iban;
    }
}