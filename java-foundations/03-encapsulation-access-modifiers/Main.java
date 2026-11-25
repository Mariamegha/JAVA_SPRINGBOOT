/**
 * Demonstrates encapsulation and validates BankAccount transactions.
 */
public class Main {
    public static void main(String[] args) {
        BankAccount firstAccount = new BankAccount("Asha", "BA101", 1000.0);
        BankAccount secondAccount = new BankAccount("Rahul", "BA102", 500.0);

        System.out.println("Initial account details:");
        firstAccount.displayAccountDetails();
        secondAccount.displayAccountDetails();

        System.out.println("Transaction tests:");
        runTests(firstAccount, secondAccount);

        System.out.println("Balance read through the getter: "
                + firstAccount.getBalance());
        System.out.println();

        System.out.println("Final account details:");
        firstAccount.displayAccountDetails();
        secondAccount.displayAccountDetails();
    }

    private static void runTests(BankAccount firstAccount, BankAccount secondAccount) {
        testSuccessfulDeposit(firstAccount);
        testSuccessfulWithdrawal(firstAccount);
        testNegativeDepositDoesNotChangeBalance(firstAccount);
        testInsufficientWithdrawalDoesNotChangeBalance(firstAccount);
        testZeroTransactionsAreRejected(firstAccount);
        testAccountsRemainIndependent(firstAccount, secondAccount);
        testNegativeInitialBalanceIsRejected();
        System.out.println();
    }

    private static void testSuccessfulDeposit(BankAccount account) {
        double startingBalance = account.getBalance();
        boolean accepted = account.deposit(200.0);
        printTestResult("Successful deposit updates the balance",
                accepted && account.getBalance() == startingBalance + 200.0);
    }

    private static void testSuccessfulWithdrawal(BankAccount account) {
        double startingBalance = account.getBalance();
        boolean accepted = account.withdraw(100.0);
        printTestResult("Successful withdrawal updates the balance",
                accepted && account.getBalance() == startingBalance - 100.0);
    }

    private static void testNegativeDepositDoesNotChangeBalance(BankAccount account) {
        double startingBalance = account.getBalance();
        boolean accepted = account.deposit(-25.0);
        printTestResult("Negative deposit is rejected without changing the balance",
                !accepted && account.getBalance() == startingBalance);
    }

    private static void testInsufficientWithdrawalDoesNotChangeBalance(
            BankAccount account) {
        double startingBalance = account.getBalance();
        boolean accepted = account.withdraw(startingBalance + 1.0);
        printTestResult("Insufficient withdrawal is rejected without changing the balance",
                !accepted && account.getBalance() == startingBalance);
    }

    private static void testZeroTransactionsAreRejected(BankAccount account) {
        double startingBalance = account.getBalance();
        boolean depositAccepted = account.deposit(0.0);
        boolean withdrawalAccepted = account.withdraw(0.0);
        printTestResult("Zero-value deposit and withdrawal are rejected",
                !depositAccepted && !withdrawalAccepted
                        && account.getBalance() == startingBalance);
    }

    private static void testAccountsRemainIndependent(
            BankAccount firstAccount, BankAccount secondAccount) {
        double firstStartingBalance = firstAccount.getBalance();
        double secondStartingBalance = secondAccount.getBalance();
        boolean accepted = secondAccount.deposit(50.0);
        printTestResult("A transaction on one account leaves the other unchanged",
                accepted
                        && secondAccount.getBalance() == secondStartingBalance + 50.0
                        && firstAccount.getBalance() == firstStartingBalance);
    }

    private static void testNegativeInitialBalanceIsRejected() {
        try {
            new BankAccount("Invalid", "BA000", -1.0);
            printTestResult("Negative initial balance is rejected", false);
        } catch (IllegalArgumentException exception) {
            printTestResult("Negative initial balance is rejected", true);
        }
    }

    private static void printTestResult(String scenario, boolean passed) {
        System.out.println((passed ? "PASS: " : "FAIL: ") + scenario);
    }
}
