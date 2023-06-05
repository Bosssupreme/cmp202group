package BankingUI.Bankaccounts;

public class BankAccounts {
    private int accountbalance;
    public BankAccounts(int initialBalance) {
        accountbalance = initialBalance;
    }
    public boolean withdraw(int amount) {
        if (amount > accountbalance) {
            return false;
        } else {
            accountbalance -= amount;
            return true;
        }

    }

}