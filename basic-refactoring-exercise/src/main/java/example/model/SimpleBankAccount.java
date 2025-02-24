package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    public static final int WITHDRAW_FEE = 1;
    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        checkUser(userID);
        this.balance += amount;
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        checkUser(userID);
        double amountWithFee = amount + WITHDRAW_FEE;
        isWithdrawAllowed(amountWithFee);
        this.balance -= amountWithFee;
    }

    private void isWithdrawAllowed(final double amount){
        if (this.balance < amount) {
            throw new IllegalArgumentException("Amount too high to withdraw (" + amount + ")");
        }
    }

    private void checkUser(final int id) {
        if (this.holder.getId() != id) {
            throw new IllegalArgumentException("Wrong user ID (" + id + ")");
        }
    }
}
