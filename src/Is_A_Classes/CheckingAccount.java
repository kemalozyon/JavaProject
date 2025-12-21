package Is_A_Classes;

public class CheckingAccount extends BankAccount{
    private double overdraftLimit;
    
    public CheckingAccount(int accNum, double initialBalance, double limit) {
        super(accNum, initialBalance);
        this.overdraftLimit = limit;
        this.type = "Checking";
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    public void setOverdraftLimit(double limit) {
        this.overdraftLimit = limit;
    }
    
    @Override
    public String toString() {
        return AccType() + super.toString() + " | Overdraft Limit: $" + 
               String.format("%.2f", overdraftLimit);
    }

	@Override
	public String AccType() {
		// TODO Auto-generated method stub
		return "Checking\n";
	}
}
