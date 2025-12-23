package Has_A_Classes;

import java.util.ArrayList;
import java.util.List;

import Is_A_Classes.*;

import java.util.ArrayList;

public class User {
    // Static variable to track total users
    private static int totalUsers = 0;
    
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String password;
    private ArrayList<BankAccount> accounts;
    
    public User(int userId, String firstName, String lastName, 
                String email, int age, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.accounts = new ArrayList<>();
        totalUsers++;
    }
    
    // Static method
    public static int getTotalUsers() {
        return totalUsers;
    }
    
    
    public boolean addAccount(BankAccount account) {
        if (account != null && !accounts.contains(account)) {
            accounts.add(account);
            return true;
        }
        return false;
    }
    
    public boolean removeAccount(int accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber) {
                accounts.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public BankAccount getAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
    
    public double getTotalBalance() {
        double total = 0;
        for (BankAccount account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void setTotalUsers(int totalUsers) {
		User.totalUsers = totalUsers;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAccounts(ArrayList<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: ").append(userId)
          .append(" | Name: ").append(firstName).append(" ").append(lastName)
          .append(" | Email: ").append(email)
          .append(" | Age: ").append(age)
          .append(" | Total Balance: $").append(String.format("%.2f", getTotalBalance()))
          .append("\nAccounts (").append(accounts.size()).append("):\n");
        
        for (BankAccount account : accounts) {
            sb.append("  - ").append(account.toString()).append("\n");
        }
        
        return sb.toString();
    }

}