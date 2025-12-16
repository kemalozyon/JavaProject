package Is_A_Classes;

public class SavingsAccount extends BankAccount{
    private double interestRate;
    
    public SavingsAccount(int accNum, double initialBalance, double rate) {
        super(accNum, initialBalance);
        this.interestRate = rate;
        this.type = "Savings";
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public double calculateInterest() {
        return balance * (interestRate / 100);
    }
    
    public void applyInterest() {
        double interest = calculateInterest();
        deposit(interest);
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Interest Rate: " + 
               String.format("%.2f", interestRate) + "%";
    }
}
