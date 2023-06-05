package BankingUI.Bankaccounts;

public class SavingsAccount extends BankAccounts{


    private final int withdrawallimit;

    {
        withdrawallimit = 20000;
    }

    public SavingsAccount(int initialBalance) {
        super(initialBalance);
    }
    public boolean withdraw(int amount) {
        if (amount > withdrawallimit) {
            return false;
        }
        return withdraw(amount);
    }
}

