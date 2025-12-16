package Interface;

public interface Withdrawable {
    /**
     * Attempts to withdraw the specified amount from the account.
     * @param amount The amount to withdraw.
     * @return true if the withdrawal was successful, false otherwise.
     */
    boolean withdraw(double amount);
}