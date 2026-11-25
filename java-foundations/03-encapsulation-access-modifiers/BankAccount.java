/**
 * Represents a bank account whose balance can only change through valid transactions.
 */
public class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        if (Double.isNaN(initialBalance)
                || Double.isInfinite(initialBalance)
                || initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance must be a finite, non-negative amount.");
        }

        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (!isPositiveFiniteAmount(amount)) {
            System.out.println("Deposit rejected: amount must be positive and finite.");
            return false;
        }

        double updatedBalance = balance + amount;
        if (Double.isInfinite(updatedBalance)) {
            System.out.println("Deposit rejected: the resulting balance is too large.");
            return false;
        }

        balance = updatedBalance;
        System.out.println("Deposit successful: " + amount);
        return true;
    }

    public boolean withdraw(double amount) {
        if (!isPositiveFiniteAmount(amount)) {
            System.out.println("Withdrawal rejected: amount must be positive and finite.");
            return false;
        }

        if (amount > balance) {
            System.out.println("Withdrawal rejected: insufficient balance.");
            return false;
        }

        balance -= amount;
        System.out.println("Withdrawal successful: " + amount);
        return true;
    }

    public void displayAccountDetails() {
        System.out.println("Account holder: " + accountHolder);
        System.out.println("Account number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println();
    }

    private boolean isPositiveFiniteAmount(double amount) {
        return amount > 0 && !Double.isInfinite(amount);
    }
}
